package br.ifsp.husaocarlos.domain.usecases.management;

import br.ifsp.husaocarlos.application.repository.InMemoryLinesOfCareDAO;
import br.ifsp.husaocarlos.domain.entities.Professor;
import br.ifsp.husaocarlos.domain.entities.Roles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.security.enterprise.credential.Password;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterLineOfCareUseCaseTest {
    Professor professor;
    String lineName;
    LinesOfCareDAO DAO;

    @BeforeEach
    void setup(){
        Password professorPassword = new Password("1234");
        professor = new Professor(0,"prof.educador@gmail.com","579.456.789-56","João",professorPassword,"la na pqp",null, Roles.Professor);
        lineName = "Line1";
        DAO = new InMemoryLinesOfCareDAO();

    }

    @Test
    void registerLinesOfCareWithSucess(){
        RegisterLinesOfCareUseCase registerLinesOfCareUseCase = new RegisterLinesOfCareUseCase(DAO);
        boolean result = registerLinesOfCareUseCase.register(lineName,professor);

        assertTrue(result);
    }

    @Test
    void registerLinesOfCareWithNullLineName(){
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,() ->{
            RegisterLinesOfCareUseCase registerLinesOfCareUseCase = new RegisterLinesOfCareUseCase(DAO);
            registerLinesOfCareUseCase.register(null,professor);
        });

        assertEquals(exception.getClass(),IllegalArgumentException.class);
    }

    @Test
    void registerLinesOfCareWithNullProfessorName(){
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,() ->{
            RegisterLinesOfCareUseCase registerLinesOfCareUseCase = new RegisterLinesOfCareUseCase(DAO);
            registerLinesOfCareUseCase.register(lineName,null);
        });

        assertEquals(exception.getClass(),IllegalArgumentException.class);
    }

    @Test
    void registerLinesOfCareWithNullDAO(){
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,() ->{
            RegisterLinesOfCareUseCase registerLinesOfCareUseCase = new RegisterLinesOfCareUseCase(null);
        });

        assertEquals(exception.getClass(),IllegalArgumentException.class);
    }
}
