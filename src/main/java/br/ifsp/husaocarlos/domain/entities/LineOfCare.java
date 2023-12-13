package br.ifsp.husaocarlos.domain.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.List;

@Entity
public class LineOfCare {
    @Id
    public String lineName;
    @OneToMany
    public List<Action> actions;
    @OneToOne
    public Professor reponsableProfessor;

    public LineOfCare(String lineName, List<Action> actions, Professor reponsableProfessor) {
        this.lineName = lineName;
        this.actions = actions;
        this.reponsableProfessor = reponsableProfessor;
    }

    public LineOfCare() {
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
