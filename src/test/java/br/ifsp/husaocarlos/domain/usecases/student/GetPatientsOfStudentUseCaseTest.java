package br.ifsp.husaocarlos.domain.usecases.student;

import br.ifsp.husaocarlos.application.persistence.MySqlUserDAO;
import br.ifsp.husaocarlos.application.repository.*;
import br.ifsp.husaocarlos.domain.entities.*;
import br.ifsp.husaocarlos.domain.entities.appointment.Appointment;
import br.ifsp.husaocarlos.domain.entities.student.Student;
import br.ifsp.husaocarlos.domain.usecases.action.ActionDAO;
import br.ifsp.husaocarlos.domain.usecases.action.RegisterActionUseCase;
import br.ifsp.husaocarlos.domain.usecases.appointment.AppointmentDAO;
import br.ifsp.husaocarlos.domain.usecases.patient.PatientDAO;
import br.ifsp.husaocarlos.domain.usecases.registration.RegisterStudentActionUseCase;
import br.ifsp.husaocarlos.domain.usecases.registration.RegistrationDAO;
import br.ifsp.husaocarlos.domain.usecases.user.UserDAO;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GetPatientsOfStudentUseCaseTest {

    @Test
    void getPatients() {
        AppointmentDAO appointmentDAO = new InMemoryAppointmentDAO();
        RegistrationDAO registrationDAO = new InMemoryRegistrationDAO();
        UserDAO userDAO = new InMemoryUserDAO();
        ActionDAO actionDAO = new InMemoryActionDAO();
        PatientDAO patientDAO = new InMemoryPatientDAO();

        // Register Action and Professor
        RegisterActionUseCase registerActionUseCase = new RegisterActionUseCase(actionDAO);
        Professor professor = new Professor("prof.educador@gmail.com","579.456.789-56","João","1234","la na pqp",null, Roles.Professor);
        LineOfCare lineOfCare = new LineOfCare("LinhaDeCuidade1",new ArrayList<>(),professor);
        Action action = new Action("Ação1","Urologista",professor,lineOfCare);
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
        GetPatientsOfStudentUseCase gps = new GetPatientsOfStudentUseCase(appointmentDAO);
        List<Patient> list = gps.getPatients(student);
        System.out.println("Patients:");
        for(Patient pt: list){
            System.out.println(pt);
        }
        assertEquals(list.isEmpty(), false);
    }
}