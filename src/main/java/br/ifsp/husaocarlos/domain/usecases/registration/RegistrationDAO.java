package br.ifsp.husaocarlos.domain.usecases.registration;

import br.ifsp.husaocarlos.domain.entities.Action;
import br.ifsp.husaocarlos.domain.entities.Registration;
import br.ifsp.husaocarlos.domain.usecases.utils.DAO;

import java.util.List;
import java.util.Optional;

public interface RegistrationDAO extends DAO<Integer, Registration> {
    Optional<Registration> findbyActionStudent(Integer actionId, String studentId);
    List<Registration> findbyAction(Action action);
}
