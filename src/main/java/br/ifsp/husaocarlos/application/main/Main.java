package br.ifsp.husaocarlos.application.main;

import br.ifsp.husaocarlos.application.repository.*;
import br.ifsp.husaocarlos.application.view.App;
import br.ifsp.husaocarlos.domain.entities.*;
import br.ifsp.husaocarlos.domain.entities.student.Student;
import br.ifsp.husaocarlos.domain.usecases.action.ActionDAO;
import br.ifsp.husaocarlos.domain.usecases.action.FindActionUseCase;
import br.ifsp.husaocarlos.domain.usecases.action.RegisterActionUseCase;
import br.ifsp.husaocarlos.domain.usecases.appointment.AppointmentDAO;
import br.ifsp.husaocarlos.domain.usecases.appointment.DischargePatient;
import br.ifsp.husaocarlos.domain.usecases.appointment.ListAppointmentUseCase;
import br.ifsp.husaocarlos.domain.usecases.appointment.SchedulePatientToAppointmentUseCase;
import br.ifsp.husaocarlos.domain.usecases.patient.CreatePatientUseCase;
import br.ifsp.husaocarlos.domain.usecases.patient.FindPatientUseCase;
import br.ifsp.husaocarlos.domain.usecases.patient.PatientDAO;
import br.ifsp.husaocarlos.domain.usecases.patient.UpdatePatientUseCase;
import br.ifsp.husaocarlos.domain.usecases.registration.ListStudentOfActionUseCase;
import br.ifsp.husaocarlos.domain.usecases.registration.RegisterStudentActionUseCase;
import br.ifsp.husaocarlos.domain.usecases.registration.RegistrationDAO;
import br.ifsp.husaocarlos.domain.usecases.student.FindStudentsUseCase;
import br.ifsp.husaocarlos.domain.usecases.user.FindUserUseCase;
import br.ifsp.husaocarlos.domain.usecases.user.GetNextHourFreeStudentUseCase;
import br.ifsp.husaocarlos.domain.usecases.user.UserDAO;

import br.ifsp.husaocarlos.domain.entities.Action;
import br.ifsp.husaocarlos.domain.entities.User;

public class Main {

    // USECASE
    public static FindUserUseCase findUserUseCase;
    public static CreatePatientUseCase createPatientUseCase;
    public static FindPatientUseCase findPatientUseCase;
    public static UpdatePatientUseCase updatePatientUseCase;

    public static SchedulePatientToAppointmentUseCase schedulePatientToAppointmentUseCase;

    public static RegisterActionUseCase registerActionUseCase;
    public static FindActionUseCase findActionUseCase;
    public static ListStudentOfActionUseCase listStudentOfActionUseCase;
    public static GetNextHourFreeStudentUseCase getNextHourFreeStudentUseCase;
    public static ListAppointmentUseCase listAppointmentUseCase;
    public static FindStudentsUseCase findStudentsUseCase;
    public static RegisterStudentActionUseCase registerStudentActionUseCase;
    public static DischargePatient dischargePatient;

    // DAOS
    private static ActionDAO actionDAO;
    private static RegistrationDAO registrationDAO;
    private static UserDAO userDAO;
    private static PatientDAO patientDAO;
    private static AppointmentDAO appointmentDAO;

    public static void main(String[] args) {
        configureDaos();
        configureInjection();
        populateFakeDatabase();
        //MySqlActionDAO dao = new MySqlActionDAO();
        //System.out.println();
        App.main(args);
    }

    private static void configureDaos(){

        actionDAO = new InMemoryActionDAO();
        userDAO = new InMemoryUserDAO();
        patientDAO = new InMemoryPatientDAO();
        registrationDAO = new InMemoryRegistrationDAO();
        appointmentDAO = new InMemoryAppointmentDAO();

    }

    private static void populateFakeDatabase(){
        // Recepcionist
        User user = new User("teste@gmail.com", "99998964059", "miguel", "password", "endereco", "", Roles.Receptionist);
        userDAO.save(user);

        // Student
        Student student = new Student("student@gmail.com", "23812205009", "Aluno1", "password", "endereco", "SC0001", Roles.Student);
        Student student2 = new Student("miguel@gmail.com", "06619184081", "Aluno2", "password", "endereco", "SC0002", Roles.Student);
        userDAO.save(student);
        userDAO.save(student2);

        // Professor
        Professor professor = new Professor("professor@gmail.com", "73885307030", "Professor1", "password", "endereco", "idk", Roles.Professor);
        userDAO.save(professor);

        // Action
        Action action = new Action("action1","Urologista", professor,"LinhaDeCuidade1");
        actionDAO.save(action);


        // Registration
        Registration newRegistration = new Registration(student.getCpf(), action.getId());
        registrationDAO.save(newRegistration);

        Patient patient = new Patient("16098760080", "Miguel", "email@gmail.com", "169999999", "Rua lá longe");
        patientDAO.save(patient);

        SchedulePatientToAppointmentUseCase sch = new SchedulePatientToAppointmentUseCase(appointmentDAO, registrationDAO, userDAO);
        String dataa = "02/12/2023 12:10";
        sch.scheduleWithDate(action, patient, student, dataa);
    }



    private static void configureInjection() {
        // User
        findUserUseCase = new FindUserUseCase(userDAO);

        // Patient
        createPatientUseCase = new CreatePatientUseCase(patientDAO);
        findPatientUseCase = new FindPatientUseCase(patientDAO);
        updatePatientUseCase = new UpdatePatientUseCase(patientDAO);

        // Action
        findActionUseCase = new FindActionUseCase(actionDAO);
        registerActionUseCase = new RegisterActionUseCase(actionDAO);

        // Registration
        listStudentOfActionUseCase = new ListStudentOfActionUseCase(registrationDAO, userDAO);
        findStudentsUseCase = new FindStudentsUseCase(userDAO, actionDAO, registrationDAO);
        registerStudentActionUseCase = new RegisterStudentActionUseCase(registrationDAO);

        // Appoitment
        getNextHourFreeStudentUseCase = new GetNextHourFreeStudentUseCase(userDAO, appointmentDAO);
        schedulePatientToAppointmentUseCase = new SchedulePatientToAppointmentUseCase(appointmentDAO, registrationDAO, userDAO);
        listAppointmentUseCase = new ListAppointmentUseCase(appointmentDAO, userDAO, actionDAO, registrationDAO);
        dischargePatient = new DischargePatient(appointmentDAO);
    }
}
