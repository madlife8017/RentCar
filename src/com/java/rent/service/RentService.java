package com.java.rent.service;

import static com.java.view.AppUI.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import com.java.rent.domain.Rent;
import com.java.rent.repository.RentRepository;
import com.java.car.domain.Car;
import com.java.common.AppService;

public class RentService implements AppService {

	private final RentRepository rentRepository = new RentRepository();

	@Override
	public void start() {
		while(true) {
			rentManagementScreen();
			int selection = inputInteger();

			switch (selection) {
			case 1:
				// 대여 조회
				searchRentData();

				break;
			case 2:
				// 신규 대여
				newRent();

				break;
			case 3:
				// 차량 정보 수정 메서드

				break;
			case 4:
				// 차량 정보 삭제 메서드

				break;
			case 5:
				// 메인 화면으로 돌아가기
				System.out.println("\n첫 화면으로 돌아갑니다.\n");
				return;

			default:
				System.out.println("메뉴를 잘못 입력하셨습니다. 다시 입력해주세요.");
			}
			System.out.println("\n====== 계속 진행하시려면 ENTER를 누르세요 ======");
			inputString();

		}		
	}

	private void searchRentData() {
		System.out.println("\n=============== 차량 검색 조건을 선택하세요. ===============");
		System.out.println("[ 1. 전체 대여 상황 조회 | 2. 대여 가능 차량 검색 | 3. 반납 예정 차량 검색 ]");
		System.out.print(">>> ");
		int selection = inputInteger();
		int signal =0;
		String sql;
		switch (selection) {
		case 1:
			System.out.println("\n### 전제 대여 상황을 검색합니다.");
			sql = "SELECT * FROM rent_history";
			signal = 1;
			rentRepository.searchRentList(sql,signal);
			break;
		case 2:
			System.out.println("\n### 대여 가능 차량을 검색합니다.");
			sql = "SELECT * FROM cars WHERE car_status='available'";
			rentRepository.avCheck(sql);
			break;			
		case 3:
			System.out.println("\n### 반납 예정 차량을 검색합니다.");
			System.out.println("(반납 예정일이 8일 미만인 차량을 조회합니다.)\n");
			sql = "SELECT * FROM rent_history WHERE (TO_DATE(user_exp_date, 'YYYY-MM-DD') - TO_DATE(sysdate, 'YYYY-MM-DD')) <8 and car_rent = 'false'";
			signal = 2;
			rentRepository.searchRentList(sql,signal);
			break;

		default:
			System.out.println("\n### 잘못 입력했습니다.");
		}		
	}

	private void newRent()  {

		System.out.println("대여하실 차량 번호를 입력하세요");
		System.out.print(">>> ");
		int carNum = inputInteger();
		if(rentRepository.rentCheck(carNum).equals("false")){
			System.out.println("대여 혹은 점검중인 차량입니다.");
		} else if (rentRepository.rentCheck(carNum).equals("TRUE")) {

			System.out.println("회원번호를 입력하세요");
			System.out.print(">>> ");
			int userNum = inputInteger();

			System.out.println("반납예정일을 입력하세요 (YY/MM/DD)");
			System.out.print(">>> ");
			String expDate = inputString();

			String date1 = LocalDate.now().format(DateTimeFormatter.ofPattern("yy/MM/dd"));
			
			Date format1 = null;
			Date format2 = null ;
			try {
				format1 = new SimpleDateFormat("yy/MM/dd").parse(date1);
				format2 = new SimpleDateFormat("yy/MM/dd").parse(expDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.out.println("잘못입력하셨습니다.");			
				e.printStackTrace();return;
			}
			long diffSec = (format2.getTime() - format1.getTime()) / 1000;
			long diffDays = diffSec/ (24*60*60);

			System.out.println("\n========= 대여 확인 =========");
			System.out.println("### 회원 번호 : " + userNum +" 번");
			System.out.println("### 대여 차량 : " + carNum +" 번");
			System.out.println("### 반납 예정일 : " + expDate );
			System.out.println("### 대여 기간 : " +diffDays +" 일");	       
			System.out.println("### 예상 비용: " + rentRepository.getFee(carNum)*diffDays+" 원");
			System.out.println("==========================");
			System.out.println("\n대여를 진행하시겠습니까? (Y/N)");
			System.out.print(">>> ");
			String confrim = inputString();
			if(confrim.equals("Y")||confrim.equals("y")) {
				rentRepository.rentprocess(carNum,userNum,expDate);
			}else if(confrim.equals("N")||confrim.equals("n")){
				System.out.println("대여가 취소되었습니다.");
				return;        	
			} 

		}
	}
}
