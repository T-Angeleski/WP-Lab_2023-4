package mk.ukim.finki.mk.lab.service;

import mk.ukim.finki.mk.lab.model.Movie;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<Movie> listAll();

    List<Movie> searchMovies(String text);

    List<Movie> searchByTitleAndRating(String title, double rating);

    Optional<Movie> findById(Long id);

    Optional<Movie> save(String title, String summary, Double rating, Long productionId, Long movieId);

    void deleteById(Long id);
}
