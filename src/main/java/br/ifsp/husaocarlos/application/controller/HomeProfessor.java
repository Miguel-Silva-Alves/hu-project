package br.ifsp.husaocarlos.application.controller;

import br.ifsp.husaocarlos.application.view.App;
import br.ifsp.husaocarlos.domain.entities.*;
import br.ifsp.husaocarlos.domain.entities.appointment.Appointment;
import br.ifsp.husaocarlos.domain.entities.student.StudentActionDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.ifsp.husaocarlos.application.main.Main.*;

public class HomeProfessor {
    //TextField
    @FXML
    private TextField txtFilter;

    // Table
    @FXML
    private TableView<Action> tableView;
    @FXML
    private TableColumn<Action, String> cAction;
    @FXML
    private TableColumn<Action, String> cSpecialty;
    @FXML
    private TableColumn<Action, String> cLineCare;

    private ObservableList<Action> tableData;

    private Professor professor;

    public HomeProfessor() {
        UserHolder userHolder = UserHolder.getInstance();
        User u = userHolder.getUser();
        professor = new Professor(u);
        System.out.println(professor);
    }

    @FXML
    private void initialize(){
        bindTableViewToItensList();
        bindColumnsToValueSources();
        loadDataAndShow();
    }

    private void bindColumnsToValueSources() {
        cAction.setCellValueFactory(new PropertyValueFactory<>("name"));
        cSpecialty.setCellValueFactory(new PropertyValueFactory<>("specialty"));
        cLineCare.setCellValueFactory(new PropertyValueFactory<>("lineOfCare"));
    }

    private void bindTableViewToItensList() {
        tableData = FXCollections.observableArrayList();
        tableView.setItems(tableData);
    }

    private void loadDataAndShow(){
        if(professor != null){
            List<Action> actions = findActionUseCase.findActionsOfProfessor(professor);
            tableData.clear();
            tableData.addAll(actions);
        }

    }
    @FXML
    void toRegisterAction(MouseEvent event) throws IOException {
        App.setRoot("ActionUI");
        ActionUI controller = (ActionUI) App.getController();
        controller.setProfessor(professor);
    }
    @FXML
    void toVisualizePatients(MouseEvent event) throws IOException {
        Action action = tableView.getSelectionModel().getSelectedItem();
        if(action != null){
            App.setRoot("ListAppointmentsUI");
            ListAppointmentsUI controller = (ListAppointmentsUI) App.getController();
            controller.setAction(action);
        }else{
            Utils.showAlert("Ação não encontrada", "É necessário escolher uma ação!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void toRegisterStudentInAction(MouseEvent event) throws IOException {
        Action action = tableView.getSelectionModel().getSelectedItem();
        if(action != null){
            App.setRoot("ListStudentsUI");
            ListStudentsUI controller = (ListStudentsUI) App.getController();
            controller.setActionToAddStudent(action);
        }else{
            Utils.showAlert("Ação não encontrada", "É necessário escolher uma ação!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void toVisualizeStudents(MouseEvent event) throws IOException {
        Action action = tableView.getSelectionModel().getSelectedItem();
        if(action != null){
            App.setRoot("ListStudentsUI");
            ListStudentsUI controller = (ListStudentsUI) App.getController();
            controller.setActionToSeeStudents(action);
        }else{
            Utils.showAlert("Ação não encontrada", "É necessário escolher uma ação!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void toFindAction(MouseEvent event){
        String actionName = txtFilter.getText();
        try{
            Optional<Action> optionalAction = findActionUseCase.findActionsOfProfessor(professor, actionName);
            if(optionalAction.isPresent()){
                List<Action> actions = new ArrayList<>();
                actions.add(optionalAction.get());
                tableData.clear();
                tableData.addAll(actions);
            }else{
                Utils.showAlert("Ação não encontrada", "Não foi possível encontrar a ação digitada!", Alert.AlertType.ERROR);
            }
        }catch (IllegalArgumentException e){
            Utils.showAlert("Erro", "Houve um erro durante a busca: " + e.getMessage(), Alert.AlertType.ERROR);
        }

    }
}
