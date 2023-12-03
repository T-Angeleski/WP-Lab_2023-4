package mk.ukim.finki.mk.lab.repository.jpa;

import mk.ukim.finki.mk.lab.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaMovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByTitleContainingOrSummaryContaining(String title, String summary);

//    void saveMovieWithProduction(Movie movie, Long productionId);
}
