package br.ifsp.husaocarlos.application.repository;

import br.ifsp.husaocarlos.domain.entities.Report;
import br.ifsp.husaocarlos.domain.usecases.report.ReportDAO;

import java.util.List;
import java.util.Optional;

public class InMemoryReportDAO implements ReportDAO {

    @Override
    public boolean save(Report object) {
        return false;
    }

    @Override
    public Optional<Report> findOne(Integer key) {
        return Optional.empty();
    }
    @Override
    public List<Report> findAll() {
        return null;
    }

    @Override
    public boolean update(Integer key, Report object) {
        return false;
    }
    @Override
    public boolean delete(Integer key) {
        return false;
    }
}
