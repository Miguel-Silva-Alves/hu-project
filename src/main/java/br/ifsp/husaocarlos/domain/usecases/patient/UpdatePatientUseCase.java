package br.ifsp.husaocarlos.domain.usecases.patient;

import br.ifsp.husaocarlos.domain.entities.Patient;
import br.ifsp.husaocarlos.domain.usecases.utils.EntityNotFoundException;
import br.ifsp.husaocarlos.domain.usecases.utils.Notification;
import br.ifsp.husaocarlos.domain.usecases.utils.Validador;

public class UpdatePatientUseCase {

    private PatientDAO patientDAO;

    public UpdatePatientUseCase(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    public boolean update(Patient patient){
        Validador<Patient> validador = new PatientInputRequestValidator();
        Notification notification = validador.validate(patient);

        if (notification.hasErros()){
            throw new IllegalArgumentException(notification.errorMessage());
        }

        String cpf = patient.getCpf();
        if(patientDAO.findByCpf(cpf).isEmpty()){
            throw new EntityNotFoundException("patient not found");
        }

        return patientDAO.update(cpf, patient);

    }

}
