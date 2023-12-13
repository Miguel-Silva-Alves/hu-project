package br.ifsp.husaocarlos.application.repository;

import br.ifsp.husaocarlos.domain.entities.Action;
import br.ifsp.husaocarlos.domain.entities.Patient;
import br.ifsp.husaocarlos.domain.usecases.patient.PatientDAO;

import java.util.*;
import java.util.stream.Collectors;

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
        return db.values().stream().toList();
    }

    @Override
    public boolean update(Patient object) {

        if(db.containsKey(object.getCpf())){
            db.replace(object.getCpf(), object);
            return true;
        }
        return false;

    }

    @Override
    public boolean delete(String cpf) {
        if(db.containsKey(cpf)){
            db.remove(cpf);
            return true;
        }
        return false;

    }

    @Override
    public List<Patient> findByName(String name) {
        ArrayList<Patient> patients = new ArrayList<>(db.values());
        return patients.stream()
                .filter(patient -> patient.getName().contains(name))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Patient> findByEmail(String email) {
        ArrayList<Patient> patients = new ArrayList<>(db.values());
        for(Patient patient: patients){
            if(patient.getEmail().equals(email)){
                return Optional.of(patient);
            }
        }
        return Optional.empty();
    }
}
