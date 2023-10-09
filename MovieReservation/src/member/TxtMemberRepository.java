package member;

import java.util.HashMap;
import java.util.Map;

public class TxtMemberRepository implements MemberRepository {
    private static Map<String,Member> members = new HashMap<>();

    @Override
    public void save(Member member) {
        members.put(member.getId(),member);
    }

    @Override
    public Member findById(String memberId) {
        return members.get(memberId);
    }
}
