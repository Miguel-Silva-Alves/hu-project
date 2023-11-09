package br.ifsp.husaocarlos.model;

import javax.security.enterprise.credential.Password;

public class Receptionist extends Person{
    Receptionist(String email, Password password) {
        super(email, password);
    }
}
