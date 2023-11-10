package br.ifsp.husaocarlos.domain.entities;

import javax.security.enterprise.credential.Password;

public class Management extends User {
    public Management(String email, String cpf, String name, Password password, String adress, String registration, Roles role) {
        super(email, cpf, name, password, adress, registration, role);
    }
}
