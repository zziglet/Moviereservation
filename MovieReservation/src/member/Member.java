package member;

import movie.Movie;
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
    }

    public Member() {
        this.id = "";
        this.pw = "";
        this.name= "";
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
        return this.movieList;
    }

    public void setMovielist(ArrayList<Movie> movielist) {
        this.movieList = movielist;
    }
}
