package br.ifsp.husaocarlos.domain.usecases.receptionist;

import br.ifsp.husaocarlos.domain.entities.Action;
import br.ifsp.husaocarlos.domain.entities.Patient;
import br.ifsp.husaocarlos.domain.entities.User;

public class AssingActionUseCase {
    private Action action;
    private Patient patient;

    public AssingActionUseCase(Action action, Patient patient) {
        if (action == null || patient == null)
            throw new IllegalArgumentException();
        this.action = action;
        this.patient = patient;
    }

    public boolean AssingAction(){
        return action.reciveNewPatient(patient);
    }
}
