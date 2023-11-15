package br.ifsp.husaocarlos.domain.entities;

import br.ifsp.husaocarlos.domain.entities.appointment.Appointment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Report {
    private final Date reportDate;
    private final List<Appointment> appointments = new ArrayList<Appointment>();

    public Report(Date reportDate) {
        this.reportDate = reportDate;
    }
    public boolean receiveNewAppointment(Appointment appointment){
        if (appointment == null)
            return false;

        return appointments.add(appointment);
    }

}
