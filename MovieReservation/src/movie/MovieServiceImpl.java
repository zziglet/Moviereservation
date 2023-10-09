package movie;

public class MovieServiceImpl implements MovieService{

    private final MovieRepository movieRepository = new TxtMovieRepository();

    @Override
    public void register(Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    public Movie findMovie(String movieName) {
        return movieRepository.findByName(movieName);
    }
}
