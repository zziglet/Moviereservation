package member;

import movie.Movie;
import movie.MovieRepository;
import movie.TxtMovieRepository;

public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository = new TxtMemberRepository();
    private final MovieRepository movieRepository = new TxtMovieRepository();

    @Override
    public void createReservation(Member member, Movie movie) {

    }

    @Override
    public void deleteReservation(Member member, Movie movie) {

    }

    @Override
    public Movie findReservation(Member member) {
        return null;
    }
}
