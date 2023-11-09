package br.ifsp.husaocarlos.domain.usecases.patient;

import br.ifsp.husaocarlos.domain.entities.Patient;

public class RegisterPatientUseCase {

    private PatientDAO patientDAO;

    public RegisterPatientUseCase(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    public boolean insert(Patient patient){
        return patientDAO.save(patient);
    }

}
