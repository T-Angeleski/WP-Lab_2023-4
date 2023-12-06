package mk.ukim.finki.mk.lab.service.impl;

import mk.ukim.finki.mk.lab.model.Movie;
import mk.ukim.finki.mk.lab.model.Production;
import mk.ukim.finki.mk.lab.model.exceptions.ProductionNotFoundException;
import mk.ukim.finki.mk.lab.repository.jpa.JpaMovieRepository;
import mk.ukim.finki.mk.lab.repository.jpa.JpaProductionRepository;
import mk.ukim.finki.mk.lab.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private final JpaMovieRepository movieRepository;
    private final JpaProductionRepository productionRepository;

    public MovieServiceImpl(JpaMovieRepository movieRepository, JpaProductionRepository productionRepository) {
        this.movieRepository = movieRepository;
        this.productionRepository = productionRepository;
    }


    @Override
    public List<Movie> listAll() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> searchMovies(String text) {
        return movieRepository.findByTitleContainingOrSummaryContaining(text, text);
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
        return movieRepository.findById(id);
    }

    @Override
    public Optional<Movie> save(String title, String summary, Double rating, Long productionId, Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElse(new Movie());
        movie.setTitle(title);
        movie.setSummary(summary);
        movie.setRating(rating);
        movie.setProduction(productionRepository.findById(productionId).get());
        movieRepository.save(movie);
        return Optional.of(movie);
    }

    @Override
    public void deleteById(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public void findMovieAndSetNewRating(String title, Double rating) {
        //TODO
//        movieRepository.setRatingForMovie(title, rating);
    }
}
