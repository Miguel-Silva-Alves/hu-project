package br.ifsp.husaocarlos.domain.entities.appointment;
import br.ifsp.husaocarlos.domain.entities.Action;
import br.ifsp.husaocarlos.domain.entities.Patient;
import br.ifsp.husaocarlos.domain.entities.student.Student;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public LocalDateTime date;
    @OneToOne
    public Action action;
    @OneToOne
    public Student student;
    @OneToOne
    public Patient patient;
    @Enumerated(EnumType.STRING)
    public AppointmentStatus status;

    public Appointment() {
    }

    public Appointment(LocalDateTime date, Action action, Student student, Patient patient) {
        this.date = date;
        this.action = action;
        this.student = student;
        this.patient = patient;
        this.id = -1;
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

    public void attend() {
        this.status = AppointmentStatus.Attend;
    }

    public void dischard() {
        this.status = AppointmentStatus.Discharge;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public String getStatusName() {
        return status.name();
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

    public String getStudentName() {
        return student.getName();
    }

    public Patient getPatient() {
        return patient;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getDateFormated() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return date.format(formatter);
    }


    public String getAction() {
        return action.getName();
    }

    public Action getActionObject(){
        return action;
    }

    public String getPatientName(){ return patient.getName(); }

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
