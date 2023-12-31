package br.ifsp.husaocarlos.domain.usecases.user;

import br.ifsp.husaocarlos.application.repository.InMemoryAppointmentDAO;
import br.ifsp.husaocarlos.application.persistence.MySqlUserDAO;
import br.ifsp.husaocarlos.application.repository.InMemoryUserDAO;
import br.ifsp.husaocarlos.domain.entities.*;
import br.ifsp.husaocarlos.domain.entities.appointment.Appointment;
import br.ifsp.husaocarlos.domain.entities.student.Student;
import br.ifsp.husaocarlos.domain.usecases.appointment.AppointmentDAO;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GetNextHourFreeStudentUseCaseTest {

    @Test
    void getNextHourFree() {

        AppointmentDAO appointmentDAO = new InMemoryAppointmentDAO();
        UserDAO userDAO = new InMemoryUserDAO();

        LocalDateTime date = LocalDateTime.now().plusHours(2);
        LocalDateTime date2 = LocalDateTime.now().minusHours(3);

        // Action

        Professor professor = new Professor("prof.educador@gmail.com","579.456.789-56","João","1234","la na pqp",null, Roles.Professor, true);

        LineOfCare lineOfCare = new LineOfCare("LinhaDeCuidade1",new ArrayList<>(),professor);
        Action action = new Action("Ação1","Urologista",professor,lineOfCare);

        // Student
        Student student = new Student("miguel.dev@gmail.com","410.852.512-57","miguel", "1234",
                "rua aldo milanetto,176","13345", Roles.Student, true);

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


        GetNextHourFreeStudentUseCase getNextHourFreeStudentUseCase = new GetNextHourFreeStudentUseCase(userDAO, appointmentDAO);
        LocalDateTime localDateTime = getNextHourFreeStudentUseCase.getNextHourFree(student);
        System.out.println("Next Hour free of " + student.getName() + " is " + localDateTime);

    }
}