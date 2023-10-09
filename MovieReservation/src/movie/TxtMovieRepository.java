package movie;

import java.util.HashMap;
import java.util.Map;

public class TxtMovieRepository implements MovieRepository{

    private static Map<String ,Movie> movies = new HashMap<>();

    @Override
    public void save(Movie movie) {
        movies.put(movie.getName(),movie);
    }

    @Override
    public Movie findByName(String movieName) {
        return movies.get(movieName);
    }
}
