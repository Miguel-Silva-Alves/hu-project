package br.ifsp.husaocarlos.domain.entities;

import javax.security.enterprise.credential.Password;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Professor extends User {
    private final HashMap<String, List<Action>> linesOfCare = new HashMap<>();
    public Professor(String email, String cpf, String name, Password password, String adress, String registration, Roles role) {
        super(email, cpf, name, password, adress, registration, role);
    }
    public boolean addNewLineOfCare(String LineName){
        try{
            linesOfCare.put(LineName,new ArrayList<Action>());
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public boolean setAppointment(){return true;}
    public void checkSchedule(){}
    public boolean setStudantInAction(){return true;}
    public String getHistory(){return null;}

}