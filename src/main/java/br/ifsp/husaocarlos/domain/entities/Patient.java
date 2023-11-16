package br.ifsp.husaocarlos.domain.entities;

public class Patient{

    private String cpf;
    private String name;
    private String email;
    private String phone;
    private String address;

    public Patient(String cpf, String name, String email, String phone, String address) {
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
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
