package br.ifsp.husaocarlos.domain.usecases.management;

import br.ifsp.husaocarlos.domain.entities.Professor;
import br.ifsp.husaocarlos.domain.entities.Roles;
import br.ifsp.husaocarlos.domain.usecases.receptionist.AssingActionUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.security.enterprise.credential.Password;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterLinesOfCareTest {
    Professor professor;
    String lineName;
    @BeforeEach
    void setup(){
        Password professorPassword = new Password("1234");
        professor = new Professor("prof.educador@gmail.com","579.456.789-56","João",professorPassword,"la na pqp",null, Roles.Professor);
        lineName = "Line1";
    }

    @Test
    void registerLinesOfCareWithSucess(){
        RegisterLinesOfCare registerLinesOfCare = new RegisterLinesOfCare(lineName,professor);
        boolean result = registerLinesOfCare.register();

        assertTrue(result);
    }

    @Test
    void registerLinesOfCareWithNullLineName(){
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,() ->{
            RegisterLinesOfCare registerLinesOfCare = new RegisterLinesOfCare(null,professor);
        });

        assertEquals(exception.getClass(),IllegalArgumentException.class);
    }

    @Test
    void registerLinesOfCareWithNullProfessorName(){
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,() ->{
            RegisterLinesOfCare registerLinesOfCare = new RegisterLinesOfCare(lineName,null);
        });

        assertEquals(exception.getClass(),IllegalArgumentException.class);
    }
}
