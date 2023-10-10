package reservation;

import member.Member;
import member.MemberRepository;
import member.TxtMemberRepository;
import movie.Movie;
import movie.MovieRepository;
import movie.TxtMovieRepository;

import java.util.ArrayList;
import java.util.Scanner;

public class ReservationServiceImpl implements ReservationService{
    private final MemberRepository memberRepository = new TxtMemberRepository();
    private final MovieRepository movieRepository = new TxtMovieRepository();

    @Override
    public void cancelReservation(Member member) {
        System.out.println("사용자의 예매 내역입니다.");
        System.out.println("취소할 예매 내역을 골라주세요.");

        /*
            member 객체에 저장되어 있는 예매 내역을 불러와야함 -> Memberservice 객체를 불러와야하지않나..
         */
    }

    @Override
    public void createReservation(Member member) {
        Scanner scan = new Scanner(System.in);
        
        // 영화 선택
        System.out.println("[예매 / 영화선택] 현재 상영되고 있는 영화입니다. 영화 제목을 입력해주세요. ");
        ArrayList<Movie> movie_list = MovieRepository.findAll(Date date);
        /*
            현재 날짜 이후의 영화들을 MovieRepository에서 찾아서 ArrayList 형식으로 반환
        */
        String[] movie_name = new String[movie_list.size()];
        for(int i = 0; i<movie_list.size(); i++) {
            movie_name[i] =movie_list.get(i).getName();
            System.out.println(movie_name[i]);
        }
            /*
               Movie 객체가 저장된 list에서 이름들을 불러와서 출력
            */
        String a = scan.next();
        // 대충 예외처리 했다 치고
        if(movieRepository.findByName(a).getName() == 0){
            System.out.println("..! 현재 상영하고 있는 영화가 아닙니다. 다시 입력해주세요");
        }
        // 날짜 선택


        // 시간 선택



        // 인원 선택



        // 좌석 선택


        // 영화 예매 완료 후

        // memberRepository 객체에서 사용자의 정보와 선택한 movie 저장
        memberRepository.save(member,movie);



    }
}



