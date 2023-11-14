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

    public List<LineOfCare> getLinesOfCareThroughManagement(Management adm) throws IllegalAccessException {
        if (adm.role != Roles.Management)
            throw new IllegalAccessException("Not a Management !");
        return adm.getLinesOfCare(DAO);
    }

    public boolean edit(String lineName, LineOfCare lineOfCare){
        return DAO.update(lineName,lineOfCare);
    }
}
