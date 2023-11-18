package br.ifsp.husaocarlos.domain.usecases.user;

import br.ifsp.husaocarlos.domain.entities.User;
import br.ifsp.husaocarlos.domain.entities.appointment.Appointment;
import br.ifsp.husaocarlos.domain.usecases.appointment.ListAppointmentUseCase;

import java.util.List;

public class CrudUserCase {

    UserDAO userDAO;

    public CrudUserCase(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean createUser(User user){
        return userDAO.save(user);
    }

    public boolean updateUser(User user){
        return userDAO.update(user.getId(), user);
    }



}
