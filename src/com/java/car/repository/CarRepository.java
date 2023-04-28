package com.java.car.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.java.car.domain.Car;
import com.java.car.domain.CarGrade;
import com.java.car.domain.CarSize;
import com.java.common.DataBaseConnection;
import com.java.common.SearchCondition;

public class CarRepository {
	
	private DataBaseConnection connection = DataBaseConnection.getInstance();

	// 차량 정보 DB 추가 메서드
	public void addCar(Car Car) {
		String sql = "INSERT INTO cars "
				+ "(car_num, car_id, car_model, car_size, car_fee, car_grade) "
				+ "VALUES(cars_seq.NEXTVAL, ?, ?, ?, ?, ?)";
		System.out.println("sql문 생성");
		
		try(Connection conn = connection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, Car.getCarId());
			System.out.println("sql문에 ID 추가");
			pstmt.setString(2, Car.getCarModel());
			System.out.println("sql문에 MODEL 추가");
			pstmt.setString(3, Car.getCarSize().toString());
			System.out.println("sql문에 SIZE 추가");
			pstmt.setInt(4, Car.getCarFee());
			System.out.println("sql문에 FEE 추가");
			pstmt.setString(5, Car.getCarGrade().toString());
			System.out.println("sql문에 GRADE 추가");
			
			System.out.println(pstmt);
			
			if(pstmt.executeUpdate() == 1) {
				System.out.println("차량 등록이 정상 처리되었습니다.");
			} else {
				System.out.println("차량 등록에 실패했습니다. 관리자에게 문의하세요.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	// 차량 정보 DB 검색 메서드
	public List<Car> searchCarList(SearchCondition condition, String keyword) {
		
		String sql = "";
		List<Car> carList = new ArrayList<>();
		
		switch (condition) {
		case CAR_MODEL: case CAR_ID: case CAR_STATUS:
			sql = "SELECT * FROM cars WHERE " + condition.toString() + " LIKE " + keyword;
			break;
		case ALL:
			sql = "SELECT * FROM cars";
		}
		System.out.println("sql : " + sql);
		
		try(Connection conn = connection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			while(rs.next()) {
				CarSize carSize = CarSize.valueOf(rs.getString("car_size"));
				CarGrade carGrade = CarGrade.valueOf(rs.getString("car_grade"));
				Car car = new Car(
						rs.getInt("car_num"),
						rs.getString("car_id"),
						rs.getString("car_model"),
						carSize,
						rs.getInt("car_fee"),
						carGrade,
						rs.getString("car_status"),
						rs.getInt("user_num")
						);
				System.out.println("검색으로 받아온 car 객체 : " + car);
				carList.add(car);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("검색 후 carList : " + carList);
		return carList;
	}

}
