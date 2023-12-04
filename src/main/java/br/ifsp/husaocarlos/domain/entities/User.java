package br.ifsp.husaocarlos.domain.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    public String cpf;
    public String email;
    public String name;
    public String password;
    public String address;
    public String registration;
    @Enumerated(EnumType.STRING)
    public Roles role;
    public Boolean isActive;

    public User() {
    }

    public User(String email, String cpf, String name, String password, String adress, String registration, Roles role, boolean isActive) {
        this.email = email;
        this.cpf = cpf;
        this.name = name;
        this.password = password;
        this.address = adress;
        this.registration = registration;
        this.role = role;
        this.isActive = isActive;
    }

    public User(String email, String cpf, String name, String password, String adress, String registration, Roles role) {
        this.email = email;
        this.cpf = cpf;
        this.name = name;
        this.password = password;
        this.address = adress;
        this.registration = registration;
        this.role = role;
        this.isActive = true;
    }

    public User (User castUser){
        this.email = castUser.getEmail();
        this.cpf = castUser.getCpf();
        this.name = castUser.getName();
        this.password = castUser.getPassword();
        this.address = castUser.getAddress();
        this.registration = castUser.getRegistration();
        this.role = castUser.getRole();
        this.isActive = true;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                ", role=" + role +
                '}';
    }

    public boolean isStudent() {
        return this.role == Roles.Student;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public boolean passwordIsValid(String password) {
        return this.password.equals(password);
    }
}

