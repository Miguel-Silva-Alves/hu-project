package br.ifsp.husaocarlos.domain.usecases.appointment;

<<<<<<< HEAD
=======
import br.ifsp.husaocarlos.domain.entities.Patient;
>>>>>>> 4c828c65fb4993b1cae5d34596c32ef8fb0fa95c
import br.ifsp.husaocarlos.domain.entities.Student;
import br.ifsp.husaocarlos.domain.entities.appointment.Appointment;
import br.ifsp.husaocarlos.domain.usecases.utils.DAO;

import java.util.List;

public interface AppointmentDAO extends DAO<Integer, Appointment> {
    List<Appointment> getAppointmentStudent(Student student);
<<<<<<< HEAD
=======
    List<Appointment> getAppointmentPatient(Patient patient);
>>>>>>> 4c828c65fb4993b1cae5d34596c32ef8fb0fa95c
}
