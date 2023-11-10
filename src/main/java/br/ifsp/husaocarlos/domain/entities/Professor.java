package br.ifsp.husaocarlos.domain.entities;

import javax.security.enterprise.credential.Password;

public class Professor extends User {
    public Professor(String email, String cpf, String name, Password password, String adress, String registration, Roles role) {
        super(email, cpf, name, password, adress, registration, role);
    }
    public boolean setAppointment(){return true;}
    public void checkSchedule(){}
    public boolean setStudantInAction(){return true;}
    public String getHistory(){return null;}

}