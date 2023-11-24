package br.ifsp.husaocarlos.domain.usecases.patient;

import br.ifsp.husaocarlos.application.repository.InMemoryPatientDAO;
import br.ifsp.husaocarlos.domain.entities.Patient;
import br.ifsp.husaocarlos.domain.usecases.utils.CheckCPF;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreatePatientUseCaseTest {

    @Test
    void insert() {

        // Dao and UseCase
        PatientDAO patientDAO = new InMemoryPatientDAO();
        CreatePatientUseCase createPatientUseCase = new CreatePatientUseCase(patientDAO);

        // Correct fields
        Patient patient = new Patient("97537062005", "miguelzinho", "checkemail@email.com", "999999", "miguel");
        boolean exec = createPatientUseCase.insert(patient);
        assertEquals(true, exec);

        // Incorrect email
        Patient patient2 = new Patient("97537062005", "miguelzinho", "checkemailemail.com", "999999", "miguel");
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> createPatientUseCase.insert(patient2));
        assertEquals("email is not valid", exception.getMessage());

    }

    @Test
    void cpf() {
        String cpf = "97537062005";
        boolean exec = CheckCPF.checkCpf(cpf);
        assertEquals(true, exec);
    }
}