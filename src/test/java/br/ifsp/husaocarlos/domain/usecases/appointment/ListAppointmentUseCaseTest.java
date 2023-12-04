package br.ifsp.husaocarlos.domain.usecases.appointment;

import br.ifsp.husaocarlos.application.repository.*;
import br.ifsp.husaocarlos.domain.entities.*;
import br.ifsp.husaocarlos.domain.entities.appointment.Appointment;
import br.ifsp.husaocarlos.domain.usecases.action.ActionDAO;
import br.ifsp.husaocarlos.domain.usecases.action.ListActionUseCase;
import br.ifsp.husaocarlos.domain.usecases.action.RegisterActionUseCase;
import br.ifsp.husaocarlos.domain.usecases.patient.PatientDAO;
import br.ifsp.husaocarlos.domain.usecases.registration.RegisterStudentActionUseCase;
import br.ifsp.husaocarlos.domain.usecases.registration.RegistrationDAO;
import br.ifsp.husaocarlos.domain.usecases.user.UserDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.security.enterprise.credential.Password;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListAppointmentUseCaseTest {

    @Test
    void getStudentsOfProfessor() {

        AppointmentDAO appointmentDAO = new InMemoryAppointmentDAO();
        RegistrationDAO registrationDAO = new InMemoryRegistrationDAO();
        UserDAO userDAO = new MySqlUserDAO();
        ActionDAO actionDAO = new InMemoryActionDAO();

        // Register Action and Professor
        RegisterActionUseCase registerActionUseCase = new RegisterActionUseCase(actionDAO);
        Professor professor = new Professor(0,"prof.educador@gmail.com","589.456.789-56","João","1234","la na pqp",null, Roles.Professor, true);
        Action action = new Action("Ação1","Urologista",professor,"LinhaDeCuidade1");
        boolean exec = registerActionUseCase.registerAction(action);
        assertEquals(true, exec);

        // Register Student
        RegisterStudentActionUseCase registerStudentActionUseCase = new RegisterStudentActionUseCase(registrationDAO);
        Student student = new Student("miguel.dev@gmail.com","411.852.512-57","miguel", "1234",
                "rua aldo milanetto,176","13345", Roles.Student);
        exec = userDAO.save(student);
        assertEquals(true, exec);

        exec = registerStudentActionUseCase.includeStudentAction(action, student);
        assertEquals(true, exec);

        // Use case
        ListAppointmentUseCase listAppointmentUseCase = new ListAppointmentUseCase(appointmentDAO, userDAO, actionDAO, registrationDAO);
        List<Student> list = listAppointmentUseCase.getStudentsOfProfessor(professor);
        System.out.println("Students:");
        for(Student studentt: list){
            System.out.println(studentt);
        }

        assertEquals(list.isEmpty(), false);

    }

    @Test
    void getPatientsOfProfessor() {
        AppointmentDAO appointmentDAO = new InMemoryAppointmentDAO();
        RegistrationDAO registrationDAO = new InMemoryRegistrationDAO();
        UserDAO userDAO = new MySqlUserDAO();
        ActionDAO actionDAO = new InMemoryActionDAO();

        // Register Action and Professor
        RegisterActionUseCase registerActionUseCase = new RegisterActionUseCase(actionDAO);
        Professor professor = new Professor(0,"prof.educador@gmail.com","579.456.789-56","João","1234","la na pqp",null, Roles.Professor, true);
        Action action = new Action("Ação1","Urologista",professor,"LinhaDeCuidade1");
        boolean exec = registerActionUseCase.registerAction(action);
        assertEquals(true, exec);

        // Register Student
        RegisterStudentActionUseCase registerStudentActionUseCase = new RegisterStudentActionUseCase(registrationDAO);
        Student student = new Student("miguel.dev@gmail.com","410.852.512-57","miguel", "1234",
                "rua aldo milanetto,176","13345", Roles.Student);
        exec = userDAO.save(student);
        assertEquals(true, exec);

        exec = registerStudentActionUseCase.includeStudentAction(action, student);
        assertEquals(true, exec);

        // Make an appointment

        LocalDateTime date = LocalDateTime.now().plusHours(2);
        // Patient
        Patient patient = new Patient("1111111111", "Miguel", "miguel@email.com", "169999999", "Rua onde ele mora, 10");

        // Appointment 1
        Appointment appointment = new Appointment(date, action, student, patient);
        exec = appointmentDAO.save(appointment);
        assertEquals(true, exec);

        // Use case
        ListAppointmentUseCase listAppointmentUseCase = new ListAppointmentUseCase(appointmentDAO, userDAO, actionDAO, registrationDAO);
        List<Patient> list = listAppointmentUseCase.getPatientsOfProfessor(professor);
        System.out.println("Patients:");
        for(Patient pt: list){
            System.out.println(pt);
        }
        assertEquals(list.isEmpty(), false);
    }

    @Test
    void getAppointments() {
        AppointmentDAO appointmentDAO = new InMemoryAppointmentDAO();
        RegistrationDAO registrationDAO = new InMemoryRegistrationDAO();
        UserDAO userDAO = new MySqlUserDAO();
        ActionDAO actionDAO = new InMemoryActionDAO();

        // Register Action and Professor
        RegisterActionUseCase registerActionUseCase = new RegisterActionUseCase(actionDAO);
        Professor professor = new Professor(0,"prof.educador@gmail.com","579.456.789-56","João","1234","la na pqp",null, Roles.Professor, true);
        Action action = new Action("Ação1","Urologista",professor,"LinhaDeCuidade1");
        boolean exec = registerActionUseCase.registerAction(action);
        assertEquals(true, exec);

        // Register Student
        RegisterStudentActionUseCase registerStudentActionUseCase = new RegisterStudentActionUseCase(registrationDAO);
        Student student = new Student("miguel.dev@gmail.com","410.852.512-57","miguel", "1234",
                "rua aldo milanetto,176","13345", Roles.Student);
        exec = userDAO.save(student);
        assertEquals(true, exec);

        exec = registerStudentActionUseCase.includeStudentAction(action, student);
        assertEquals(true, exec);

        // Make an appointment

        LocalDateTime date = LocalDateTime.now().plusHours(2);
        // Patient
        Patient patient = new Patient("1111111111", "Miguel", "miguel@email.com", "169999999", "Rua onde ele mora, 10");

        // Appointment 1
        Appointment appointment = new Appointment(date, action, student, patient);
        exec = appointmentDAO.save(appointment);
        assertEquals(true, exec);

        // Use case
        ListAppointmentUseCase listAppointmentUseCase = new ListAppointmentUseCase(appointmentDAO, userDAO, actionDAO, registrationDAO);
        List<Appointment> list = listAppointmentUseCase.getAppointments(professor, "1111111111");

        System.out.println("Appointments:");
        for(Appointment pt: list){
            System.out.println(pt);
        }
        assertEquals(list.isEmpty(), false);
    }
}