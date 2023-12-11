package br.ifsp.husaocarlos.domain.usecases.action;

import br.ifsp.husaocarlos.application.repository.InMemoryActionDAO;
import br.ifsp.husaocarlos.domain.entities.Action;
import br.ifsp.husaocarlos.domain.entities.Professor;
import br.ifsp.husaocarlos.domain.entities.Roles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class FindActionUseCaseTest {

    ActionDAO actionDAO;
    FindActionUseCase findActionUseCase;

    @BeforeEach
    void setUp() {
        actionDAO = new InMemoryActionDAO();
        findActionUseCase = new FindActionUseCase(actionDAO);

        RegisterActionUseCase registerActionUseCase = new RegisterActionUseCase(actionDAO);
        Professor professor = new Professor(0,"prof.educador@gmail.com","579.456.789-56","João","1234","la na pqp",null, Roles.Professor, true);
        Action action = new Action("Ação1","Urologista",professor,"LinhaDeCuidade1");
        registerActionUseCase.registerAction(action);
    }

    @Test
    void getActions() {

        List<Action> list = findActionUseCase.getActions();
        assertEquals(list.isEmpty(), true);

        list = findActionUseCase.getActions();
        assertEquals(list.isEmpty(), false);
    }

    @Test
    void getActionByName() {
        // Incorrect
        Optional<Action> action = findActionUseCase.getActionByName("idk");
        assertEquals(false, action.isPresent());

        // Correct
        Optional<Action> action2 = findActionUseCase.getActionByName("Ação1");
        assertEquals(true, action2.isPresent());
    }
}