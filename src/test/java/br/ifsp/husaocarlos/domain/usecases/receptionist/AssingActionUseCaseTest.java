package br.ifsp.husaocarlos.domain.usecases.receptionist;

import br.ifsp.husaocarlos.domain.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.security.enterprise.credential.Password;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssingActionUseCaseTest {
    Action action;
    Professor professor;
    Patient patient;
    @BeforeEach
    public void setup(){
        Password professorPassword = new Password("1234");
        professor = new Professor("prof.educador@gmail.com","579.456.789-56","João",professorPassword,"la na pqp",null, Roles.Professor);
        action = new Action("Ação1","Urologista",professor,"LinhaDeCuidade1");
        patient = new Patient("567.894.784-42","Paulo","contato@email.com","(16)99356-8742","outro canto do mundo");

    }
    @Test
    public void AssingActionWithSucess(){
        AssingActionUseCase assingActionUseCase = new AssingActionUseCase(action,patient);
        boolean result = assingActionUseCase.AssingAction();
        assertTrue(result);
    }

}
