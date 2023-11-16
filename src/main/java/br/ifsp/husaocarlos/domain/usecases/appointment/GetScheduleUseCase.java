package br.ifsp.husaocarlos.domain.usecases.appointment;

import br.ifsp.husaocarlos.domain.entities.Student;
import br.ifsp.husaocarlos.domain.entities.appointment.Appointment;

import java.util.List;

public class GetScheduleUseCase {
    private final AppointmentDAO appointmentDAO;

    public GetScheduleUseCase(AppointmentDAO appointmentDAO) {
        this.appointmentDAO = appointmentDAO;
    }

    public List<Appointment> getAppointmentStudent(Student student) {
        return appointmentDAO.getAppointmentStudent(student);
    }
}
