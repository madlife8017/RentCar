package com.java.main;

import com.java.car.service.CarService;
import com.java.common.AppService;
import com.java.rent.service.RentService;

public class AppContorller {

	private AppService service;

	//시스템을 정해주는 기능
	public void chooseSystem(int selectNumber) {
		switch (selectNumber) {
		case 1 :
			break;
		case 2 :
			service = new CarService();
			break;
		case 3 :
			service = new RentService();
			break;
		case 4:
			System.out.println("# 프로그램을 종료합니다");
			System.exit(0);
		default:
			System.out.println("# 메뉴를 다시 입력하세요.");	
<<<<<<< HEAD
		}		
=======
		}
		
>>>>>>> 2443e07db919e71804e65132a1a372604079abd8
		service.start();
		
	}
	
}
