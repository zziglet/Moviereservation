package Menu;

import Member.*;
import java.util.Scanner;
import static java.lang.System.exit;

public class MainMenu {
    Member member= new Member();
    public void ShowMenu(){
        Scanner scan=new Scanner(System.in);

        System.out.print("\n\n[메인 메뉴] 원하시는 서비스의 숫자를 입력해주세요.\n\n");
        System.out.print("1. 예매\n2. 마이페이지\n3. 종료\n");


        while(true){
            System.out.print("MovieReservation >>");
            int input=scan.nextInt();

            switch(input){
                case 1:
                    MemberService.CreateReservation();
                    break;
                case 2:
                    MyPageMenu.ShowMenu2();
                    break;
                case 3:
                    Exit();
                    break;
                default:
                    System.out.print("..! 오류 : 잘못된 입력입니다. 다시 입력해주세요");
                    System.out.println();
                    break;
            }
        }

    }

    public void Exit(){
        System.out.println("영화 예매 프로그램을 이용해주셔서 감사합니다.\n프로그램을 종료합니다.\n");
        exit(0);
    }

}
