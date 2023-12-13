package br.ifsp.husaocarlos.application.persistence;

import br.ifsp.husaocarlos.application.repository.utils.JPAUtil;
import br.ifsp.husaocarlos.domain.entities.Action;
import br.ifsp.husaocarlos.domain.entities.Registration;
import br.ifsp.husaocarlos.domain.usecases.registration.RegistrationDAO;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class MySqlRegistrationDAO implements RegistrationDAO {
    private final EntityManager em = JPAUtil.getEntityManager();
    @Override
    public Optional<Registration> findbyActionStudent(String actionId, String studentId) {
        List<Registration> registrationList = findAll();
        for(Registration registration: registrationList){
            if(Objects.equals(registration.getActionId(), actionId) && Objects.equals(registration.getStudentId(), studentId)){
                return Optional.of(registration);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Registration> findbyAction(Action action) {
        List<Registration> registrationList = findAll();
        ArrayList<Registration> registrations = new ArrayList<>();
        for(Registration registration: registrationList){
            if(Objects.equals(registration.getActionId(), action.getId())){
                registrations.add(registration);
            }
        }
        return registrations;
    }

    @Override
    public boolean save(Registration object) {
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
    public Optional<Registration> findOne(Integer key) {
        return Optional.ofNullable(em.find(Registration.class, key));
    }

    @Override
    public List<Registration> findAll() {
        String jpql = "SELECT r FROM Registration r";
        return em.createQuery(jpql, Registration.class).getResultList();
    }

    @Override
    public boolean update(Registration object) {
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
    public boolean delete(Integer key) {
        Optional<Registration> registration = findOne(key);
        if (registration.isPresent()){
            em.remove(registration);
            return true;
        }
        return false;
    }
}
