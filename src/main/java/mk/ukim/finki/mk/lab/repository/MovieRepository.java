package mk.ukim.finki.mk.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.mk.lab.model.Movie;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class MovieRepository {
    public static List<Movie> movies = null;
    private Random random = new Random();

    @PostConstruct
    public void init() {
        // Generate 10 movies with random rating
        movies = new ArrayList<>();
        IntStream.range(0, 10)
                .forEach(i -> {
                    String title = String.format("Movie name #%d", i+1);
                    String summary = String.format("Movie #%d summary", i+1);

                    double rating = 1 + random.nextDouble() * 4;
                    // Round down to 2 decimal
                    rating = rating*100;
                    rating = Math.round(rating);
                    rating = rating /100;

                    movies.add(new Movie(title, summary, rating));
                });
    }

    public List<Movie> findAll() {
        return movies;
    }

    public List<Movie> searchMovies(String text) {
        return movies.stream()
                .filter(movie -> movie.getTitle().contains(text) ||
                        movie.getSummary().contains(text))
                .collect(Collectors.toList());
    }


}
