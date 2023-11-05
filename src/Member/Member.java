package Member;

import Movie.Movie;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.regex.Pattern;

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
        this.name = "";
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

    public ArrayList<Movie> getMovielist() {
        // txt 파일을 읽어와서 movieList로 반환
        this.movieList = new ArrayList<>();
        int idx = 1;
        try {
            //movie.txt 불러오기
            String userdir = System.getProperty("user.dir") + "./src/user/";
            String path = userdir + this.id + ".txt";
            BufferedReader membr = new BufferedReader
                    (new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));

            //한줄씩 읽기
            String line;
            membr.readLine(); // 첫 줄에는 계정 정보가 있으므로 미리 읽어들임
            
            while ((line = membr.readLine()) != null) {
                String[] info = line.split("\\s+");

                for(int i=info.length-1 ; i>0; i--) {
                    if(Pattern.matches("^[\\d]{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$",info[i])){
                        idx = i;
                        break;
                    }
                }

                for(int i=0; i<info.length; ) {

                    String[] movienamelist = new String[idx-1];
                    for(int j=1; j<idx; j++) {
                        movienamelist[j-1] = info[j];
                    }

                    //movie 인자
                    String movietheater = info[0];
                    String moviename = String.join(" ", movienamelist);
                    String moviedate = info[idx];
                    String moviestarttime = info[idx+1];
                    String movieendtime = info[idx+2];
                    String[] movierseat = new String[info.length - (idx + 4)];
                    String[] movieseat = {""};
                    int cnt = 0;
                    for (int j = idx + 4; j < info.length; j++) {
                        movierseat[cnt] = info[j];
                        cnt++;
                    }

                    Movie movie = new Movie(movietheater, moviename, moviedate, moviestarttime, movieendtime, movierseat, movieseat);
                    movieList.add(movie);

                    break;
                    
                    }
                
                }

            membr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        return movieList;

    }

}
