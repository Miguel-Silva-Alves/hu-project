package br.ifsp.husaocarlos.domain.usecases.registration;

import br.ifsp.husaocarlos.application.repository.InMemoryRegistrationDAO;
import br.ifsp.husaocarlos.application.repository.InMemoryUserDAO;
import br.ifsp.husaocarlos.application.repository.MySqlUserDAO;
import br.ifsp.husaocarlos.application.persistence.MySqlUserDAO;
import br.ifsp.husaocarlos.domain.entities.Action;
import br.ifsp.husaocarlos.domain.entities.Professor;
import br.ifsp.husaocarlos.domain.entities.Roles;
import br.ifsp.husaocarlos.domain.entities.Student;
import br.ifsp.husaocarlos.domain.usecases.user.UserDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class ListStudentOfActionUseCaseTest {

    @Test
    void listStudents() {

        // DAO
        RegistrationDAO registrationDAO = new InMemoryRegistrationDAO();
        UserDAO userDAO = new InMemoryUserDAO();

        RegisterStudentActionUseCase registerStudentActionUseCase = new RegisterStudentActionUseCase(registrationDAO);
        Professor professor = new Professor(0,"prof.educador@gmail.com","579.456.789-56","João","1234","la na pqp",null, Roles.Professor, true);
        Action action = new Action(1,"Ação1","Urologista",professor,"LinhaDeCuidade1");
        Student student = new Student("miguel.dev@gmail.com","410.852.512-57","miguel", "1234",
                "rua aldo milanetto,176","13345", Roles.Student);

        // Save student
        boolean saved = userDAO.save(student);
        Assertions.assertEquals(true, saved);

        // Save registration on Action
        boolean exec = registerStudentActionUseCase.includeStudentAction(action, student);
        Assertions.assertEquals(true, exec);

        // List students of some action
        ListStudentOfActionUseCase listStudentOfActionUseCase = new ListStudentOfActionUseCase(registrationDAO, userDAO);
        List<Student> list = listStudentOfActionUseCase.listStudents(action);
        for(Student studentFor: list){
            System.out.println(studentFor);
        }

        Assertions.assertEquals(list.get(0).getRole(), Roles.Student);

    }
}