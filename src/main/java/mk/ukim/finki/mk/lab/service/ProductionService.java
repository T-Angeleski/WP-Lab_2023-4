package mk.ukim.finki.mk.lab.service;

import mk.ukim.finki.mk.lab.model.Production;

import java.util.List;
import java.util.Optional;

public interface ProductionService {

    List<Production> findAll();

    Optional<Production> findById(Long id);
}
