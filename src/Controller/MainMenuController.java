package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {
    @FXML
    private Button addCustomerButton;

    @FXML
    private Button customersButton;

    @FXML
    private Button appointmentsButton;

    @FXML
    private Button addAppointmentButton;

    private Stage stage;
    private Parent scene;

    /**
     * Retrieves the stage from the given path and event
     * @param FXMLPath path of the FXML document to set up the next scene
     * @param event that triggers the action
     * @return the stage from the given path and event
     */
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

    /**
     * Calculates and sets the position of the current scene to show it on the desktop
     */
    private void setWindowPosition(){
        double x = (Screen.getPrimary().getBounds().getWidth() - scene.getBoundsInParent().getWidth())/2;
        double y = (Screen.getPrimary().getBounds().getHeight() - scene.getBoundsInParent().getHeight())/2;
        stage.setX(x);
        stage.setY(y);
        stage.setResizable(false);
    }

    /**
     * Called to initialize the controller after its root element has been completely processed
     * Sets up customers, appointments, add customer, and add appointment buttons
     */
    @FXML
    public void initialize(){
        addCustomerButton.setOnAction(event -> {
            stage = getStage("../view/AddCustomer.fxml", event);
            stage.show();
            setWindowPosition();
        });

        customersButton.setOnAction(event -> {
            stage = getStage("../view/CustomersList.fxml", event);
            stage.show();
            setWindowPosition();
        });

        appointmentsButton.setOnAction(event -> {
            stage = getStage("../view/AppointmentsList.fxml", event);
            stage.show();
            setWindowPosition();
        });

        addAppointmentButton.setOnAction(event -> {
            stage = getStage("../view/AddAppointment.fxml", event);
            stage.show();
            setWindowPosition();
        });

    }
}
