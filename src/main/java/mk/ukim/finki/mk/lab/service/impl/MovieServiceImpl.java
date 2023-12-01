package mk.ukim.finki.mk.lab.service.impl;

import mk.ukim.finki.mk.lab.model.Movie;
import mk.ukim.finki.mk.lab.model.Production;
import mk.ukim.finki.mk.lab.model.exceptions.MovieNotFoundException;
import mk.ukim.finki.mk.lab.model.exceptions.ProductionNotFoundException;
import mk.ukim.finki.mk.lab.repository.MovieRepository;
import mk.ukim.finki.mk.lab.repository.ProductionRepository;
import mk.ukim.finki.mk.lab.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final ProductionRepository productionRepository;

    public MovieServiceImpl(MovieRepository movieRepository, ProductionRepository productionRepository) {
        this.movieRepository = movieRepository;
        this.productionRepository = productionRepository;
    }

    @Override
    public List<Movie> listAll() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> searchMovies(String text) {
        return movieRepository.searchMovies(text);
    }

    @Override
    public List<Movie> searchByTitleAndRating(String title, double rating) {
        if (title == null || rating == 0) {
            return new ArrayList<>();
        }

        return movieRepository.findAll()
                .stream()
                .filter(movie -> movie.getTitle().contains(title) &&
                        movie.getRating() >= rating)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Movie> findById(Long id) {
        Optional<Movie> movie = movieRepository.findById(id);

        return movie;
    }

    @Override
    public Optional<Movie> save(String title, String summary, Double rating, Long productionId, Long movieId) {
        Production production = productionRepository.findById(productionId)
                .orElseThrow(() -> new ProductionNotFoundException(productionId));
        return movieRepository.save(title, summary, rating, production, movieId);
    }

    @Override
    public void deleteById(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public void findMovieAndSetNewRating(String title, Double rating) {
        movieRepository.setRatingForMovie(title, rating);
    }
}
