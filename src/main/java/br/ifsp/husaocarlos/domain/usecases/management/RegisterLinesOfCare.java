package br.ifsp.husaocarlos.domain.usecases.management;

import br.ifsp.husaocarlos.domain.entities.Professor;
import br.ifsp.husaocarlos.domain.usecases.user.UserDAO;

public class RegisterLinesOfCare {
    String currentLineOfCareName;
    Professor currentResponsableProfessor;
    UserDAO DAO;

    public RegisterLinesOfCare(UserDAO DAO) {
        if (DAO == null)
            throw new IllegalArgumentException();
        this.DAO = DAO;
    }

    public boolean register(String LineOfCareName, Professor professor) throws Exception {
        if (LineOfCareName == null || professor == null)
            throw new IllegalArgumentException();

        currentLineOfCareName = LineOfCareName;
        currentResponsableProfessor = professor;
        if (currentResponsableProfessor.addNewLineOfCare(currentLineOfCareName))
            return DAO.update(professor.getId(),professor);
        return false;
    }
}
