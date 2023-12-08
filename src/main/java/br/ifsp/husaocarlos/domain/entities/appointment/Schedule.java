package br.ifsp.husaocarlos.domain.entities.appointment;

import br.ifsp.husaocarlos.domain.entities.Action;
import br.ifsp.husaocarlos.domain.entities.Student;

public class Schedule{

    private Action action;
    private Student student;
    private String date;


    public Schedule(Action action, Student student, String date) {
        this.action = action;
        this.student = student;
        this.date = date;
    }

    public String getActionName() {
        return action.getName();
    }

    public String getStudentName() {
        return student.getName();
    }

    public String getStudentCPF() {
        return student.getCpf();
    }


    public String getDate() {
        return date;
    }

    public Action getAction() {
        return action;
    }

    public Student getStudent() {
        return student;
    }
}
