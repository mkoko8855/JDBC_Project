package com.java.user.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.common.DataBaseConnection;
import com.java.user.domain.Grade;
import com.java.user.domain.User;

public class UserRepository {

	private DataBaseConnection connection = DataBaseConnection.getInstance(); /*받자*/

	/*변수 3개준비*/
//	Connection conn = null; 
//	PreparedStatement pstmt = null; 
//	ResultSet rs = null;
	
	
	public void addUser(User user) {
		
		/*user클래스에서 오버라이딩한 다음 주소값이 아닌 실값이 뭔지 확인하자*/
		System.out.println("repository: " + user); 
		String sql = "INSERT INTO users (user_number, user_name, phone_number) VALUES(users_seq.NEXTVAL, ?, ?)";
		
		
		
		
		
		/*받았으니 커넥션 타입의 변수를 준비*/
		try(Connection conn = connection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
//			conn = connection.getConnection();  트라이한테 전달했으니 주석처리
//			pstmt = conn.prepareStatement(sql); 트라이한테 전달했으니 주석처리
			
			
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getPhoneNumber());
			
			if(pstmt.executeUpdate() == 1) { /*확인과정임. 나중에는 무조건 되야하니 안씀.*/
				System.out.println("회원 가입이 정상 처리되었습니다.");
			} else {
				System.out.println("회원 가입에 실패 했습니다. 관리자에게 문의하세요.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} /*finally를 써야하지만, 생략할 수 있는 문법을 쓰자*/
		  /*
		  finally 대신에 try with Resources 라는 문법이 있다.
		  자바 1.8 이후에 사용할 수 있는 문법이다.
		  try문에 자원 객체를 전달하면, try가 끝날 경우
		  자동으로 자원을 종료해주는 기능이다. (즉, finally에서 따로 close()안해도 된다.)
		  아무 객체나 close를 해주는 것은 아니다.
		  자원을 종료하려는 객체가 AutoCloseable 이라는 인터페이스의 구현체여야만 한다.
		  
		  try with Resuorces는 맨 위에 null 로 선언한 저 3개 변수가 필요가 없다. 주석처리하자
		  try문에 괄호를 열고 저거 써주고 그아래
		  conn = ~~와 pstmt = ~~는 트라이한테 전달했으니 주석처리 하면 된다.
		  */
		
		
		/*그럼이제 회원관리시스템 > 신규회원추가 기능은 완료한것이다. 1번의 1번 기능은 완료!*/
		
	}

	
	
	
	
		/* 이제는 회원검색이다. 입력을 받아서 검색을 할 예정이다. */
		/* 동명이인의 가능성도 있기 때문에 List쓰자*/
		/* 즉, 회원의 이름으로 정보 검색을 하는 곳*/
		public List<User> findByUserName(String userName){ /*메서드이름은 finByUserName으로지음*/
		/*그러면 서비스쪽에서 검색하고자 하는 이름을 받아야겠지? 매개값으로 String userName적자*/
			List<User> userList = new ArrayList<>(); 	//return용 List를 만듦
			
			/*sql부터써주자*/
			String sql = "SELECT * FROM users WHERE user_name = ?";
			
			try(Connection conn = connection.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql)){
					pstmt.setString(1, userName);
					ResultSet rs = pstmt.executeQuery();
					
					/*동명이인의 가능성도 있으니*/
					while(rs.next()) {
						/*조회결과있으면 트루 없으면 폴스*/
						//한행씩 타겟이 잡힌다. 행이 여러개니까 유저로 포장을 해서 처리를 해줘야지.
						//그리고 여러명일 수 있기 떄문에 리턴을 List로 잡았다.
						//이제 포장한 객체를 List에 하나씩 담아주면 된다.
						//위로가서 return용 List를 만들자
						
						
						//객체 하나 만들어주자 세터메서드 이용해서 채워줘야하는데 생성자의 매개값으로 바로 전달해버리자
						Grade grade = Grade.valueOf(rs.getString("grade")); //아래에서 String grade 대신에 쓴 Grade valueof안먹어서 여기다가 그냥 객체를만들어주고 아래에는 grade만 써줬다.
						User user = new User(
								rs.getInt("user_number"),
								rs.getString("user_name"),
								rs.getString("phone_number"),
								rs.getInt("total_paying"),
							    grade /*그레이드는 enum이라는 그레이드타입이기 떄문에 스트링을 못받는다.*/
								); //이제 객체 포장 완료. 
						
						/*위에서 미리 만들어놓은 List에 만든 객체들을 추가해준다*/
						userList.add(user);
						/*더 이상 조회할 데이터가 없을 떄 반복문은 끝나게 된다. 그리고나서 UserList를 리턴해주면 된다.*/
					}
			}catch (Exception e) {
					e.printStackTrace();
				}
			return userList; /*다끝나고 UserList리턴~  그럼 이제 조회기능을 확인해보자~!*/
			}





		public void deleteUser(int delUserNum) {
			String sql = "DELETE FROM users WHERE user_number=?";
			try(Connection conn = connection.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql)){
				
				/*물음표하나준거 채우자*/
				pstmt.setInt(1, delUserNum);
				/*이제 실행을 시켜서 삭제가 됐다 안됐다 라는 것을 알아야겠지 근데 사용자가 delUserNum을 보낼 때 조회된 결과의 회원번호가 4번인데 56번을 쳐버리면?*/
				if(pstmt.executeUpdate() == 1) {
					System.out.println("\n### 회원정보가 정상 삭제되었습니다.");
				} else {
					/*delete메서드를 불렀을 때 같이 삭제됐던 delUserNum이 없었다는 것이니*/
					System.out.println("\n### 검색한 회원의 회원번호로만 삭제가 가능합니다.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
}

