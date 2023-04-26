package com.java.movie.service;

import com.java.common.AppService;
import com.java.common.Condition;
import com.java.movie.domain.Movie;
import com.java.movie.repository.MovieRepository;

import static com.java.view.AppUI.*;
import static com.java.common.Condition.*;


import java.time.LocalDateTime;
import java.util.List;

public class MovieService implements AppService {

	private final MovieRepository movieRepository = new MovieRepository(); /*이 객체 이용해서 메서드를 호출하자. 아래로가자*/
	
	/*오버라이드해야 빨간줄없어지니 그냥 컨트롤스페이스엔터~!*/
	@Override
	public void start() { /*앱컨트롤러로가서 영화 관련된 메뉴를 사용자가 선택하면 무비서비스의 start가 발동되도록 처리하자*/
		
		while(true) {
			movieManagementScreen();
			/*입력도받자*/
			int selection = inputInteger();
			
			switch (selection) {
			case 1:
				insertMovieData();
				break;
			case 2:
				showSearchResult();
				break;
			case 3:
				return;

			default:
				System.out.println("메뉴를 다시 입력하세요.");
			}
			System.out.println("\n====== 계속 진행하시려면 ENTER를 누르세요 ======");
			inputString();
		}
	}
	
	//영화 정보 추가 비즈니스 로직
	private void insertMovieData() {
		System.out.println("\n======= 영화 DVD 정보를 추가합니다. =======");
		System.out.print("# 영화명: ");
		String movieName = inputString();
		
		System.out.print("# 국가명: ");
		String nation = inputString();
		
		System.out.print("# 발매연도: ");
		int pubYear = inputInteger();
		
		Movie newMovie = new Movie();
		newMovie.setMovieName(movieName);
		newMovie.setNation(nation);
		newMovie.setPubYear(pubYear);
		
		movieRepository.addMovie(newMovie);
	}
	
	/*스위치2번 > 영화 정보 검색 비즈니스 로직*/
	private List<Movie> searchMovieData() {
		System.out.println("\n=============== 영화 DVD 검색 조건을 선택하세요. ===============");
		System.out.println("[ 1. 제목검색 | 2. 국가검색 | 3. 발매연도검색 | 4. 전체검색 ]");
		System.out.print(">>> ");
		int selection = inputInteger();
		
		Condition condition = ALL; /*기본값은 ALL, Condition이란 이넘클래스를 만들고 다시 넘어와서 ALL 써준 것이다.*/
		switch (selection) { /*여기서 SQL을 만들고 메서드한테 보내줄 것이다. 레파지토리 쪽에는 커넥션과 prepared로 세팅만 해놓고 sql을 받아서 실행을 시켜주면 되잖아. 물음표 있으면 채워주고.*/
		case 1:
			System.out.println("\n### 제목으로 검색합니다.");
			condition = MOVIE_NAME;
			break;
		case 2:
			System.out.println("\n### 국가명으로 검색합니다.");
			condition = NATION;
			break;
		case 3:
			System.out.println("\n### 발매연도로 검색합니다.");
			condition = PUB_YEAR;
			break;
		case 4:
			System.out.println("\n### 전체 정보를 검색합니다.");
			break;

		default:
			System.out.println("\n### 잘못 입력했습니다.");
		}
		
		
		
		String keyword = "";
		/*ALL이 아니라면 검색어를 입력 받겠다*/
		if(condition != ALL) {
			System.out.print("# 검색어: ");
			keyword = "'%" + inputString() + "%'";
		}
		/*이제 무비 레파지토리에 키워드가 무엇인지, 컨디션이 무엇이었는지 같이 전달해줘야 한다.*/
		return movieRepository.searchMovieList(condition, keyword);	
	}
	
	/*영화 검색 정보 출력(여기서 서치무비데이터 메서드를 불러줄 예정)*/
	private void showSearchResult() {
		List<Movie> movies = searchMovieData(); /*여기로 리턴값을 받음*/
		
		
		/*반복문처리*/
		/*영화가 검색이 안되었을수도 있으니까(이상하게 입력한다던가..)*/
		if(movies.size() > 0) {
			/*0보다 크면 내용을 보여줄거고*/
			/*검색 결과 있으면 반복문이용.*/
			for(Movie m : movies) {
				System.out.println(m);
			}
			System.out.printf("\n============================ 검색 결과 (총 %d건) ============================\n", movies.size());
		} else {
			System.out.println("\n### 검색 결과가 없습니다.");
		}
		
	}
	
}














