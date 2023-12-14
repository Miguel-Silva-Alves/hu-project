package br.ifsp.husaocarlos.application.persistence;
import br.ifsp.husaocarlos.application.repository.utils.JPAUtil;
import br.ifsp.husaocarlos.domain.entities.LineOfCare;
import br.ifsp.husaocarlos.domain.usecases.management.LinesOfCareDAO;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class MySqlLinesOfCareDAO implements LinesOfCareDAO {
    private final EntityManager em = JPAUtil.getEntityManager();
    @Override
    public boolean save(LineOfCare object) {
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
    public Optional<LineOfCare> findOne(String key) {
        return Optional.ofNullable(em.find(LineOfCare.class, key));
    }

    @Override
    public List<LineOfCare> findAll() {
        String jpql = "SELECT a FROM LineOfCare a";
        return em.createQuery(jpql, LineOfCare.class).getResultList();
    }

    @Override
    public boolean update(LineOfCare object) {
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
        Optional<LineOfCare> lineOfCare = findOne(key);
        if (lineOfCare.isPresent()){
            em.remove(lineOfCare);
            return true;
        }
        return false;
    }
}
