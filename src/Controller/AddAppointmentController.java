package Controller;

import DAO.AppointmentDao;
import DAO.DBCache;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.Appointment;
import model.Contact;
import model.Customer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class AddAppointmentController {

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label appointmentIdLabel;

    @FXML
    private Label titleLabel;

    @FXML
    private Label contactLabel;

    @FXML
    private Label typeLabel;

    @FXML
    private Label startDateLabel;

    @FXML
    private Label endDateLabel;

    @FXML
    private TextField appointmentTextField;

    @FXML
    private ComboBox<Contact> contactComboBox;

    @FXML
    private TextField titleTextField;

    @FXML
    private TextField typeTextField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField locationTextField;

    @FXML
    private Label locationLabel;

    @FXML
    private ComboBox<LocalTime> startTimeComboBox;

    @FXML
    private ComboBox<LocalTime> endTimeComboBox;

    @FXML
    private Label startTimeLabel1;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private Button allAppointmentsButton;

    @FXML
    private ComboBox<Customer> customersComboBox;

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

    public void populateAppointmentData(Appointment appointment){
        if (appointment != null) {
            titleLabel.setText("Update Appointment");
            datePicker.setValue(appointment.getStartTime().toLocalDate());
            startTimeComboBox.setValue(appointment.getStartTime().toLocalTime());
            endTimeComboBox.setValue(appointment.getEndTime().toLocalTime());;
            appointmentTextField.setText(Integer.toString(appointment.getAppointmentId()));
            contactComboBox.setValue(appointment.getContact());
            titleTextField.setText(appointment.getTitle());
            typeTextField.setText(appointment.getType());
            locationTextField.setText(appointment.getLocation());
            descriptionTextArea.setText(appointment.getDescription());
        }
    }


    @FXML
    public void initialize(){
        ObservableList<Contact> contactsList = FXCollections.observableArrayList();
        Collection<Contact> contactsCollection = DBCache.getInstance().getContactHashMap().values();
        Iterator<Contact> iterator = contactsCollection.iterator();
        while (iterator.hasNext()){
            contactsList.add(iterator.next());
        }
        contactComboBox.setItems(contactsList);

        ObservableList<LocalTime> startTimes = setUpTime(Appointment.getSTART_HOUR(), Appointment.getEND_HOUR());
        ObservableList<LocalTime> endTimes = setUpTime(Appointment.getSTART_HOUR().plusMinutes(15), Appointment.getEND_HOUR().plusMinutes(30));
        startTimeComboBox.setItems(startTimes);
        endTimeComboBox.setItems(endTimes);
        startTimeComboBox.getSelectionModel().selectFirst();
        endTimeComboBox.getSelectionModel().selectFirst();

        startTimeComboBox.setOnAction(event ->{
            if (startTimeComboBox.getSelectionModel().getSelectedIndex() > endTimeComboBox.getSelectionModel().getSelectedIndex()) {
                endTimeComboBox.setValue(startTimeComboBox.getValue().plusMinutes(15));
            }
        });

        endTimeComboBox.setOnAction(event ->{
            if (endTimeComboBox.getSelectionModel().getSelectedIndex() < startTimeComboBox.getSelectionModel().getSelectedIndex()) {
                startTimeComboBox.setValue(endTimeComboBox.getValue().minusMinutes(15));
            }
        });

        datePicker.setValue(LocalDate.now());
        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        Collection<Customer> customerCollection = DBCache.getInstance().getCustomerHashMap().values();
        Iterator<Customer> customerIterator = customerCollection.iterator();
        while (customerIterator.hasNext()){
            customerList.add(customerIterator.next());
        }
        customersComboBox.setItems(customerList);
        customersComboBox.getSelectionModel().selectFirst();

        saveButton.setOnAction(event -> {
            saveAppointment();
            stage = getStage("../view/AppointmentsList.fxml", event);
            stage.show();
            setWindowPosition();
        });

        cancelButton.setOnAction(event -> {
            stage = getStage("../view/MainMenu.fxml", event);
            stage.show();
            setWindowPosition();
        });

        allAppointmentsButton.setOnAction(event -> {
            stage = getStage("../view/AppointmentsList.fxml", event);
            stage.show();
            setWindowPosition();
        });

    }

    private ObservableList<LocalTime> setUpTime(LocalTime start, LocalTime end){
        ObservableList<LocalTime> times = FXCollections.observableArrayList();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        while (start.isBefore(end.minusMinutes(29))){
            times.add(start);
            start = start.plusMinutes(15);
        }
        return times;
    }

    private void saveAppointment(){
        LocalDate date = datePicker.getValue();
        LocalTime startTime = startTimeComboBox.getValue();
        LocalTime endTime = endTimeComboBox.getValue();
        LocalDateTime startDateTime = LocalDateTime.of(date.getYear(), date.getMonth(),
                date.getDayOfMonth(), startTime.getHour(), startTime.getMinute());
        LocalDateTime endDateTime = LocalDateTime.of(date.getYear(), date.getMonth(),
                date.getDayOfMonth(), endTime.getHour(), endTime.getMinute());
        int customerId = customersComboBox.getSelectionModel().getSelectedItem().getCustomerId();
        System.out.println(DBCache.getInstance().getUser().getUserId());
        int userId = DBCache.getInstance().getUser().getUserId();
        Appointment app = new Appointment(Integer.parseInt(appointmentTextField.getText()),
                titleTextField.getText(),
                descriptionTextArea.getText(),
                locationTextField.getText(),
                typeTextField.getText(),
                startDateTime,
                endDateTime,
                customerId,
                userId,
                contactComboBox.getSelectionModel().getSelectedItem().getContactId());
        AppointmentDao appointmentDao = new AppointmentDao();
        if (DBCache.getAppById(app.getAppointmentId()) != null){
            appointmentDao.update(app);
        } else {
            appointmentDao.add(app);
        }

    }

}
