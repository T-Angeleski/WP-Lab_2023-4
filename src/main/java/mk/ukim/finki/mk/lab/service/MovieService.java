package mk.ukim.finki.mk.lab.service;

import mk.ukim.finki.mk.lab.model.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MovieService {
    List<Movie> listAll();
    List<Movie> searchMovies(String text);
    List<Movie> searchByTitleAndRating(String title, double rating);
    Movie findById(Long id);
}
