package br.ifsp.husaocarlos.domain.entities;

public class Registration {

    Integer id;
    Integer studentId;
    Integer actionId;

    public Registration(Integer id, Integer studentId, Integer actionId) {
        this.id = id;
        this.studentId = studentId;
        this.actionId = actionId;
    }

    public Registration(Integer studentId, Integer actionId) {
        this.studentId = studentId;
        this.actionId = actionId;
        this.id = -1;
    }

    public Integer getId() {
        return id;
    }

    public Integer getActionId() {
        return actionId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", actionId=" + actionId +
                '}';
    }
}
