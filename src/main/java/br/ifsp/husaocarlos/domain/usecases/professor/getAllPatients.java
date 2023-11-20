package br.ifsp.husaocarlos.domain.usecases.professor;

import br.ifsp.husaocarlos.domain.entities.Patient;
import br.ifsp.husaocarlos.domain.entities.Professor;
import br.ifsp.husaocarlos.domain.entities.Roles;
import br.ifsp.husaocarlos.domain.usecases.patient.PatientDAO;

import java.util.List;

public class getAllPatients {
    PatientDAO DAO;

    public getAllPatients(PatientDAO DAO) {
        if (DAO == null)
            throw new IllegalArgumentException();
        this.DAO = DAO;
    }

    public List<Patient> get(Professor professor) throws IllegalAccessException {
        if (professor == null )
            throw new IllegalArgumentException("Null Professor !");
        if (professor.role.equals(Roles.Professor))
            throw new IllegalAccessException("Not a Professor !");
        return professor.viewPatients(DAO);
    }
}
