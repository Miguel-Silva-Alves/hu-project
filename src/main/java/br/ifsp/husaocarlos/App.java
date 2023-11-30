package br.ifsp.husaocarlos;
import jakarta.persistence.*;
import br.ifsp.husaocarlos.domain.entities.Roles;
import br.ifsp.husaocarlos.domain.entities.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        // Criando um objeto da Classe user:
        User user = new User("gabriel.rufino05@gmail.com","568.694.788-98","Gabriel","123wo4","Rua longe",null, Roles.Student,true);


        EntityManagerFactory factory = Persistence.createEntityManagerFactory("hospital");

        EntityManager em = factory.createEntityManager();
        // Iniciando uma transação:
        em.getTransaction().begin();
        // Gravando o objeto no banco de dados:
        em.persist(user);
        // "Comitando" a transação:
        em.getTransaction().commit();

        em.close();
        launch();
    }
}