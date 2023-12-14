package br.ifsp.husaocarlos.domain.usecases.user;

import br.ifsp.husaocarlos.domain.entities.User;
import br.ifsp.husaocarlos.domain.usecases.utils.CheckCPF;
import br.ifsp.husaocarlos.domain.usecases.utils.Hash;

import java.util.Optional;

public class LoginUserUseCase {

    private UserDAO userDAO;

    public LoginUserUseCase(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public Optional<User> loginCpf(String login, String password){
        String hashed = Hash.toHash(password);
        if (CheckCPF.checkCpf(login)) {
            return userDAO.findUserByCpf(login, hashed);
        }
        return userDAO.findUserByEmail(login, hashed);
    }

}
