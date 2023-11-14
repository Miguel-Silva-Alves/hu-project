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
    private int idCounter = 0;
    @Override
    public boolean save(Action object) {
        if (object.getId() != -1){
            return false;
        }
        object.setId(idCounter);
        db.put(idCounter, object);
        idCounter++;
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
    public boolean update(Integer key, Action object) {
        return true;
    }

    @Override
    public boolean delete(Integer key) {
        return false;
    }
}
