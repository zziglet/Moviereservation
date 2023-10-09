package movie;

public interface MovieService {
    void register(Movie movie);

    Movie findMovie(String movieName);
}
