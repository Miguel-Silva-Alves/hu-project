package br.ifsp.husaocarlos.domain.usecases.action;

import br.ifsp.husaocarlos.application.repository.InMemoryActionDAO;
import br.ifsp.husaocarlos.domain.entities.Action;
import br.ifsp.husaocarlos.domain.entities.Professor;
import br.ifsp.husaocarlos.domain.entities.Roles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.security.enterprise.credential.Password;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EditActionUseCaseTest {

    @Test
    void EditAction() {
        ActionDAO actionDAO = new InMemoryActionDAO();
        Password professorPassword = new Password("1234");
        Professor professor = new Professor(0,"prof.educador@gmail.com","579.456.789-56","João",professorPassword,"la na pqp",null, Roles.Professor);
        Action action = new Action("Ação1","Urologista",professor,"LinhaDeCuidade1");
        EditActionUseCase editActionUseCase = new EditActionUseCase(actionDAO);
        boolean exec = editActionUseCase.edit(0,action);
        assertTrue(exec);
    }
}

