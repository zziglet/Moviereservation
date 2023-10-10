import member.Member;

public class MemberApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        Member member = new Member("kim","122","ksw");
        memberService.join(member);
        Member findMember = memberService.findMember("kim");
        System.out.println("member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}
