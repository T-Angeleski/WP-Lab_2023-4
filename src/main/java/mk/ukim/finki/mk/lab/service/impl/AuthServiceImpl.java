package mk.ukim.finki.mk.lab.service.impl;

import mk.ukim.finki.mk.lab.model.User;
import mk.ukim.finki.mk.lab.model.UserFullname;
import mk.ukim.finki.mk.lab.model.exceptions.InvalidCredentialsException;
import mk.ukim.finki.mk.lab.model.exceptions.PasswordDoesNotMatchException;
import mk.ukim.finki.mk.lab.repository.jpa.JpaUserRepository;
import mk.ukim.finki.mk.lab.service.AuthService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AuthServiceImpl implements AuthService {

    private final JpaUserRepository userRepository;

    public AuthServiceImpl(JpaUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private boolean checkCredentials(String username, String password) {
        return username == null || password == null || username.isEmpty() || password.isEmpty();
    }

    @Override
    public User login(String username, String password) {
        if (checkCredentials(username, password))
            throw new IllegalArgumentException();
        return userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(InvalidCredentialsException::new);
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, LocalDate dateOfBirth) {
        if (checkCredentials(username, password))
            throw new IllegalArgumentException();

        if (!password.equals(repeatPassword))
            throw new PasswordDoesNotMatchException(password, repeatPassword);
        UserFullname fn = new UserFullname();
        fn.setName(name);
        fn.setSurname(surname);
        User user = new User(username, fn, password, dateOfBirth);
        return userRepository.save(user);
    }
}
