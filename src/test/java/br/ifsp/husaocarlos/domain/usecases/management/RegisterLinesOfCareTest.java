package br.ifsp.husaocarlos.domain.usecases.management;

import br.ifsp.husaocarlos.application.repository.InMemoryUserDAO;
import br.ifsp.husaocarlos.domain.entities.Professor;
import br.ifsp.husaocarlos.domain.entities.Roles;
import br.ifsp.husaocarlos.domain.usecases.receptionist.AssingActionUseCase;
import br.ifsp.husaocarlos.domain.usecases.user.UserDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.security.enterprise.credential.Password;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterLinesOfCareTest {
    Professor professor;
    String lineName;
    UserDAO DAO;
    boolean result;
    @BeforeEach
    void setup(){
        Password professorPassword = new Password("1234");
        professor = new Professor(0,"prof.educador@gmail.com","579.456.789-56","João",professorPassword,"la na pqp",null, Roles.Professor);
        lineName = "Line1";
        DAO = new InMemoryUserDAO();
    }

    @Test
    void registerLinesOfCareWithSucess(){
        RegisterLinesOfCare registerLinesOfCare = new RegisterLinesOfCare(DAO);
        final Exception exception = assertThrows(Exception.class,() ->{
            result = registerLinesOfCare.register(lineName,professor);
        });

        assertTrue(result);
    }

    @Test
    void registerLinesOfCareWithNullLineName(){
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,() ->{
            RegisterLinesOfCare registerLinesOfCare = new RegisterLinesOfCare(DAO);
            registerLinesOfCare.register(null,professor);
        });

        assertEquals(exception.getClass(),IllegalArgumentException.class);
    }

    @Test
    void registerLinesOfCareWithNullProfessorName(){
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,() ->{
            RegisterLinesOfCare registerLinesOfCare = new RegisterLinesOfCare(DAO);
            registerLinesOfCare.register(lineName,null);
        });

        assertEquals(exception.getClass(),IllegalArgumentException.class);
    }

    @Test
    void registerLinesOfCareWithNullDAO(){
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,() ->{
            RegisterLinesOfCare registerLinesOfCare = new RegisterLinesOfCare(null);
        });

        assertEquals(exception.getClass(),IllegalArgumentException.class);
    }
}
