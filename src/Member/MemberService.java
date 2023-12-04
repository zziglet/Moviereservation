package Member;

import Menu.MainMenu;
import Movie.Movie;
import Movie.MovieRepository;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Stream;


public class MemberService {

    MemberRepository memberRepository = new MemberRepository();
    MovieRepository movieRepository = new MovieRepository();
    Scanner scan = new Scanner(System.in);

    public void CreateReservation(Member member) {
        //예매 가능 영화 목록 출력하기
        ArrayList<Movie> movieAllList = movieRepository.find();
        ArrayList<Movie> movieList = new ArrayList<Movie>();

        //빈 좌석이 0개인 목록은 제외하기
        int cnt = 0;
        for (int j = 0; j < movieAllList.size(); j++) {
            for (String st : movieAllList.get(j).getSeat()) {
                if (st == "0") cnt++;
            }
            if (cnt < 72) {
                movieList.add(movieAllList.get(j));
            }
            cnt = 0;
        }

        Set<String> movieNames = new HashSet<String>();
        Set<String> movieDates = new HashSet<String>();
        Set<String> movieStarts = new HashSet<String>();

        movieNames.add(movieList.get(0).getName());
        String movieNameBeforeThis = movieList.get(0).getName();
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).getName().equals(movieNameBeforeThis)) continue;
            movieNames.add(movieList.get(i).getName());
            movieNameBeforeThis = movieList.get(i).getName();
        }

        System.out.println("[예매 / 영화선택] 현재 상영되고 있는 영화입니다. 영화 제목을 입력해주세요.\n");

        Iterator<String> nameIter = movieNames.iterator();
        while (nameIter.hasNext())
            System.out.println(nameIter.next());

        String movieNameInput;
        boolean flag1 = false; // 영화 제목 체크
        while (!flag1) {
            System.out.print("MovieReservation >> ");
            movieNameInput = scan.nextLine();
            if (!Pattern.matches("^[가-힣a-zA-Z0-9 ,:.!?()~-]+$", movieNameInput)) {
                System.out.println("..! 오류 : 잘못된 입력입니다. 다시 입력해주세요.\n");
                continue;
            }
            if (movieNames.contains(movieNameInput)) {
                flag1 = true;
                for (int i = 0; i < movieList.size(); i++) {
                    if (movieList.get(i).getName().equals(movieNameInput)) {
                        movieDates.add(movieList.get(i).getDate());
                    } else {
                        movieList.remove(i);
                        i--;
                    }
                }

                System.out.println("[예매 / 날짜선택] 선택하신 영화의 상영 날짜입니다. 날짜를 입력해주세요.\n");

                Iterator<String> dateIter = movieDates.iterator();
                while (dateIter.hasNext())
                    System.out.println(dateIter.next());

                String movieDateInput;
                boolean flag2 = false; //영화 상영 날짜 체크
                while (!flag2) {
                    System.out.println();
                    System.out.print("MovieReservation >> ");
                    movieDateInput = scan.nextLine();

                    if (!Pattern.matches("^[\\d]{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$", movieDateInput)) {
                        System.out.println("..! 오류 : 잘못된 입력입니다. 다시 입력해주세요.");
                        continue;
                    }

                    if (movieDates.contains(movieDateInput)) {
                        flag2 = true;
                        for (int i = 0; i < movieList.size(); i++) {
                            if (movieList.get(i).getDate().equals(movieDateInput)) {
                                movieStarts.add(movieList.get(i).getStart());
                            } else {
                                movieList.remove(i);
                                i--;
                            }
                        }
                        System.out.println("[예매 / 시간선택] 선택하신 영화의 상영 시간입니다. 시간을 입력해주세요.\n");

                        Iterator<String> startIter = movieStarts.iterator();
                        while (startIter.hasNext())
                            System.out.println(startIter.next());

                        String movieStartInput;
                        boolean flag3 = false; //영화 상영 시작시간 체크
                        lp1:
                        while (!flag3) {
                            System.out.println();
                            System.out.print("MovieReservation >> ");
                            movieStartInput = scan.nextLine();

                            if (!Pattern.matches("^([1-9]|[01][0-9]|2[0-3]):([0-5][0-9])$", movieStartInput)) {
                                System.out.println("..! 오류 : 잘못된 입력입니다. 다시 입력해주세요.");
                                continue;
                            }

                            if (movieStarts.contains(movieStartInput)) {
                                flag3 = true;
                                for (int i = 0; i < movieList.size(); i++) {
                                    if (!movieList.get(i).getStart().equals(movieStartInput)) {
                                        movieList.remove(i);
                                        i--;
                                    }
                                    else { //요구 사항 -> 예매내역과 동일한 시간대 영화는 예매 불가
                                        String inputStartTime = movieList.get(i).getStart();
                                        String inputEndTime = movieList.get(i).getEnd();
                                        int inputStartTimeInteger = Integer.parseInt(inputStartTime.substring(0, 2));
                                        int inputStartMinuteInteger = Integer.parseInt(inputStartTime.substring(3));
                                        int inputEndTimeInteger = Integer.parseInt(inputEndTime.substring(0, 2));
                                        int inputEndMinuteInteger = Integer.parseInt(inputEndTime.substring(3));
                                        int inputTimeInteger = (inputEndMinuteInteger - inputStartMinuteInteger >= 0) ? inputEndTimeInteger - inputStartTimeInteger : inputEndTimeInteger - inputStartTimeInteger - 1;
                                        int inputMinuteInteger = (inputEndMinuteInteger - inputStartMinuteInteger >= 0) ? inputEndMinuteInteger - inputStartMinuteInteger : inputEndMinuteInteger - inputStartMinuteInteger + 60;

                                        ArrayList<Movie> mov = member.getMovielist();
                                        int[] reservatedMovieStartTime = new int[mov.size()];
                                        int[] reservatedMovieStartMinute = new int[mov.size()];
                                        int[] reservatedMovieEndTime = new int[mov.size()];
                                        int[] reservatedMovieEndMinute = new int[mov.size()];
                                        for (int j = 0; j < mov.size(); j++) {
                                            reservatedMovieStartTime[j] = Integer.parseInt(mov.get(j).getStart().substring(0, 2));
                                            reservatedMovieStartMinute[j] = Integer.parseInt(mov.get(j).getStart().substring(3));
                                            reservatedMovieEndTime[j] = Integer.parseInt(mov.get(j).getEnd().substring(0, 2));
                                            reservatedMovieEndMinute[j] = Integer.parseInt(mov.get(j).getEnd().substring(3));
                                        }
                                        for (int j = 0; j < mov.size(); j++) {
                                            if (movieDateInput.equals(mov.get(j).getDate()) && (inputStartTimeInteger * 60 + inputStartMinuteInteger) < ((reservatedMovieStartTime[j] + inputTimeInteger) * 60 + (reservatedMovieStartMinute[j] + inputMinuteInteger)) && ((reservatedMovieEndTime[j] + inputTimeInteger) * 60 + (reservatedMovieEndMinute[j] + inputMinuteInteger)) > (inputEndTimeInteger * 60 + inputEndMinuteInteger)) {
                                                System.out.println("동일한 시간대에 예매한 영화가 있습니다. 원하시는 서비스의 숫자를 입력해주세요.");
                                                System.out.print("  1. 다시 입력\n  2. 예매 페이지\n  3. 메인 메뉴\n");
                                                System.out.println();
                                                System.out.print("MovieReservation >>");

                                                String input=scan.nextLine();
                                                String result = input.replaceAll("\\s+", "");

                                                if(result.equals("1")){
                                                    flag3 = false;
                                                    continue lp1;
                                                }
                                                else if(result.equals("2")){
                                                    CreateReservation(member);
                                                }
                                                else if(result.equals("3")){
                                                    MainMenu showmenu = new MainMenu(member);
                                                    showmenu.ShowMenu();
                                                }
                                            }
                                        }
                                    }
                                }
                                System.out.println("[예매 / 인원선택] 영화예매를 진행하실 인원 수를 입력해주세요.\n");

                                String movieNumber;
                                boolean flag4 = true; //인원 수 체크
                                while (flag4) {
                                    System.out.print("MovieReservation >> ");
                                    movieNumber = scan.nextLine();
                                    if (!Pattern.matches("[1-9]+|\\d+명", movieNumber)) {
                                        System.out.println("..! 오류 : 잘못된 입력입니다. 다시 입력해주세요.");
                                        continue;
                                    }
                                    if (!Pattern.matches("[1-9]+", movieNumber))
                                        movieNumber = movieNumber.substring(0, movieNumber.length() - 1);
                                    int num = Integer.parseInt(movieNumber);

                                    //남은 좌석 수보다 많은 인원 수 입력했을 시
                                    Movie movie = movieList.get(0);
                                    String[] seats = movie.getRseat();
                                    int seatsNum = movie.getSeat().length; // 전체 좌석 수 수저우저수저ㅜ서주서주서주서
                                    int reservedSeatsNum = 0; // 이미 예약된 좌석 수
                                    for (String seat : seats) {
                                        if (seat.equals("00")) reservedSeatsNum++;
                                    }
                                    int availableSeats = seatsNum - reservedSeatsNum; // 남은 좌석 수

                                    // 입력받은 인원 수와 남은 좌석 수 비교
                                    if (num > availableSeats) {
                                        System.out.println("..! 오류 : 남은 좌석이 " + availableSeats + "석입니다. " + "다시 입력해주세요.");
                                        continue; // 사용자가 다시 인원 수를 입력하도록 유도
                                    }

                                    if (num >= 1 && num <= 5) {
                                        flag4 = false;
                                        int allSeatsNum = movie.getSeat().length;
                                        int colNum;
                                        int lastColNum;
                                        int rowNum;
                                        if(allSeatsNum>=26*26){
                                            rowNum = (allSeatsNum%26==0)?allSeatsNum/26:allSeatsNum/26+1;
                                            colNum = 26;
                                            lastColNum = (allSeatsNum%26!=0)?allSeatsNum%26:0;
                                        }
                                        else if(allSeatsNum>=19*19){
                                            rowNum = (allSeatsNum%19==0)?allSeatsNum/19:allSeatsNum/19+1;
                                            colNum  = 19;
                                            lastColNum = (allSeatsNum%19!=0)?allSeatsNum%19:0;
                                        }
                                        else if(allSeatsNum >= 12*12){
                                            rowNum = (allSeatsNum%12==0)?allSeatsNum/12:allSeatsNum/12+1;
                                            colNum = 12;
                                            lastColNum = (allSeatsNum%12!=0)?allSeatsNum%12:0;
                                        }
                                        else {
                                            rowNum = (allSeatsNum%12==0)? allSeatsNum / 12 : allSeatsNum/12+1;
                                            colNum = 12;
                                            lastColNum = (allSeatsNum%12!=0)?allSeatsNum%12:0;
                                        }
                                        String[] reservedSeats = movie.getRseat();
                                        System.out.println("[예매 / 좌석선택] 좌석을 선택해주세요.\n");
                                        if(lastColNum==0){
                                            for (int i = 0; i < rowNum; i++) {
                                                int line = 65 + i;
                                                char ch = (char) line;
                                                System.out.print(ch + "|\t");
                                                for (int j = 0; j < colNum; j++) {
                                                    if (j == colNum-1) System.out.print(reservedSeats[colNum * i + j]);
                                                    else System.out.print(reservedSeats[colNum * i + j] + "|");
                                                }
                                                if (i%rowNum==0) System.out.println("\n");
                                                else System.out.println();
                                            }
                                        }

                                        else{
                                            for (int i = 0; i < rowNum-1; i++) {
                                                int line = 65 + i;
                                                char ch = (char) line;
                                                System.out.print(ch + "|\t");
                                                for (int j = 0; j < colNum; j++) {
                                                    if (j == colNum-1) System.out.print(reservedSeats[colNum * i + j]);
                                                    else System.out.print(reservedSeats[colNum * i + j] + "|");
                                                }
                                                if (i%rowNum==0) System.out.println("\n");
                                                else System.out.println();
                                            }
                                            int line = 65 + rowNum-1;
                                            char ch = (char) line;
                                            System.out.print(ch + "|\t");
                                            for (int j = 0; j < lastColNum; j++) {
                                                System.out.print(reservedSeats[colNum * (rowNum-1) + j] + "|");
                                            }
                                            System.out.println();
                                        }

                                        System.out.println("첫 열에 표시된 영대문자는 각 행의 좌석 라인입니다.");
                                        System.out.println("00으로 표시된 좌석은 이미 예약된 좌석입니다. 잔여 좌석 수는 " + (seatsNum - reservedSeatsNum) + "/" + seatsNum + "석입니다.");
                                        System.out.println("다음 중 원하는 메뉴를 선택하세요.\n");
                                        System.out.println("1. 좌석 선택");
                                        System.out.println("2. 메인 메뉴로 돌아가기.\n");

                                        boolean flag5 = true; //좌석 선택, 돌아가기 메뉴 오류 체크
                                        while (flag5) {
                                            System.out.print("MovieReservation >> ");
                                            String result1 = scan.nextLine();
                                            String input = result1.replaceAll("\\s+", "");

                                            if (input.equals("1")) {
                                                flag5 = false;
                                                System.out.println("[예매 / 좌석선택] 예매하려는 좌석번호를 입력해주세요.");
                                                String seatInput;
                                                String seatInputlower;
                                                boolean flag6 = true; //좌석 번호 입력 체크
                                                lp2:
                                                while (flag6) {
                                                    System.out.print("MovieReservation >> ");
                                                    seatInputlower = scan.nextLine();
                                                    seatInput = seatInputlower.toUpperCase();
                                                    input = seatInput.trim();
                                                    input = input.replaceAll("\\s+", " ");
                                                    String[] splited_input = input.split(" ");

                                                    for (int i=0; i<splited_input.length; i++) {
                                                        if (!Pattern.matches("^[A-L][0-9][1-6]", splited_input[i])) {
                                                            System.out.println("!오류 : 잘못된 입력입니다. 다시 입력해주세요.");
                                                            continue lp2;
                                                        }

                                                    }
                                                    Long distinctCount = Stream.of(splited_input).distinct().count();

                                                    if(distinctCount != splited_input.length) {
                                                        System.out.println("..! 중복되는 좌석을 선택했습니다. 다시 입력해주세요.");
                                                        continue;
                                                    }

                                                    if (splited_input.length != num) {
                                                        System.out.println("..! 앞서 입력하신 인원 수 (" + num + "명)에 맞지 않는 좌석을 선택했습니다. 다시 입력해주세요.");
                                                        continue;
                                                    }

                                                    for (int i = 0; i < splited_input.length; i++) {


                                                        char ch = splited_input[i].charAt(0);
                                                        ch = Character.toUpperCase(ch);
                                                        String idxOfNumber = splited_input[i].substring(1, 3);
                                                        int idxOfNumberInt = Integer.parseInt(idxOfNumber) - 1;
                                                        int idxOfLine = (int) ch - 65;
                                                        if (movieList.get(0).getSeat()[idxOfLine * colNum + idxOfNumberInt] == "0") {
                                                            System.out.println("..! 이미 예약되어있는 자리 입니다. 다시 입력해주세요.");
                                                            continue lp2;
                                                        }
                                                        for (int j=0; j<splited_input[i].length(); j++) {
                                                            String[] str = splited_input[i].split("");
                                                            String upper = str[0].toUpperCase();
                                                            str[0] = upper;
                                                            String result = "";
                                                            for(String st : str) {
                                                                result += st;
                                                            }
                                                            splited_input[i] = result;
                                                        }
                                                    }

                                                    flag6 = false;
                                                    Movie movie1 = movieList.get(0);
                                                    movie1.setRseat(splited_input);
                                                    String seatsstr = "";
                                                    for (String str : splited_input) {
                                                        seatsstr += str + " ";
                                                    }

                                                    movieRepository.SaveMovietxt(movie1, seatsstr);
                                                    memberRepository.SaveMovie(member, movie1);
                                                }
                                                System.out.println("영화 예매를 완료했습니다. 처음 화면으로 돌아갑니다.");
                                                new MainMenu(member).ShowMenu();
                                            } else if (input.equals("2")) {
                                                new MainMenu(member).ShowMenu();
                                                flag5 = false;
                                            } else System.out.println("..! 오류 : 잘못된 입력입니다. 다시 입력해주세요.");

                                        }

                                    }
                                    else{
                                        System.out.println("..! 오류 : 예매 가능 인원 수가 아닙니다. 다시 입력해주세요.");
                                        continue;
                                    }
                                }

                            }
                            else{

                                System.out.println("..! 오류 : 선택하신 영화의 상영시간이 아닙니다. 다시 입력해주세요");
                                continue;
                            }
                        }

                    }
                    else{
                        System.out.println("..! 오류 : 선택하신 영화의 상영날짜가 아닙니다. 다시 입력해주세요");
                        continue;
                    }
                }

            }
            else{
                System.out.println("..! 현재 상영하고 있는 영화가 아닙니다. 다시 입력해주세요.\n");
                continue;
            }
        }
    }

    //영화 예매 취소 메소드
    public void CancelReservation(Member member) {
        System.out.print("[예매 취소]\n사용자의 예매 내역입니다.\n취소할 예매 내역을 골라주세요.\n");
        //예매 목록 출력 id.txt
        memberRepository.FindMovie(member);
        MainMenu mainmenu = new MainMenu(member);

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("MovieReservation >> ");
                String choiceInput = scanner.nextLine();
                int choice;

                try {
                    choice = Integer.parseInt(choiceInput);

                    ArrayList<Movie> movieList = member.getMovielist();

                    if (choice > 0 && choice <= movieList.size()) {
                        Movie cancelMovie = movieList.get(choice - 1);
                        System.out.println("[" + choice + "]");
                        System.out.println("영화 제목:" + cancelMovie.getName());
                        System.out.println("상영 날짜:" + cancelMovie.getDate());
                        System.out.println("좌석:" + String.join("/", cancelMovie.getRseat()));

                        System.out.println("예매를 취소하시겠습니까? (y/n): ");
                        while (true) {
                            System.out.print("MovieReservation >> ");
                            String ans = scanner.next();
                            if (ans.equals("y") || ans.equals("Y")) {
                                System.out.println("사용자의 예매 내역이 취소되었습니다. 메인 메뉴로 돌아갑니다.");
                                for (String seat : cancelMovie.getRseat()) {
                                    memberRepository.DeleteMembertxtMovie(cancelMovie, member);
                                    movieRepository.DeleteMovietxt(cancelMovie, seat);
                                }
                                mainmenu.ShowMenu();
                                break;
                            } else if (ans.equals("n") || ans.equals("N")) {
                                System.out.println("메인 메뉴로 돌아갑니다.");
                                mainmenu.ShowMenu();
                                break;
                            } else {
                                System.out.println("..! 오류: 예매 취소를 원하시면 y를 원하지 않으시면 n을 입력해야합니다. 다시 입력해주세요.");
                            }
                        }
                        break;
                    } else {
                        System.out.println("..! 오류: 예매되어 있는 영화 번호를 입력해야 합니다. 다시 입력해주세요");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("..! 오류: 예매되어 있는 영화 번호를 입력해야 합니다. 다시 입력해주세요");
                }
            }
        }

    }

}
