package br.ifsp.husaocarlos.domain.entities;

import javax.security.enterprise.credential.Password;

public class Management extends User {
    public Management(Integer id, String email, String cpf, String name, Password password, String adress, String registration, Roles role) {
        super(id, email, cpf, name, password, adress, registration, role);
    }
}
