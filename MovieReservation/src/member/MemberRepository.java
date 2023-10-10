package member;

public interface MemberRepository {

    //회원가입 -> 회원 저장
    void save(Member member);

    //회원조회 -> 아이디로 회원 찾자 (나중에 비밀번호로 같이 찾아야할 수 있음)
    Member findById(String id);
}
