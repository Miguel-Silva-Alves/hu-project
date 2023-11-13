package br.ifsp.husaocarlos.domain.usecases.action;

import br.ifsp.husaocarlos.domain.entities.Action;
import br.ifsp.husaocarlos.domain.usecases.utils.DAO;

import java.util.Optional;

public interface ActionDAO extends DAO<Integer, Action>{
    Optional<Action> findByName(String name);
}
