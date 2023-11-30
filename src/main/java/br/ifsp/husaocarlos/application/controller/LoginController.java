package br.ifsp.husaocarlos.application.controller;
import br.ifsp.husaocarlos.application.repository.MySqlUserDAO;
import br.ifsp.husaocarlos.domain.entities.User;
import br.ifsp.husaocarlos.domain.usecases.user.CreateUpdateUserUseCase;
import br.ifsp.husaocarlos.domain.usecases.user.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.Optional;

public class LoginController {

    @FXML
    private Button login_btn;

    @FXML
    private PasswordField password_tf;

    @FXML
    private TextField username_tf;

    private User currentLoggedUser;

    @FXML
    void loginCurrentUser(MouseEvent event) {
        UserDAO dao = new MySqlUserDAO();
        Optional<User> user = dao.findUserByLogin(username_tf.getText(),password_tf.getText());
        if (user.isPresent()){
            currentLoggedUser = user.get();
            System.out.println(currentLoggedUser);
        }

    }

}

