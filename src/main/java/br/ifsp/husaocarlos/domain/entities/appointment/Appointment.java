package br.ifsp.husaocarlos.domain.entities.appointment;

import br.ifsp.husaocarlos.domain.entities.Action;
import br.ifsp.husaocarlos.domain.entities.Patient;
import br.ifsp.husaocarlos.domain.entities.Student;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Appointment {
    Integer id;
    LocalDateTime date ;
    Action action;
    Student student;
    Patient patient;

<<<<<<< HEAD
=======
    AppointmentStatus status;

>>>>>>> 4c828c65fb4993b1cae5d34596c32ef8fb0fa95c
    public Appointment(LocalDateTime date, Action action, Student student, Patient patient) {
        this.date = date;
        this.action = action;
        this.student = student;
        this.patient = patient;
        this.id = -1;
<<<<<<< HEAD
=======
        this.status = AppointmentStatus.Scheduled;
    }

    public Appointment(Integer id, LocalDateTime date, Action action, Student student, Patient patient, AppointmentStatus status) {
        this.id = id;
        this.date = date;
        this.action = action;
        this.student = student;
        this.patient = patient;
        this.status = status;
    }

    public void attend(){
        this.status = AppointmentStatus.Attend;
    }

    public void dischard(){
        this.status = AppointmentStatus.Discharge;
    }

    public AppointmentStatus getStatus() {
        return status;
>>>>>>> 4c828c65fb4993b1cae5d34596c32ef8fb0fa95c
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

<<<<<<< HEAD
=======
    public Patient getPatient() {
        return patient;
    }

>>>>>>> 4c828c65fb4993b1cae5d34596c32ef8fb0fa95c
    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", date=" + date +
                ", action=" + action +
                ", student=" + student +
                ", patient=" + patient +
                '}';
    }
}
