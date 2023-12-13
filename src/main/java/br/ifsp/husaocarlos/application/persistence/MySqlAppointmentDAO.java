package br.ifsp.husaocarlos.application.persistence;
import br.ifsp.husaocarlos.application.repository.utils.JPAUtil;
import br.ifsp.husaocarlos.domain.entities.Patient;
import br.ifsp.husaocarlos.domain.entities.Student;
import br.ifsp.husaocarlos.domain.entities.appointment.Appointment;
import br.ifsp.husaocarlos.domain.usecases.appointment.AppointmentDAO;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MySqlAppointmentDAO implements AppointmentDAO {
    private final EntityManager em = JPAUtil.getEntityManager();
    @Override
    public List<Appointment> getAppointmentStudent(Student student) {
        List<Appointment> appointments = findAll();
        return  appointments.stream()
                .filter(appointment -> appointment.getStudent().getCpf().equals(student.getCpf()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Appointment> getAppointmentPatient(Patient patient) {
        List<Appointment> appointments = findAll();
        return  appointments.stream()
                .filter(appointment -> appointment.getPatient().getCpf().equals(patient.getCpf()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean save(Appointment object) {
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
            return true;
        }catch (RuntimeException exception){
            System.out.println(exception);
            return false;
        }
    }

    @Override
    public Optional<Appointment> findOne(Integer key) {
        return Optional.ofNullable(em.find(Appointment.class, key));
    }

    @Override
    public List<Appointment> findAll() {
        String jpql = "SELECT a FROM Appointment a";
        return em.createQuery(jpql, Appointment.class).getResultList();
    }

    @Override
    public boolean update(Appointment object) {
        try {
            em.merge(object);
            return true;
        }catch (RuntimeException e){
            return false;
        }
    }

    @Override
    public boolean delete(Integer key) {
        Optional<Appointment> appointment = findOne(key);
        if (appointment.isPresent()){
            em.remove(appointment);
            return true;
        }
        return false;
    }
}
