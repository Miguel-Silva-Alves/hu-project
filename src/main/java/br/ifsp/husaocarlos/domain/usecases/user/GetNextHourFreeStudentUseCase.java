package br.ifsp.husaocarlos.domain.usecases.user;
import br.ifsp.husaocarlos.domain.entities.student.Student;
import br.ifsp.husaocarlos.domain.entities.appointment.Appointment;
import br.ifsp.husaocarlos.domain.usecases.appointment.AppointmentDAO;
import java.util.*;
import java.time.LocalDateTime;
import java.util.Comparator;

public class GetNextHourFreeStudentUseCase {

    UserDAO userDAO;
    AppointmentDAO appointmentDAO;

    public GetNextHourFreeStudentUseCase(UserDAO userDAO, AppointmentDAO appointmentDAO) {
        this.userDAO = userDAO;
        this.appointmentDAO = appointmentDAO;
    }

    public LocalDateTime getNextHourFree(Student student){

        LocalDateTime now = LocalDateTime.now();
        // Todas as consultas depois de agora
        Optional<Appointment> lastAppointment = appointmentDAO.getAppointmentStudent(student)
                .stream()
                .filter(appointment -> appointment.getDate().isAfter(now))
                .max(Comparator.comparing(Appointment::getDate));


        if(lastAppointment.isPresent()){
            return lastAppointment.get().getDate().plusHours(1);
        }
        return now.plusHours(1);

    }
}
