package br.ifsp.husaocarlos.domain.entities;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "patient")
public class Patient{
    @Id
    public String cpf;
    public String name;
    public String email;
    public String phone;
    public String address;
    public LocalDateTime registerTime;
    @ManyToOne
    @JoinColumn(name = "action_id")
    public Action registeredAction;

    public Patient(String cpf, String name, String email, String phone, String address) {
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        registerTime = LocalDateTime.now();
    }

    public Patient() {
    }

    @Override
    public String toString() {
        return "Patient{" +
                "cpf='" + cpf + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", registerTime=" + registerTime +
                '}';
    }

    public String getCpf() {
        return cpf;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public LocalDateTime getRegisterTime() {
        return registerTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
