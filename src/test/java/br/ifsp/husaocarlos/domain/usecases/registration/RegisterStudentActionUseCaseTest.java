package br.ifsp.husaocarlos.domain.usecases.registration;

import br.ifsp.husaocarlos.application.repository.InMemoryRegistrationDAO;
import br.ifsp.husaocarlos.domain.entities.Action;
import br.ifsp.husaocarlos.domain.entities.Professor;
import br.ifsp.husaocarlos.domain.entities.Roles;
import br.ifsp.husaocarlos.domain.entities.Student;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.security.enterprise.credential.Password;


class RegisterStudentActionUseCaseTest {
    @Test
    public void includeStudentAction(){
        RegistrationDAO registrationDAO = new InMemoryRegistrationDAO();
        RegisterStudentActionUseCase registerStudentActionUseCase = new RegisterStudentActionUseCase(registrationDAO);
        Password professorPassword = new Password("1234");
        Professor professor = new Professor(0,"prof.educador@gmail.com","579.456.789-56","João",professorPassword,"la na pqp",null, Roles.Professor, true);
        Action action = new Action(1,"Ação1","Urologista",professor,"LinhaDeCuidade1");
        Student student = new Student(0,"miguel.dev@gmail.com","410.852.512-57","miguel", professorPassword,
                "rua aldo milanetto,176","13345", Roles.Student, true);
        boolean exec = registerStudentActionUseCase.includeStudentAction(action, student);

        Assertions.assertEquals(true, exec);
        boolean exec2 = registerStudentActionUseCase.includeStudentAction(action, student);

        Assertions.assertEquals(false, exec2);
    }
}