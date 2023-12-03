package mk.ukim.finki.mk.lab.repository.jpa;

import mk.ukim.finki.mk.lab.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
}
