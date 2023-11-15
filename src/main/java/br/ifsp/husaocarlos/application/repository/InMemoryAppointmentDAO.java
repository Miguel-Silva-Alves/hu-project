package br.ifsp.husaocarlos.application.repository;

import br.ifsp.husaocarlos.App;
import br.ifsp.husaocarlos.domain.entities.Patient;
import br.ifsp.husaocarlos.domain.entities.Registration;
import br.ifsp.husaocarlos.domain.entities.Student;
import br.ifsp.husaocarlos.domain.entities.appointment.Appointment;
import br.ifsp.husaocarlos.domain.usecases.appointment.AppointmentDAO;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryAppointmentDAO implements AppointmentDAO {
    private static final Map<Integer, Appointment> db = new HashMap<>();
    private int idCounter = 0;
    @Override
    public boolean save(Appointment object) {
        if (object.getId() != -1){
            return false;
        }
        object.setId(idCounter);
        db.put(idCounter, object);
        idCounter++;
        return true;
    }

    @Override
    public Optional<Appointment> findOne(Integer key) {
        return Optional.empty();
    }

    @Override
    public List<Appointment> findAll() {
        return null;
    }

    @Override
    public boolean update(Integer key, Appointment object) {

        if(db.containsKey(key)){
            db.replace(key, object);
            return true;
        }
        return false;

    }

    @Override
    public boolean delete(Integer key) {
        return false;
    }

    @Override
    public List<Appointment> getAppointmentStudent(Student student) {
        ArrayList<Appointment> appointments = new ArrayList<>(db.values());
        return  appointments.stream()
                .filter(appointment -> appointment.getStudent().getId().equals(student.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Appointment> getAppointmentPatient(Patient patient) {
        ArrayList<Appointment> appointments = new ArrayList<>(db.values());
        return  appointments.stream()
                .filter(appointment -> appointment.getPatient().getCpf().equals(patient.getCpf()))
                .collect(Collectors.toList());
    }
}
