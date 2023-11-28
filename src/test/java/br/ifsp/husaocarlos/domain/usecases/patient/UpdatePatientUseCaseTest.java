package br.ifsp.husaocarlos.domain.usecases.patient;

import br.ifsp.husaocarlos.application.repository.InMemoryPatientDAO;
import br.ifsp.husaocarlos.domain.entities.Patient;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UpdatePatientUseCaseTest {

    @Test
    void update() {
        PatientDAO patientDAO = new InMemoryPatientDAO();

        // Saving patient
        Patient patient = new Patient("97537062005", "miguel", "miguel@gmail.com", "16996057200", "testandosom");
        patientDAO.save(patient);

        patient.setEmail("joaozinho@gmail.com");
        patient.setName("JOão");
        patient.setPhone("123123");

        UpdatePatientUseCase updatePatientUseCase = new UpdatePatientUseCase(patientDAO);
        boolean exec = updatePatientUseCase.update(patient);
        assertEquals(true, exec);

        FindPatientUseCase findPatientUseCase = new FindPatientUseCase(patientDAO);
        Optional<Patient> p2 = findPatientUseCase.findByCpf(patient.getCpf());
        assertEquals(true, p2.isPresent());

        System.out.println(p2.get());

    }
}