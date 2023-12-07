package br.ifsp.husaocarlos.application.controller;

import br.ifsp.husaocarlos.domain.entities.Receptionist;
import br.ifsp.husaocarlos.domain.entities.User;

public class HomeReceptionist {

    public HomeReceptionist() {
        UserHolder userHolder = UserHolder.getInstance();
        User u = userHolder.getUser();
        Receptionist receptionist = new Receptionist(u);
        System.out.printf(receptionist.toString());

    }


}
