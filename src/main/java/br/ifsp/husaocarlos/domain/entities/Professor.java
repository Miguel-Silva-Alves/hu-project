package br.ifsp.husaocarlos.domain.entities;

import br.ifsp.husaocarlos.domain.usecases.patient.PatientDAO;
import jakarta.persistence.*;

import javax.security.enterprise.credential.Password;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Entity
@DiscriminatorValue("Professor")
public class Professor extends User {
    @OneToMany(mappedBy = "Professor", cascade = CascadeType.ALL,orphanRemoval = true)
    public List<Action> actions = new ArrayList<Action>();

    public Professor(Integer id, String email, String cpf, String name, String password, String adress, String registration, Roles role, boolean isActive) {
        super(email, cpf, name, password, adress, registration, role, isActive);
    }

    public Professor(String email, String cpf, String name, String password, String adress, String registration, Roles role) {
        super(email, cpf, name, password, adress, registration, role);
    }

    public Professor() {
    }

    public Professor(User user) {
        super(user.getEmail(), user.getCpf(), user.getName(), user.getPassword(), user.getAddress(), user.getRegistration(), Roles.Professor, user.getActive());
    }

    public boolean setAppointment(){return true;}
    public void checkSchedule(){}
    public boolean setStudantInAction(){return true;}
    public String getHistory(){return null;}
    public List<Patient> viewPatients(PatientDAO DAO){
        return DAO.findAll();
    }

}