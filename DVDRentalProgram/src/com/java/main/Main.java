package com.java.main;

import static com.java.view.AppUI.inputInteger;

import com.java.view.AppUI;

public class Main {

	public static void main(String[] args) {
		
		
		AppController controller = new AppController(); /*얘가 메뉴다받아서 서비스객체를 생성해줄거임*/
		
		
		
		
		
		/*이제 사용자가 프로그램을 종료시켜달라고 얘기할떄까지 프로그램이 계속 진행이 되어야 겠지*/
		while(true) {
			AppUI.startScreen(); /*DVD대여관리시스템이 바로 출력이 되겠지~*/
			
			/*정수를받기위해*/
			int selectNumber = inputInteger(); 
			/*스태틱메서드이기 떄문에 객체생성 없이도 메서드를 부를 수 있는데,
			  얘는 입력을 받는 거니까 이 메서드가 어울리지 않으니 클래스 이름을 가려주고 싶다면?
			  import를 할때 스태틱 메서드 자체를 임포트 할 수 있는 기능이 있다.
			  AppUI.inputinteger(); 이부분에서 AppUI를 뺴고싶다는 얘기다.
			  맨위로가서 import static com.java.view.AppUI.inputInteger; 라고하면
			  스태틱 메서드 자체를 임포트할 수 있어서 클래스 이름 없이 사용 가능하다. 
			  정수받는 부분에서 AppUI.inputInteger();가 아니라
			  inputInteger();만 쓰자.
			 */
			
			/* selectNumber가 몇번이냐에따라서 실행되는것이 달라지겠지 컨트롤러클래스로 가자*/
			
			controller.chooseSystem(selectNumber); 
		}
		
	}
}
