package br.ifsp.husaocarlos.domain.entities;

import java.util.ArrayList;
import java.util.List;

public class Action {

   Integer id;
   String name;
   String specialty;
   Professor professor;
   String lineOfCare;

   private final List<Patient> patients = new ArrayList<Patient>();

   public Action(Integer id, String name, String specialty, Professor professor, String lineOfCare) {
      this.id = id;
      this.name = name;
      this.specialty = specialty;
      this.professor = professor;
      this.lineOfCare = lineOfCare;
   }

   public boolean reciveNewPatient(Patient patient){
      if (patient == null)
         return false;

      return patients.add(patient);
   }

   public Integer getId() {
      return id;
   }
}
