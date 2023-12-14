package br.ifsp.husaocarlos.domain.usecases.user;

import br.ifsp.husaocarlos.application.repository.InMemoryAppointmentDAO;
import br.ifsp.husaocarlos.application.repository.InMemoryRegistrationDAO;
import br.ifsp.husaocarlos.application.persistence.MySqlUserDAO;
import br.ifsp.husaocarlos.application.repository.InMemoryUserDAO;
import br.ifsp.husaocarlos.domain.entities.*;
import br.ifsp.husaocarlos.domain.entities.appointment.Appointment;
import br.ifsp.husaocarlos.domain.entities.student.Student;
import br.ifsp.husaocarlos.domain.usecases.appointment.AppointmentDAO;
import br.ifsp.husaocarlos.domain.usecases.registration.RegisterStudentActionUseCase;
import br.ifsp.husaocarlos.domain.usecases.registration.RegistrationDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DeleteUserUseCaseTest {

    @Test
    void deleteUser() {
        UserDAO userDAO = new InMemoryUserDAO();
        AppointmentDAO appointmentDAO = new InMemoryAppointmentDAO();

        // Create User
        User user = new User(
                "email",
                "51485378842",
                "name",
                "1234",
                "adress",
                "registration",
                Roles.Receptionist
        );

        // Save User
        boolean exec = userDAO.save(user);
        assertTrue(exec);

        // Use Case
        DeleteUserUseCase deleteUserUseCase = new DeleteUserUseCase(userDAO, appointmentDAO);
        exec = deleteUserUseCase.deleteUser(user);
        assertTrue(exec);

        // Trying to delete some user that not exists
        exec = deleteUserUseCase.deleteUser(user);
        assertFalse(exec);

        // Saving user and register some appointment
        Student student = new Student(
                "email",
                "cpf",
                "name",
                "1234",
                "adress",
                "registration",
                Roles.Receptionist
        );

        // Save Student
        exec = userDAO.save(student);
        assertTrue(exec);

        // Save some appointment
        RegistrationDAO registrationDAO = new InMemoryRegistrationDAO();

        LocalDateTime date = LocalDateTime.now().plusHours(2);

        // Action
        Professor professor = new Professor("prof.educador@gmail.com","579.456.789-56","João","1234","la na pqp",null, Roles.Professor, true);
        LineOfCare lineOfCare = new LineOfCare("LinhaDeCuidade1",new ArrayList<>(),professor);
        Action action = new Action("Ação1","Urologista",professor,lineOfCare);

        // Linkar o student na action
        RegisterStudentActionUseCase registerStudentActionUseCase = new RegisterStudentActionUseCase(registrationDAO);

        // Save registration on Action
        exec = registerStudentActionUseCase.includeStudentAction(action, student);
        assertTrue(exec);

        // Patient
        Patient patient = new Patient("1111111111", "Miguel", "miguel@email.com", "169999999", "Rua onde ele mora, 10");
        // Appointment
        Appointment appointment = new Appointment(date, action, student, patient);
        exec = appointmentDAO.save(appointment);
        assertTrue(exec);

        // Trying to delete student with appointment
        exec = deleteUserUseCase.deleteUser(user);
        assertFalse(exec);
    }
}