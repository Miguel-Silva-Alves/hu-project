package br.ifsp.husaocarlos.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public String studentId;
    public String actionId;

    public Registration(Integer id, String cpf, String actionId) {
        this.id = id;
        this.studentId = cpf;
        this.actionId = actionId;
    }

    public Registration(String cpf, String actionId) {
        this.studentId = cpf;
        this.actionId = actionId;
        this.id = -1;
    }

    public Registration() {
    }

    public Integer getId() {
        return id;
    }

    public String getActionId() {
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
