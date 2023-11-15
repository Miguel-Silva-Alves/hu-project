package br.ifsp.husaocarlos.domain.usecases.appointment;

import br.ifsp.husaocarlos.application.repository.InMemoryAppointmentDAO;
import br.ifsp.husaocarlos.application.repository.InMemoryRegistrationDAO;
import br.ifsp.husaocarlos.application.repository.InMemoryUserDAO;
import br.ifsp.husaocarlos.domain.entities.*;
import br.ifsp.husaocarlos.domain.entities.appointment.Appointment;
import br.ifsp.husaocarlos.domain.usecases.registration.RegisterStudentActionUseCase;
import br.ifsp.husaocarlos.domain.usecases.registration.RegistrationDAO;
import br.ifsp.husaocarlos.domain.usecases.user.UserDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.security.enterprise.credential.Password;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DischargePatientTest {

    @Test
    void discharge() {

        AppointmentDAO appointmentDAO = new InMemoryAppointmentDAO();
        UserDAO userDAO = new InMemoryUserDAO();
        RegistrationDAO registrationDAO = new InMemoryRegistrationDAO();

        LocalDateTime date = LocalDateTime.now().plusHours(2);

        // Action
        Password professorPassword = new Password("1234");
        Professor professor = new Professor(0,"prof.educador@gmail.com","579.456.789-56","João",professorPassword,"la na pqp",null, Roles.Professor);
        Action action = new Action("Ação1","Urologista",professor,"LinhaDeCuidade1");

        // Student
        Student student = new Student("miguel.dev@gmail.com","410.852.512-57","miguel", professorPassword,
                "rua aldo milanetto,176","13345", Roles.Student);

        // Linkar o student na action
        RegisterStudentActionUseCase registerStudentActionUseCase = new RegisterStudentActionUseCase(registrationDAO);
        // Save student
        boolean saved = userDAO.save(student);
        Assertions.assertEquals(true, saved);

        // Save registration on Action
        boolean execR = registerStudentActionUseCase.includeStudentAction(action, student);
        Assertions.assertEquals(true, execR);

        // Patient
        Patient patient = new Patient("1111111111", "Miguel", "miguel@email.com", "169999999", "Rua onde ele mora, 10");
        // Appointment 1
        Appointment appointment = new Appointment(date, action, student, patient);
        boolean exec = appointmentDAO.save(appointment);
        assertEquals(true, exec);

        // Dar alta
        DischargePatient dischargePatient = new DischargePatient(appointmentDAO);

        boolean execd = dischargePatient.discharge(patient);
        assertEquals(false, execd);

        // Atendendo a consulta
        appointment.attend();
        boolean updated = appointmentDAO.update(appointment.getId(), appointment);
        assertEquals(true, updated);


        execd = dischargePatient.discharge(patient);
        assertEquals(true, execd);

    }
}