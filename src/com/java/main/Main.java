package com.java.main;

import static com.java.view.AppUI.inputInteger;

import com.java.view.AppUI;

public class Main {

	public static void main(String[] args) {
		
		AppContorller controller = new AppContorller();
		
		while(true){			
			AppUI.startScreen();
			int selectNumber = inputInteger();
			controller.chooseSystem(selectNumber);
		}
	}
}
