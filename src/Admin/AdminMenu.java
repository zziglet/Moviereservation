package Admin;

import Member.*;
import static java.lang.System.exit;
import java.util.Scanner;
import Member.Member;
import Menu.MainMenu;
import Menu.SignUpInMenu;
import Admin.AdminService;

public class AdminMenu {

    Scanner scan=new Scanner(System.in);
    AdminService adminService=new AdminService();

    public void ShowAdminMenu(){

    while(true){
        System.out.print("\n[관리자 메뉴] 원하시는 서비스의 숫자를 입력해주세요.\n\n");
        System.out.print("  1. 영화 추가\n  2. 영화 수정\n  3. 영화 삭제\n  4. 로그아웃\n  5. 종료\n\n");
        System.out.print("MovieReservation >>");

        String input = scan.nextLine().replaceAll("\\s+", "");

        if(input.equals("1"))
            ShowAddMenu(); 
        else if(input.equals("2"))
            ShowEditMenu();
    else if(input.equals("3")){
            ShowDelMenu();
         }
        else if(input.equals("4"))
            new SignUpInMenu().logout();
        else if(input.equals("5"))
        {System.out.println("영화 예매 프로그램을 이용해주셔서 감사합니다.\n프로그램을 종료합니다.\n");
        exit(0);}
        else {
            System.out.print("..! 오류 : 잘못된 입력입니다. 다시 입력해주세요");
            System.out.println();
        }

    }

}

public void ShowAddMenu(){
    while (true) {
        System.out.print("[관리자 메뉴] 추가할 항목을 선택하세요.\n\n");
        System.out.print("  1. 상영관 추가\n  2. 영화 정보 추가\n  3. 상영 스케줄 추가\n  4. 뒤로가기\n\n");
        System.out.print("MovieReservation >>");

        String input = scan.nextLine().replaceAll("\\s+", "");

        if(input.equals("1")){
            adminService.AddTheater();
            ShowAdminMenu();
        }
        else if(input.equals("2"))
            {adminService.AddMovieInfo();
            ShowAdminMenu();
            }
        else if(input.equals("3")){
            adminService.AddMovie();
            ShowAdminMenu();
        }
        else if(input.equals("4"))
            ShowAdminMenu();
        else {
            System.out.print("..! 오류 : 잘못된 입력입니다. 다시 입력해주세요");
            System.out.println();
        }

    }
}

public void ShowEditMenu(){
     while (true) {
        System.out.print("[관리자 메뉴] 수정할 항목을 선택하세요.\n\n");
        System.out.print("  1. 상영관 수정\n  2. 영화 정보 수정\n  3. 뒤로가기\n\n");
        System.out.print("MovieReservation >>");

        String input = scan.nextLine().replaceAll("\\s+", "");

        if(input.equals("1"))
            {EditTheater(); 
            ShowAdminMenu();
            }     
        else if(input.equals("2"))
            {EditMovieInfo();  
            ShowAdminMenu();
            } 
        else if(input.equals("3"))
         ShowAdminMenu();
        
        else {
            System.out.print("..! 오류 : 잘못된 입력입니다. 다시 입력해주세요");
            System.out.println();
        }

    }
}

public void EditTheater(){
    System.out.println("[상영관 수정] 수정할 항목을 선택해주세요.\n");
        System.out.println(" 1. 관 번호 수정\n 2. 전체 좌석 수 수정\n 3. 뒤로가기\n\n");
        System.out.print("MovieReservation >> ");
        String input = scan.nextLine().replaceAll("\\s+", "");

        if(input.equals("1"))
            adminService.EditTheaterNum();      
        else if(input.equals("2"))
            adminService.EditTheaterSeat();  
        else if(input.equals("3"))
            ShowEditMenu();  
        else {
            System.out.print("..! 오류 : 잘못된 입력입니다. 다시 입력해주세요");
            System.out.println();
        }
}

public void EditMovieInfo(){
     System.out.println("[영화 정보 수정] 수정할 항목을 선택해주세요.\n");
        System.out.println(" 1. 영화 제목 수정\n 2. 영화 러닝 타임 수정\n 3. 뒤로가기\n\n");
        System.out.print("MovieReservation >> ");
        String input = scan.nextLine().replaceAll("\\s+", "");

        if(input.equals("1"))
            //인자가 이게 맞나..
            adminService.EditTheaterInfoName(null,null);     
        else if(input.equals("2"))
            adminService.EditTheaterRuntime();  
         else if(input.equals("3"))
            ShowEditMenu();     
        else {
            System.out.print("..! 오류 : 잘못된 입력입니다. 다시 입력해주세요");
            System.out.println();
        }
}

public void ShowDelMenu(){
     while (true) {
        System.out.print("[관리자 메뉴] 삭제할 항목을 선택하세요.\n\n");
        System.out.print("  1. 상영관 삭제\n  2. 영화 정보 삭제\n  3. 상영 스케줄 삭제\n  4. 뒤로가기\n\n");
        System.out.print("MovieReservation >>");

        String input = scan.nextLine().replaceAll("\\s+", "");

        if(input.equals("1")){
            adminService.DeleteTheater();
            ShowAdminMenu();  
        }
        else if(input.equals("2")){
            adminService.DeleteMovieInfo();
            ShowAdminMenu();  
        }
        else if(input.equals("3")){
            adminService.DeleteMovie();
            ShowAdminMenu();  
        }
        else if(input.equals("4"))
            ShowAdminMenu();
        else {
            System.out.print("..! 오류 : 잘못된 입력입니다. 다시 입력해주세요");
            System.out.println();
        }

    }
}


}





