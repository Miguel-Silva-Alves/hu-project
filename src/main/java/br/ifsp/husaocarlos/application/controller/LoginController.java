package br.ifsp.husaocarlos.application.controller;
import br.ifsp.husaocarlos.application.repository.InMemoryUserDAO;
import br.ifsp.husaocarlos.application.view.App;
import br.ifsp.husaocarlos.domain.entities.Roles;
import br.ifsp.husaocarlos.domain.entities.User;
import br.ifsp.husaocarlos.domain.usecases.user.FindUserUseCase;
import br.ifsp.husaocarlos.domain.usecases.user.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
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
    void loginCurrentUser(MouseEvent event) throws IOException {
        UserDAO dao = new InMemoryUserDAO();
        User user = new User("teste@gmail.com",
                "99998964059",
                "miguel",
                "password",
                "endereco",
                "idk",
                Roles.Receptionist);
        dao.save(user);

        // Use Case
        FindUserUseCase findUserUseCase = new FindUserUseCase(dao);
        Optional<User> userOptional = findUserUseCase.getUserByCPF(username_tf.getText());
        if (userOptional.isPresent()){
            currentLoggedUser = userOptional.get();
            UserHolder userHolder = UserHolder.getInstance();
            userHolder.setUser(currentLoggedUser);
            switch (currentLoggedUser.getRole()){
                case Receptionist -> App.setRoot("HomeReceptionist");
            }
        }

    }

}

