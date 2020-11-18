package Controller;

import DAO.DBCache;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Appointment;
import model.Customer;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Iterator;

import static DAO.DBCache.*;

public class AppointmentListController {

    @FXML
    private Button backButton;

    @FXML
    private TableView<Appointment> appointmentsTableView;

    @FXML
    private TableColumn<Appointment, String> titleColumn;

    @FXML
    private TableColumn<Appointment, String> descriptionColumn;

    @FXML
    private TableColumn<Appointment, String> locationColumn;

    @FXML
    private TableColumn<Appointment, String> contactColumn;

    @FXML
    private TableColumn<Appointment, String> typeColumn;

    @FXML
    private TableColumn<Appointment, String> startTimeColumn;

    @FXML
    private TableColumn<Appointment, String> endTimeColumn;

    @FXML
    private TableColumn<Appointment, String> customerColumn;

    @FXML
    private Button addNewAppointmentButton;

    @FXML
    private Button updateAppointmentButton;

    @FXML
    private Button deleteAppointmentButton;

    @FXML
    private ComboBox<?> contactNameComboBox;

    @FXML
    private Label contactNameLabel;

    @FXML
    private RadioButton weekRadioButton;

    @FXML
    private RadioButton monthRadioButton;

    @FXML
    private RadioButton allRadioButton;

    private Stage stage;
    private Parent scene;

    private Stage getStage(String FXMLPath, ActionEvent event){
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        try {
            scene = FXMLLoader.load(getClass().getResource(FXMLPath));
            stage.setScene(new Scene(scene));
            // throws an exception for the .load() method if it is not possible to load the hierarchy from the FXML doc
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stage;
    }

    private void setWindowPosition(){
        double x = (Screen.getPrimary().getBounds().getWidth() - scene.getBoundsInParent().getWidth())/2;
        double y = (Screen.getPrimary().getBounds().getHeight() - scene.getBoundsInParent().getHeight())/2;
        stage.setX(x);
        stage.setY(y);
        stage.setResizable(false);
    }

    private void fillAppointmentTable() {
        ObservableList<Appointment> appointmentsList = FXCollections.observableArrayList();
        Collection<Appointment> appointmentsCollection = getInstance().getAppointmentHashMap().values();
        Iterator<Appointment> iterator = appointmentsCollection.iterator();
        while (iterator.hasNext()){
            Appointment app = iterator.next();
            LocalDate date = app.getStartTime().toLocalDate();
            if (weekRadioButton.isSelected() && date.getYear() == LocalDate.now().getYear() &&
                (date.getDayOfYear() - LocalDate.now().getDayOfYear()) <= 7){
                appointmentsList.add(app);
            } else if (monthRadioButton.isSelected() && date.getYear() == LocalDate.now().getYear() &&
                       date.getMonth() == LocalDate.now().getMonth()){
                appointmentsList.add(app);
            } else {
                appointmentsList.add(app);
            }
            appointmentsList.add(iterator.next());
        }
        appointmentsTableView.setItems(appointmentsList);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

        contactColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Appointment, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Appointment, String> app) {
                return app.getValue().getCustomer().getName();
            }
        });

        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        customerColumn.setCellValueFactory(new PropertyValueFactory<>("customer"));
    }

    @FXML
    public void initialize(){
        fillAppointmentTable();

        weekRadioButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                fillAppointmentTable();
            }
        });

        monthRadioButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                fillAppointmentTable();
            }
        });

        allRadioButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                fillAppointmentTable();
            }
        });


        backButton.setOnAction(new EventHandler<ActionEvent>() {
        public void handle(ActionEvent event) {
            stage = getStage("../view/MainMenu.fxml", event);
            stage.show();
            setWindowPosition();
        }
        });

        addNewAppointmentButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            stage = getStage("../view/AddAppointment.fxml", event);
            stage.show();
            setWindowPosition();
            }
        });

        updateAppointmentButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                stage = getStage("../view/AddAppointment.fxml", event);
                stage.show();
                setWindowPosition();
            }
        });

        deleteAppointmentButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            }
        });
    }


}
