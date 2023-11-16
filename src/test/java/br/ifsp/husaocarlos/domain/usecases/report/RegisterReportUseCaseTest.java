package br.ifsp.husaocarlos.domain.usecases.report;

import br.ifsp.husaocarlos.application.repository.*;
import br.ifsp.husaocarlos.domain.entities.*;
import br.ifsp.husaocarlos.domain.entities.appointment.Appointment;
import br.ifsp.husaocarlos.domain.usecases.action.ActionDAO;
import br.ifsp.husaocarlos.domain.usecases.action.EditActionUseCase;
import br.ifsp.husaocarlos.domain.usecases.appointment.AppointmentDAO;
import br.ifsp.husaocarlos.domain.usecases.registration.RegistrationDAO;
import br.ifsp.husaocarlos.domain.usecases.user.UserDAO;
import org.junit.jupiter.api.Test;

import javax.security.enterprise.credential.Password;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterReportUseCaseTest {

    @Test
    void RegisterReport() {
        ReportDAO reportDAO = new InMemoryReportDAO();

        LocalDateTime date = LocalDateTime.now().plusHours(2);

        Report report = new Report(date);

        Password professorPassword = new Password("1234");
        Professor professor = new Professor(0,"prof.educador@gmail.com","579.456.789-56","João",professorPassword,"la na pqp",null, Roles.Professor);
        Action action = new Action("Ação1","Urologista",professor,"LinhaDeCuidade1");

        Student student = new Student("miguel.dev@gmail.com","410.852.512-57","miguel", professorPassword,
                "rua aldo milanetto,176","13345", Roles.Student);

        Patient patient = new Patient("1111111111", "Miguel", "miguel@email.com", "169999999", "Rua onde ele mora, 10");

        Appointment appointment = new Appointment(date, action, student, patient);

        RegisterReportUseCase registerReportUseCase = new RegisterReportUseCase(reportDAO);
        report.receiveNewAppointment(appointment);
        boolean exec = registerReportUseCase.registerReport(report);
        assertTrue(exec);
    }
}
