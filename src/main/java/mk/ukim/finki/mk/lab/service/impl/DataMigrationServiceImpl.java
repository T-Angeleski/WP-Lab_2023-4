package mk.ukim.finki.mk.lab.service.impl;

import mk.ukim.finki.mk.lab.model.Movie;
import mk.ukim.finki.mk.lab.model.Production;
import mk.ukim.finki.mk.lab.repository.inMemory.MovieRepository;
import mk.ukim.finki.mk.lab.repository.inMemory.ProductionRepository;
import mk.ukim.finki.mk.lab.repository.jpa.JpaMovieRepository;
import mk.ukim.finki.mk.lab.repository.jpa.JpaProductionRepository;
import mk.ukim.finki.mk.lab.service.DataMigrationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataMigrationServiceImpl implements DataMigrationService {
    private final MovieRepository inMemoryMovieRepository;
    private final ProductionRepository inMemoryProductionRepository;
    private final JpaMovieRepository movieRepository;
    private final JpaProductionRepository productionRepository;

    public DataMigrationServiceImpl(MovieRepository inMemoryMovieRepository, ProductionRepository inMemoryProductionRepository, JpaMovieRepository movieRepository, JpaProductionRepository productionRepository) {
        this.inMemoryMovieRepository = inMemoryMovieRepository;
        this.inMemoryProductionRepository = inMemoryProductionRepository;
        this.movieRepository = movieRepository;
        this.productionRepository = productionRepository;
    }


    @Override
    public void migrateData() {
        List<Movie> movies = inMemoryMovieRepository.findAll();
        List<Production> productions = inMemoryProductionRepository.findAll();

        productionRepository.saveAll(productions);
        movieRepository.saveAll(movies);

    }
}
