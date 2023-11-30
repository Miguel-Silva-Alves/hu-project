package br.ifsp.husaocarlos.domain.usecases.action;

import br.ifsp.husaocarlos.domain.entities.Action;

public class EditActionUseCase {

    private final ActionDAO actionDAO;

    public EditActionUseCase(ActionDAO actionDAO) {
        this.actionDAO = actionDAO;
    }

    public boolean edit(Integer id, Action action){
        return actionDAO.update(action);
    }

}
