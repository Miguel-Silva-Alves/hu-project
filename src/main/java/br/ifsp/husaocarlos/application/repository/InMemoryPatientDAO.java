package br.ifsp.husaocarlos.application.repository;

import br.ifsp.husaocarlos.domain.entities.Patient;
import br.ifsp.husaocarlos.domain.usecases.patient.PatientDAO;

import java.util.List;
import java.util.Optional;

public class InMemoryPatientDAO implements PatientDAO {

    @Override
    public boolean save(Patient object) {
        return true;
    }

    @Override
    public Optional<Patient> findOne(String key) {
        return Optional.empty();
    }

    @Override
    public List<Patient> findAll() {
        return null;
    }

    @Override
    public void update(String key, Patient object) {

    }

    @Override
    public boolean delete(String key) {
        return false;
    }

    @Override
    public Optional<Patient> findByCpf(String cpf) {
        return Optional.empty();
    }
}
