package br.ifsp.husaocarlos.application.repository;

import br.ifsp.husaocarlos.domain.entities.LineOfCare;
import br.ifsp.husaocarlos.domain.entities.Patient;
import br.ifsp.husaocarlos.domain.usecases.management.LinesOfCareDAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryLinesOfCareDAO implements LinesOfCareDAO {
    private static final Map<String, LineOfCare> db = new HashMap<>();
    @Override
    public boolean save(LineOfCare object) {


        if (db.containsKey(object.getLineName())){
            return false;
        }
        db.put(object.getLineName(), object);
        return true;
    }

    @Override
    public Optional<LineOfCare> findOne(String key) {
        return Optional.empty();
    }

    @Override
    public List<LineOfCare> findAll() {
        return null;
    }

    @Override
    public boolean update(LineOfCare object) {

        return true;
    }

    @Override
    public boolean delete(String key) {
        return false;
    }
}
