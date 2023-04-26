package com.java.movie.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.java.common.Condition;
import com.java.common.DataBaseConnection;
import com.java.movie.domain.Movie;

public class MovieRepository {

	
	private DataBaseConnection connection = DataBaseConnection.getInstance(); /* 요거 있어야 커넥션 객체를 받을 수 있다 */
	
	
	/*이제 영화를 추가하는 메서드를 작성하자*/
	public void addMovie(Movie movie) { /*무비타입의 객체를 보내세요 라는 뜻으로 매개변수에 무비를 넣어줌*/
		
		String sql = "INSERT INTO movie (serial_number, movie_name, nation, pub_year) VALUES(movie_seq.NEXTVAL, ?, ?, ?)";
		
		
		/*이제 연동로직ㄱㄱ*/
		try(Connection conn = connection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			/*물음표채워줘야지*/
			pstmt.setString(1, movie.getMovieName());
			pstmt.setString(2,  movie.getNation());
			pstmt.setInt(3,  movie.getPubYear());
			
			if(pstmt.executeUpdate() == 1) {
				System.out.printf("\n### [%s] 영화가 신규 등록 되었습니다.\n", movie.getMovieName());
			} else {
				System.out.println("신규 영화 등록 실패!");
			}
			
		} catch (Exception e) {
		e.printStackTrace();
		}
	}


	public List<Movie> searchMovieList(Condition condition, String keyword) {
		
		String sql = ""; /*빈문자열 : 비어있는 상태*/
		
		List<Movie> movieList = new ArrayList<>();
		
		
		/*컨디션의 값이 무엇인지 체크*/
		switch (condition) {
		case MOVIE_NAME:
		case NATION:
		case PUB_YEAR:
			sql = "SELECT * FROM movie WHERE " + condition.toString() + " LIKE " + keyword;
			break;
		case ALL:
			sql = "SELECT * FROM movie";
		}
			System.out.println("sql: " + sql);
			
			try(Connection conn = connection.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql)){
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					Movie movie = new Movie(
							rs.getInt("serial_number"),
							rs.getString("movie_name"),
							rs.getString("nation"),
							rs.getInt("pub_year"),
							Boolean.parseBoolean(rs.getString("rental")), /* 불린 타입으로 써줘야 컬럼에 있는 스트링 값을 불린으로 변환해서 넣어주겟다. 그래야 객체 초기화가 가능하다*/
							rs.getInt("charge")
							);
					/*객체가 생성이 되었으니 movie에 add하겠다~*/
							movieList.add(movie);
				}
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		/*리스트로 리턴해라 라고 얘기하니까 리스트 하나 선언해주자 위로 ㄱㄱ*/
		/*		List<Movie> movieList = new ArrayList<>(); 이거 넣어주자*/
		return movieList;
	}


	
	
	public List<Movie> searchByRental(String rental) { /*그냥 rantal이라고지어주자*/
		List<Movie> movieList = new ArrayList<>();
		String sql = "";
		/*sql ㄱㄱ*/
		/*String sql = "SELECT * FROM movie WHERE rental = true"; >  나중에 반납 진행할땐 대여중인 목록을 또 처리해야 하니 이걸 if문으로 처리할 수 있겠다. 나중에 sql을 다르게 쓸 일이 있다. 반납같은거까지 처리하려고 하면 처리도해야한다. if절을 만들자*/
		if(rental.equals("true")) {
			 sql = "SELECT * FROM movie WHERE rental = 'true'";
		}else {
			sql = "SELECT * FROM movie WHERE rental = 'false'";
		}
		
		try(Connection conn = connection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
					while(rs.next()) {
						Movie movie = new Movie(
								rs.getInt("serial_number"),
								rs.getString("movie_name"),
								rs.getString("nation"),
								rs.getInt("pub_year"),
								Boolean.parseBoolean(rs.getString("rental")), /* 불린 타입으로 써줘야 컬럼에 있는 스트링 값을 불린으로 변환해서 넣어주겟다. 그래야 객체 초기화가 가능하다*/
								rs.getInt("charge")
								);
						movieList.add(movie);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
		return movieList;
	}
}

