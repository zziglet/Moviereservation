import movie.Movie;
import movie.MovieService;
import movie.MovieServiceImpl;

public class MoiveApp {
    public static void main(String[] args) {
        MovieService movieService = new MovieServiceImpl();
        Movie movie = new Movie("오펜하이머","2023-01-01","09:00");
        movieService.register(movie);
        Movie findMovie = movieService.findMovie("오펜하이머");
        System.out.println("findMovie = " + findMovie.getName());
        System.out.println("movie = " + movie.getName());
    }
}
