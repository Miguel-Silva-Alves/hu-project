package br.ifsp.husaocarlos.application.repository;
import br.ifsp.husaocarlos.application.repository.utils.JPAUtil;
import br.ifsp.husaocarlos.domain.entities.User;
import br.ifsp.husaocarlos.domain.usecases.user.UserDAO;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;

import java.util.*;

public class MySqlUserDAO implements UserDAO {

    private final EntityManager em = JPAUtil.getEntityManager();


    @Override
    public boolean save(User object) {
        try{
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
            return true;
        }catch (RuntimeException exception){
            return false;
        }

    }

    @Override
    public Optional<User> findOne(String key) {
        return Optional.ofNullable(em.find(User.class, key));
    }

    @Override
    public List<User> findAll() {
        String jpql = "SELECT u FROM users u";
        return em.createQuery(jpql, User.class).getResultList();
    }

    @Override
    public boolean update(User object) {
        try {
            em.merge(object);
            return true;
        }catch (RuntimeException e){
            return false;
        }
    }

    @Override
    public boolean delete(String key) {
        Optional<User> user = findOne(key);
        if (user.isPresent()){
            em.remove(user);
            return true;
        }
        return false;
    }

    @Override
    public Optional<User> findUserByLogin(String username, String password) {
        String jpql = "SELECT u FROM User u where u.password = ?1 and u.email = ?2";
        return Optional.ofNullable(em.createQuery(jpql, User.class).setParameter(1, password).setParameter(2, username).getSingleResult());
    }
}
