package Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerListController {
    @FXML
    private Button saveButton;

    @FXML
    private Button backButton;

    @FXML
    private Button addNewCustomerButton;

    @FXML
    private Button updateCustomerButton;

    @FXML
    private Button deleteCustomerButton;

    @FXML
    private TableView<?> customerTableView;

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

            }
        });

        backButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                stage = getStage("../view/MainMenu.fxml", event);
                stage.show();
            }
        });

        addNewCustomerButton.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                stage = getStage("../view/AddCustomer.fxml", event);
                stage.show();
            }
        });

        updateCustomerButton.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                stage = getStage("../view/AddAppointment.fxml", event);
                stage.show();
            }
        });
    }
}
