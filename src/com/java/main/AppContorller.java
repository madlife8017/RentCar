package com.java.main;

import com.java.car.service.CarService;
import com.java.common.AppService;
<<<<<<< HEAD
import com.java.rent.service.RentService;
=======
import com.java.user.service.UserService;
>>>>>>> 6a3c204e388bb2ed8a42ae1caff4a461f578d4a0

public class AppContorller {

	private AppService service;

	//시스템을 정해주는 기능
	public void chooseSystem(int selectNumber) {
		switch (selectNumber) {
		case 1 :
<<<<<<< HEAD
			break;
=======
			service = new UserService();
			break;			
>>>>>>> 6a3c204e388bb2ed8a42ae1caff4a461f578d4a0
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
		service.start();
		
=======
		}
		service.start();
>>>>>>> 6a3c204e388bb2ed8a42ae1caff4a461f578d4a0
	}
}
