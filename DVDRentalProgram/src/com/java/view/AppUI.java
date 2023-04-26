package com.java.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AppUI {

	
	private static Scanner sc = new Scanner(System.in);
	
	
	/*이제 메서드 몇개 좀 작성해보자*/
	
	
	public static String inputString() {
		return sc.nextLine(); /*sc.nextLine으로 사용자의 입력값을 받겠다*/
							  /*다른클래스에서 스캐너를 일일이 생성할 필요없다.*/
	}
	
	/*정수를 받을 일도 있겠지?*/
	public static int inputInteger() {
		int num = 0;
		/*받긴하는데 에러받을 수도 있으니*/
		try {
			num = sc.nextInt(); /*정수받기*/
		} catch (InputMismatchException  e) {
			/*정수가 아닌 이상한값을 적었을테니*/
			System.out.println("정수로 입력 해 주세요");
		} finally {
			sc.nextLine(); //공백도 받을 수 있게 이걸로 처리하고.
		}
		return num;
	}
	
	
	/* 메뉴를 제공하는 기능도 이 클래스에서 전담해보자 */
	/*시작 화면 출력*/
	/*메서드는*/
	public static void startScreen() {
		System.out.println("\n========== DVD 대여 관리 시스템 ==========");
		System.out.println("### 1. 회원 관리 시스템");
		System.out.println("### 2. 대여 주문 관리 시스템");
		System.out.println("### 3. 영화 DVD 관리 시스템");
		System.out.println("### 4. 프로그램 종료");
		System.out.println("-----------------------------------------");
		System.out.print(">>> ");
	}
	
	
	
	
	/*회원 관리 시스템 화면 출력*/
	public static void userManagementScreen() {
		System.out.println("\n======== 회원 관리 관리 시스템 ==========");
		System.out.println("### 1. 신규 회원 추가");
		System.out.println("### 2. 회원 검색");
		System.out.println("### 3. 회원 탈퇴");
		System.out.println("### 4. 첫 화면으로 돌아가기");
		System.out.println("---------------------------------------");
		System.out.print(">>> ");

	}
	
	
	
	
	
	
	
	
	/*대여 주문 관리 시스템 화면 출력*/
	
	public static void orderManagementScreen() {
        System.out.println("\n========= 대여 주문 관리 시스템 =========");
        System.out.println("### 1. 영화 DVD 대여하기");
        System.out.println("### 2. 영화 DVD 반납하기");
        System.out.println("### 3. 첫 화면으로 가기");
        System.out.println("----------------------------------------");
        System.out.print(">>> ");
    }
	

	
	
	
	
	/*영화 관리 시스템 화면 출력*/
	public static void movieManagementScreen() {
        System.out.println("\n========= 영화 DVD 관리 시스템 =========");
        System.out.println("### 1. 신규 영화 DVD 추가");
        System.out.println("### 2. 영화 DVD 정보 검색");
        System.out.println("### 3. 첫 화면으로 가기"); /*이건뭐그냥 리턴처리하면됨.*/
        System.out.println("----------------------------------------");
        System.out.print(">>> ");
    }
	
	
	
	
	
}
