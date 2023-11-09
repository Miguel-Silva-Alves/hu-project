package br.ifsp.husaocarlos.domain.entities;

import javax.security.enterprise.credential.Password;

public class Student extends Person{
    Student(String email, Password password) {
        super(email, password);
    }
}
