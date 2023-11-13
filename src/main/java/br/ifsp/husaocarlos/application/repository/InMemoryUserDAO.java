package br.ifsp.husaocarlos.application.repository;

import br.ifsp.husaocarlos.domain.entities.Action;
import br.ifsp.husaocarlos.domain.entities.User;
import br.ifsp.husaocarlos.domain.usecases.user.UserDAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryUserDAO implements UserDAO {
    private static final Map<Integer, User> db = new HashMap<>();

    @Override
    public boolean save(User object) {
       User newUser = db.put(object.getId(),object);
        return newUser == null;
    }

    @Override
    public Optional<User> findOne(Integer key) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public boolean update(Integer key, User object) {
        User modifiedUser = db.replace(key,object);
        return modifiedUser == null;
    }

    @Override
    public boolean delete(Integer key) {
        return false;
    }

}
