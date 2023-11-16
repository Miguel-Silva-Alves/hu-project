package br.ifsp.husaocarlos.domain.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Patient{

    private String cpf;
    private String name;
    private String email;
    private String phone;
    private String address;
    private LocalDateTime registerTime;

    public Patient(String cpf, String name, String email, String phone, String address) {
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        registerTime = LocalDateTime.now();
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

    @Override
    public String toString() {
        return "Patient{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
