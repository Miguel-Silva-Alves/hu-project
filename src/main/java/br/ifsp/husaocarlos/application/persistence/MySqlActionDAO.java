package br.ifsp.husaocarlos.application.persistence;
import br.ifsp.husaocarlos.application.repository.utils.JPAUtil;
import br.ifsp.husaocarlos.domain.entities.Action;
import br.ifsp.husaocarlos.domain.entities.Professor;
import br.ifsp.husaocarlos.domain.usecases.action.ActionDAO;
import jakarta.persistence.EntityManager;
import java.util.*;
import java.util.stream.Collectors;

public class MySqlActionDAO implements ActionDAO {
    private final EntityManager em = JPAUtil.getEntityManager();


    @Override
    public Optional<Action> findByName(String name) {
        String jpql = "SELECT a FROM Action a WHERE a.name = ?1 ";
        return Optional.ofNullable(em.createQuery(jpql, Action.class)
                .setParameter(1,name).getSingleResult());
    }

    @Override
    public List<Action> findByProfessor(Professor professor) {
        List<Action> actions = findAll();
        return actions.stream()
                .filter(action -> action.getProfessor().getCpf().equals(professor.getCpf()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean save(Action object) {
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
    public Optional<Action> findOne(Integer key) {
        return Optional.ofNullable(em.find(Action.class, key));
    }

    @Override
    public List<Action> findAll() {
        String jpql = "SELECT a FROM Action a";
        return em.createQuery(jpql, Action.class).getResultList();
    }

    @Override
    public boolean update(Action object) {
        try {
            em.merge(object);
            return true;
        }catch (RuntimeException e){
            return false;
        }
    }

    @Override
    public boolean delete(Integer key) {
        Optional<Action> action = findOne(key);
        if (action.isPresent()){
            em.remove(action);
            return true;
        }
        return false;
    }
}
