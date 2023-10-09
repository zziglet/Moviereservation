package member;

public interface MemberRepository {
    void save(Member member);

    Member findById(String id);
}
