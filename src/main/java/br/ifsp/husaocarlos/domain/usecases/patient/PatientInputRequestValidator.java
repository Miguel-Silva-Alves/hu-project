package br.ifsp.husaocarlos.domain.usecases.patient;

import br.ifsp.husaocarlos.domain.entities.Patient;
import br.ifsp.husaocarlos.domain.usecases.utils.CheckCPF;
import br.ifsp.husaocarlos.domain.usecases.utils.Notification;
import br.ifsp.husaocarlos.domain.usecases.utils.Validador;

public class PatientInputRequestValidator extends Validador<Patient> {

    public Notification validate(Patient patient){
        Notification notification = new Notification();
        if(patient == null){
            notification.addError("user is null");
            return notification;
        }

        if (nullOrEmpty(patient.getCpf())){
            notification.addError("cpf is null or empty");
        }else if(!CheckCPF.checkCpf(patient.getCpf())){
            notification.addError("cpf is not valid");
        }

        if (nullOrEmpty(patient.getName())){
            notification.addError("name is null or empty");
        }

        if (nullOrEmpty(patient.getEmail())){
            notification.addError("email is null or empty");
        }else if(!patient.getEmail().contains("@") || !patient.getEmail().contains(".com")){
            notification.addError("email is not valid");
        }

        if (nullOrEmpty(patient.getPhone())){
            notification.addError("phone is null or empty");
        }

        if (nullOrEmpty(patient.getAddress())){
            notification.addError("address is null or empty");
        }

        if (nullOrEmpty(String.valueOf(patient.getRegisterTime()))){
            notification.addError("register time is null or empty");
        }

        return notification;
    }

}
