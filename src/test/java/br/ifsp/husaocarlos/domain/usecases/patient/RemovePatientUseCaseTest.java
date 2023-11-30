package br.ifsp.husaocarlos.domain.usecases.patient;

import br.ifsp.husaocarlos.application.repository.InMemoryPatientDAO;
import br.ifsp.husaocarlos.domain.entities.Patient;
import br.ifsp.husaocarlos.domain.usecases.utils.exceptions.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RemovePatientUseCaseTest {

    RemovePatientUseCase removePatientUseCase;

    @BeforeEach
    void setUp() {
        // DAO
        PatientDAO patientDAO = new InMemoryPatientDAO();

        // Usecase
        removePatientUseCase = new RemovePatientUseCase(patientDAO);

        // Saving patient
        Patient patient = new Patient("123456789", "miguel", "miguel@gmail.com", "16996057200", "testandosom");
        patientDAO.save(patient);
    }

    @Test
    void remove() {
        Throwable exception = assertThrows(EntityNotFoundException.class, () -> removePatientUseCase.remove("123456781"));
        assertEquals("patient not found", exception.getMessage());

        boolean exec = removePatientUseCase.remove("123456789");
        assertEquals(true, exec);
    }


    @Test
    void testRemove() {
        Patient p2 = new Patient("123456781", "miguel", "miguel@gmail.com", "16996057200", "testandosom");
        Throwable exception = assertThrows(EntityNotFoundException.class, () -> removePatientUseCase.remove(p2));
        assertEquals("patient not found", exception.getMessage());

        Patient p3 = new Patient("123456789", "miguel", "miguel@gmail.com", "16996057200", "testandosom");
        boolean exec = removePatientUseCase.remove(p3);
        assertEquals(true, exec);
    }
}