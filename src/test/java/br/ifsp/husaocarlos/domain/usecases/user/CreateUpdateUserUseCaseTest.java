package br.ifsp.husaocarlos.domain.usecases.user;

import br.ifsp.husaocarlos.application.repository.InMemoryAppointmentDAO;
import br.ifsp.husaocarlos.application.repository.InMemoryUserDAO;
import br.ifsp.husaocarlos.domain.entities.Roles;
import br.ifsp.husaocarlos.domain.entities.User;
import br.ifsp.husaocarlos.domain.usecases.appointment.AppointmentDAO;
import org.junit.jupiter.api.Test;

import javax.security.enterprise.credential.Password;

import static org.junit.jupiter.api.Assertions.*;

class CreateUpdateUserUseCaseTest {



    @Test
    void createUser() {
        UserDAO userDAO = new InMemoryUserDAO();

        // Create User
        Password pass = new Password("1234");
        User user = new User(
                "email",
                "cpf",
                "name",
                pass,
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

        UserDAO userDAO = new InMemoryUserDAO();

        // Create User
        Password pass = new Password("1234");
        User user = new User(
                "email",
                "cpf",
                "name",
                pass,
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