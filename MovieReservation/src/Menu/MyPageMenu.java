package Menu;

import java.util.Scanner;

import Member.Member;
import Member.MemberRepository;
import Member.MemberService;

public class MyPageMenu{

    Member member;
    MemberRepository memberrepo;
    MemberService membersev;
    
    static Scanner scan = new Scanner(System.in);
    String moviefile = "./src/movie.txt";
    String userfile;
    
    MyPageMenu(Member member) {
    	super();
        this.member = member;
    }
    
    public void ShowMenu2() {

    	int choice = 0;
    	while(choice != 3) {
    		System.out.println("[마이페이지] 원하는 서비스의 숫자를 입력해주세요.\n");
        	System.out.println("1. 예매 내역 확인\n2. 예매 취소\n3. 뒤로 가기\n");
                System.out.print("MovieReservation >");
                String input = scan.nextLine();

                String result = input.replaceAll(" ", "");
                    
                if(result.equals("1")) choice = 1;
                else if(result.equals("2")) choice = 2;
                else if(result.equals("3")) choice = 3;
                
                switch (choice) {
                    case 1:
                        memberrepo.FindMovie(member);
                        break;
                    case 2:
                        membersev.CancelReservation(member);
                        break;
                    case 3:
                        break;
                    default:
                        System.out.println("..! 오류 : 잘못된 입력입니다. 다시 입력해주세요.");
                        break;
            }
    	}
    }
    

}
