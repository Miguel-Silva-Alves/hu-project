package br.ifsp.husaocarlos.domain.usecases.appointment;
import br.ifsp.husaocarlos.domain.entities.Patient;

import br.ifsp.husaocarlos.domain.entities.Student;
import br.ifsp.husaocarlos.domain.entities.appointment.Appointment;
import br.ifsp.husaocarlos.domain.usecases.utils.DAO;

import java.util.List;

public interface AppointmentDAO extends DAO<Integer, Appointment> {
    List<Appointment> getAppointmentStudent(Student student);

    List<Appointment> getAppointmentPatient(Patient patient);
}
