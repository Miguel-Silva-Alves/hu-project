package br.ifsp.husaocarlos.domain.usecases.report;

import br.ifsp.husaocarlos.domain.entities.Report;


public class RegisterReportUseCase {
    ReportDAO DAO;

    public RegisterReportUseCase(ReportDAO DAO) {
        this.DAO = DAO;
    }

    public boolean registerReport(Report report){

        return DAO.save(report);
    }
}
