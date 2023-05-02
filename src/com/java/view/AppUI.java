package com.java.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.java.rent.repository.RentRepository;

public class AppUI {

	private static Scanner sc = new Scanner(System.in);

	public static void Startlogo() {
		System.out.println();
		System.out.println("===========================================================================================================");
		System.out.println("███████╗██████╗ ███████╗███████╗██████╗     ██████╗ ███████╗███╗   ██╗████████╗     ██████╗ █████╗ ██████╗");
		System.out.println("██╔════╝██╔══██╗██╔════╝██╔════╝██╔══██╗    ██╔══██╗██╔════╝████╗  ██║╚══██╔══╝    ██╔════╝██╔══██╗██╔══██╗");
		System.out.println("███████╗██████╔╝█████╗  █████╗  ██║  ██║    ██████╔╝█████╗  ██╔██╗ ██║   ██║       ██║     ███████║██████╔╝");
		System.out.println("╚════██║██╔═══╝ ██╔══╝  ██╔══╝  ██║  ██║    ██╔══██╗██╔══╝  ██║╚██╗██║   ██║       ██║     ██╔══██║██╔══██╗");
		System.out.println("███████║██║     ███████╗███████╗██████╔╝    ██║  ██║███████╗██║ ╚████║   ██║       ╚██████╗██║  ██║██║  ██║");
		System.out.println("╚══════╝╚═╝     ╚══════╝╚══════╝╚═════╝     ╚═╝  ╚═╝╚══════╝╚═╝  ╚═══╝   ╚═╝        ╚═════╝╚═╝  ╚═╝╚═╝  ╚═╝");
		System.out.println("===========================================================================================================");
		System.out.println();
		System.out.println("Speed Rent Car : Release 23.0.0.0");
		System.out.println("Version 23.3.0.0");
		System.out.println("Copyright (c) 2023, ICT team, All rights reserved.");
		System.out.println();
		System.out.println("-----------------------------------------------------------------------------------------------------------");


	}
	public static void StartNotice() {
		
		String sql = "SELECT * FROM cars WHERE car_status='AVAILABLE' ";
		int count = RentRepository.avCheck2(sql);
		String sql2 = "SELECT * FROM rent_history WHERE (TO_DATE(user_exp_date, 'YYYY-MM-DD') - TO_DATE(sysdate, 'YYYY-MM-DD')) <8 and car_rent = 'false'";
		int signal = 3;		
		String date1 = LocalDate.now().format(DateTimeFormatter.ofPattern("yy/MM/dd"));
		System.out.println();
		System.out.println("====================== "+date1+"기준 NOTICE ======================\n");
		System.out.println("현재 대여가능 차량 총 ["+count+"] 대\n");
		System.out.println("반납예정 차량(잔여 대여기간 8일미만) > ");
		RentRepository.searchRentList(sql2,signal);
		System.out.println("\n===============================================================");
		System.out.println();



	}

	public static String inputString() { //입력값(공백 및 문자) 받는 메서드
		return sc.nextLine();
	}	

	public static int inputInteger() { //입력값(메뉴선택) 받는 메서드
		int num = 0;
		try {
			num = sc.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("정수로 입력해 주세요.");
		} finally {
			sc.nextLine();
		}		
		return num;
	}

	// 메인(시작) 화면 출력
	public static void startScreen() {

		System.out.println("\n========== Rent Car 관리 시스템 ==========");
		System.out.println("### 1. 회원 관리 시스템");
		System.out.println("### 2. 차량 관리 시스템");
		System.out.println("### 3. 대여 / 반납 시스템");
		System.out.println("### 4. 프로그램 종료");
		System.out.println("--------------------------------------");
		System.out.print(">>> ");

	}

	// 회원 관리 시스템 화면 출력
	public static void userManagementScreen() {
		System.out.println("\n========== 회원 관리 시스템 ==========");
		System.out.println("### 1. 신규 회원 추가");
		System.out.println("### 2. 회원 검색");
		System.out.println("### 3. 회원 탈퇴");
		System.out.println("### 4. 첫 화면으로 가기");
		System.out.println("---------------------------------");
		System.out.print(">>> ");
	}

	// 차량 관리 시스템 화면 출력
	public static void carManagementScreen() {
		System.out.println("\n========= 차량 관리 시스템 =========");
		System.out.println("### 1. 신규 차량 등록");
		System.out.println("### 2. 차량 검색");
		System.out.println("### 3. 차량 정보 수정");
		System.out.println("### 4. 차량 정보 삭제");
		System.out.println("### 5. 첫 화면으로 가기");
		System.out.println("----------------------------------------");
		System.out.print(">>> ");
	}

	// 차량 정보 수정 화면
	public static void carModifyScreen() {
		System.out.println("\n========= 차량 정보 수정=========");
		System.out.println("### 1. 차량 ID 수정");
		System.out.println("### 2. 차량 모델명 수정");
		System.out.println("### 3. 차량 구분 수정");
		System.out.println("### 4. 차량 요금 수정");
		System.out.println("### 5. 차량 등급 수정");
		System.out.println("### 6. 차량 정보 수정 취소");
		System.out.println("----------------------------------------");
		System.out.print(">>> ");
	}

	// 렌트 관리 시스템 화면 출력
	public static void rentManagementScreen() {
		System.out.println("\n========= 대여/반납 관리 시스템 =========");
		System.out.println("### 1. 대여 조회");
		System.out.println("### 2. 신규 대여");
		System.out.println("### 3. 대여 연장");
		System.out.println("### 4. 차량 반납");
		System.out.println("### 5. 대여별 매출 조회");	
		System.out.println("### 6. 첫 화면으로 가기");
		System.out.println("----------------------------------------");
		System.out.print(">>> ");
	}

}
