package menu;

import menu.ShowMainMenu;
import menu.ShowMenu;

import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        ShowMenu menu = new ShowMainMenu();
        menu.showMenu();

        //1.회원가입
        //2.로그인
        //3.종료
        //if (사용자가 1를 입력)
        //  {
        //      String 아이디, 비밀번호, 이름 입력받고 저장
        //      Member member = new Member(아이디,비밀번호,이름);
        //      memberService.join(member);
        //      회원가입 완료 문장 콘솔에 출력
        //}
        System.out.println("Hello world!");
    }
}