package br.ifsp.husaocarlos.application.repository;

import br.ifsp.husaocarlos.domain.entities.LineOfCare;
import br.ifsp.husaocarlos.domain.usecases.management.LinesOfCareDAO;

import java.util.List;
import java.util.Optional;

public class InMemoryLinesOfCareDAO implements LinesOfCareDAO {

    @Override
    public boolean save(LineOfCare object) {
        return false;
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
    public boolean update(String key, LineOfCare object) {
        return false;
    }

    @Override
    public boolean delete(String key) {
        return false;
    }
}
