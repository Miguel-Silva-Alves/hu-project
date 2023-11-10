package br.ifsp.husaocarlos.domain.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActionTest {
    @Test
    public void compare(){
        Action action = new Action();
        int act = action.compare(10,20);
        Assertions.assertEquals(0, act);
    }
}