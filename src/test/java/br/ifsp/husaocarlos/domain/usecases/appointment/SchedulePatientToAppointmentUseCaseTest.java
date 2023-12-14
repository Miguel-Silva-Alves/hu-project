package br.ifsp.husaocarlos.domain.usecases.appointment;

import br.ifsp.husaocarlos.application.repository.InMemoryAppointmentDAO;
import br.ifsp.husaocarlos.application.repository.InMemoryRegistrationDAO;
import br.ifsp.husaocarlos.application.repository.InMemoryUserDAO;
import br.ifsp.husaocarlos.application.persistence.MySqlUserDAO;
import br.ifsp.husaocarlos.domain.entities.*;
import br.ifsp.husaocarlos.domain.entities.appointment.Appointment;
import br.ifsp.husaocarlos.domain.entities.student.Student;
import br.ifsp.husaocarlos.domain.usecases.registration.RegisterStudentActionUseCase;
import br.ifsp.husaocarlos.domain.usecases.registration.RegistrationDAO;
import br.ifsp.husaocarlos.domain.usecases.user.UserDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SchedulePatientToAppointmentUseCaseTest {

    @Test
    void schedule() {
        AppointmentDAO appointmentDAO = new InMemoryAppointmentDAO();
        UserDAO userDAO = new MySqlUserDAO();
        RegistrationDAO registrationDAO = new InMemoryRegistrationDAO();

        LocalDateTime date = LocalDateTime.now().plusHours(2);
        LocalDateTime date2 = LocalDateTime.now().minusHours(3);

        // Action
        Professor professor = new Professor(0,"prof.educador@gmail.com","579.456.789-56","João","1234","la na pqp",null, Roles.Professor, true);
        LineOfCare lineOfCare = new LineOfCare("LinhaDeCuidade1",new ArrayList<>(),professor);
        Action action = new Action("Ação1","Urologista",professor,lineOfCare);

        // Student
        Student student = new Student("miguel.dev@gmail.com","410.852.512-57","miguel", "1234",
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

        // Appointment 2
        Appointment appointment2 = new Appointment(date2, action, student, patient);
        boolean exec2 = appointmentDAO.save(appointment2);
        assertEquals(true, exec2);

        SchedulePatientToAppointmentUseCase sch = new SchedulePatientToAppointmentUseCase(appointmentDAO, registrationDAO, userDAO);
        Appointment appointment1 = sch.schedule(action, patient);
        assertEquals(appointment1.getClass(), Appointment.class);
    }
    @Test
    void schedule2() {
        AppointmentDAO appointmentDAO = new InMemoryAppointmentDAO();
        UserDAO userDAO = new InMemoryUserDAO();
        RegistrationDAO registrationDAO = new InMemoryRegistrationDAO();

        LocalDateTime date = LocalDateTime.now().plusHours(2);
        LocalDateTime date2 = LocalDateTime.now().minusHours(3);

        // Action
        Professor professor = new Professor(0,"prof.educador@gmail.com","579.456.789-56","João","1234","la na pqp",null, Roles.Professor, true);
        LineOfCare lineOfCare = new LineOfCare("LinhaDeCuidade1",new ArrayList<>(),professor);
        Action action = new Action("Ação1","Urologista",professor,lineOfCare);

        // Student
        Student student = new Student("miguel.dev@gmail.com","410.852.512-57","miguel", "1234",
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

        // Appointment 2
        Appointment appointment2 = new Appointment(date2, action, student, patient);
        boolean exec2 = appointmentDAO.save(appointment2);
        assertEquals(true, exec2);

        SchedulePatientToAppointmentUseCase sch = new SchedulePatientToAppointmentUseCase(appointmentDAO, registrationDAO, userDAO);
        String dataa = "02/12/2023 12:10";
        Optional<Appointment> appointment1 = sch.scheduleWithDate(action, patient, student, dataa);
        assertEquals(appointment1.get().getClass(), Appointment.class);
    }

}