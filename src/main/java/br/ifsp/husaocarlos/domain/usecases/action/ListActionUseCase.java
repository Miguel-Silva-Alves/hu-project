package br.ifsp.husaocarlos.domain.usecases.action;

import br.ifsp.husaocarlos.domain.entities.Action;

import java.util.List;

public class ListActionUseCase {

    ActionDAO actionDAO;

    public ListActionUseCase(ActionDAO actionDAO) {
        this.actionDAO = actionDAO;
    }

    public List<Action> getActions(){
        return this.actionDAO.findAll();
    }
}
