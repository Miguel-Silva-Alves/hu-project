package br.ifsp.husaocarlos.domain.usecases.management;

import br.ifsp.husaocarlos.application.repository.InMemoryLinesOfCareDAO;
import br.ifsp.husaocarlos.domain.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.security.enterprise.credential.Password;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EditLinesOfCareUseCaseTest {

    LinesOfCareDAO DAO;

    Management management;
    String lineName;
    LineOfCare lineOfCare;
    Professor professor;

    @BeforeEach
    void setup(){
        DAO = new InMemoryLinesOfCareDAO();
        management = new Management("adm.admin@gmail.com","579.456.789-56","João","1234","la na pqp",null, Roles.Management, true);
        lineName = "Linha1";
        Password professorPassword = new Password("123456");
        professor = new Professor(0,"prof.educador@gmail.com","579.456.789-56","João","1234","la na pqp",null, Roles.Professor, true);
        lineOfCare = new LineOfCare(lineName,new ArrayList<Action>(),professor);
        DAO.save(lineOfCare);
    }

    @Test
    void EditLinesOfCareWithSucess(){
        EditLinesOfCareUseCase editLinesOfCareUseCase = new EditLinesOfCareUseCase(DAO);
        lineOfCare.setLineName("NovoNome");
        boolean result = editLinesOfCareUseCase.edit("Linha1",lineOfCare);
        assertTrue(result);
    }


}
