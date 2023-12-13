package br.ifsp.husaocarlos.domain.entities.appointment;
import br.ifsp.husaocarlos.domain.entities.Action;
import br.ifsp.husaocarlos.domain.entities.Patient;
import br.ifsp.husaocarlos.domain.entities.student.Student;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


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
        if(this.status == AppointmentStatus.InService){
            this.status = AppointmentStatus.Attend;
        }

    }

    public void dischard() {
        this.status = AppointmentStatus.Discharge;
    }

    public void inService(){
        if(this.status == AppointmentStatus.Scheduled){
            this.status = AppointmentStatus.InService;
        }
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

    public String getDateOfAppointment(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return Objects.equals(id, that.id) && Objects.equals(date, that.date) && Objects.equals(action, that.action) && Objects.equals(student, that.student) && Objects.equals(patient, that.patient) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, action, student, patient, status);
    }
}
