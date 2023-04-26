package com.java.main;

import com.java.common.AppService;
import com.java.movie.service.MovieService;
import com.java.order.service.OrderService;
import com.java.user.service.UserService;

public class AppController {
	
	/*인터페이스 만들었으니*/
	private AppService service;
	/*케이스 1, 2, 3, 4들이 모두 서비스객체가 들어갈 것이다. 다형성을 위해서 선언한 것이다. 일일이 타입마다 변수를 선언할 필요가 없다.*/
	
	
	/*시스템을 정해주는 기능이다.*/
	/*메서드하나선언부터해보자*/
	public void chooseSystem(int selectNumber) { /*매개값으로는 selectNumber를 받는다.*/
		/*1,2,3,4말고 다른것도 올 수 있으니*/
		switch(selectNumber){
		case 1:
			service = new UserService();
			break;
		case 2:
			service = new OrderService();
			break;
		case 3:
			service = new MovieService();
			break;
		case 4:
			System.out.println("# 프로그램을 종료합니다.");
			System.exit(0); /*프로그램 끝*/
			break;
		default:
			/*사용자가 무엇을 잘못입력한거니*/
			System.out.println("# 메뉴를 다시 입력하세요");
		}
		service.start(); 
		/*서비스변수에 여러 서비스들을 생성해서 스타트가 먼저 진행될 수 있도록 처리했다.*/
		/*그러면 1번을 사용자가 선택했으면, UserSerivce 객체의 start가 실행이 된다.*/
	}
}
