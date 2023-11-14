package br.ifsp.husaocarlos.domain.usecases.registration;

import br.ifsp.husaocarlos.domain.entities.Action;
import br.ifsp.husaocarlos.domain.entities.Registration;
import br.ifsp.husaocarlos.domain.entities.Student;
import br.ifsp.husaocarlos.domain.entities.User;
import br.ifsp.husaocarlos.domain.usecases.user.UserDAO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListStudentOfActionUseCase {

    RegistrationDAO registrationDAO;
    UserDAO userDAO;

    public ListStudentOfActionUseCase(RegistrationDAO registrationDAO, UserDAO userDAO) {
        this.registrationDAO = registrationDAO;
        this.userDAO = userDAO;
    }

    public List<Student> listStudents(Action action){
        List<Registration> registrations = registrationDAO.findAll();
        return registrations.stream()
                .filter(registration -> registration.getActionId().equals(action.getId()))
                .map(registration -> userDAO.findOne(registration.getStudentId()))
                .collect(Collectors.toList())
                .stream()
                .flatMap(user -> user.map(Stream::of).orElseGet(Stream::empty))
                .map(user -> (Student) user)
                .collect(Collectors.toList());
    }

}
