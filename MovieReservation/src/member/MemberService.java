package member;

import movie.Movie;

public interface MemberService {

    //영화 예매를 위한 메소드 (인자를 뭘 넘겨야할지 아직 감이 안옴)
    void createReservation(Member member, Movie movie);

    //영화 예매 취소 메소드
    void deleteReservation(Member member, Movie movie);

    //예매 내역 확인 (마이페이지를 들어가서 예매 내역확인)
    Movie findReservation(Member member);
}
