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
        Password managementPassword = new Password("1234");
        management = new Management(0,"adm.admin@gmail.com","579.456.789-56","João",managementPassword,"la na pqp",null, Roles.Management);
        lineName = "Linha1";
        Password professorPassword = new Password("123456");
        professor = new Professor(0,"prof.educador@gmail.com","579.456.789-56","João",professorPassword,"la na pqp",null, Roles.Professor);
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
