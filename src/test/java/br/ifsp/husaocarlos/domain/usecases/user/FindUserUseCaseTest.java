package br.ifsp.husaocarlos.domain.usecases.user;

import br.ifsp.husaocarlos.application.repository.InMemoryUserDAO;
import br.ifsp.husaocarlos.domain.entities.Roles;
import br.ifsp.husaocarlos.domain.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class FindUserUseCaseTest {

    UserDAO userDAO;
    @BeforeEach
    void setUp() {
        userDAO = new InMemoryUserDAO();
        User user = new User("teste@gmail.com",
                "99998964059",
                "miguel",
                "password",
                "endereco",
                "idk",
                Roles.Professor);
        userDAO.save(user);
    }

    @Test
    void getUserByCPF() {
        FindUserUseCase findUserUseCase = new FindUserUseCase(userDAO);
        Optional<User> userOptional = findUserUseCase.getUserByCPF("99998964059");
        assertEquals(true, userOptional.isPresent());

    }
}