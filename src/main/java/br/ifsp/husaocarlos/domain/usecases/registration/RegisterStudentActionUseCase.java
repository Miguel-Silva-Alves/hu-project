package br.ifsp.husaocarlos.domain.usecases.registration;
import br.ifsp.husaocarlos.domain.entities.Action;
import br.ifsp.husaocarlos.domain.entities.Registration;
import br.ifsp.husaocarlos.domain.entities.Student;

import java.util.Optional;

public class RegisterStudentActionUseCase {

    RegistrationDAO registrationDAO;

    public RegisterStudentActionUseCase( RegistrationDAO registrationDAO) {
        this.registrationDAO = registrationDAO;
    }

    public boolean includeStudentAction(Action action, Student student){
        Optional<Registration> registration = registrationDAO.findbyActionStudent(action.getId(), student.getCpf());
        if (registration.isPresent()){
            return false;
        }
        Registration newRegistration = new Registration(student.getCpf(), action.getId());
        this.registrationDAO.save(newRegistration);
        return true;
    }
}
