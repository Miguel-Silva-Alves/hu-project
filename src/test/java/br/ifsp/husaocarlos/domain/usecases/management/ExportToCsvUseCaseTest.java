package br.ifsp.husaocarlos.domain.usecases.management;
import br.ifsp.husaocarlos.domain.entities.Professor;
import br.ifsp.husaocarlos.domain.entities.Roles;
import br.ifsp.husaocarlos.domain.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.security.enterprise.credential.Password;


public class ExportToCsvUseCaseTest {

    private User currentUser;

    @BeforeEach
    public void setup(){
        currentUser = new Professor("prof.educador@gmail.com","579.456.789-56","João","123456","la na pqp",null, Roles.Professor);
    }

    @Test
    public void CreateAndExporteCSVFileWithSucess() {
        ExportToCsvUseCase exportToCsvUseCase = new ExportToCsvUseCase(currentUser);
        exportToCsvUseCase.setFileName("Test.csv");

        exportToCsvUseCase.addRow(new String[]{"Nome", "Idade", "Cidade"});
        exportToCsvUseCase.addRow(new String[]{"João", "25", "São Paulo"});
        exportToCsvUseCase.addRow(new String[]{"Maria", "30", "Rio de Janeiro"});
        exportToCsvUseCase.closeAndExportFile();
    }
}
