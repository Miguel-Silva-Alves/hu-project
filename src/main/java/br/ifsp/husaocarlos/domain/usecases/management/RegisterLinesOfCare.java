package br.ifsp.husaocarlos.domain.usecases.management;

import br.ifsp.husaocarlos.domain.entities.Professor;

public class RegisterLinesOfCare {
    String currentLineOfCareName;
    Professor currentResponsableProfessor;
    public RegisterLinesOfCare(String LineOfCareName, Professor professor) {
        if (LineOfCareName == null || professor == null)
            throw new IllegalArgumentException();

        currentLineOfCareName = LineOfCareName;
        currentResponsableProfessor = professor;
    }

    public boolean register(){
        return currentResponsableProfessor.addNewLineOfCare(currentLineOfCareName);
    }
}
