package br.ifsp.husaocarlos.domain.usecases.user;

import br.ifsp.husaocarlos.domain.entities.student.Student;
import br.ifsp.husaocarlos.domain.entities.User;
import br.ifsp.husaocarlos.domain.entities.appointment.Appointment;
import br.ifsp.husaocarlos.domain.usecases.appointment.AppointmentDAO;

import java.util.List;

public class DeleteUserUseCase {

    UserDAO userDAO;
    AppointmentDAO appointmentDAO;

    public DeleteUserUseCase(UserDAO userDAO, AppointmentDAO appointmentDAO) {
        this.userDAO = userDAO;
        this.appointmentDAO = appointmentDAO;
    }

    public boolean deleteUser(User user){
        return userDAO.delete(user.getCpf());
    }

    public boolean deleteUser(Student student){
        List<Appointment> appointmentList = appointmentDAO.getAppointmentStudent(student);
        if(!appointmentList.isEmpty()){
            return false;
        }
        return userDAO.delete(student.getCpf());
    }

}
