package br.ifsp.husaocarlos.application.repository;
import br.ifsp.husaocarlos.domain.entities.Action;
import br.ifsp.husaocarlos.domain.entities.Registration;
import br.ifsp.husaocarlos.domain.usecases.registration.RegistrationDAO;
import java.util.*;


public class InMemoryRegistrationDAO implements RegistrationDAO {
    private static final Map<Integer, Registration> db = new HashMap<>();
    private int idCounter = 0;
    @Override
    public Optional<Registration> findbyActionStudent(String actionId, String studentId) {

        for(Integer key: db.keySet()){
            Registration registration = db.get(key);
            System.out.println(registration);
            if(Objects.equals(registration.getActionId(), actionId) && Objects.equals(registration.getStudentId(), studentId)){
                return Optional.of(registration);
            }
        }
        return Optional.empty();

    }

    @Override
    public List<Registration> findbyAction(Action action) {
        ArrayList<Registration> registrations = new ArrayList<>(db.values());

        return registrations
                .stream()
                .filter(registration -> registration.getActionId().equals(action.getId()))
                .toList();
    }

    @Override
    public boolean save(Registration object) {
        if (object.getId() != -1 || db.containsKey(object.getId())){
            return false;
        }
        object.setId(idCounter);
        db.put(idCounter, object);
        idCounter++;
        return true;
    }

    @Override
    public Optional<Registration> findOne(Integer key) {
        return Optional.empty();
    }

    @Override
    public List<Registration> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Registration object) {
        return true;
    }

    @Override
    public boolean delete(Integer key) {
        return false;
    }
}
