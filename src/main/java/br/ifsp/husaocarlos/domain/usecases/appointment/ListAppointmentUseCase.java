package br.ifsp.husaocarlos.domain.usecases.appointment;

import br.ifsp.husaocarlos.domain.entities.Action;
import br.ifsp.husaocarlos.domain.entities.Patient;
import br.ifsp.husaocarlos.domain.entities.Professor;
import br.ifsp.husaocarlos.domain.entities.Student;
import br.ifsp.husaocarlos.domain.entities.appointment.Appointment;
import br.ifsp.husaocarlos.domain.usecases.action.ActionDAO;
import br.ifsp.husaocarlos.domain.usecases.action.RegisterActionUseCase;
import br.ifsp.husaocarlos.domain.usecases.patient.PatientDAO;
import br.ifsp.husaocarlos.domain.usecases.registration.ListStudentOfActionUseCase;
import br.ifsp.husaocarlos.domain.usecases.registration.RegistrationDAO;
import br.ifsp.husaocarlos.domain.usecases.student.GetPatientsOfStudentUseCase;
import br.ifsp.husaocarlos.domain.usecases.user.UserDAO;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;

public class ListAppointmentUseCase {

    AppointmentDAO appointmentDAO;
    UserDAO userDAO;
    ActionDAO actionDAO;
    RegistrationDAO registrationDAO;

    public ListAppointmentUseCase(AppointmentDAO appointmentDAO, UserDAO userDAO, ActionDAO actionDAO, RegistrationDAO registrationDAO) {
        this.appointmentDAO = appointmentDAO;
        this.userDAO = userDAO;
        this.actionDAO = actionDAO;
        this.registrationDAO = registrationDAO;
    }

    public List<Student> getStudentsOfProfessor(Professor professor){
        ListStudentOfActionUseCase lsa = new ListStudentOfActionUseCase(registrationDAO, userDAO);
        List<Action> actions = actionDAO.findByProfessor(professor);
        LinkedHashSet hashSet = new LinkedHashSet();
        for(Action action: actions){
            hashSet.addAll(lsa.listStudents(action));
        }
        return hashSet.stream().toList();
    }

    public List<Patient> getPatientsOfProfessor(Professor professor){
        List<Student> students = getStudentsOfProfessor(professor);

        if(students.isEmpty()){
            return new ArrayList<>();
        }

        LinkedHashSet hashSet = new LinkedHashSet();
        GetPatientsOfStudentUseCase gst = new GetPatientsOfStudentUseCase(appointmentDAO);
        for(Student student: students){
            hashSet.addAll(gst.getPatients(student));
        }
        return hashSet.stream().toList();

    }


    public List<Appointment> getAppointments(Professor professor, String cpf){
        List<Patient> patients = getPatientsOfProfessor(professor);
        if(patients.isEmpty()){
            return new ArrayList<>();
        }

        for(Patient patient: patients){
            if(patient.getCpf() == cpf){
                return appointmentDAO.getAppointmentPatient(patient);
            }
        }

        return new ArrayList<>();
    }

    public List<Appointment> getAppointments(Student student){
        return appointmentDAO.getAppointmentStudent(student);
    }

}
