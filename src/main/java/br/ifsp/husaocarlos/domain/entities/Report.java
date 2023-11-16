package br.ifsp.husaocarlos.domain.entities;

import br.ifsp.husaocarlos.domain.entities.appointment.Appointment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Report {
    private final LocalDateTime reportDate;
    private final List<Appointment> appointments = new ArrayList<Appointment>();

    public Report(LocalDateTime reportDate) {
        this.reportDate = reportDate;
    }
    public boolean receiveNewAppointment(Appointment appointment){
        if (appointment == null)
            return false;

        return appointments.add(appointment);
    }

}
