package br.ifsp.husaocarlos.domain.usecases.student;

import br.ifsp.husaocarlos.application.repository.InMemoryActionDAO;
import br.ifsp.husaocarlos.application.repository.InMemoryRegistrationDAO;
import br.ifsp.husaocarlos.application.repository.InMemoryUserDAO;
import br.ifsp.husaocarlos.domain.entities.Professor;
import br.ifsp.husaocarlos.domain.entities.Registration;
import br.ifsp.husaocarlos.domain.entities.Roles;
import br.ifsp.husaocarlos.domain.entities.User;
import br.ifsp.husaocarlos.domain.entities.student.Student;
import br.ifsp.husaocarlos.domain.usecases.action.ActionDAO;
import br.ifsp.husaocarlos.domain.usecases.registration.RegistrationDAO;
import br.ifsp.husaocarlos.domain.usecases.user.UserDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FindStudentsUseCaseTest {

    UserDAO userDAO;
    ActionDAO actionDAO;
    RegistrationDAO registrationDAO;

    @BeforeEach
    void setUp() {
        userDAO = new InMemoryUserDAO();
        registrationDAO = new InMemoryRegistrationDAO();
        actionDAO = new InMemoryActionDAO();
        Student student = new Student("miguel.dev@gmail.com","410.852.512-57","miguel", "1234",
                "rua aldo milanetto,176","13345", Roles.Student);
        Professor professor = new Professor("prof.educador@gmail.com","579.456.789-56","João","1234","la na pqp",null, Roles.Professor);
        userDAO.save(student);
        userDAO.save(professor);
    }

    @Test
    void findAll() {
        FindStudentsUseCase findStudentsUseCase = new FindStudentsUseCase(userDAO, actionDAO, registrationDAO);
        List<Student> studentsList = findStudentsUseCase.findAll();
        System.out.println(studentsList);
        assertEquals(1, studentsList.size());
    }

    @Test
    void findStudentsOfProfessor() {
    }
}