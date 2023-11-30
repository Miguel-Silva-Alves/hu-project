package br.ifsp.husaocarlos.application.main;
import jakarta.persistence.*;
import br.ifsp.husaocarlos.domain.entities.Roles;
import br.ifsp.husaocarlos.domain.entities.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HUSystem extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HUSystem.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
//        // Criando um objeto da Classe user:
//        User user = new User(1,"gabriel.rufino05@gmail.com","568.694.788-98","Gabriel","1234","Rua longe",null, Roles.Student,true);
//
//
//        EntityManagerFactory factory = Persistence.createEntityManagerFactory("hospital");
//
//        EntityManager em = factory.createEntityManager();
//        // Iniciando uma transação:
//        em.getTransaction().begin();
//        // Gravando o objeto no banco de dados:
//        em.persist(user);
//        // "Comitando" a transação:
//        em.getTransaction().commit();
//
//        em.close();
        launch();
    }
}