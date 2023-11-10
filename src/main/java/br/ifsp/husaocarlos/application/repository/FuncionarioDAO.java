package br.ifsp.husaocarlos.application.repository;

import br.ifsp.husaocarlos.domain.entities.User;

import java.util.List;
import java.util.Optional;

public class FuncionarioDAO implements DAO<String, User>{

    @Override
    public boolean save(User object) {
        return false;
    }

    @Override
    public Optional<User> findOne(String key) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void update(String key, User object) {

    }

    @Override
    public boolean delete(String key) {
        return false;
    }
}
