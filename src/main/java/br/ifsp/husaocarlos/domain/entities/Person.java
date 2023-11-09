package br.ifsp.husaocarlos.domain.entities;

import javax.security.enterprise.credential.Password;

public class Person {

    String email;
    Password password;

    Person(String email, Password password){
        this.email = email;
        this.password = password;
    }

    boolean passwordIsValid(Password password){
        System.out.println(password);
        return true;
    }


}
