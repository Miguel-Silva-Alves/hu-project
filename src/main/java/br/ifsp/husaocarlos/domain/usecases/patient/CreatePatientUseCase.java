package br.ifsp.husaocarlos.domain.usecases.patient;

import br.ifsp.husaocarlos.domain.entities.Patient;
import br.ifsp.husaocarlos.domain.usecases.utils.EntityAlreadyExistsException;
import br.ifsp.husaocarlos.domain.usecases.utils.Notification;
import br.ifsp.husaocarlos.domain.usecases.utils.Validador;

public class CreatePatientUseCase {

    private PatientDAO patientDAO;

    public CreatePatientUseCase(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    public boolean insert(Patient patient){
        Validador<Patient> validador = new PatientInputRequestValidator();
        Notification notification = validador.validate(patient);

        if (notification.hasErros()){
            throw new IllegalArgumentException(notification.errorMessage());
        }

        if (patientDAO.findByCpf(patient.getCpf()).isPresent()){
            throw new EntityAlreadyExistsException("this cpf is already in use");
        }

        return patientDAO.save(patient);
    }



}
