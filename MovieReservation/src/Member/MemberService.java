package Member;

import Movie.Movie;
import Movie.MovieRepository;
import Menu.MainMenu;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;


public class MemberService {

    MemberRepository memberRepository = new MemberRepository();
    MovieRepository movieRepository = new MovieRepository();
    Scanner scan = new Scanner(System.in);
    //영화 예매를 위한 메소드 (인자를 뭘 넘겨야할지 아직 감이 안옴)
    public void CreateReservation(Member member){
        ArrayList<Movie> movieList = movieRepository.find();
        Set<String> movieNames = new HashSet<String>();
        Set<String> movieDates = new HashSet<String>();
        Set<String> movieStarts = new HashSet<String>();

        movieNames.add(movieList.get(0).getName());
        String movieNameBeforeThis = movieList.get(0).getName();
        for (int i = 0; i < movieList.size(); i++){
            if(movieList.get(i).getName().equals(movieNameBeforeThis)) continue;
            movieNames.add(movieList.get(i).getName());
            movieNameBeforeThis = movieList.get(i).getName();
        }

        System.out.println("[예매 / 영화선택] 현재 상영되고 있는 영화입니다. 영화 제목을 입력해주세요.\n");

        Iterator<String> nameIter = movieNames.iterator();
        while(nameIter.hasNext())
            System.out.println(nameIter.next());

        String movieNameInput;
        boolean flag1 = false; // 영화 제목 체크
        while(!flag1) {
            System.out.print("MovieReservation >> ");
            movieNameInput = scan.nextLine();
            if (!Pattern.matches("^[가-힣a-zA-Z0-9 ,:.!?()~-]+$",movieNameInput)) {
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
                boolean flag2 = false;
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
                        boolean flag3 = false;
                        while (!flag3) {
                            System.out.println();
                            System.out.print("MovieReservation >> ");
                            movieStartInput = scan.nextLine();

                            if (!Pattern.matches("^([1-9]|[01][0-9]|2[0-3]):([0-5][0-9])$", movieStartInput)) {
                                System.out.println("..! 오류 : 잘못된 입력입니다. 다시 입력해주세요.");
                                continue;
                            }

                            System.out.println(movieStartInput);
                            ArrayList<Movie> mov = member.getMovielist();
                            int[] starttimes = new int[mov.size()];
                            int[] endtimes = new int[mov.size()];
                            int[] runningtimes = new int[mov.size()];
                            for(int i = 0; i < mov.size()-1; i++){
                                starttimes[i] =Integer.parseInt(mov.get(i).getStart().replaceAll(":",""));
                                endtimes[i]=Integer.parseInt(mov.get(i).getEnd().replaceAll(":",""));
                                runningtimes[i] = endtimes[i] - starttimes[i];
                            }
                            for(int i=0;i<mov.size()-1;i++){
                                System.out.println(starttimes[i]);
                                System.out.println(endtimes[i]);
                                System.out.println(runningtimes[i]);
                            }

                            if (false/*요구사항 반영 조건*/) {
                                System.out.println("동일한 시간대에 예매한 영화가 있습니다. 다시 입력해주세요.");
                                continue;
                            }

                            if (movieStarts.contains(movieStartInput)) {
                                flag3 = true;
                                for (int i = 0; i < movieList.size(); i++) {
                                    if (!movieList.get(i).getStart().equals(movieStartInput)) {
                                        movieList.remove(i);
                                        i--;
                                    }
                                }
                                System.out.println("[예매 / 인원선택] 영화예매를 진행하실 인원 수를 입력해주세요.\n");

                                String movieNumber;
                                boolean flag4 = true;
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
                                    if (num >= 1 && num <= 5) {
                                        flag4 = false;
                                        Movie movie = movieList.get(0);
                                        String[] reservedSeats = movie.getRseat();
                                        System.out.println("[예매 / 좌석선택] 좌석을 선택해주세요.\n");
                                        for (int i = 0; i < 12; i++) {
                                            int line = 65 + i;
                                            char ch = (char) line;
                                            System.out.print(ch + "|\t");
                                            for (int j = 0; j < 6; j++) {
                                                if (j == 5) System.out.print(reservedSeats[6 * i + j]);
                                                else System.out.print(reservedSeats[6 * i + j] + "|");
                                            }
                                            if (i == 1 || i == 5 || i == 11) System.out.println("\n");
                                            else System.out.println();
                                        }
                                        String[] seats = movie.getSeat();
                                        int seatsNum = 72;
                                        int reservedSeatsNum = 0;
                                        for (int i = 0; i < seats.length; i++) {
                                            if (seats[i].equals("0")) reservedSeatsNum++;
                                        }
                                        System.out.println("첫 열에 표시된 영대문자는 각 행의 좌석 라인입니다.");
                                        System.out.println("00으로 표시된 좌석은 이미 예약된 좌석입니다. 전여 좌석 수는 " + (seatsNum - reservedSeatsNum) + "/" + seatsNum + "석입니다.");
                                        System.out.println("다음 중 원하는 메뉴를 선택하세요.\n");
                                        System.out.println("1. 좌석 선택");
                                        System.out.println("2. 메인 메뉴로 돌아가기.\n");

                                        boolean flag5 = true;
                                        while (flag5) {
                                            System.out.print("MovieReservation >> ");
                                            String input = scan.nextLine();

                                            if (input.equals("1")) {
                                                flag5=false;
                                                System.out.println("[예매 / 좌석선택] 예매하려는 좌석번호를 입력해주세요.");
                                                String seatInput;
                                                boolean flag6 = true;
                                                lp:
                                                while (flag6) {
                                                    System.out.print("MovieReservation >> ");
                                                    seatInput = scan.nextLine();
                                                    String[] splited_input = seatInput.split("[\\s\\t\\v]+");
                                                    for (String el : splited_input) {
                                                        if (!Pattern.matches("^[A-Z][0-9][0-9]", el)) {
                                                            System.out.println("!오류 : 잘못된 입력입니다. 다시 입력해주세요.");
                                                            continue lp;
                                                        }
                                                    }
                                                    if (splited_input.length != num) {
                                                        System.out.println("..! 앞서 입력하신 인원 수 (" + num + "명)에 맞지 않는 좌석을 선택했습니다. 다시 입력해주세요.");
                                                        continue;
                                                    }
                                                    for (int i = 0; i<splited_input.length;i++){
                                                        char ch = splited_input[i].charAt(0);
                                                        String idxOfNumber = splited_input[i].substring(1,3);
                                                        int idxOfNumberInt = Integer.parseInt(idxOfNumber)-1;
                                                        int idxOfLine = (int)ch - 65;
                                                        if(movieList.get(0).getSeat()[idxOfLine*6+idxOfNumberInt]=="0"){
                                                            System.out.println("..! 이미 예약되어있는 자리 입니다. 다시 입력해주세요.");
                                                            continue lp;
                                                        }
                                                    }
                                                    flag6 = false;
                                                    Movie movie1 = movieList.get(0);
                                                    movie1.setRseat(splited_input);
                                                    /*String[] appendRSeats = movieList.get(0).getRseat();
                                                    String[] appendSeats = movieList.get(0).getSeat();
                                                    for (int i = 0; i<splited_input.length;i++){
                                                        char ch = splited_input[i].charAt(0);
                                                        String idxOfNumber = splited_input[i].substring(1,3);
                                                        int idxOfNumberInt = Integer.parseInt(idxOfNumber)-1;
                                                        int idxOfLine = (int)ch - 65;
                                                        appendRSeats[idxOfLine*6+idxOfNumberInt] = "00";
                                                        appendSeats[idxOfLine*6+idxOfNumberInt] = "0";
                                                    }
                                                    movie1.setRseat(appendRSeats);
                                                    movie1.setSeat(appendSeats);
                                                    */
                                                    String seatsstr = "";
                                                    for(String str : splited_input){
                                                        seatsstr += str+ " ";
                                                    }


                                                    movieRepository.SaveMovietxt(movie1,seatsstr);
                                                    memberRepository.SaveMovie(member,movie1);

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
                                        System.out.println("..! 오류 : 최대 예매 인원 수를 초과했습니다. 다시 입력해주세요.");
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
        MainMenu mainmenu=new MainMenu(member);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("MovieReservation >> ");
            int choice = scanner.nextInt();
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
        }

    }

}
