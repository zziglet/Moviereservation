package Member;

public class MemberServiceApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberService();
        memberService.CreateReservation(new Member("id","pw","name"));
    }
}
