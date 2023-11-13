package br.ifsp.husaocarlos.domain.entities;

import javax.security.enterprise.credential.Password;

public class User {

    Integer id;
    String email;
    String cpf;
    String name;
    Password password;
    String adress;
    String registration;
    Roles role;

    public User(Integer id, String email, String cpf, String name, Password password, String adress, String registration, Roles role) {
        this.id = id;
        this.email = email;
        this.cpf = cpf;
        this.name = name;
        this.password = password;
        this.adress = adress;
        this.registration = registration;
        this.role = role;
    }

    boolean passwordIsValid(String password){
        return this.password.compareTo(password);
    }

    public Integer getId() {
        return id;
    }
}
