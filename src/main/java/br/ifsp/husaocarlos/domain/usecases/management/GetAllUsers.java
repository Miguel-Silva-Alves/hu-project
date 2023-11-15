package br.ifsp.husaocarlos.domain.usecases.management;

import br.ifsp.husaocarlos.domain.entities.Management;
import br.ifsp.husaocarlos.domain.entities.Roles;
import br.ifsp.husaocarlos.domain.entities.User;
import br.ifsp.husaocarlos.domain.usecases.user.UserDAO;

import java.util.List;

public class GetAllUsers {

    UserDAO DAO;

    public GetAllUsers(UserDAO DAO) {
        if (DAO == null)
            throw new IllegalArgumentException();
        this.DAO = DAO;
    }

    public List<User> get(Management management) throws IllegalAccessException {
        if (management == null)
            throw new IllegalArgumentException("Null management !");
        if (management.role != Roles.Management)
            throw new IllegalAccessException("Not a management !");

        return management.getAllUsers(DAO);
    }
}
