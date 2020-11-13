package Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class AppointmentListController {

    @FXML
    private Button saveButton;

    @FXML
    private Button backButton;

    @FXML
    private TableView<?> appointmentsTableView;

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

    @FXML
    public void initialize(){
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                stage = getStage("../view/MainMenu.fxml", event);
                stage.show();
            }
        });

        backButton.setOnAction(new EventHandler<ActionEvent>() {
        public void handle(ActionEvent event) {
            stage = getStage("../view/MainMenu.fxml", event);
            stage.show();
        }
        });

        addNewAppointmentButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            stage = getStage("../view/AddAppointment.fxml", event);
            stage.show();
            }
        });

        updateAppointmentButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                stage = getStage("../view/AddAppointment.fxml", event);
                stage.show();
            }
        });

        deleteAppointmentButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            }
        });
    }
}
