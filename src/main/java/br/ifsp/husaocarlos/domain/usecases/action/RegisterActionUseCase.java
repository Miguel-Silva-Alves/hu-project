package br.ifsp.husaocarlos.domain.usecases.action;

import br.ifsp.husaocarlos.domain.entities.Action;
import br.ifsp.husaocarlos.domain.entities.Professor;

public class RegisterActionUseCase {

    ActionDAO actionDAO;

    public RegisterActionUseCase(ActionDAO actionDAO) {
        this.actionDAO = actionDAO;
    }

    public boolean registerAction(Action action){
        if (action.getProfessor() == null){
            throw new IllegalArgumentException("professor cannot be null");
        }
        return actionDAO.save(action);
    }
}
