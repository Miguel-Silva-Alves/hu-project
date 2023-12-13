package br.ifsp.husaocarlos.domain.usecases.professor;
import br.ifsp.husaocarlos.domain.entities.LineOfCare;
import br.ifsp.husaocarlos.domain.usecases.management.LinesOfCareDAO;

import java.util.Optional;

public class FindLineOfCareUseCase {
    LinesOfCareDAO dao;

    public FindLineOfCareUseCase(LinesOfCareDAO dao) {
        this.dao = dao;
    }

    public Optional<LineOfCare> findLineOfCareByName(String name){
        return dao.findOne(name);
    }
}
