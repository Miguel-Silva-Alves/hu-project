package br.ifsp.husaocarlos.domain.usecases.student;

import br.ifsp.husaocarlos.domain.entities.Patient;
import br.ifsp.husaocarlos.domain.entities.student.Student;
import br.ifsp.husaocarlos.domain.entities.appointment.Appointment;
import br.ifsp.husaocarlos.domain.usecases.appointment.AppointmentDAO;

import java.util.List;
import java.util.stream.Collectors;

public class GetPatientsOfStudentUseCase {

    AppointmentDAO appointmentDAO;

    public GetPatientsOfStudentUseCase(AppointmentDAO appointmentDAO ) {
        this.appointmentDAO = appointmentDAO;
    }

    public List<Patient> getPatients(Student student){
        List<Appointment> appointments = appointmentDAO.getAppointmentStudent(student);
        return appointments.stream()
                .map(Appointment::getPatient)
                .distinct()
                .collect(Collectors.toList());
    }
}
