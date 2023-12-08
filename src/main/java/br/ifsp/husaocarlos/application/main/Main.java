package br.ifsp.husaocarlos.application.main;

import br.ifsp.husaocarlos.application.repository.*;
import br.ifsp.husaocarlos.application.view.App;
import br.ifsp.husaocarlos.domain.entities.*;
import br.ifsp.husaocarlos.domain.usecases.action.ActionDAO;
import br.ifsp.husaocarlos.domain.usecases.action.FindActionUseCase;
import br.ifsp.husaocarlos.domain.usecases.appointment.AppointmentDAO;
import br.ifsp.husaocarlos.domain.usecases.patient.CreatePatientUseCase;
import br.ifsp.husaocarlos.domain.usecases.patient.PatientDAO;
import br.ifsp.husaocarlos.domain.usecases.registration.ListStudentOfActionUseCase;
import br.ifsp.husaocarlos.domain.usecases.registration.RegistrationDAO;
import br.ifsp.husaocarlos.domain.usecases.user.FindUserUseCase;
import br.ifsp.husaocarlos.domain.usecases.user.GetNextHourFreeStudentUseCase;
import br.ifsp.husaocarlos.domain.usecases.user.UserDAO;

public class Main {

    // USECASE
    public static FindUserUseCase findUserUseCase;
    public static CreatePatientUseCase createPatientUseCase;

    public static FindActionUseCase findActionUseCase;
    public static ListStudentOfActionUseCase listStudentOfActionUseCase;
    public static GetNextHourFreeStudentUseCase getNextHourFreeStudentUseCase;

    public static void main(String[] args) {
        configureInjection();
        populateFakeDatabase();
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
        // Action
        Action action = new Action("action1","Urologista", professor,"LinhaDeCuidade1");
        actionDAO.save(action);

        RegistrationDAO registrationDAO = new InMemoryRegistrationDAO();

        // Registration
        Registration newRegistration = new Registration(student.getCpf(), action.getId());
        registrationDAO.save(newRegistration);
    }

    private static void configureInjection() {
        // User
        UserDAO userDAO = new InMemoryUserDAO();
        findUserUseCase = new FindUserUseCase(userDAO);

        // Patient
        PatientDAO patientDAO = new InMemoryPatientDAO();
        createPatientUseCase = new CreatePatientUseCase(patientDAO);

        // Action
        ActionDAO actionDAO = new InMemoryActionDAO();
        findActionUseCase = new FindActionUseCase(actionDAO);

        // Registration
        RegistrationDAO registrationDAO = new InMemoryRegistrationDAO();
        listStudentOfActionUseCase = new ListStudentOfActionUseCase(registrationDAO, userDAO);

        // Appoitment
        AppointmentDAO appointmentDAO = new InMemoryAppointmentDAO();
        getNextHourFreeStudentUseCase = new GetNextHourFreeStudentUseCase(userDAO, appointmentDAO);


    }
}
