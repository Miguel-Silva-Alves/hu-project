package br.ifsp.husaocarlos.domain.usecases.receptionist;

import br.ifsp.husaocarlos.domain.entities.Action;
import br.ifsp.husaocarlos.domain.entities.Patient;
import br.ifsp.husaocarlos.domain.entities.User;
import br.ifsp.husaocarlos.domain.usecases.action.ActionDAO;

public class AssingActionUseCase {
    private ActionDAO DAO;

    public AssingActionUseCase(ActionDAO DAO) {
        if (DAO == null)
            throw new IllegalArgumentException();
        this.DAO = DAO;
    }

    public boolean AssingAction(Action action, Patient patient){
        if (action == null || patient == null)
            throw new IllegalArgumentException();
        if (action.reciveNewPatient(patient)){
            return DAO.update(action);
        }
        return false;
    }
}
