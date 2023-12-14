package br.ifsp.husaocarlos.domain.usecases.registration;

import br.ifsp.husaocarlos.application.repository.InMemoryRegistrationDAO;
import br.ifsp.husaocarlos.domain.entities.Action;
import br.ifsp.husaocarlos.domain.entities.LineOfCare;
import br.ifsp.husaocarlos.domain.entities.Professor;
import br.ifsp.husaocarlos.domain.entities.Roles;
import br.ifsp.husaocarlos.domain.entities.student.Student;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


class RegisterStudentActionUseCaseTest {
    @Test
    public void includeStudentAction(){
        RegistrationDAO registrationDAO = new InMemoryRegistrationDAO();
        RegisterStudentActionUseCase registerStudentActionUseCase = new RegisterStudentActionUseCase(registrationDAO);
        Professor professor = new Professor(0,"prof.educador@gmail.com","579.456.789-56","João","1234","la na pqp",null, Roles.Professor, true);

        LineOfCare lineOfCare = new LineOfCare("LinhaDeCuidade1",new ArrayList<>(),professor);
        Action action = new Action("Ação1","Urologista",professor,lineOfCare);

        Student student = new Student("miguel.dev@gmail.com","410.852.512-57","miguel", "1234",
                "rua aldo milanetto,176","13345", Roles.Student);
        boolean exec = registerStudentActionUseCase.includeStudentAction(action, student);

        Assertions.assertEquals(true, exec);
        boolean exec2 = registerStudentActionUseCase.includeStudentAction(action, student);

        Assertions.assertEquals(false, exec2);
    }
}