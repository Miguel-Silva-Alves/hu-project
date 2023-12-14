package br.ifsp.husaocarlos.domain.usecases.user;
import br.ifsp.husaocarlos.application.persistence.MySqlUserDAO;
import br.ifsp.husaocarlos.application.repository.InMemoryUserDAO;
import br.ifsp.husaocarlos.domain.entities.Roles;
import br.ifsp.husaocarlos.domain.entities.User;
import org.junit.jupiter.api.Test;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CreateUpdateUserUseCaseTest {



    @Test
    void createUser() {
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

        FindUserUseCase findUserUseCase = new FindUserUseCase(userDAO);
        Optional<User> optional = findUserUseCase.getUserByCPF("94439761020");
        assertEquals(true, optional.isPresent());

        System.out.println(optional.get());

    }

    @Test
    void updateUser() {

        UserDAO userDAO = new MySqlUserDAO();

        // Create User
        User user = new User(
                "email",
                "cpf",
                "name",
                "1234",
                "adress",
                "registration",
                Roles.Receptionist
        );
        CreateUpdateUserUseCase createUpdateUserUseCase = new CreateUpdateUserUseCase(userDAO);
        boolean exec = createUpdateUserUseCase.createUser(user);
        assertEquals(exec, true);

        user.setName("Joãozinho");
        exec = createUpdateUserUseCase.updateUser(user);
        assertEquals(exec, true);

    }
}