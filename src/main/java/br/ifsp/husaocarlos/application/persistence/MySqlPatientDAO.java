package br.ifsp.husaocarlos.application.persistence;
import br.ifsp.husaocarlos.application.repository.utils.JPAUtil;
import br.ifsp.husaocarlos.domain.entities.Patient;
import br.ifsp.husaocarlos.domain.usecases.patient.PatientDAO;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class MySqlPatientDAO implements PatientDAO {
    private final EntityManager em = JPAUtil.getEntityManager();

    @Override
    public List<Patient> findByName(String name) {
        String jpql = "SELECT p FROM Patient p WHERE p.name = ?1";
        return em.createQuery(jpql, Patient.class).setParameter(1,name).getResultList();
    }

    @Override
    public Optional<Patient> findByEmail(String email) {
        String jpql = "SELECT p FROM Patient p WHERE p.email = ?1";
        return Optional.ofNullable(em.createQuery(jpql, Patient.class).setParameter(1,email).getSingleResult());
    }

    @Override
    public boolean save(Patient object) {
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
            return true;
        }catch (RuntimeException exception){
            return false;
        }
    }

    @Override
    public Optional<Patient> findOne(String key) {
        return Optional.ofNullable(em.find(Patient.class, key));
    }

    @Override
    public List<Patient> findAll() {
        String jpql = "SELECT p FROM Patient p";
        return em.createQuery(jpql, Patient.class).getResultList();
    }

    @Override
    public boolean update(Patient object) {
        try {
            em.getTransaction().begin();
            em.merge(object);
            em.getTransaction().commit();
            return true;
        }catch (RuntimeException e){
            return false;
        }
    }

    @Override
    public boolean delete(String key) {
        Optional<Patient> patient = findOne(key);
        if (patient.isPresent()){
            em.remove(patient);
            return true;
        }
        return false;
    }
}
