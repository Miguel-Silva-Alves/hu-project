package br.ifsp.husaocarlos.application.repository;

import br.ifsp.husaocarlos.domain.entities.Patient;
import br.ifsp.husaocarlos.domain.entities.User;
import br.ifsp.husaocarlos.domain.usecases.user.UserDAO;

import java.util.*;

public class InMemoryUserDAO implements UserDAO {
    private static final Map<String, User> db = new HashMap<>();

    public Optional<User> findUserByCPF(String cpf) {
        if(db.containsKey(cpf)){
            return Optional.of(db.get(cpf));
        }
        return Optional.empty();
    }

    @Override
    public boolean save(User object) {
        if(db.containsKey(object.getCpf())){
            return false;
        }
        db.put(object.getCpf(), object);
        return true;
    }

    @Override
    public Optional<User> findOne(String key) {
        System.out.println(new ArrayList<>(db.values()));
        if(db.containsKey(key)){
            return Optional.of(db.get(key));
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(User object) {
        return false;
    }

    @Override
    public boolean delete(String key) {
        return false;
    }

    @Override
    public Optional<User> findUserByCpf(String cpf, String passwordHashed) {
        if (db.containsKey(cpf)) {
            if(db.get(cpf).getPassword().equals(passwordHashed)){
                return Optional.of(db.get(cpf));
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findUserByEmail(String email, String passwordHashed) {
        return Optional.empty();
    }
}
