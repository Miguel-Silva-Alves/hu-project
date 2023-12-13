package br.ifsp.husaocarlos.domain.entities.student;

import br.ifsp.husaocarlos.domain.entities.Roles;
import br.ifsp.husaocarlos.domain.entities.User;

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

    public Student(User user) {
        super(user.getEmail(), user.getCpf(), user.getName(), user.getPassword(), user.getAddress(), user.getRegistration(), Roles.Student, user.getActive());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
