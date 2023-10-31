package Member;

import Movie.Movie;
import Menu.MainMenu;

import java.awt.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;


public class MemberService {

    //영화 예매를 위한 메소드 (인자를 뭘 넘겨야할지 아직 감이 안옴)
    void CreateReservation(Member member, Movie movie){
        
    }

    //영화 예매 취소 메소드
    public void CancelReservation(Member member) {
        System.out.print("[예매 취소]\n사용자의 예매 내역입니다.\n취소할 예매 내역을 골라주세요.");
        //예매 목록 출력 id.txt
        MemberRepository repository=new MemberRepository();
        repository.FindMovie(member);

        Scanner scanner=new Scanner(System.in);

        while(true){
            int choice=scanner.nextInt();
            ArrayList<Movie> movieList=member.getMovielist();

            if(choice>0&&choice<=movieList.size()){
                Movie cancledMovie=movieList.get(choice-1);
                System.out.println("["+choice+"]");
                System.out.println("영화 제목:"+cancledMovie.getName());
                System.out.println("상영 날짜:"+cancledMovie.getDate());
                System.out.println("좌석:"+String.join("/", cancledMovie.getRseat()));

                while(true){
                    System.out.println("예매를 취소하시겠습니까? (y/n): ");
                    String ans=scanner.next();
                    if(ans.equals("y")||ans.equals("Y")) {
                        System.out.println("예매가 취소되었습니다.");
                        //repository.DeleteMembertxtMovie();
                        //repository.DeleteMovietxt();
                        break;
                    }else if(ans.equals("n")||ans.equals("N")) {
                        System.out.println("메인 메뉴로 돌아갑니다.");
                        MainMenu mainmenu = new MainMenu();
                        mainmenu.ShowMenu();
                        break;
                    }else{
                        System.out.println("..! 오류: 예매 취소를 원하시면 y를 원하지 않으시면 n을 입력해야합니다. 다시 입력해주세요.");
                    }
                }
                break;
            }else{
                System.out.println("..! 오류: 예매되어 있는 영화 번호를 입력해야 합니다. 다시 입력해주세요");
            }
        }

    }

}
