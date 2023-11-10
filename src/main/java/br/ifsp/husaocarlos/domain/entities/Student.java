package br.ifsp.husaocarlos.domain.entities;

import javax.security.enterprise.credential.Password;

public class Student extends User {
    public Student(String email, String cpf, String name, Password password, String adress, String registration, Roles role) {
        super(email, cpf, name, password, adress, registration, role);
    }
}
