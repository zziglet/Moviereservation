package member;

import movie.Movie;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Member {
    private String id;
    private String pw;
    private String name;

    private ArrayList<Movie> movieList;

    public Member(String id, String pw, String name) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.movieList = new ArrayList<>();
    }

    public Member() {
        this.id = "";
        this.pw = "";
        this.name= "";
        this.movieList = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Movie> getMovielist(){
        // txt 파일을 읽어와서 movieList로 반환
        boolean isInteger;
        try {
            BufferedReader membr = new BufferedReader
                    (new InputStreamReader
                            (new FileInputStream("./src/user/"+this.id+".txt"), StandardCharsets.UTF_8));

            String line;
            membr.readLine(); // -> 첫 줄에는 계정 정보가 있으므로 넘김
            while ((line = membr.readLine()) != null) {
                String[] info = line.split(" ");
                for(int i=1 ; i<info.length; i++) {
                    String[] info2 = info[i].split("");
                    try{
                        Integer.parseInt(info2[0]);
                        isInteger = true;
                    }catch(NumberFormatException e) {
                        isInteger = false;
                    }

                    if(isInteger) {
                        String[] movienamelist = new String[i-1];
                        for(int j=1; j<i; j++) {
                            movienamelist[j-1] = info[j];
                        }

                        String movietheater = info[0];
                        String moviename = String.join(" ", movienamelist);
                        String moviedate = info[i];
                        String moviestarttime = info[i+1];
                        String movieendtime = info[i+2];
                        String[] movierseat = new String[info.length - (i+3)];
                        String[] movieseat = {""};
                        int cnt = 0;
                        for(int j=i+3; j<info.length; j++) {
                            movierseat[cnt] = info[j];
                            cnt++;
                        }

                        Movie movie = new Movie(movietheater, moviename, moviedate, moviestarttime, movieendtime, movierseat,movieseat);
                        System.out.printf("%s %s %s %s %s", movie.getTheater(), movie.getName(),
                                movie.getDate(), movie.getStart(), movie.getEnd());
                        System.out.println();
                        System.out.println("===================================");
                        for(String st : movie.getRseat())
                        {
                            System.out.printf(" %s", st);
                        }
                        System.out.println();


                        /*구현 도중 확인 출력 문구인데 나중에 없애도 될까요
                         * System.out.printf("%s %s %s %s %s", movie.getTheater(), movie.getName(),
                         * movie.getDate(), movie.getStart(), movie.getEnd()); for(String st :
                         * movie.getRseat()) { System.out.printf(" %s", st); } System.out.println();
                         */

                        movieList.add(movie);
                        break;
                    }
                }
            }

        } catch(IOException e) {
            e.printStackTrace();
        }

        return movieList;

    }


}
