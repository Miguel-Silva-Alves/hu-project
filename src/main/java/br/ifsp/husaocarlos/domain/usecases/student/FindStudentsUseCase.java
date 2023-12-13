package br.ifsp.husaocarlos.domain.usecases.student;

import br.ifsp.husaocarlos.domain.entities.Action;
import br.ifsp.husaocarlos.domain.entities.Professor;
import br.ifsp.husaocarlos.domain.entities.Registration;
import br.ifsp.husaocarlos.domain.entities.User;
import br.ifsp.husaocarlos.domain.entities.student.Student;
import br.ifsp.husaocarlos.domain.usecases.action.ActionDAO;
import br.ifsp.husaocarlos.domain.usecases.registration.ListStudentOfActionUseCase;
import br.ifsp.husaocarlos.domain.usecases.registration.RegistrationDAO;
import br.ifsp.husaocarlos.domain.usecases.user.UserDAO;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FindStudentsUseCase {
    private UserDAO userDAO;
    private ActionDAO actionDAO;
    private RegistrationDAO registrationDAO;

    public FindStudentsUseCase(UserDAO userDAO, ActionDAO actionDAO, RegistrationDAO registrationDAO) {
        this.userDAO = userDAO;
        this.actionDAO = actionDAO;
        this.registrationDAO = registrationDAO;
    }

    public List<Student> findAll(){
        List<User> users = userDAO.findAll();
        return users
                .stream()
                .filter(user -> user.isStudent())
                .map(user -> (Student) user)
                .collect(Collectors.toList());
    }

    public List<Student> findStudentsOfAction(Action action){
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

    public Optional<Student> findStudentByEmail(String email){
        List<User> users = userDAO.findAll();
        return users
                .stream()
                .filter(user -> user.isStudent())
                .map(user -> (Student) user)
                .filter(student -> student.getEmail().equals(email))
                .findFirst();
    }

    public Optional<Student> findStudentsOfActionbyEmail(Action action, String email){
        List<Registration> registrations = registrationDAO.findAll();
        return registrations.stream()
                .filter(registration -> registration.getActionId().equals(action.getId()))
                .map(registration -> userDAO.findOne(registration.getStudentId()))
                .collect(Collectors.toList())
                .stream()
                .flatMap(user -> user.map(Stream::of).orElseGet(Stream::empty))
                .map(user -> (Student) user)
                .filter(student -> student.getEmail().equals(email))
                .findFirst();
    }

}
