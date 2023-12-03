package mk.ukim.finki.mk.lab.service.impl;

import mk.ukim.finki.mk.lab.model.Production;
import mk.ukim.finki.mk.lab.model.exceptions.ProductionNotFoundException;
import mk.ukim.finki.mk.lab.repository.jpa.JpaProductionRepository;
import mk.ukim.finki.mk.lab.service.ProductionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductionServiceImpl implements ProductionService {

    private final JpaProductionRepository productionRepository;

    public ProductionServiceImpl(JpaProductionRepository productionRepository) {
        this.productionRepository = productionRepository;
    }


    @Override
    public List<Production> findAll() {
        return productionRepository.findAll();
    }

    @Override
    public Optional<Production> findById(Long id) {
        Production production = productionRepository.findById(id)
                .orElseThrow(() -> new ProductionNotFoundException(id));

        return Optional.of(production);
    }
}
