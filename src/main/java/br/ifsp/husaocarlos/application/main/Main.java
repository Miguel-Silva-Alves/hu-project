package br.ifsp.husaocarlos.application.main;
import br.ifsp.husaocarlos.application.persistence.*;
import br.ifsp.husaocarlos.application.repository.*;
import br.ifsp.husaocarlos.application.view.App;
import br.ifsp.husaocarlos.domain.entities.*;
import br.ifsp.husaocarlos.domain.entities.student.Student;
import br.ifsp.husaocarlos.domain.usecases.action.ActionDAO;
import br.ifsp.husaocarlos.domain.usecases.action.FindActionUseCase;
import br.ifsp.husaocarlos.domain.usecases.action.RegisterActionUseCase;
import br.ifsp.husaocarlos.domain.usecases.appointment.*;
import br.ifsp.husaocarlos.domain.usecases.management.LinesOfCareDAO;
import br.ifsp.husaocarlos.domain.usecases.patient.CreatePatientUseCase;
import br.ifsp.husaocarlos.domain.usecases.patient.FindPatientUseCase;
import br.ifsp.husaocarlos.domain.usecases.patient.PatientDAO;
import br.ifsp.husaocarlos.domain.usecases.patient.UpdatePatientUseCase;
import br.ifsp.husaocarlos.domain.usecases.professor.FindLineOfCareUseCase;
import br.ifsp.husaocarlos.domain.usecases.receptionist.AssingActionUseCase;
import br.ifsp.husaocarlos.domain.usecases.registration.ListStudentOfActionUseCase;
import br.ifsp.husaocarlos.domain.usecases.registration.RegisterStudentActionUseCase;
import br.ifsp.husaocarlos.domain.usecases.registration.RegistrationDAO;
import br.ifsp.husaocarlos.domain.usecases.student.FindStudentsUseCase;
import br.ifsp.husaocarlos.domain.usecases.user.FindUserUseCase;
import br.ifsp.husaocarlos.domain.usecases.user.GetNextHourFreeStudentUseCase;
import br.ifsp.husaocarlos.domain.usecases.user.LoginUserUseCase;
import br.ifsp.husaocarlos.domain.usecases.user.UserDAO;
import java.util.ArrayList;
import br.ifsp.husaocarlos.domain.entities.Action;
import br.ifsp.husaocarlos.domain.entities.User;

public class Main {

    // USECASE
    public static LoginUserUseCase loginUserUseCase;
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
    public static UpdateAppointmentUseCase updateAppointmentUseCase;
    public static FindLineOfCareUseCase findLineOfCareUseCase;
    public static AssingActionUseCase assingActionUseCase;

    // DAOS
    private static ActionDAO actionDAO;
    private static RegistrationDAO registrationDAO;
    private static UserDAO userDAO;
    private static PatientDAO patientDAO;
    private static AppointmentDAO appointmentDAO;
    private static LinesOfCareDAO linesOfCareDAO;


    public static void main(String[] args) {
        configureDaos();
        configureInjection();
        //populateFakeDatabase();
        //populateDatabase();
        App.main(args);
    }

    private static void configureDaos(){

        actionDAO = new MySqlActionDAO();
        userDAO = new MySqlUserDAO();
        patientDAO = new MySqlPatientDAO();
        registrationDAO = new MySqlRegistrationDAO();
        appointmentDAO = new MySqlAppointmentDAO();
        linesOfCareDAO = new MySqlLinesOfCareDAO();
    }

    private static void populateFakeDatabase(){

        // Management
        User management = new Management("admin@gmail.com","91328945809","tomas","password","casa 2 rua 180","teste",Roles.Management,true);
        userDAO.save(management);

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

        LineOfCare line = new LineOfCare("LinhaDeCuidade1",new ArrayList<Action>(),professor);

        // Action
        Action action = new Action("action1","Urologista", professor,line);
        Action action2 = new Action("action2","Dermatologista", professor,line);
        actionDAO.save(action);
        actionDAO.save(action2);


        // Registration
        Registration newRegistration = new Registration(student.getCpf(), action.getId());
        registrationDAO.save(newRegistration);

        Patient patient = new Patient("40293742049", "Miguel", "email@gmail.com", "169999999", "Rua lá longe");
        patientDAO.save(patient);

        SchedulePatientToAppointmentUseCase sch = new SchedulePatientToAppointmentUseCase(appointmentDAO, registrationDAO, userDAO);
        String dataa = "02/12/2023 12:10";
        sch.scheduleWithDate(action, patient, student, dataa);
    }
    private static void populateDatabase(){

<<<<<<< HEAD
        // Management
        User management = new Management("admin@gmail.com","91328945809","tomas","password","casa 2 rua 180","teste",Roles.Management,true);
        userDAO.save(management);

=======
>>>>>>> e190843780b12b55177867940c2ea8193cdd2f20
        // Recepcionist
        User user = new Receptionist("teste@gmail.com", "99998964059", "miguel", "password", "endereco", "", Roles.Receptionist, true);
        userDAO.save(user);

        // Student
        Student student = new Student("student@gmail.com", "23812205009", "Aluno1", "password", "endereco", "idk", Roles.Student);
        userDAO.save(student);

        // Professor
        Professor professor = new Professor("professor@gmail.com", "73885307030", "Professor1", "password", "endereco", "idk", Roles.Professor);
        userDAO.save(professor);


        LineOfCare line = new LineOfCare("LinhaDeCuidade1",new ArrayList<>(),professor);
        linesOfCareDAO.save(line);

        // Action
        Action action = new Action("action1","Urologista", professor,line);
        System.out.println("Action1");
        System.out.println(actionDAO.save(action));


        // Registration
        Registration newRegistration = new Registration(student.getCpf(), action.getId());
        registrationDAO.save(newRegistration);

        PatientDAO patientDAO = new MySqlPatientDAO();
        Patient patient = new Patient("16098760080", "Miguel", "email@gmail.com", "169999999", "Rua lá longe");
        patientDAO.save(patient);

        AppointmentDAO appointmentDAO = new MySqlAppointmentDAO();
        SchedulePatientToAppointmentUseCase sch = new SchedulePatientToAppointmentUseCase(appointmentDAO, registrationDAO, userDAO);
        String dataa = "02/12/2023 12:10";
        System.out.println(sch.scheduleWithDate(action, patient, student, dataa));
    }

    private static void configureInjection() {
        // User
        loginUserUseCase = new LoginUserUseCase(userDAO);
        findUserUseCase = new FindUserUseCase(userDAO);

        // Patient
        createPatientUseCase = new CreatePatientUseCase(patientDAO);
        findPatientUseCase = new FindPatientUseCase(patientDAO);
        updatePatientUseCase = new UpdatePatientUseCase(patientDAO);

        // Action
        findActionUseCase = new FindActionUseCase(actionDAO);
        registerActionUseCase = new RegisterActionUseCase(actionDAO);
        assingActionUseCase = new AssingActionUseCase(actionDAO);

        // Registration
        listStudentOfActionUseCase = new ListStudentOfActionUseCase(registrationDAO, userDAO);
        findStudentsUseCase = new FindStudentsUseCase(userDAO, actionDAO, registrationDAO);
        registerStudentActionUseCase = new RegisterStudentActionUseCase(registrationDAO);

        // Appoitment
        getNextHourFreeStudentUseCase = new GetNextHourFreeStudentUseCase(userDAO, appointmentDAO);
        schedulePatientToAppointmentUseCase = new SchedulePatientToAppointmentUseCase(appointmentDAO, registrationDAO, userDAO);
        listAppointmentUseCase = new ListAppointmentUseCase(appointmentDAO, userDAO, actionDAO, registrationDAO);
        dischargePatient = new DischargePatient(appointmentDAO);
        updateAppointmentUseCase = new UpdateAppointmentUseCase(appointmentDAO);

        // Line Of Care
        findLineOfCareUseCase = new FindLineOfCareUseCase(linesOfCareDAO);
    }
}
