package br.ifsp.husaocarlos.domain.usecases.patient;

import br.ifsp.husaocarlos.domain.entities.Patient;
import br.ifsp.husaocarlos.domain.usecases.utils.EntityNotFoundException;

public class RemovePatientUseCase {

    PatientDAO patientDAO;

    public RemovePatientUseCase(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    public boolean remove(String cpf){
        if(cpf == null || patientDAO.findByCpf(cpf).isEmpty()){
            throw new EntityNotFoundException("patient not found");
        }
        return patientDAO.delete(cpf);
    }

    public boolean remove(Patient patient){
        if(patient == null || patientDAO.findByCpf(patient.getCpf()).isEmpty()){
            throw new EntityNotFoundException("patient not found");
        }
        return patientDAO.delete(patient.getCpf());
    }

}
