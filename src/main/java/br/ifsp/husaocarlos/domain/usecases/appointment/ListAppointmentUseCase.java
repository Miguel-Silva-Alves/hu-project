package br.ifsp.husaocarlos.domain.usecases.appointment;

import br.ifsp.husaocarlos.application.view.App;
import br.ifsp.husaocarlos.domain.entities.Action;
import br.ifsp.husaocarlos.domain.entities.Patient;
import br.ifsp.husaocarlos.domain.entities.Professor;
import br.ifsp.husaocarlos.domain.entities.appointment.AppointmentStatus;
import br.ifsp.husaocarlos.domain.entities.student.Student;
import br.ifsp.husaocarlos.domain.entities.appointment.Appointment;
import br.ifsp.husaocarlos.domain.usecases.action.ActionDAO;
import br.ifsp.husaocarlos.domain.usecases.registration.ListStudentOfActionUseCase;
import br.ifsp.husaocarlos.domain.usecases.registration.RegistrationDAO;
import br.ifsp.husaocarlos.domain.usecases.student.GetPatientsOfStudentUseCase;
import br.ifsp.husaocarlos.domain.usecases.user.UserDAO;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Appointment> findAppointmentsOfStudent(Student student){
        return appointmentDAO.getAppointmentStudent(student);
    }

    public List<Appointment> findAppointmentsOfStudentbyPatient(Student student, String namePatient){
        return appointmentDAO
                .getAppointmentStudent(student)
                .stream()
                .filter(appointment -> appointment.getPatient().getName().equals(namePatient))
                .collect(Collectors.toList());
    }

    // Fix
    public List<Appointment> findAppointmentsOfStudentbyDate(Student student, String date){
        return appointmentDAO
                .getAppointmentStudent(student)
                .stream()
                .filter(appointment -> appointment.getDateOfAppointment().equals(date))
                .collect(Collectors.toList());
    }

    public List<Appointment> findAppointmentsOfStudentbyAction(Student student, String actionName){
        return appointmentDAO
                .getAppointmentStudent(student)
                .stream()
                .filter(appointment -> appointment.getActionObject().getName().equals(actionName))
                .collect(Collectors.toList());
    }

    public List<Appointment> findAppointmentsOfStudentbyStatus(Student student, String appointmentStatus){
        AppointmentStatus status;
        try{
            status = AppointmentStatus.valueOf(appointmentStatus);
        }catch (IllegalArgumentException illegalArgumentException){
            throw new IllegalArgumentException("status is not recognized");
        }

        return appointmentDAO
                .getAppointmentStudent(student)
                .stream()
                .filter(appointment -> appointment.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    public List<Appointment> getAppointmentsOfPatient(Patient patient, Action action){
        if(patient.getCpf() == null || patient.getCpf().isEmpty()){
            throw new IllegalArgumentException("cpf cannot be null");
        }

        if(action == null){
            // get all appointments
            return appointmentDAO.getAppointmentPatient(patient);
        }
        return appointmentDAO
                .getAppointmentPatient(patient)
                .stream()
                .filter(appointment -> appointment.getActionObject().getName().equals(action.getName()))
                .toList();

    }

    public List<Appointment> findAll(){
        return appointmentDAO.findAll();
    }

    public List<Appointment> findAppointmentOfAction(Action action, Patient patient) {
        if (action == null || action.getId() == null) {
            throw new IllegalArgumentException("action cannot be null");
        }
        if (patient == null){
            // all appointments of some action
            return appointmentDAO.getAppointmentAction(action);
        }
        return appointmentDAO
                .getAppointmentAction(action)
                .stream()
                .filter(appointment -> appointment.getPatient().getCpf().equals(patient.getCpf()))
                .collect(Collectors.toList());
    }

}
