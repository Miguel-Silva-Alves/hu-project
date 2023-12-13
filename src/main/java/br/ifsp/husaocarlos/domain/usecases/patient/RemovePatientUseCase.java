package br.ifsp.husaocarlos.domain.usecases.patient;

import br.ifsp.husaocarlos.domain.entities.Patient;
import br.ifsp.husaocarlos.domain.usecases.utils.exceptions.EntityNotFoundException;

public class RemovePatientUseCase {

    PatientDAO patientDAO;

    public RemovePatientUseCase(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    public boolean remove(String cpf){
        if(cpf == null || patientDAO.findOne(cpf).isEmpty()){
            throw new EntityNotFoundException("patient not found");
        }
        return patientDAO.delete(cpf);
    }

    public boolean remove(Patient patient){
        if(patient == null || patientDAO.findOne(patient.getCpf()).isEmpty()){
            throw new EntityNotFoundException("patient not found");
        }
        return patientDAO.delete(patient.getCpf());
    }

}
