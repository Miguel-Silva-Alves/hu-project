package br.ifsp.husaocarlos.domain.usecases.action;

import br.ifsp.husaocarlos.domain.entities.Action;
import br.ifsp.husaocarlos.domain.entities.Professor;

import java.util.List;
import java.util.Optional;

public class FindActionUseCase {
    ActionDAO actionDAO;

    public FindActionUseCase(ActionDAO actionDAO) {
        this.actionDAO = actionDAO;
    }

    public List<Action> findAll(){
        return this.actionDAO.findAll();
    }

    public Optional<Action> findActionByName(String name){
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException("name cannot be null");
        }
        return actionDAO.findByName(name);
    }

    public List<Action> findActionsOfProfessor(Professor professor){
        if(professor == null){
            throw new IllegalArgumentException("professor cannot be null");
        }
        return actionDAO.findByProfessor(professor);
    }

}
