package br.ifsp.husaocarlos.domain.entities;

import org.junit.jupiter.api.Test;

import javax.security.enterprise.credential.Password;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void passwordIsValid() {

        User user = new User("miguel.dev@gmail.com","410.852.512-57","miguel","senha123",
                "rua aldo milanetto,176","13345",Roles.Professor, true);

        boolean ret = user.passwordIsValid("senha123");
        boolean ret2 = user.passwordIsValid("senha1234");
        assertEquals(true, ret);
        assertEquals(false, ret2);
    }
}