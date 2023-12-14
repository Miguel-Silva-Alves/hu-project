package br.ifsp.husaocarlos.domain.usecases.professor;

import br.ifsp.husaocarlos.application.repository.InMemoryPatientDAO;
import br.ifsp.husaocarlos.domain.entities.Patient;
import br.ifsp.husaocarlos.domain.entities.Professor;
import br.ifsp.husaocarlos.domain.entities.Roles;
import br.ifsp.husaocarlos.domain.usecases.management.RegisterLinesOfCareUseCase;
import br.ifsp.husaocarlos.domain.usecases.patient.PatientDAO;
import br.ifsp.husaocarlos.domain.usecases.patient.RegisterPatientUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.security.enterprise.credential.Password;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class getAllPatientsTest {

    PatientDAO DAO;
    Professor professor;
    Patient patient;

    @BeforeEach
    void setup(){
        DAO = new InMemoryPatientDAO();
        professor = new Professor("prof.educador@gmail.com","579.456.789-56","João","123456","la na pqp",null, Roles.Professor, true);
        patient = new Patient("1111111111", "Miguel", "miguel@email.com", "169999999", "Rua onde ele mora, 10");
        RegisterPatientUseCase registerPatientUseCase = new RegisterPatientUseCase(DAO);
        registerPatientUseCase.insert(patient);
    }

    @Test
    void getAllPatientsWithSucess() throws IllegalAccessException {
        getAllPatients getAllPatients = new getAllPatients(DAO);
        List<Patient> patientList = getAllPatients.get(professor);
        assertEquals(patient,patientList.get(0));
    }

    @Test
    void getAllPatientsWithNullDAO(){
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,() ->{
            getAllPatients getAllPatients = new getAllPatients(null);
        });

        assertEquals(exception.getClass(),IllegalArgumentException.class);
    }

    @Test
    void getAllPatientsWithNullProfessor(){
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,() ->{
            getAllPatients getAllPatients = new getAllPatients(DAO);
            getAllPatients.get(null);
        });
        assertEquals(exception.getClass(),IllegalArgumentException.class);
    }
}
