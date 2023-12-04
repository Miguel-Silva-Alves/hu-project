package br.ifsp.husaocarlos.domain.usecases.management;
import br.ifsp.husaocarlos.application.repository.MySqlUserDAO;
import br.ifsp.husaocarlos.domain.entities.Management;
import br.ifsp.husaocarlos.domain.entities.Professor;
import br.ifsp.husaocarlos.domain.entities.Roles;
import br.ifsp.husaocarlos.domain.entities.User;
import br.ifsp.husaocarlos.domain.usecases.user.UserDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.security.enterprise.credential.Password;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GetAllUsersTest {

    UserDAO DAO;
    Management management;
    Professor professor;

    @BeforeEach
    void setup(){
        DAO = new MySqlUserDAO();
        management = new Management(0,"adm.admin@gmail.com","579.456.789-56","João","1234","la na pqp",null, Roles.Management, true);
        Password professorPassword = new Password("123456");
        professor = new Professor("prof.educador@gmail.com","579.456.789-56","João","1234","la na pqp",null, Roles.Professor);
        DAO.save(professor);
    }

    @Test
    void GetAllUsersWithSucess() throws IllegalAccessException {
        GetAllUsers getAllUsers = new GetAllUsers(DAO);
        List<User> userList = getAllUsers.get(management);
        assertEquals(professor,userList.get(0));
    }

    @Test
    void getAllUsersWithNullDAO(){
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,() ->{
            GetAllUsers getAllUsers = new GetAllUsers(null);
        });

        assertEquals(exception.getClass(),IllegalArgumentException.class);
    }

    @Test
    void getAllUsersWithNullManagement(){
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,() ->{
            GetAllUsers getAllUsers = new GetAllUsers(DAO);
            getAllUsers.get(null);
        });
        assertEquals(exception.getClass(),IllegalArgumentException.class);
    }

    @Test
    void getAllUsersWithDifferentRole(){
        Password managementPassword = new Password("1234");
        management = new Management(0,"adm.admin@gmail.com","579.456.789-56","João","1234","la na pqp",null, Roles.Professor, true);
        final IllegalAccessException exception = assertThrows(IllegalAccessException.class,() ->{
            GetAllUsers getAllUsers = new GetAllUsers(DAO);
            getAllUsers.get(management);
        });

        assertEquals(exception.getClass(),IllegalAccessException.class);
    }
}
