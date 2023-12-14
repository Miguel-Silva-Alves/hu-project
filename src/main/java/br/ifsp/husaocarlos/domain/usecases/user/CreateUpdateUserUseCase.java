package br.ifsp.husaocarlos.domain.usecases.user;

import br.ifsp.husaocarlos.domain.entities.User;
import br.ifsp.husaocarlos.domain.usecases.utils.Hash;

public class CreateUpdateUserUseCase {

    UserDAO userDAO;

    public CreateUpdateUserUseCase(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean createUser(User user){
        return userDAO.save(user);
    }

    public boolean updateUser(User user){
        return userDAO.update(user);
    }



}
