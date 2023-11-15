package br.ifsp.husaocarlos.domain.usecases.patient;

import br.ifsp.husaocarlos.domain.usecases.utils.DAO;
import br.ifsp.husaocarlos.domain.entities.Patient;

import java.util.Optional;

public interface PatientDAO extends DAO<String, Patient> {
    Optional<Patient> findByCpf(String cpf);
}
