package br.ifsp.husaocarlos.domain.entities;

public class Registration {

    Integer id;
    String studentId;
    Integer actionId;

    public Registration(Integer id, String cpf, Integer actionId) {
        this.id = id;
        this.studentId = cpf;
        this.actionId = actionId;
    }

    public Registration(String cpf, Integer actionId) {
        this.studentId = cpf;
        this.actionId = actionId;
        this.id = -1;
    }

    public Integer getId() {
        return id;
    }

    public Integer getActionId() {
        return actionId;
    }

    public String getStudentId() {
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
