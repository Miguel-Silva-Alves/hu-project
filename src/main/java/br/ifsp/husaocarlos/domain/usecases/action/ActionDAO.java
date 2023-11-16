package br.ifsp.husaocarlos.domain.usecases.action;

import br.ifsp.husaocarlos.domain.entities.Action;
import br.ifsp.husaocarlos.domain.entities.Professor;
import br.ifsp.husaocarlos.domain.usecases.utils.DAO;

import java.util.List;
import java.util.Optional;

public interface ActionDAO extends DAO<Integer, Action>{
    Optional<Action> findByName(String name);
    List<Action> findByProfessor(Professor professor);
}
