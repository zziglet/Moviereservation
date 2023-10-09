package reservation;

import member.Member;
import member.MemberRepository;
import member.TxtMemberRepository;
import movie.Movie;
import movie.MovieRepository;
import movie.TxtMovieRepository;

public class ReservationServiceImpl implements ReservationService{
    private final MemberRepository memberRepository = new TxtMemberRepository();
    private final MovieRepository movieRepository = new TxtMovieRepository();

    @Override
    public void createReservation(String memberId,String movieName) {
        Member member = memberRepository.findById(memberId);
        // 대충 txt파일에 member.getName()해서 회원 이름저장
        Movie movie = movieRepository.findByName(movieName);
        movie.getName();
        movie.getDate();
        movie.getTime();
        // 대충 txt파일에 movie 저장;
    }
}
