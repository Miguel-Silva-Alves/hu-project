package br.ifsp.husaocarlos.domain.usecases.appointment;

import br.ifsp.husaocarlos.domain.entities.Action;
import br.ifsp.husaocarlos.domain.entities.Patient;
import br.ifsp.husaocarlos.domain.entities.student.Student;
import br.ifsp.husaocarlos.domain.entities.appointment.Appointment;
import br.ifsp.husaocarlos.domain.usecases.registration.ListStudentOfActionUseCase;
import br.ifsp.husaocarlos.domain.usecases.registration.RegistrationDAO;
import br.ifsp.husaocarlos.domain.usecases.user.GetNextHourFreeStudentUseCase;
import br.ifsp.husaocarlos.domain.usecases.user.UserDAO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SchedulePatientToAppointmentUseCase {

    AppointmentDAO appointmentDAO;
    RegistrationDAO registrationDAO;
    UserDAO userDAO;

    public SchedulePatientToAppointmentUseCase(AppointmentDAO appointmentDAO, RegistrationDAO registrationDAO, UserDAO userDAO) {
        this.appointmentDAO = appointmentDAO;
        this.registrationDAO = registrationDAO;
        this.userDAO = userDAO;
    }

    public Appointment schedule(Action action, Patient patient){
        // Lista de estudantes de uma action
        ListStudentOfActionUseCase listStudentOfActionUseCase = new ListStudentOfActionUseCase(registrationDAO, userDAO);
        List<Student> students = listStudentOfActionUseCase.listStudents(action);
        if(students.isEmpty()){
            throw new IllegalArgumentException("action has no students vinculated");
        }
        // Pegar a próxima hora livre de um aluno
        GetNextHourFreeStudentUseCase gnt = new GetNextHourFreeStudentUseCase(userDAO, appointmentDAO);
        Optional<Student> student = students.stream()
                .min(Comparator.comparing(gnt::getNextHourFree));


        if(!student.isPresent()){
            throw new IllegalArgumentException("this is no student able to make this appointment");
        }

        Appointment appointment = new Appointment(gnt.getNextHourFree(student.get()), action, student.get(), patient);


        if (!appointmentDAO.save(appointment)){
            throw new IllegalArgumentException("appoitment can not be created");
        }
        appointmentDAO.save(appointment);
        return appointment;
    }

    public Optional<Appointment> scheduleWithDate(Action action, Patient patient, Student student, String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        Appointment appointment = new Appointment(dateTime, action, student, patient);

        if (!appointmentDAO.save(appointment)){
            return Optional.empty();
        }
        return Optional.of(appointment);
    }

}
