package br.ifsp.husaocarlos.domain.entities;

import javax.security.enterprise.credential.Password;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Professor extends User {
    private final HashMap<String, List<Action>> linesOfCare = new HashMap<>();

    public Professor(Integer id, String email, String cpf, String name, Password password, String adress, String registration, Roles role) {
        super(id, email, cpf, name, password, adress, registration, role);
    }

    public boolean addNewLineOfCare(String LineName) throws Exception {

        List<Action> list = linesOfCare.get(LineName);
        if (list == null) {
            linesOfCare.put(LineName, new ArrayList<Action>());
            return true;
        }
        else
            throw new Exception("Line of Care already exists");

    }
    public boolean setAppointment(){return true;}
    public void checkSchedule(){}
    public boolean setStudantInAction(){return true;}
    public String getHistory(){return null;}

}