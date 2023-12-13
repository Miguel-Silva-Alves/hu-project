package br.ifsp.husaocarlos.domain.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import javax.security.enterprise.credential.Password;
@Entity
@DiscriminatorValue("Student")
public class Student extends User {
    public Student(String email, String cpf, String name, String password, String adress, String registration, Roles role, boolean isActive) {
        super(email, cpf, name, password, adress, registration, role, isActive);
    }


    public Student(String email, String cpf, String name, String password, String adress, String registration, Roles role) {
        super(email, cpf, name, password, adress, registration, role);
    }


    public Student() {
    }
}
