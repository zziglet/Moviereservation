package Admin;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class AdminService {
    public void EditTheaterNum() {
        try {
            // 1.
            // 관리자에게 수정할 관 번호 선택
            System.out.println("수정할 관 번호가 담긴 리스트입니다. \n");
            String userdir = System.getProperty("user.dir") + "./src/user/";
            String path = userdir + "theater.txt";
            BufferedReader membr = new BufferedReader(
                    new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));
            StringBuffer inputBuffer = new StringBuffer();
            ArrayList<String> movieList = new ArrayList<>();
            String line;

            while ((line = membr.readLine()) != null) {
                movieList.add(line);
            }

            membr.close();
            for (int i = 0; i < movieList.size(); i++) {
                System.out.println((i + 1) + ". " + movieList.get(i));
            }
            System.out.println();

            System.out.println("수정할 리스트의 번호를 입력하십시오.");
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();
            // 오류 처리 해줘야함
            int result = Integer.parseInt(input.replaceAll("\\s+", ""));

            System.out.println("수정할 관 번호를 입력하십시오.");
            input = scan.nextLine();
            // 오류 처리 해줘야함
            String repString = input.replaceAll("\\s+", "");
            String[] info = movieList.get(result - 1).split(" ");
            String targetKey = info[0];

            //////////////////////////////////////////////////////////
            //// movie.txt (상영 스케줄)에 수정된 내역 적용

            path = userdir + "movie.txt";
            membr = new BufferedReader(
                    new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));

            while ((line = membr.readLine()) != null) {
                info = line.split(" ");
                if(info[1].equals(targetKey))
                    info[3] = repString;
                    line = "";
                for (String str : info) {
                    line += str + " ";
                }
                inputBuffer.append(line);
                inputBuffer.append("\n");
            }

            membr.close();

            FileOutputStream fileout = new FileOutputStream(path);
            fileout.write(inputBuffer.toString().getBytes());
            fileout.close();
            scan.close();
            // 예매 내역 반영 -> id.txt 안에도 키 값을 부여해야 하나?

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void EditTheaterSeat(String keyString, int seatNum) {

    }

    public void EditTheaterInfoName(String keyString, String moviename) {
        try {
            // 1.
            // 관리자에게 수정할 제목 리스트 제공
            System.out.println("수정할 제목이 담긴 리스트입니다. \n");
            String userdir = System.getProperty("user.dir") + "./src/user/";
            String path = userdir + "movieinfo.txt";
            BufferedReader membr = new BufferedReader(
                    new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));
            StringBuffer inputBuffer = new StringBuffer();
            ArrayList<String> movieList = new ArrayList<>();
            String line;

            while ((line = membr.readLine()) != null) {
                movieList.add(line);
            }

            membr.close();
            for (int i = 0; i < movieList.size(); i++) {
                System.out.println((i + 1) + ". " + movieList.get(i));
            }
            System.out.println();

            System.out.println("수정할 리스트의 번호를 입력하십시오.");
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();
            // 오류 처리 해줘야함
            int result = Integer.parseInt(input.replaceAll("\\s+", ""));

            System.out.println("수정할 제목을 입력하십시오.");
            input = scan.nextLine();
            // 오류 처리 해줘야함
            String repString = input.replaceAll("\\s+", "");
            String[] info = movieList.get(result - 1).split(" ");
            String targetKey = info[0];

            //////////////////////////////////////////////////////////
            //// movie.txt (상영 스케줄)에 수정된 내역 적용

            path = userdir + "movie.txt";
            membr = new BufferedReader(
                    new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));

            while ((line = membr.readLine()) != null) {
                info = line.split(" ");
                if(info[1].equals(targetKey))
                    info[5] = repString;
                    line = "";
                for (String str : info) {
                    line += str + " ";
                }
                inputBuffer.append(line);
                inputBuffer.append("\n");
            }

            membr.close();

            FileOutputStream fileout = new FileOutputStream(path);
            fileout.write(inputBuffer.toString().getBytes());
            fileout.close();
            scan.close();
            // 예매 내역 반영 -> id.txt 안에도 키 값을 부여해야 하나?

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void EditTheaterRuntime(String keyString, int runningTime) {

    }
}
