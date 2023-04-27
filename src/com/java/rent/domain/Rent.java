package com.java.rent.domain;

import java.time.LocalDateTime;

import com.java.user.domain.UserGrade;

/*
CREATE TABLE rent_history(
    rent_num NUMBER(7) CONSTRAINT rent_pk PRIMARY KEY,
    user_num NUMBER(5) CONSTRAINT rent_user_fk REFERENCES rent_users(user_num),
    user_rent_date DATE DEFAULT sysdate,
    user_return_date DATE,
    car_rent VARCHAR2(5) DEFAULT 'false'
);

CREATE SEQUENCE rent_history_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 10000000
    NOCYCLE
    NOCACHE;
 */

public class Rent {

	private int rentNum;
	private int userNum;
	private LocalDateTime userRentDate;
	private LocalDateTime userReturnDate;
	private Boolean carRent;

	public Rent() {}

	public Rent(int rentNum, int userNum, LocalDateTime userRentDate, LocalDateTime userReturnDate, Boolean carRent) {
		super();
		this.rentNum = rentNum;
		this.userNum = userNum;
		this.userRentDate = userRentDate;
		this.userReturnDate = userReturnDate;
		this.carRent = carRent;
	}

	public int getRentNum() {
		return rentNum;
	}

	public void setRentNum(int rentNum) {
		this.rentNum = rentNum;
	}

	public int getUserNum() {
		return userNum;
	}

	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}

	public LocalDateTime getUserRentDate() {
		return userRentDate;
	}

	public void setUserRentDate(LocalDateTime userRentDate) {
		this.userRentDate = userRentDate;
	}

	public LocalDateTime getUserReturnDate() {
		return userReturnDate;
	}

	public void setUserReturnDate(LocalDateTime userReturnDate) {
		this.userReturnDate = userReturnDate;
	}

	public Boolean getCarRent() {
		return carRent;
	}

	public void setCarRent(Boolean carRent) {
		this.carRent = carRent;
	}

	@Override
	public String toString() {
		String carRent = this.carRent ? "렌트가능" : "렌트중";
		return
				"### 렌트번호 : " + rentNum +
				", 렌트한 회원 번호 : " + userNum +
				", 렌트일자: " + userRentDate +
				", 반납일자: " + userReturnDate +
				", 렌트상태: " + carRent;
	}



}
