package br.ifsp.husaocarlos.domain.entities;

import br.ifsp.husaocarlos.domain.usecases.management.LinesOfCareDAO;
import br.ifsp.husaocarlos.domain.usecases.user.UserDAO;

import javax.security.enterprise.credential.Password;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Management extends User {
    public Management(Integer id, String email, String cpf, String name, Password password, String adress, String registration, Roles role) {
        super(id, email, cpf, name, password, adress, registration, role);
    }
    public List<LineOfCare> getLinesOfCare(LinesOfCareDAO DAO){
        return DAO.findAll();
    }

    public List<User> getAllUsers(UserDAO DAO){
        return DAO.findAll();
    }

}
