package br.ifsp.husaocarlos.application.main;

import br.ifsp.husaocarlos.application.persistence.*;
import br.ifsp.husaocarlos.application.repository.*;
import br.ifsp.husaocarlos.application.view.App;
import br.ifsp.husaocarlos.domain.entities.*;
import br.ifsp.husaocarlos.domain.entities.appointment.Appointment;
import br.ifsp.husaocarlos.domain.usecases.action.ActionDAO;
import br.ifsp.husaocarlos.domain.usecases.action.FindActionUseCase;
import br.ifsp.husaocarlos.domain.usecases.appointment.AppointmentDAO;
import br.ifsp.husaocarlos.domain.usecases.appointment.ListAppointmentUseCase;
import br.ifsp.husaocarlos.domain.usecases.appointment.SchedulePatientToAppointmentUseCase;
import br.ifsp.husaocarlos.domain.usecases.management.LinesOfCareDAO;
import br.ifsp.husaocarlos.domain.usecases.patient.CreatePatientUseCase;
import br.ifsp.husaocarlos.domain.usecases.patient.FindPatientUseCase;
import br.ifsp.husaocarlos.domain.usecases.patient.PatientDAO;
import br.ifsp.husaocarlos.domain.usecases.patient.UpdatePatientUseCase;
import br.ifsp.husaocarlos.domain.usecases.registration.ListStudentOfActionUseCase;
import br.ifsp.husaocarlos.domain.usecases.registration.RegistrationDAO;
import br.ifsp.husaocarlos.domain.usecases.user.FindUserUseCase;
import br.ifsp.husaocarlos.domain.usecases.user.GetNextHourFreeStudentUseCase;
import br.ifsp.husaocarlos.domain.usecases.user.UserDAO;

import java.util.ArrayList;
import java.util.Optional;

import br.ifsp.husaocarlos.application.view.App;
import br.ifsp.husaocarlos.domain.entities.Action;
import br.ifsp.husaocarlos.domain.entities.User;
import javafx.scene.shape.Line;

public class Main {

    // USECASE
    public static FindUserUseCase findUserUseCase;
    public static CreatePatientUseCase createPatientUseCase;
    public static FindPatientUseCase findPatientUseCase;
    public static UpdatePatientUseCase updatePatientUseCase;

    public static SchedulePatientToAppointmentUseCase schedulePatientToAppointmentUseCase;

    public static FindActionUseCase findActionUseCase;
    public static ListStudentOfActionUseCase listStudentOfActionUseCase;
    public static GetNextHourFreeStudentUseCase getNextHourFreeStudentUseCase;
    public static ListAppointmentUseCase listAppointmentUseCase;

    public static void main(String[] args) {
        configureInjection();
        //populateFakeDatabase();
        populateDatabase();
        MySqlActionDAO dao = new MySqlActionDAO();
        System.out.println();
        App.main(args);
    }

    private static void populateFakeDatabase(){
        UserDAO dao = new InMemoryUserDAO();
        // Recepcionist
        User user = new User("teste@gmail.com", "99998964059", "miguel", "password", "endereco", "", Roles.Receptionist);
        dao.save(user);

        // Student
        Student student = new Student("student@gmail.com", "23812205009", "Aluno1", "password", "endereco", "idk", Roles.Student);
        dao.save(student);

        // Professor
        Professor professor = new Professor("professor@gmail.com", "73885307030", "Professor1", "password", "endereco", "idk", Roles.Professor);
        dao.save(professor);

        ActionDAO actionDAO = new InMemoryActionDAO();
        LineOfCare line = new LineOfCare("LinhaDeCuidade1",new ArrayList<Action>(),professor);
        // Action
        Action action = new Action("action1","Urologista", professor,line);
        actionDAO.save(action);

        RegistrationDAO registrationDAO = new InMemoryRegistrationDAO();

        // Registration
        Registration newRegistration = new Registration(student.getCpf(), action.getId());
        registrationDAO.save(newRegistration);

        PatientDAO patientDAO = new InMemoryPatientDAO();
        Patient patient = new Patient("16098760080", "Miguel", "email@gmail.com", "169999999", "Rua lá longe");
        patientDAO.save(patient);

        AppointmentDAO appointmentDAO = new InMemoryAppointmentDAO();
        SchedulePatientToAppointmentUseCase sch = new SchedulePatientToAppointmentUseCase(appointmentDAO, registrationDAO, dao);
        String dataa = "02/12/2023 12:10";
        sch.scheduleWithDate(action, patient, student, dataa);
    }
    private static void populateDatabase(){
        UserDAO dao = new MySqlUserDAO();
        ActionDAO actionDAO = new MySqlActionDAO();
        RegistrationDAO registrationDAO = new InMemoryRegistrationDAO();
        LinesOfCareDAO linesOfCareDAO = new MySqlLinesOfCareDAO();

        // Recepcionist
        User user = new Receptionist("teste@gmail.com", "99998964059", "miguel", "password", "endereco", "", Roles.Receptionist, true);
        dao.save(user);

        // Student
        Student student = new Student("student@gmail.com", "23812205009", "Aluno1", "password", "endereco", "idk", Roles.Student);
        dao.save(student);

        // Professor
        Professor professor = new Professor("professor@gmail.com", "73885307030", "Professor1", "password", "endereco", "idk", Roles.Professor);
        dao.save(professor);


        LineOfCare line = new LineOfCare("LinhaDeCuidade1",new ArrayList<>(),professor);
        linesOfCareDAO.save(line);

        // Action
        Action action = new Action("action1","Urologista", professor,line);
        System.out.println("Action1");
        System.out.println(actionDAO.save(action));


        // Registration
        Registration newRegistration = new Registration(student.getCpf(), action.getId());
        registrationDAO.save(newRegistration);

        PatientDAO patientDAO = new MySqlPatientDAO();
        Patient patient = new Patient("16098760080", "Miguel", "email@gmail.com", "169999999", "Rua lá longe");
        patientDAO.save(patient);

        AppointmentDAO appointmentDAO = new MySqlAppointmentDAO();
        SchedulePatientToAppointmentUseCase sch = new SchedulePatientToAppointmentUseCase(appointmentDAO, registrationDAO, dao);
        String dataa = "02/12/2023 12:10";
        System.out.println(sch.scheduleWithDate(action, patient, student, dataa));
    }
    private static void configureInjection() {
        // User
        UserDAO userDAO = new InMemoryUserDAO();
        findUserUseCase = new FindUserUseCase(userDAO);

        // Patient
        PatientDAO patientDAO = new InMemoryPatientDAO();
        createPatientUseCase = new CreatePatientUseCase(patientDAO);
        findPatientUseCase = new FindPatientUseCase(patientDAO);
        updatePatientUseCase = new UpdatePatientUseCase(patientDAO);

        // Action
        ActionDAO actionDAO = new InMemoryActionDAO();
        findActionUseCase = new FindActionUseCase(actionDAO);

        // Registration
        RegistrationDAO registrationDAO = new InMemoryRegistrationDAO();
        listStudentOfActionUseCase = new ListStudentOfActionUseCase(registrationDAO, userDAO);

        // Appoitment
        AppointmentDAO appointmentDAO = new InMemoryAppointmentDAO();
        getNextHourFreeStudentUseCase = new GetNextHourFreeStudentUseCase(userDAO, appointmentDAO);
        schedulePatientToAppointmentUseCase = new SchedulePatientToAppointmentUseCase(appointmentDAO, registrationDAO, userDAO);
        listAppointmentUseCase = new ListAppointmentUseCase(appointmentDAO, userDAO, actionDAO, registrationDAO);
    }
}
