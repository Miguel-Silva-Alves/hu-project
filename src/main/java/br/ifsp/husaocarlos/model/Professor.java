package br.ifsp.husaocarlos.model;

import javax.security.enterprise.credential.Password;

public class Professor extends Person{
    Professor(String email, Password password) {
        super(email, password);
    }
}
