package br.ifsp.husaocarlos.application.repository;

import br.ifsp.husaocarlos.domain.entities.Action;
import br.ifsp.husaocarlos.domain.entities.Patient;
import br.ifsp.husaocarlos.domain.usecases.action.ActionDAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryActionDAO implements ActionDAO {
    private static final Map<Integer, Action> db = new HashMap<>();
    @Override
    public Optional<Action> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public boolean save(Action object) {
        if (db.containsKey(object.getId())){
            return false;
        }
        db.put(object.getId(), object);
        return true;
    }

    @Override
    public Optional<Action> findOne(Integer key) {
        return Optional.empty();
    }

    @Override
    public List<Action> findAll() {
        return null;
    }

    @Override
    public void update(Integer key, Action object) {

    }

    @Override
    public boolean delete(Integer key) {
        return false;
    }
}
