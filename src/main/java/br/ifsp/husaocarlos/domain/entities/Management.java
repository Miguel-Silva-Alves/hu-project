package br.ifsp.husaocarlos.domain.entities;
import br.ifsp.husaocarlos.domain.usecases.management.LinesOfCareDAO;
import br.ifsp.husaocarlos.domain.usecases.user.UserDAO;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.List;

@Entity
@DiscriminatorValue("Management")
public class Management extends User {

    public Management(String email, String cpf, String name, String password, String adress, String registration, Roles role, boolean isActive) {
        super(email, cpf, name, password, adress, registration, role, isActive);
    }

    public Management() {
    }

    public List<LineOfCare> getLinesOfCare(LinesOfCareDAO DAO){
        return DAO.findAll();
    }

    public List<User> getAllUsers(UserDAO DAO){
        return DAO.findAll();
    }

}
