package br.ifsp.husaocarlos.domain.usecases.user;

import br.ifsp.husaocarlos.domain.entities.User;
import br.ifsp.husaocarlos.domain.usecases.utils.CheckCPF;
import br.ifsp.husaocarlos.domain.usecases.utils.Validador;

import java.util.Optional;

public class FindUserUseCase {

    UserDAO userDAO;

    public FindUserUseCase(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public Optional<User> getUserByCPF(String cpf){
        if(Validador.nullOrEmpty(cpf)){
            throw new IllegalArgumentException("cpf can not be null");
        }
        if (!CheckCPF.checkCpf(cpf)){
            throw new IllegalArgumentException("cpf is not valid");
        }
        return userDAO.findOne(cpf);
    }

}
