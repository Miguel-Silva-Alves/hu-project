package br.ifsp.husaocarlos.domain.entities.appointment;

public class Schedule{

    private String actionName;
    private String studentName;
    private String studentCPF;
    private String date;

    public Schedule(String actionName, String studentName, String studentCPF, String date) {
        this.actionName = actionName;
        this.studentName = studentName;
        this.studentCPF = studentCPF;
        this.date = date;
    }

    public String getActionName() {
        return actionName;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentCPF() {
        return studentCPF;
    }

    public String getDate() {
        return date;
    }
}
