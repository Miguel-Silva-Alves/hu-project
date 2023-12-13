package br.ifsp.husaocarlos.domain.usecases.patient;

import br.ifsp.husaocarlos.domain.entities.Patient;
import br.ifsp.husaocarlos.domain.usecases.utils.Validador;

import java.util.List;
import java.util.Optional;

public class FindPatientUseCase {

    PatientDAO patientDAO;

    public FindPatientUseCase(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    public Optional<Patient> findByCpf(String cpf){
        if(Validador.nullOrEmpty(cpf)){
            throw new IllegalArgumentException("cpf can not be null");
        }
        return patientDAO.findOne(cpf);
    }

    public List<Patient> findByName(String name){
        if(Validador.nullOrEmpty(name)){
            throw new IllegalArgumentException("name can not be null");
        }
        return patientDAO.findByName(name);
    }

    public Optional<Patient> findByEmail(String email){
        if(Validador.nullOrEmpty(email)){
            throw new IllegalArgumentException("email can not be null");
        }
        return patientDAO.findByEmail(email);
    }

    public List<Patient> findAll(){
        return patientDAO.findAll();
    }

}
