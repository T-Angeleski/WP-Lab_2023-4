package mk.ukim.finki.mk.lab.repository.jpa;

import mk.ukim.finki.mk.lab.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<User, Long> {
}
