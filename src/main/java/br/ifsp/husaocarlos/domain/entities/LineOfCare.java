package br.ifsp.husaocarlos.domain.entities;

import java.util.List;

public class LineOfCare {
    private String lineName;
    private List<Action> actions;
    private Professor reponsableProfessor;

    public LineOfCare(String lineName, List<Action> actions, Professor reponsableProfessor) {
        this.lineName = lineName;
        this.actions = actions;
        this.reponsableProfessor = reponsableProfessor;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    @Override
    public String toString() {
        return "LineOfCare{" +
                "lineName='" + lineName + '\'' +
                ", actions=" + actions +
                ", reponsableProfessor=" + reponsableProfessor +
                '}';
    }
}
