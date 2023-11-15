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
    public Roles role;

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
    public User(String email, String cpf, String name, Password password, String adress, String registration, Roles role) {
        this.id = -1;
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
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                ", role=" + role +
                '}';
    }

    public String getName() {
        return name;
    }
}
