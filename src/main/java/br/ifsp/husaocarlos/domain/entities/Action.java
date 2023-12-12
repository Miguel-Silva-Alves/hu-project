package br.ifsp.husaocarlos.domain.entities;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "action")
public class Action {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   public Integer id;
   public String name;
   public String specialty;
   @ManyToOne
   public Professor professor;
   public String lineOfCare;
   @OneToMany
   public final List<Patient> patients = new ArrayList<>();

   public Action(Integer id, String name, String specialty, Professor professor, String lineOfCare) {
      this.id = id;
      this.name = name;
      this.specialty = specialty;
      this.professor = professor;
      this.lineOfCare = lineOfCare;
   }

   public Action(String name, String specialty, Professor professor, String lineOfCare) {
      this.name = name;
      this.specialty = specialty;
      this.professor = professor;
      this.lineOfCare = lineOfCare;
      this.id = -1;
   }

   public Action() {
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public Professor getProfessor() {
      return professor;
   }

   public boolean reciveNewPatient(Patient patient){
      if (patient == null)
         return false;

      return patients.add(patient);
   }

   public Integer getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public String getLineOfCare() {
      return lineOfCare;
   }

   public String getSpecialty() {
      return specialty;
   }

   @Override
   public String toString() {
      return "Action{" +
              "id=" + id +
              ", name='" + name + '\'' +
              ", specialty='" + specialty + '\'' +
              ", professor=" + professor +
              '}';
   }
}
