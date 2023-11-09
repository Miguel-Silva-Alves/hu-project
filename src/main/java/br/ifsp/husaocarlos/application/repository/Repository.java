package br.ifsp.husaocarlos.application.repository;

import br.ifsp.husaocarlos.domain.entities.Person;

import java.util.HashMap;

public class Repository {
    private static HashMap<String, Person> banco;

    private Repository() {}

    public static HashMap<String, Person> getDatabase() {
        if (banco == null)
            banco = new HashMap<>();
        return banco;
    }
}
