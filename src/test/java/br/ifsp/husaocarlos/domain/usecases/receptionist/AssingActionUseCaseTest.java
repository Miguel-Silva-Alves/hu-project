package br.ifsp.husaocarlos.domain.usecases.receptionist;
import br.ifsp.husaocarlos.application.repository.InMemoryActionDAO;
import br.ifsp.husaocarlos.application.repository.InMemoryPatientDAO;
import br.ifsp.husaocarlos.domain.entities.*;
import br.ifsp.husaocarlos.domain.usecases.action.ActionDAO;
import br.ifsp.husaocarlos.domain.usecases.patient.PatientDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class AssingActionUseCaseTest {
    Action action;
    Professor professor;
    LineOfCare lineOfCare;
    Patient patient;
    ActionDAO DAO;
    PatientDAO patientDAO;
    @BeforeEach
    public void setup(){
        professor = new Professor("prof.educador@gmail.com","579.456.789-56","João","1234","la na pqp",null, Roles.Professor, true);

        lineOfCare = new LineOfCare("LinhaDeCuidade1",new ArrayList<>(),professor);
        action = new Action("Ação1","Urologista",professor,lineOfCare);

        patient = new Patient("567.894.784-42","Paulo","contato@email.com","(16)99356-8742","outro canto do mundo");
        DAO = new InMemoryActionDAO();
        patientDAO = new InMemoryPatientDAO();

    }
    @Test
    public void AssingActionWithSucess(){
        AssingActionUseCase assingActionUseCase = new AssingActionUseCase(DAO);
        boolean result = assingActionUseCase.AssingAction(action,patient);
        assertTrue(result);
    }

    @Test
    public void AssingActionWithNullPatient(){
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,() ->{
            AssingActionUseCase assingActionUseCase = new AssingActionUseCase(DAO);
            assingActionUseCase.AssingAction(action,null);
        });

        assertEquals(exception.getClass(),IllegalArgumentException.class);
    }

    @Test
    public void AssingActionWithNullAction(){
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,() ->{
            AssingActionUseCase assingActionUseCase = new AssingActionUseCase(DAO);
            assingActionUseCase.AssingAction(null,patient);
        });
        assertEquals(exception.getClass(),IllegalArgumentException.class);
    }

    @Test
    public void AssingActionWithNullDAO(){
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,() ->{
            AssingActionUseCase assingActionUseCase = new AssingActionUseCase(null);
        });
        assertEquals(exception.getClass(),IllegalArgumentException.class);
    }

}
