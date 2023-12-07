package br.ifsp.husaocarlos.application.main;

import br.ifsp.husaocarlos.application.repository.InMemoryActionDAO;
import br.ifsp.husaocarlos.application.repository.InMemoryPatientDAO;
import br.ifsp.husaocarlos.application.repository.InMemoryRegistrationDAO;
import br.ifsp.husaocarlos.application.repository.InMemoryUserDAO;
import br.ifsp.husaocarlos.application.view.App;
import br.ifsp.husaocarlos.domain.entities.*;
import br.ifsp.husaocarlos.domain.usecases.action.ActionDAO;
import br.ifsp.husaocarlos.domain.usecases.patient.CreatePatientUseCase;
import br.ifsp.husaocarlos.domain.usecases.patient.PatientDAO;
import br.ifsp.husaocarlos.domain.usecases.registration.RegistrationDAO;
import br.ifsp.husaocarlos.domain.usecases.user.FindUserUseCase;
import br.ifsp.husaocarlos.domain.usecases.user.UserDAO;

public class Main {

    public static FindUserUseCase findUserUseCase;
    public static CreatePatientUseCase createPatientUseCase;

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
        Action action = new Action("Ação1","Urologista", professor,"LinhaDeCuidade1");
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
    }
}
