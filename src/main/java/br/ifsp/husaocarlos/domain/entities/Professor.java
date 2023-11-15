package br.ifsp.husaocarlos.domain.entities;

import br.ifsp.husaocarlos.domain.usecases.patient.PatientDAO;

import javax.security.enterprise.credential.Password;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Professor extends User {

    public Professor(Integer id, String email, String cpf, String name, Password password, String adress, String registration, Roles role) {
        super(id, email, cpf, name, password, adress, registration, role);
    }
    public Professor(String email, String cpf, String name, Password password, String adress, String registration, Roles role) {
        super(email, cpf, name, password, adress, registration, role);
    }

    public boolean setAppointment(){return true;}
    public void checkSchedule(){}
    public boolean setStudantInAction(){return true;}
    public String getHistory(){return null;}
    public List<Patient> viewPatients(PatientDAO DAO){
        return DAO.findAll();
    }

}