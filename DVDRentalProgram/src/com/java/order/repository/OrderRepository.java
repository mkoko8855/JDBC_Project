package com.java.order.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.java.common.DataBaseConnection;

public class OrderRepository {

	
	/*데이터베이스 커넥션부터 만들어야겠지*/
	private DataBaseConnection connection = DataBaseConnection.getInstance(); //를 통해 객체를 받아오자.

	
	
	
	public void addOrder(int movieNumber, int userNumber) {
		String sql = "INSERT INTO order_history (order_no, user_number, serial_number) VALUES(order_seq.NEXTVAL, ?, ?)";		
		
		/*완성했으니 연동로직 ㄱㄱ*/
		try(Connection conn = connection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
				pstmt.setInt(1, userNumber);
				pstmt.setInt(2,  movieNumber);
				if(pstmt.executeUpdate() == 1) {
					System.out.println("주문이 성공적으로 이루어졌습니다. ");
				} else {
					System.out.println("주문에 실패 했습니다. ");
					
					
					
					/*트리거 붙이고 나서 실행해야한다. sql디벨로퍼 ㄱㄱ*/
				}
				
	} catch (Exception e) {
		e.printStackTrace();
	}
		
	}
	
	
}