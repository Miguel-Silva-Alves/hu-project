package br.ifsp.husaocarlos.domain.usecases.patient;

import br.ifsp.husaocarlos.application.repository.DAO;
import br.ifsp.husaocarlos.domain.entities.Patient;
import br.ifsp.husaocarlos.domain.entities.Person;

import java.util.List;
import java.util.Optional;

public interface PatientDAO extends DAO<String, Patient> {
    Optional<Patient> findByCpf(String cpf);
}
