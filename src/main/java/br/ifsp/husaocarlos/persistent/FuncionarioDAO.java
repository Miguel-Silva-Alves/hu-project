package br.ifsp.husaocarlos.persistent;

import br.ifsp.husaocarlos.model.Person;

import java.util.List;
import java.util.Optional;

public class FuncionarioDAO implements DAO<String,Person>{

    @Override
    public boolean save(Person object) {
        return false;
    }

    @Override
    public Optional<Person> findOne(String key) {
        return Optional.empty();
    }

    @Override
    public List<Person> findAll() {
        return null;
    }

    @Override
    public void update(String key, Person object) {

    }

    @Override
    public boolean delete(String key) {
        return false;
    }
}
