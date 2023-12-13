package br.ifsp.husaocarlos.domain.usecases.appointment;

import br.ifsp.husaocarlos.domain.entities.appointment.Appointment;
import br.ifsp.husaocarlos.domain.entities.appointment.AppointmentStatus;
import br.ifsp.husaocarlos.domain.entities.student.Student;

import java.util.Optional;

public class UpdateAppointmentUseCase {

    AppointmentDAO appointmentDAO;

    public UpdateAppointmentUseCase(AppointmentDAO appointmentDAO) {
        this.appointmentDAO = appointmentDAO;
    }

    public boolean changeStatus(Student student, Appointment appointment){
        if(student == null || appointment == null){
            throw new IllegalArgumentException("student and appointment cannot be null");
        }
        Optional<Appointment> optApp = appointmentDAO.findOne(appointment.getId());
        if(optApp.isPresent()){
            Appointment app = optApp.get();
            if(app.getStudent().getCpf().equals(student.getCpf())){
                return appointmentDAO.update(app);
            }
            return false;
        }
        return false;
    }
}
