package br.ifsp.husaocarlos.application.repository;
import br.ifsp.husaocarlos.domain.entities.User;
import br.ifsp.husaocarlos.domain.usecases.user.UserDAO;
import java.util.*;

public class InMemoryUserDAO implements UserDAO {
    private static final Map<Integer, User> db = new HashMap<>();
    private int idCounter = 0;
    @Override
    public boolean save(User object) {
        if (object.getId() != -1){
            return false;
        }
        object.setId(idCounter);
        db.put(idCounter, object);
        idCounter++;
        return true;
    }

    @Override
    public Optional<User> findOne(Integer key) {
        if(db.containsKey(key)){
            return Optional.of(db.get(key));
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Integer key, User object) {
        User modifiedUser = db.replace(key,object);
        return modifiedUser == null;
    }

    @Override
    public boolean delete(Integer key) {
        return false;
    }

}
