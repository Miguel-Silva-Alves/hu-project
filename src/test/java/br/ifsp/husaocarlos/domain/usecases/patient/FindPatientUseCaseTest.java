package br.ifsp.husaocarlos.domain.usecases.patient;

import br.ifsp.husaocarlos.application.repository.InMemoryPatientDAO;
import br.ifsp.husaocarlos.domain.entities.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class FindPatientUseCaseTest {
    FindPatientUseCase findPatientUseCase;

    @BeforeEach
    void setUp() {
        // DAO
        PatientDAO patientDAO = new InMemoryPatientDAO();

        // Usecase
        findPatientUseCase = new FindPatientUseCase(patientDAO);

        // Saving patient
        Patient patient = new Patient("123456789", "miguel", "miguel@gmail.com", "16996057200", "testandosom");
        patientDAO.save(patient);
    }

    @Test
    void findByCpf() {
        // CPF Incorrect
        String cpf = "123445566";
        Optional<Patient> optionalPatient = findPatientUseCase.findByCpf(cpf);
        assertEquals(false, optionalPatient.isPresent());

        // CPF Correct
        cpf = "123456789";
        optionalPatient = findPatientUseCase.findByCpf(cpf);
        assertEquals(true, optionalPatient.isPresent());

        // CPF Empty
        final String cpf3 = "";
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> findPatientUseCase.findByCpf(cpf3));
        assertEquals("cpf can not be null", exception.getMessage());
    }

    @Test
    void findByName() {
        // Name Ausent
        String name = "gar";
        List<Patient> patients = findPatientUseCase.findByName(name);
        assertEquals(true, patients.isEmpty());

        // Name Correct
        name = "miguel";
        patients = findPatientUseCase.findByName(name);
        assertEquals(false, patients.isEmpty());

        // Name Empty
        final String name3 = "";
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> findPatientUseCase.findByName(name3));
        assertEquals("name can not be null", exception.getMessage());
    }

    @Test
    void findByEmail() {
        // Email Incorrect
        String email = "migu@gmail.com";
        Optional<Patient> optionalPatient = findPatientUseCase.findByEmail(email);
        assertEquals(false, optionalPatient.isPresent());

        // Email Correct
        email = "miguel@gmail.com";
        optionalPatient = findPatientUseCase.findByEmail(email);
        assertEquals(true, optionalPatient.isPresent());

        // Email Empty
        final String email3 = "";
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> findPatientUseCase.findByEmail(email3));
        assertEquals("email can not be null", exception.getMessage());
    }

    @Test
    void findAll() {
        // Get all Patients
        List<Patient> patients = findPatientUseCase.findAll();
        assertEquals(false, patients.isEmpty());
    }
}