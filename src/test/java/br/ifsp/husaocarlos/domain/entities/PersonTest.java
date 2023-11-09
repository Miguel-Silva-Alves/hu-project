package br.ifsp.husaocarlos.domain.entities;

import org.junit.jupiter.api.Test;

import javax.security.enterprise.credential.Password;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    @Test
    void passwordIsValid() {
        Password password = new Password("senha123");
        Person person = new Person("miguel", password);

        boolean ret = person.passwordIsValid("senha123");
        boolean ret2 = person.passwordIsValid("senha1234");
        assertEquals(true, ret);
        assertEquals(false, ret2);
    }
}