package br.ifsp.husaocarlos.domain.usecases.user;

import br.ifsp.husaocarlos.application.repository.InMemoryAppointmentDAO;
import br.ifsp.husaocarlos.application.repository.InMemoryRegistrationDAO;
import br.ifsp.husaocarlos.application.repository.InMemoryUserDAO;
import br.ifsp.husaocarlos.domain.entities.*;
import br.ifsp.husaocarlos.domain.entities.appointment.Appointment;
import br.ifsp.husaocarlos.domain.usecases.appointment.AppointmentDAO;
import br.ifsp.husaocarlos.domain.usecases.registration.RegisterStudentActionUseCase;
import br.ifsp.husaocarlos.domain.usecases.registration.RegistrationDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.security.enterprise.credential.Password;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DeleteUserUseCaseTest {

    @Test
    void deleteUser() {
        UserDAO userDAO = new InMemoryUserDAO();
        AppointmentDAO appointmentDAO = new InMemoryAppointmentDAO();

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

        // Save User
        boolean exec = userDAO.save(user);
        assertEquals(exec, true);

        // Use Case
        DeleteUserUseCase deleteUserUseCase = new DeleteUserUseCase(userDAO, appointmentDAO);
        exec = deleteUserUseCase.deleteUser(user);
        assertEquals(exec, true);

        // Trying to delete some user that not exists
        exec = deleteUserUseCase.deleteUser(user);
        assertEquals(exec, false);

        // Saving user and register some appointment
        Student student = new Student(
                "email",
                "cpf",
                "name",
                pass,
                "adress",
                "registration",
                Roles.Receptionist
        );

        // Save Student
        exec = userDAO.save(student);
        assertEquals(exec, true);

        // Save some appointment
        RegistrationDAO registrationDAO = new InMemoryRegistrationDAO();

        LocalDateTime date = LocalDateTime.now().plusHours(2);

        // Action
        Password professorPassword = new Password("1234");
        Professor professor = new Professor(0,"prof.educador@gmail.com","579.456.789-56","João",professorPassword,"la na pqp",null, Roles.Professor, true);
        Action action = new Action("Ação1","Urologista",professor,"LinhaDeCuidade1");

        // Linkar o student na action
        RegisterStudentActionUseCase registerStudentActionUseCase = new RegisterStudentActionUseCase(registrationDAO);

        // Save registration on Action
        exec = registerStudentActionUseCase.includeStudentAction(action, student);
        Assertions.assertEquals(true, exec);

        // Patient
        Patient patient = new Patient("1111111111", "Miguel", "miguel@email.com", "169999999", "Rua onde ele mora, 10");
        // Appointment
        Appointment appointment = new Appointment(date, action, student, patient);
        exec = appointmentDAO.save(appointment);
        assertEquals(true, exec);

        // Trying to delete student with appointment
        exec = deleteUserUseCase.deleteUser(user);
        assertEquals(exec, false);
    }
}