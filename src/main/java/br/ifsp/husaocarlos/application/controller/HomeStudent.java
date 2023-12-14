package br.ifsp.husaocarlos.application.controller;

import br.ifsp.husaocarlos.domain.entities.Professor;
import br.ifsp.husaocarlos.domain.entities.User;
import br.ifsp.husaocarlos.domain.entities.appointment.Appointment;
import br.ifsp.husaocarlos.domain.entities.appointment.AppointmentStatus;
import br.ifsp.husaocarlos.domain.entities.student.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.util.List;

import static br.ifsp.husaocarlos.application.main.Main.listAppointmentUseCase;
import static br.ifsp.husaocarlos.application.main.Main.updateAppointmentUseCase;

public class HomeStudent {

    // Button
    @FXML
    private Button btnUpdateAppointment;

    // ChoiceBox
    @FXML
    private ChoiceBox chbOptionsFilter;

    // TextField
    @FXML
    private TextField txtFilter;

    // Table
    @FXML
    private TableView<Appointment> tableView;
    @FXML
    private TableColumn<Appointment, String> cDate;
    @FXML
    private TableColumn<Appointment, String> cAction;
    @FXML
    private TableColumn<Appointment, String> cStudent;
    @FXML
    private TableColumn<Appointment, String> cStatus;

    private ObservableList<Appointment> tableData;

    private Student student;

    public HomeStudent() {
        UserHolder userHolder = UserHolder.getInstance();
        User u = userHolder.getUser();
        student = new Student(u);
        System.out.println(student);
    }

    @FXML
    private void initialize(){
        chbOptionsFilter.getItems().add("Data:");
        chbOptionsFilter.getItems().add("Paciente:");
        chbOptionsFilter.getItems().add("Ação:");
        chbOptionsFilter.getItems().add("Status:");
        chbOptionsFilter.getItems().add("Todos:");
        chbOptionsFilter.setValue("Escolha o filtro...");

        bindTableViewToItensList();
        bindColumnsToValueSources();
        loadAppointments();
    }

    private void bindColumnsToValueSources() {
        cDate.setCellValueFactory(new PropertyValueFactory<>("dateFormated"));
        cAction.setCellValueFactory(new PropertyValueFactory<>("action"));
        cStudent.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        cStatus.setCellValueFactory(new PropertyValueFactory<>("statusName"));
    }

    private void bindTableViewToItensList() {
        tableData = FXCollections.observableArrayList();
        tableView.setItems(tableData);
    }

    private void loadAppointments(){
        if (student != null) {
            List<Appointment> appointments = listAppointmentUseCase.findAppointmentsOfStudent(student);
            tableData.clear();
            tableData.addAll(appointments);
        }
    }

    @FXML
    void getSelected(MouseEvent event){
        Appointment appointment = tableView.getSelectionModel().getSelectedItem();
        if(appointment != null){
            btnUpdateAppointment.setVisible(true);
            switch (appointment.getStatus()){
                case Scheduled -> btnUpdateAppointment.setText("Em atendimento");
                case InService -> btnUpdateAppointment.setText("Atendido");
                default -> btnUpdateAppointment.setVisible(false);
            }
            updateAppointmentUseCase.changeStatus(student,appointment);
        }

    }

    @FXML
    void changeAppointment(MouseEvent event){
        Appointment appointment = tableView.getSelectionModel().getSelectedItem();
        if(btnUpdateAppointment.getText().equals("Em atendimento")){
            appointment.inService();
        } else if (btnUpdateAppointment.getText().equals("Atendido")) {
            appointment.attend();
        }

        // Update
        if(updateAppointmentUseCase.changeStatus(student, appointment)){
            loadAppointments();
            Utils.showAlert("Sucesso", "Consulta atualizada com sucesso!", Alert.AlertType.CONFIRMATION);
        }else{
            Utils.showAlert("Erro", "Não foi possível atualizar a consulta!", Alert.AlertType.ERROR);
        }

        btnUpdateAppointment.setVisible(false);

    }

    @FXML
    void searchAppointments(MouseEvent event){
        String filter = chbOptionsFilter.getValue().toString();
        String filterValue = txtFilter.getText();
        if(filterValue.isEmpty() && !filter.equals("Todos:")){
            Utils.showAlert("Erro", "Informe algum filtro", Alert.AlertType.ERROR);
        }else{
            List<Appointment> appointments;
            try{
                if(filter.equals("Paciente:")){
                    appointments = listAppointmentUseCase.findAppointmentsOfStudentbyPatient(student, filterValue);
                } else if (filter.equals("Ação:")) {
                    appointments = listAppointmentUseCase.findAppointmentsOfStudentbyAction(student, filterValue);
                } else if (filter.equals("Data:")) {
                    appointments = listAppointmentUseCase.findAppointmentsOfStudentbyDate(student, filterValue);
                }else if (filter.equals("Status:")) {
                    appointments = listAppointmentUseCase.findAppointmentsOfStudentbyStatus(student, filterValue);
                } else{
                    appointments = listAppointmentUseCase.findAppointmentsOfStudent(student);
                }
                tableData.clear();
                tableData.addAll(appointments);
            }catch (IllegalArgumentException e){
                Utils.showAlert("Erro", e.getMessage(), Alert.AlertType.ERROR);
            }
        }
        txtFilter.setText("");
    }

}
