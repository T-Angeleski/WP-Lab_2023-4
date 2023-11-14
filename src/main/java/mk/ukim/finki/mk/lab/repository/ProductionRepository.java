package mk.ukim.finki.mk.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.mk.lab.model.Production;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductionRepository {
    public static List<Production> productions = null;

    @PostConstruct
    public void init() {
        productions = new ArrayList<>();
        productions.add(new Production("HBO", "USA", "Some address"));
        productions.add(new Production("Netflix", "USA", "Some address2"));
        productions.add(new Production("FOX", "Germany", "Berlin"));
        productions.add(new Production("Sitel", "MKD", "Skopje"));
        productions.add(new Production("MRT", "MKD", "Veles"));
    }

    public List<Production> findAll() {
        return productions;
    }
    public Optional<Production> findById(Long id) {
        return productions
                .stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

}
