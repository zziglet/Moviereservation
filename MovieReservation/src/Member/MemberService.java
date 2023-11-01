package Member;

import Movie.Movie;
import Movie.MovieRepository;
import Menu.MainMenu;

import java.awt.*;
import java.io.*;
import java.util.*;


public class MemberService {

    MemberRepository memberRepository = new MemberRepository();
    MovieRepository movieRepository = new MovieRepository();
    MainMenu mainmenu = new MainMenu();
    Scanner scan = new Scanner(System.in);
    //영화 예매를 위한 메소드 (인자를 뭘 넘겨야할지 아직 감이 안옴)
    public void CreateReservation(Member member){
        ArrayList<Movie> movieList = movieRepository.find();
        Set<String> movieNames = new HashSet<String>();
        Set<String> movieDates = new HashSet<String>();
        Set<String> movieTimes = new HashSet<String>();

        movieNames.add(movieList.get(0).getName());
        String movieNameBeforeThis = movieList.get(0).getName();
        for (int i = 0; i < movieList.size(); i++){
            if(movieList.get(i).getName().equals(movieNameBeforeThis)) continue;
            movieNames.add(movieList.get(i).getName());
            movieNameBeforeThis = movieList.get(i).getName();
        }

        System.out.println("[예매 / 영화선택] 현재 상영되고 있는 영화입니다. 영화 제목을 입력해주세요.\n");

        Iterator<String> nameIter = movieNames.iterator();
        while(nameIter.hasNext())
            System.out.println(nameIter.next());
        System.out.println();
        System.out.print("MovieReservation >> ");
        String movieNameInput = scan.nextLine();

/*
영화 제목 검사 부분
 */

        for (int i = 0; i < movieList.size();) {
            if (!movieList.get(i).getName().equals(movieNameInput))
                movieList.remove(i);
            else
                i++;
        }
        System.out.println("[예매 / 날짜선택] 선택하신 영화의 상영 날짜입니다. 날짜를 입력해주세요.\n");

        Iterator<String> dateIter = movieDates.iterator();
        while (dateIter.hasNext())
            System.out.println(dateIter.next());
        System.out.println();
        System.out.print("MovieReservation >> ");
        String movieDateInput = scan.nextLine();

        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).getName().equals(movieNameInput)){
                movieDates.add(movieList.get(i).getDate());
            }
        }

    }


    //영화 예매 취소 메소드
    public void CancelReservation(Member member) {
        System.out.print("[예매 취소]\n사용자의 예매 내역입니다.\n취소할 예매 내역을 골라주세요.");
        //예매 목록 출력 id.txt
        memberRepository.FindMovie(member);

        Scanner scanner=new Scanner(System.in);

        while(true){
            System.out.print("MovieReservation >> ");
            int choice=scanner.nextInt();
            ArrayList<Movie> movieList=member.getMovielist();

            if(choice>0&&choice<=movieList.size()){
                Movie cancelMovie=movieList.get(choice-1);
                System.out.println("["+choice+"]");
                System.out.println("영화 제목:"+cancelMovie.getName());
                System.out.println("상영 날짜:"+cancelMovie.getDate());
                System.out.println("좌석:"+String.join("/", cancelMovie.getRseat()));


                System.out.println("예매를 취소하시겠습니까? (y/n): ");
                while(true){
                        System.out.print("MovieReservation >> ");
                        String ans=scanner.next();
                        if(ans.equals("y")||ans.equals("Y")) {
                            System.out.println("사용자의 예매 내역이 취소되었습니다. 메인 메뉴로 돌아갑니다.");
                            for (String seat : cancelMovie.getRseat()) {
                                memberRepository.DeleteMembertxtMovie(cancelMovie, member);
                                movieRepository.DeleteMovietxt(cancelMovie, seat);
                            }
                            mainmenu.ShowMenu();
                            break;
                        }else if(ans.equals("n")||ans.equals("N")) {
                            System.out.println("메인 메뉴로 돌아갑니다.");
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
