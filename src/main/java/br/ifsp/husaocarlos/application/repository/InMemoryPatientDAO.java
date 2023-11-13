package br.ifsp.husaocarlos.application.repository;

import br.ifsp.husaocarlos.domain.entities.Patient;
import br.ifsp.husaocarlos.domain.usecases.patient.PatientDAO;

import java.util.*;

public class InMemoryPatientDAO implements PatientDAO {

    private static final Map<String, Patient> db = new HashMap<>();

    @Override
    public boolean save(Patient object) {
        if (db.containsKey(object.getCpf())){
            return false;
        }
        db.put(object.getCpf(), object);
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
