package Menu;

import Member.Member;
import Member.MemberRepository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Pattern;

//id, pw, name을 넘겨서 savemember()에 인자로 해서 회원가입 후 txt 파일 생성
public class SignUpInMenu {

    Scanner scan = new Scanner(System.in);

    public void Start() {
        SignUpInMenu menu1 = new SignUpInMenu();

        System.out.println("MovieReservation>> ");
        System.out.println("환영합니다 영화 예매 시스템입니다.");
        System.out.println("원하는 메뉴의 숫자 입력 후, Enter 키를 눌러주세요.\n");

        menu1.PrintMenu();
        menu1.MenuInput();
    }

    void PrintMenu() {
        System.out.println("    1. 회원 가입");
        System.out.println("    2. 로그인");
        System.out.println("    3. 종료\n");
        System.out.print("MovieReservation>> ");
    }

    void MenuInput() {

        String input = scan.nextLine();
        String input1 = input.replaceAll("\\s","");

        if(input1.equals("1"))
            SignUp();
        else if(input1.equals("2"))
            SignIn();
        else if(input1.equals("3"))
            exit();
        else {
            System.out.println("!오류: 잘못된 입력입니다. 다시 입력해주세요.\n");
            System.out.print("MovieReservation>> ");
            MenuInput();
        }
    }

    //회원가입 시 아이디 중복체크
    String FileIdRead(String id) {
        String newid=id;
        String path = System.getProperty("user.dir") + "\\MovieReservation\\src\\user";
        File folder = new File(path); //사용자 데이터 파일
        File[] fileList = folder.listFiles();
        if(fileList != null){
            for(File file :fileList) {
                if(file.isFile() && file.canRead()) {
                    if(file.getName().equals(id+".txt")) {
                        System.out.println("!오류: 이미 존재하는 아이디입니다. 다시 입력해주세요.\n");
                    /*
                     * System.out.print("MovieReservation>> ");
                     * newid=FileIdRead(newid=scan.nextLine()); //아이디 문법 규칙 검증은?
                     * */
                    return null;
                }
            }
        }
    }
        return newid;
    }

    //회원가입 (이름 -> 아이디 -> 비밀번호)
    void SignUp() {

        System.out.println("[회원가입] 사용자의 정보를 입력해주세요.\n");
        System.out.println("이름을 입력하세요");
        System.out.print("MovieReservation>> ");
        String name = scan.nextLine();
        //이름 문법규칙 체크
        while(name.isBlank()||name.trim()!=name||(!Pattern.matches("^[a-zA-z가-힣]*$", name.replaceAll("\\s","")))) {
            System.out.println(" ..! 오류: 이름은 한글, 영문 대/소문자로 구성된 길이가 1 이상의 문자열이어야 합니다. 다시 입력해주세요.\n");
            System.out.print("MovieReservation>> ");
            name = scan.nextLine();
        }

        System.out.println("아이디를 입력하세요");
        System.out.print("MovieReservation>> ");
        String id = null;
        boolean idcorrect=true;
        //아이디 문법규칙, 의미규칙 체크
        //문법 체크 후 중복 체크하는 걸로
        while(idcorrect) {
            System.out.print("MovieReservation>> ");
            id = scan.nextLine();

            if(id.trim()!=id||!(Pattern.matches("^[0-9a-zA-z]*$",id))||id.length()<5||id.length()>10) {
                System.out.println(" ..! 오류: 아이디는 길이가 5 이상 10 이하인 영문 대/소문자, 숫자만으로 구성된 문자열이어야 합니다. 다시 입력해주세요.\n");
                idcorrect = false;
            }else { //문법 규칙에 부합할때만 중복 체크
                id = FileIdRead(id); //ID 중복체크
                if(id == null)
                    idcorrect = false;
            }
            idcorrect = !(idcorrect); //idcorrect가 false라면 아이디 오류
        }

        System.out.println("\n비밀번호를 입력하세요.");
        System.out.print("MovieReservation>> ");
        String pw = scan.nextLine();
        //비밀번호 문법규칙 체크
        while(pw.trim()!=pw||!(Pattern.matches("^[0-9a-zA-z]*$",pw))||pw.length()<8||pw.length()>20) {
            System.out.println("..! 오류: 비밀번호는 영문 대/소문자, 숫자만으로 구성된 길이가 8이상 20이하의 문자열이어야 합니다. 다시 입력해주세요.\n");
            System.out.print("MovieReservation>> ");
            pw = scan.nextLine();
        }

        System.out.println("\n..! 회원가입이 완료되었습니다.\n");

        //MemberRepository Class의 SaveMember()메소드에 이름, 아이디, 비밀번호를 인자로 넘겨 사용자 정보 데이터 파일을 생성한다.

        MemberRepository user = new MemberRepository();
        user.SaveMember(name, id, pw);

        System.out.println("MovieReservation>> ");
        System.out.println("환영합니다 영화 예매 시스템입니다.");
        System.out.println("원하는 메뉴의 숫자 입력 후, Enter 키를 눌러주세요.\n");
        PrintMenu();
        MenuInput();

    }

    //로그인 (아이디 > 비밀번호)
    void SignIn() {
        try {
            System.out.println("[로그인]");
            System.out.println("아이디를 입력하세요.");
            System.out.print("MovieReservation>> ");
            String id = scan.nextLine();
            String path = System.getProperty("user.dir") + "\\MovieReservation\\src\\user";
            File folder = new File(path);
            File[] filelist = folder.listFiles();

            boolean temp=true;
            boolean isID=true;
            String name="";
            String pw="";
            String line="";

            while(temp) {
                if(filelist != null){
                    for(File file:filelist) {
                    if(file.isFile()&&file.canRead()) {
                        //해당 아이디를 가진 유저 메모장의 경우
                        if(file.getName().equals(id+".txt")) {
                            isID=false;
                            FileReader filereader = new FileReader(file);
                            BufferedReader bufReader = new BufferedReader(filereader);
                            line=bufReader.readLine();
                            String arr[]=line.split("\\s");
                            System.out.println("비밀번호를 입력해주세요.");
                            System.out.print("MovieReservation>> ");
                            pw=scan.nextLine();
                            //비밀번호가 일치하는지 체크
                            while(!(arr[arr.length-1].equals(pw))) {
                                System.out.println("..! 오류: 비밀번호가 일치하지 않습니다. 다시 입력해주세요\n");
                                System.out.print("MovieReservation>> ");
                                pw=scan.nextLine();
                            }
                            temp=false;
                            /*
                            name=line.substring(0,line.length()-pw.length()-1);
                            Scanner s = new Scanner(file);
                            s.nextLine();
                            while(s.hasNextLine()) {
                                String res1[]=s.nextLine().split("\\s");
                                Flight flight= new Flight(res1[0],res1[1],res1[2],res1[3],res1[4],res1[5]);
                                String res2[]=s.nextLine().split("\\s");
                                FlightTicket flightticket = new FlightTicket(flight,res2[1],Integer.parseInt(res2[0]));
                                flightTicketList.add(flightticket);
                            }
                            s.close();
                             */
                            
                            filereader.close();
                            bufReader.close();
                        }
                    }
                }
                }
                
                //폴더에 해당 아이디를 가진 유저 메모장이 없는 경우
                if(isID) {
                    System.out.println("..!오류 : 등록되지 않은 아이디입니다. 다시 입력해주세요.\n");
                    System.out.print("MovieReservation>> ");
                    id = scan.nextLine();
                }
            }
            System.out.println("로그인 완료!\n");

            //로그인 완료 시 유저 객체 생성
            Member user = new Member(id,pw,name);
            //메인메뉴 객체 생성
            MainMenu mainmenu = new MainMenu();
            //메인메뉴의 showMenu 함수 호출
            mainmenu.ShowMenu();

        }catch(Exception e) {
            e.printStackTrace();
        }

    }

    void exit() {
        System.out.println("\n영화 예매 프로그램을 이용해주셔서 감사합니다.");
        System.out.println("프로그램을 종료합니다.");
        System.exit(0);
    }

}
