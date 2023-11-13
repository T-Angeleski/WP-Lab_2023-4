package mk.ukim.finki.mk.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.mk.lab.model.Movie;
import mk.ukim.finki.mk.lab.model.Production;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class MovieRepository {
    public static List<Movie> movies = null;
    private final ProductionRepository productionRepository;
    private final Random random = new Random();

    public MovieRepository(ProductionRepository productionRepository) {
        this.productionRepository = productionRepository;
    }

    @PostConstruct
    public void init() {
        // Generate 10 movies with random rating
        movies = new ArrayList<>();
        List<Production> prods = productionRepository.findAll();
        IntStream.range(0, 10)
                .forEach(i -> {
                    String title = String.format("Movie name #%d", i+1);
                    String summary = String.format("Movie #%d summary", i+1);

                    double rating = 1 + random.nextDouble() * 9;
                    // Round down to 2 decimal
                    rating = rating*100;
                    rating = Math.round(rating);
                    rating = rating /100;

                    Movie m = new Movie(title, summary, rating);
                    m.setProduction(prods.get(i%5));
                    movies.add(m);
                });
    }

    public List<Movie> findAll() {
        return movies;
    }

    public Optional<Movie> findById(Long id) {
        return movies.stream()
                .filter(m -> Objects.equals(m.getId(), id))
                .findFirst();
    }

    public List<Movie> searchMovies(String text) {
        return movies.stream()
                .filter(movie -> movie.getTitle().contains(text) ||
                        movie.getSummary().contains(text))
                .collect(Collectors.toList());
    }

    public Optional<Movie> save(String movieTitle, String summary, double rating, Production production) {
        if (production == null) {
            throw new IllegalArgumentException();
        }
        Movie movie = new Movie(movieTitle, summary, rating);
        movie.setProduction(production);
        movie.setId(production.getId());

        movies.removeIf(m -> m.getTitle().equals(movie.getTitle()));
        movies.add(movie);

        return Optional.of(movie);
    }

}
