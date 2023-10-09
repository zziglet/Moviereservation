package member;

public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository = new TxtMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(String memberId) {
        return memberRepository.findById(memberId);
    }
}
