package Menu;

import Member.*;
import java.util.Scanner;
import static java.lang.System.exit;

public class MainMenu {
    Member member= new Member();
    
	public MainMenu(Member member) {
		super();
		this.member = member;
	}
	
    public void ShowMenu(){
        try (Scanner scan = new Scanner(System.in)) {
			MemberService memberservice = new MemberService();
			MyPageMenu mypagemenu = new MyPageMenu(this.member);
			
			while(true){
			    System.out.print("[메인 메뉴] 원하시는 서비스의 숫자를 입력해주세요.\n\n");
			    System.out.print("  1. 예매\n  2. 마이페이지\n  3. 로그아웃\n  4. 종료\n\n");
			    System.out.print("MovieReservation >>");
			    
			    String input=scan.nextLine();
			    String result = input.replaceAll("\\s+", "");
			    
			    if(result.equals("1"))
			    	memberservice.CreateReservation(this.member);
			    else if(result.equals("2"))
			    	mypagemenu.ShowMenu2();
				else if(result.equals("3")){
					new SignUpInMenu().logout();
				}
			    else if(result.equals("4"))
			    	Exit();
			    else {
			        System.out.print("..! 오류 : 잘못된 입력입니다. 다시 입력해주세요");
			        System.out.println();
			    }
			}
		}

    }

    public void Exit(){
        System.out.println("영화 예매 프로그램을 이용해주셔서 감사합니다.\n프로그램을 종료합니다.\n");
        exit(0);
    }

}
