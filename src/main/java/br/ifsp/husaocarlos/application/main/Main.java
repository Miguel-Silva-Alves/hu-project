package br.ifsp.husaocarlos.application.main;

import br.ifsp.husaocarlos.application.persistence.MySqlActionDAO;
import br.ifsp.husaocarlos.application.persistence.MySqlUserDAO;
import br.ifsp.husaocarlos.application.view.App;
import br.ifsp.husaocarlos.domain.entities.Action;
import br.ifsp.husaocarlos.domain.entities.User;

public class Main {

    public static void main(String[] args) {
        MySqlActionDAO dao = new MySqlActionDAO();
        System.out.println();
        App.main(args);
    }
}
