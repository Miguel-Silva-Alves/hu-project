package br.ifsp.husaocarlos.domain.usecases.action;

import br.ifsp.husaocarlos.domain.entities.Action;

import java.util.List;
import java.util.Optional;

public class FindActionUseCase {
    ActionDAO actionDAO;

    public FindActionUseCase(ActionDAO actionDAO) {
        this.actionDAO = actionDAO;
    }

    public List<Action> getActions(){
        return this.actionDAO.findAll();
    }

    public Optional<Action> getActionByName(String name){
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException("name cannot be null");
        }
        return actionDAO.findByName(name);
    }

}
