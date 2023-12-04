package br.ifsp.husaocarlos.domain.usecases.user;
import br.ifsp.husaocarlos.application.repository.MySqlUserDAO;
import br.ifsp.husaocarlos.domain.entities.Roles;
import br.ifsp.husaocarlos.domain.entities.User;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class CreateUpdateUserUseCaseTest {



    @Test
    void createUser() {
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