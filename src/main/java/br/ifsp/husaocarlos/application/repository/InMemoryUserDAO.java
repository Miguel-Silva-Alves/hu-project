package br.ifsp.husaocarlos.application.repository;
import br.ifsp.husaocarlos.domain.entities.User;
import br.ifsp.husaocarlos.domain.usecases.user.UserDAO;
import java.util.*;

public class InMemoryUserDAO implements UserDAO {


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
    public boolean update(String key, User object) {
        return false;
    }

    @Override
    public boolean delete(String key) {
        return false;
    }
}
