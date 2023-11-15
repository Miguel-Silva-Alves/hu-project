package br.ifsp.husaocarlos.application.repository;

import br.ifsp.husaocarlos.domain.entities.User;

import java.util.HashMap;

public class Repository {
    private static HashMap<String, User> banco;

    private Repository() {}

    public static HashMap<String, User> getDatabase() {
        if (banco == null)
            banco = new HashMap<>();
        return banco;
    }
}
