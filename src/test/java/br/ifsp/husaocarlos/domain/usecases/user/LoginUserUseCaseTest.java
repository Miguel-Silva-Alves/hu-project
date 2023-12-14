package br.ifsp.husaocarlos.domain.usecases.user;

import br.ifsp.husaocarlos.application.repository.InMemoryUserDAO;
import br.ifsp.husaocarlos.domain.entities.Roles;
import br.ifsp.husaocarlos.domain.entities.User;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class LoginUserUseCaseTest {

    @Test
    void loginCpf() {
        UserDAO userDAO = new InMemoryUserDAO();

        // Create User
        User user = new User(
                "email",
                "94439761020",
                "name",
                "1234",
                "adress",
                "registration",
                Roles.Receptionist
        );
        System.out.println(user);
        CreateUpdateUserUseCase createUpdateUserUseCase = new CreateUpdateUserUseCase(userDAO);
        boolean exec = createUpdateUserUseCase.createUser(user);
        assertEquals(true, exec);

        LoginUserUseCase loginUserUseCase = new LoginUserUseCase(userDAO);
        Optional<User> optional = loginUserUseCase.loginCpf("94439761020", "1234");
        assertEquals(true, optional.isPresent());
    }
}