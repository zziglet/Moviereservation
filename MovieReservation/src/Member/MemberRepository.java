package Member;

import Movie.Movie;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MemberRepository {

    public MemberRepository() {
    }

    public void SaveMember(String name, String id, String pw) {
        /*
            회원가입 시에 id.txt 파일에 회원 정보 저장 -> menu에서 id,pw,이름 입력받고 각 변수들은 넘김
            이 함수를 통해 생성되는 파일은 항상 새로운 파일
         */
        try {
            String userdir = System.getProperty("user.dir") + "/MovieReservation/src/user/";
            System.out.println(userdir);
            String path = userdir + id + ".txt";
            File file = new File(path);
            FileWriter fw = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fw);
            String str = name + " " + id + " " + pw;
            writer.write(str + System.lineSeparator());
            writer.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    public void SaveMovie(Member member, Movie movie) {
        try {
            String path = "src/user/" + member.getId() + ".txt";
            File file = new File(path);
            if (!file.exists()) {
                System.out.println("Cannot find file");
                return;
            }
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter writer = new BufferedWriter(fw);
            String str = movie.getTheater() + " " + movie.getName() + " " + movie.getDate()
                    + " " + movie.getStart() + " " + movie.getEnd() + " " + movie.getRseat().length + " ";
            for (String i : movie.getRseat()) {
                str += i + " ";
            }
            writer.write(str + System.lineSeparator());
            writer.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    public void DeleteMembertxtMovie(Movie movie, Member member) {
        String targetstr = movie.getTheater() + " " + movie.getName() + " " + movie.getDate() + " "
                + movie.getStart() + " " + movie.getEnd() + " " + movie.getRseat().length + " ";
        for (String str : movie.getRseat()) {
            targetstr += str + " ";
        }
        targetstr = targetstr.trim();

        ArrayList<String> memtxt = new ArrayList<>();
        try {
            String userdir = System.getProperty("user.dir") + "/MovieReservation/src/user/";

            String path = userdir + member.getId() + ".txt";
            BufferedReader membr = new BufferedReader
                    (new InputStreamReader
                            (new FileInputStream(path), StandardCharsets.UTF_8));

            String line;

            while ((line = membr.readLine()) != null) {
                line = line.trim();
                memtxt.add(line);
            }


            memtxt.remove(targetstr);

            File file = new File(path);
            if (!file.exists()) {
                System.out.println("Cannot find file");
                return;
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fw);

            for (String str : memtxt) {
                writer.write(str + System.lineSeparator());
            }
            writer.close();

        } catch (IOException e) {
            e.getStackTrace();
        }

    }

    public void FindMovie(Member member) {
        ArrayList<Movie> mov = member.getMovielist();
        for (int i = 0; i < mov.size(); i++) {
            Movie movies = mov.get(i);
            System.out.println("====================================");
            System.out.println("[" + (i + 1) + "]");
            System.out.println(movies.getName());
            System.out.println(movies.getDate());
            String seats = "좌석: ";
            for (int j = 0; j < movies.getRseat().length; j++) {
                seats += movies.getRseat()[j];
                seats += " / ";
            }
            seats = seats.substring(0, seats.length() - 2);
            System.out.println(seats);
        }
    }
}
