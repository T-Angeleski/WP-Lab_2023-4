package mk.ukim.finki.mk.lab.service.impl;

import mk.ukim.finki.mk.lab.model.Movie;
import mk.ukim.finki.mk.lab.repository.MovieRepository;
import mk.ukim.finki.mk.lab.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
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
}
