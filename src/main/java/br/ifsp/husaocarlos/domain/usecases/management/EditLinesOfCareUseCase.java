package br.ifsp.husaocarlos.domain.usecases.management;

import br.ifsp.husaocarlos.domain.entities.LineOfCare;
import br.ifsp.husaocarlos.domain.entities.Management;
import br.ifsp.husaocarlos.domain.entities.Roles;

import java.util.List;

public class EditLinesOfCareUseCase {

    LinesOfCareDAO DAO;

    public EditLinesOfCareUseCase(LinesOfCareDAO DAO) {
        this.DAO = DAO;
    }

    public List<LineOfCare> getLinesOfCareThroughManagement(Management management) throws IllegalAccessException {
        if (management == null)
            throw new IllegalArgumentException("Null management !");
        if (management.role != Roles.Management)
            throw new IllegalAccessException("Not a management !");

        return management.getLinesOfCare(DAO);
    }

    public boolean edit(String lineName, LineOfCare lineOfCare){
        if (lineName == null || lineOfCare == null)
            throw new IllegalArgumentException("One or more null argmuments !");

        return DAO.update(lineName,lineOfCare);
    }
}
