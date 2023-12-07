package br.ifsp.husaocarlos.domain.usecases.user;

import br.ifsp.husaocarlos.domain.entities.User;
import br.ifsp.husaocarlos.domain.usecases.utils.DAO;

import java.util.Optional;

public interface UserDAO extends DAO<String, User> {
    public Optional<User> findUserByLogin(String username,String password);

    public Optional<User> findUserByCPF(String cpf);

}
