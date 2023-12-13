package br.ifsp.husaocarlos.domain.entities.student;

import br.ifsp.husaocarlos.domain.entities.Action;

public class StudentActionDTO {


    private Action action;
    private Student student;
    private String lineCare;

    public StudentActionDTO(Action action, Student student, String lineCare) {
        this.action = action;
        this.student = student;
        this.lineCare = lineCare;
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

    public Action getAction() {
        return action;
    }

    public Student getStudent() {
        return student;
    }

    public String getLineCare() {
        return lineCare;
    }
}
