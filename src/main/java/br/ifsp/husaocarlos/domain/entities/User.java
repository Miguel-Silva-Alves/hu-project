package br.ifsp.husaocarlos.domain.entities;
import br.ifsp.husaocarlos.domain.usecases.utils.Hash;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
public class User {

    @Id
    public String cpf;
    public String email;
    public String name;
    public String password;
    public String address;
    public String registration;
    @Enumerated(EnumType.STRING)
    @Column(name = "role", insertable = false, updatable = false)
    public Roles role;
    public Boolean isActive;

    public User() {
    }

    public User(String email, String cpf, String name, String password, String adress, String registration, Roles role, boolean isActive) {
        this.email = email;
        this.cpf = cpf;
        this.name = name;
        this.password = Hash.toHash(password);
        this.address = adress;
        this.registration = registration;
        this.role = role;
        this.isActive = isActive;
    }

    public User(String email, String cpf, String name, String password, String adress, String registration, Roles role) {
        this(email, cpf, name, password, adress, registration, role, true);
    }

    public User (User castUser){
        this(castUser.getEmail(),
                castUser.getCpf(),
                castUser.getName(),
                castUser.getPassword(),
                castUser.getAddress(),
                castUser.getRegistration(),
                castUser.getRole(), true);
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

