package br.ifsp.husaocarlos.domain.usecases.action;

import br.ifsp.husaocarlos.application.repository.InMemoryActionDAO;
import br.ifsp.husaocarlos.domain.entities.Action;
import br.ifsp.husaocarlos.domain.entities.Professor;
import br.ifsp.husaocarlos.domain.entities.Roles;
import org.junit.jupiter.api.Test;

import javax.security.enterprise.credential.Password;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListActionUseCaseTest {

    @Test
    void getActions() {

        ActionDAO actionDAO = new InMemoryActionDAO();
        ListActionUseCase listActionUseCase = new ListActionUseCase(actionDAO);

        List<Action> list = listActionUseCase.getActions();
        assertEquals(list.isEmpty(), true);

        // Insert new Action
        RegisterActionUseCase registerActionUseCase = new RegisterActionUseCase(actionDAO);
        Professor professor = new Professor(0,"prof.educador@gmail.com","579.456.789-56","João","1234","la na pqp",null, Roles.Professor, true);
        Action action = new Action("Ação1","Urologista",professor,"LinhaDeCuidade1");
        boolean exec = registerActionUseCase.registerAction(action);
        assertEquals(true, exec);

        list = listActionUseCase.getActions();
        assertEquals(list.isEmpty(), false);
    }
}