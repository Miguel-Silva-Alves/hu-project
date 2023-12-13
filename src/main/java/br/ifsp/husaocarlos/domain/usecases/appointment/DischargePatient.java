package br.ifsp.husaocarlos.domain.usecases.appointment;

import br.ifsp.husaocarlos.domain.entities.Patient;
import br.ifsp.husaocarlos.domain.entities.appointment.Appointment;
import br.ifsp.husaocarlos.domain.entities.appointment.AppointmentStatus;

import java.util.List;
import java.util.stream.Collectors;

public class DischargePatient {

    AppointmentDAO appointmentDAO;

    public DischargePatient(AppointmentDAO appointmentDAO) {
        this.appointmentDAO = appointmentDAO;
    }

    public boolean discharge(Patient patient , Appointment appointmentView){

        List<Appointment> appointments = appointmentDAO.getAppointmentPatient(patient);
        boolean exist = appointments.stream()
                .anyMatch(appointment -> appointment.getStatus().equals(AppointmentStatus.Scheduled) ||
                        appointment.getStatus().equals(AppointmentStatus.InService));

        if (exist){
            return false;
        }
        for(Appointment appointment: appointments){
            if (appointment.equals(appointmentView)){
                appointmentView.dischard();
                appointmentDAO.update(appointmentView);
            }

        }
        return true;
    }
}
