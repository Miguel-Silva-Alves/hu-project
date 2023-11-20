package br.ifsp.husaocarlos.domain.usecases.management;

import br.ifsp.husaocarlos.domain.entities.Roles;
import br.ifsp.husaocarlos.domain.entities.User;
import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ExportToCsvUseCase {

    private User currentUser;
    private FileWriter writer;
    private CSVWriter csvWriter;

    public ExportToCsvUseCase(User currentUser) {
        this.currentUser = currentUser;
    }

    public boolean setFileName(String filename){
        try{
            writer = new FileWriter(filename);
            csvWriter = new CSVWriter(writer);
            return true;
        }catch (IOException exception){
            exception.printStackTrace();
            return false;
        }
    }

    public void addRow(String[] newRow){
        csvWriter.writeNext(newRow);
    }

    public boolean closeAndExportFile(){
        try{
            csvWriter.close();
            return true;
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }


}
