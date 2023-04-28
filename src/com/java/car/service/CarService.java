package com.java.car.service;

import static com.java.view.AppUI.carManagementScreen;
import static com.java.view.AppUI.inputInteger;
import static com.java.view.AppUI.inputString;
import static com.java.common.SearchCondition.*;

import java.util.List;

import com.java.car.domain.Car;
import com.java.car.domain.CarGrade;
import com.java.car.domain.CarSize;
import com.java.car.repository.CarRepository;
import com.java.common.AppService;
import com.java.common.SearchCondition;


public class CarService implements AppService {
	
	private final CarRepository carRepository = new CarRepository();

	@Override
	public void start() {
		while(true) {
			carManagementScreen();
			int selection = inputInteger();
			
			switch (selection) {
			case 1:
				// 신규 차량 등록 메서드
				insertNewCar();
				break;
			case 2:
				// 차량 검색 메서드
				showSearchResult();
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
	
	// 차령 검색 정보 출력 메서드
	private void showSearchResult() {
		List<Car> cars = searchCarData();
		
		if(cars.size() > 0) {
			for(Car car : cars) {
				System.out.println(car);
			}
			System.out.printf("\n============================ 검색 결과 (총 %d건) ============================\n", cars.size());
		} else {
			System.out.println("\n### 검색 결과가 없습니다.");
		}
		
	}

	// 차량 검색 메서드
	private List<Car> searchCarData() {
		System.out.println("\n=============== 차량 검색 조건을 선택하세요. ===============");
		System.out.println("[ 1. 차종 검색 | 2. 차량 ID 검색 | 3. 차량 상태 검색 | 4. 전체검색 ]");
		System.out.print(">>> ");
		int selection = inputInteger();
		
		SearchCondition condition = ALL;
		
		switch (selection) {
		case 1:
			System.out.println("\n### 차종으로 검색합니다.");
			condition = CAR_MODEL;
			break;
		case 2:
			System.out.println("\n### 차량 ID로 검색합니다.");
			condition = CAR_ID;
			break;
		case 3:
			System.out.println("\n### 차량 상태로 검색합니다.");
			condition = CAR_STATUS;
			break;
		case 4:
			System.out.println("\n### 전체 정보를 검색합니다.");
			break;

		default:
			System.out.println("\n### 잘못 입력했습니다.");
		}
		
		String keyword = "";
		if(condition != ALL) {
			System.out.print("# 검색어 : ");
			keyword = "'%" + inputString().toUpperCase() + "%'";
		}
		
		return carRepository.searchCarList(condition, keyword);
		
	}

	// 신규 차량 등록 메서드
	private void insertNewCar() {
		
		System.out.println("\n======= 신규 차량을 등록합니다. =======");
		System.out.print("# 차량 ID : ");
		String carId = inputString().toUpperCase();
		
		System.out.print("# 차량 모델명 : ");
		String carModel = inputString().toUpperCase();
		
		System.out.println("# 차량 사이즈 [ Compact, Small, Midsize, Big, SUV, Van, Foreign ]");
		System.out.print(" : ");
		String ipCarSize = inputString().toUpperCase();
		CarSize carSize = null;
		
		try {
			carSize = CarSize.valueOf(ipCarSize);
		} catch (Exception e) {
			System.out.println("규격 외의 Size입니다. 등록을 다시 진행해주세요.");
			return;
		}
		
		
		System.out.print("# 차량 대여 비용 : ");
		int carFee = inputInteger();
		
		System.out.print("# 차량 등급 [ S, A, B, C ] : ");
		String ipCarGrade = inputString().toUpperCase();
		CarGrade carGrade = null;
		
		try {
			carGrade = CarGrade.valueOf(ipCarGrade);
		} catch (Exception e) {
			System.out.println("규격 외의 등급입니다. 등록을 다시 진행해주세요.");
			return;
		}
		
		Car newCar = new Car();
		newCar.setCarId(carId);
		newCar.setCarModel(carModel);
		newCar.setCarSize(carSize);
		newCar.setCarFee(carFee);
		newCar.setCarGrade(carGrade);
		System.out.println("Car 객체 생성 후 값 추가 완료");
		System.out.println(newCar);
		
		
		
		carRepository.addCar(newCar);
		System.out.println("addCar 메서드 실행 완료");
		
	}

}
