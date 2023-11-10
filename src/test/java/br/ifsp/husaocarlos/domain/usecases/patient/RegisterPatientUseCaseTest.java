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
        Patient patient = new Patient("1111111111", "Miguel", "miguel@email.com", "169999999", "Rua onde ele mora, 10");
        boolean inserted = registerPatientUseCase.insert(patient);
        Assertions.assertEquals(true, inserted);

        boolean inserted2 = registerPatientUseCase.insert(patient);
        Assertions.assertEquals(false, inserted2);
    }
}