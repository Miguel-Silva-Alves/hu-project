package br.ifsp.husaocarlos.persistent;

import br.ifsp.husaocarlos.model.Person;

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
