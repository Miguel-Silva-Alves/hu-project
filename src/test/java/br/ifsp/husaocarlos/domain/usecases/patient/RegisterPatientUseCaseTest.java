package br.ifsp.husaocarlos.domain.usecases.patient;

import br.ifsp.husaocarlos.application.repository.InMemoryPatientDAO;
import br.ifsp.husaocarlos.domain.entities.Patient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RegisterPatientUseCaseTest {
    @Test
    public void insert(){
        PatientDAO dao = new InMemoryPatientDAO();
        RegisterPatientUseCase registerPatientUseCase = new RegisterPatientUseCase(dao);
        Patient patient = new Patient();
        boolean inserted = registerPatientUseCase.insert(patient);
        Assertions.assertEquals(true, inserted);
    }
}