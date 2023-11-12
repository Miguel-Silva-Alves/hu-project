package br.ifsp.husaocarlos.domain.entities;

import br.ifsp.husaocarlos.application.repository.InMemoryPatientDAO;
import br.ifsp.husaocarlos.domain.usecases.patient.PatientDAO;
import br.ifsp.husaocarlos.domain.usecases.patient.RegisterPatientUseCase;

import javax.security.enterprise.credential.Password;

public class Receptionist extends User {
    public Receptionist(String email, String cpf, String name, Password password, String adress, String registration, Roles role) {
        super(email, cpf, name, password, adress, registration, role);
    }
    public boolean registerPatient(Patient patient){
        PatientDAO DAO = new InMemoryPatientDAO();
        RegisterPatientUseCase registerPatientUseCase = new RegisterPatientUseCase(DAO);
        return registerPatientUseCase.insert(patient);
    }


}
