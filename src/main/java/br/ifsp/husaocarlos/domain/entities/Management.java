package br.ifsp.husaocarlos.domain.entities;

import javax.security.enterprise.credential.Password;

public class Management extends Person{
    Management(String email, Password password) {
        super(email, password);
    }
}
