package Member;

import Movie.Movie;

public class MemberService {

    //영화 예매를 위한 메소드 (인자를 뭘 넘겨야할지 아직 감이 안옴)
    void CreateReservation(Member member, Movie movie){
        
    }

    //영화 예매 취소 메소드
    void CancelReservation(Member member, Movie movie){
        System.out.print("[마이페이지]원하는 서비스의 숫자를 입력해주세요.\n\n");
        System.out.print("1. 예매 내역 확인\n2.예매 취소\n3.뒤로 가기");
    }

}
