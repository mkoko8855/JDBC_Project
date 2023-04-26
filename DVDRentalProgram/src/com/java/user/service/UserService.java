package com.java.user.service;

import static com.java.view.AppUI.inputInteger;
import static com.java.view.AppUI.inputString;
import static com.java.view.AppUI.userManagementScreen;

import java.util.List;

import com.java.common.AppService;
import com.java.user.domain.User;
import com.java.user.repository.UserRepository;
public class UserService implements AppService {

	/*인터페이스를 제작해서 처음 메뉴에 들어왔을 떄 start메뉴선언을 해서 일괄적으로(회원, 대여, 영화 관리든 모두 통일함) 제작.*/

	
	
	
	private final UserRepository userRepository = new UserRepository();
	
	
	
	
	
	@Override
	public void start() {
		
		while(true) {
			/*회원 관리에 대한 메뉴부터 보여주고 나서 입력을 받아야 하니 AppUI로 가자
			  가서 회원 관리 시스템 화면 출력을 작성해줬다. 이제 AppUI를 부르자
			  import static com.java.view.AppUI.*; 하고 AppUI를 지워도됨~
			 */
			userManagementScreen();
			
			/*int selection = inputInteger();은 AppUI의 정수를 받기 위한 작업이다.*/
			int selection = inputInteger();
			
			
			
			switch(selection) {
			case 1:
				join(); 
				break;
			case 2:
				showSearchResult();
				break;
			case 3:
				deleteUser();
				break;
			case 4:
				
				return;
			default:
				System.out.println("메뉴를 다시 입력해주세요");
			
			}
			
			System.out.println("\n====== 계속 진행하시려면 ENTER를 누르세요 ======");
			inputString();/*사용자가 엔터 친 값을 받을것이다.*/
		}
	}
	
	
	
	
	
	/*회원 추가 비즈니스 로직*/
	/*외부에서는 이 메서드를 부를 필요가 없다. 이 안에서만 부른다 > 프라이빗으로.*/
	private void join() { /*조인은 이 클래스 안에서 1번..을 누를때니까.*/
		
		System.out.println("\n====== 회원 가입을 진행합니다. ======");
		
		System.out.print("# 회원명: ");
		String name = inputString(); /*스캐너일일이 필요없지~ 스태틱으로 만들어놨으니까*/
		
		System.out.println("# 전화번호: ");
		String phone = inputString();
		
		
		
		/*유저레파지토리에 있는 메서드를 부를 것이다.*/
		/*AddUer를 부르자~*/
		/*근데 이 메서드를 조인에서만 사용할 것이 아니기 때문에*/
		/*맨위로가서 	Private final UserRepository userRepository = new UserRepository(); 만들어주자*/
		/*즉, 회원명 받고 전화번호 받고 넘기자*/
		
		User user = new User();/*회원명과 전화번호를 객체로 담아서*/
		user.setUserName(name);
		user.setPhoneNumber(phone);
		
		/*담았으니 user를 보내주자*/
		userRepository.addUser(user);		
		
		
	}
	
	
	
	
	/*회원 이름으로 검색 비즈니스 로직*/
	private List<User> searchUser(){
		System.out.println("\n### 조회할 회원의 이름을 입력하세요.");
		System.out.println(">>> ");
		String name = inputString(); /*입력값 받아주고*/
		return userRepository.findByUserName(name); /*find가 우리에게 List로 리턴해주겠지 이걸 리턴하자*/
	}
	
	
	private int showSearchResult() {
		List<User> users = searchUser(); /*여기서 보여주는 로직을 메뉴에 넣을 것이다. 얘가 바로 위를 불러서 검색을 진행하고 여기서 출력을 진행하는 것의 결과를 스위치문에 넣는 것이다.*/
	
		/*조회문 코드를 전부 완료한 다음, 조회결과가 없을 수도 있으니까 조건문을 써줘야지*/
		if(!users.isEmpty()) {
			/*비어있지 않니 라고 물어보는 것이다. 부정적인것을 else로 뺴려고 !붙여준 것이다. 가독성을 좋게 하기 위해*/
			System.out.println("\n==================== 회원 조회 결과 ====================");
			/*리스트 안에 내용들을 반복문으로 보자*/
			for(User user : users) {
				System.out.println(user); /*toString오버라이딩해놨잖아*/
			}
		} else {
			System.out.println("\n### 조회 결과가 없습니다.");
		}
		return users.size(); /*데이터가 조회되지 않으면 의미없는 로직~*/
	}
	
	
	
	
	
	
	
	
	
	/*회원 탈퇴 비즈니스 로직*/
	private void deleteUser() {
		
		//삭제할 회원의 이름을 입력받아서 일단은 삭제 대상 회원만 가지고 오는 부분.
		showSearchResult(); /*여기서회원보여줄거고 보여줄 회원이 없다라고얘기하겠지*/
		
		/*이름을 입력받았는데 없는 회원이면 안되니까*/
		if(showSearchResult() > 0) {
			
		/*비어있지 않다면 탈퇴할 회원 번호가 들어갈거고*/
		/*입력받는부분*/
		System.out.println("\n### 탈퇴할 회원의 번호를 입력하세요.");
		System.out.print(">>> ");
		int delUserNum = inputInteger(); /*스캐너 선언해놨으니 이렇게써도되겠지~*/
		userRepository.deleteUser(delUserNum); 
		}
		
	}
}
