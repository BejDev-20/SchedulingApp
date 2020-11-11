package Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;

public class MainMenuController {
    @FXML
    private Button addCustomerButton;

    @FXML
    private Button customersButton;

    @FXML
    private Button calendarButton;

    @FXML
    private Button addAppointmentButton;

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
        addCustomerButton.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {

            }
        });

        customersButton.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {

            }
        });

        calendarButton.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {

            }
        });

        addAppointmentButton.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {

            }
        });

    }
}