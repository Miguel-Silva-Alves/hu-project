package br.ifsp.husaocarlos.domain.usecases.patient;

import br.ifsp.husaocarlos.domain.usecases.utils.DAO;
import br.ifsp.husaocarlos.domain.entities.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientDAO extends DAO<String, Patient> {
    List<Patient> findByName(String name);
    Optional<Patient> findByEmail(String email);
}
