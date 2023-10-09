package movie;

public interface MovieRepository {
    void save(Movie movie);

    Movie findByName(String movieName);
}
