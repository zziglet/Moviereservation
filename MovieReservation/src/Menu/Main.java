//package Menu;
//
//import Movie.Movie;
//import Movie.MovieRepository;
//
//public class Main {
//    public static void main(String[] args) {
//        // TODO Auto-generated method stub
//        //test
//        MovieRepository m=new MovieRepository();
//        ArrayList<Movie> list=m.find();
//
//        m.DeleteMovietxt(list.get(0), "A01");
//        m.DeleteMovietxt(list.get(0), "A02");
//        m.DeleteMovietxt(list.get(0), "A03");
//        m.DeleteMovietxt(list.get(1), "A01");
//        m.DeleteMovietxt(list.get(1), "A02");
//        m.DeleteMovietxt(list.get(1), "A03");
//
//
//        SignUpInMenu SignUpInMenu = new SignUpInMenu();
//        SignUpInMenu.Start();
//    }
//}

package Menu;

import Movie.Movie;
import Movie.MovieRepository;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //test
        MovieRepository m= new MovieRepository();
        ArrayList<Movie> list=m.find();

//        m.SaveMovietxt(list.get(0), "A01");
        m.DeleteMovietxt(list.get(0), "A01");
        m.DeleteMovietxt(list.get(0), "A02");
        m.DeleteMovietxt(list.get(5), "A01");
//        m.DeleteMovietxt(list.get(1), "A01");
//        m.DeleteMovietxt(list.get(1), "A02");
//        m.DeleteMovietxt(list.get(1), "A03");




        SignUpInMenu SignUpInMenu = new SignUpInMenu();
        SignUpInMenu.Start();
    }
}
