package br.ifsp.husaocarlos.domain.usecases.management;

import br.ifsp.husaocarlos.domain.entities.Action;
import br.ifsp.husaocarlos.domain.entities.LineOfCare;
import br.ifsp.husaocarlos.domain.entities.Professor;

import java.util.ArrayList;
import java.util.List;

public class RegisterLinesOfCareUseCase {
    LinesOfCareDAO DAO;

    public RegisterLinesOfCareUseCase(LinesOfCareDAO DAO) {
        if (DAO == null)
            throw new IllegalArgumentException();
        this.DAO = DAO;
    }

    public boolean register(String lineName,Professor professor){
        if (professor == null || lineName == null)
            throw new IllegalArgumentException();
        List<Action> actionList = new ArrayList<>();
        LineOfCare newLine = new LineOfCare(lineName,actionList, professor);
        return DAO.save(newLine);
    }
}
