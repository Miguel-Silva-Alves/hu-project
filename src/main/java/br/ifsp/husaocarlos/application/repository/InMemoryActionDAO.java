package br.ifsp.husaocarlos.application.repository;

import br.ifsp.husaocarlos.domain.entities.Action;
import br.ifsp.husaocarlos.domain.entities.Patient;
import br.ifsp.husaocarlos.domain.entities.Professor;
import br.ifsp.husaocarlos.domain.usecases.action.ActionDAO;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryActionDAO implements ActionDAO {
    private static final Map<Integer, Action> db = new HashMap<>();
    @Override
    public Optional<Action> findByName(String name) {
        ArrayList<Action> actions = new ArrayList<>(db.values());
        return actions.stream().filter(action -> action.getName().equals(name)).findFirst();
    }

    @Override
    public List<Action> findByProfessor(Professor professor) {
        ArrayList<Action> actions = new ArrayList<>(db.values());
        return actions.stream()
                .filter(action -> action.getProfessor().getCpf().equals(professor.getCpf()))
                .collect(Collectors.toList());

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
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Action object) {
        return true;
    }

    @Override
    public boolean delete(Integer key) {
        return false;
    }
}
