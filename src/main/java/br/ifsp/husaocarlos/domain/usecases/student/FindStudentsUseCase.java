package br.ifsp.husaocarlos.domain.usecases.student;

import br.ifsp.husaocarlos.domain.entities.Action;
import br.ifsp.husaocarlos.domain.entities.Professor;
import br.ifsp.husaocarlos.domain.entities.User;
import br.ifsp.husaocarlos.domain.entities.student.Student;
import br.ifsp.husaocarlos.domain.usecases.action.ActionDAO;
import br.ifsp.husaocarlos.domain.usecases.registration.ListStudentOfActionUseCase;
import br.ifsp.husaocarlos.domain.usecases.registration.RegistrationDAO;
import br.ifsp.husaocarlos.domain.usecases.user.UserDAO;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<Student> findStudentsOfProfessor(Professor professor){
        ListStudentOfActionUseCase lsa = new ListStudentOfActionUseCase(registrationDAO, userDAO);
        List<Action> actions = actionDAO.findByProfessor(professor);
        LinkedHashSet hashSet = new LinkedHashSet();
        for(Action action: actions){
            hashSet.addAll(lsa.listStudents(action));
        }
        return hashSet.stream().toList();
    }

}
