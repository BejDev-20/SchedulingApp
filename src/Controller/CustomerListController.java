package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

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
}
