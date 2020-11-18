package Controller;

import DAO.DBCache;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.Customer;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class CustomerListController {

    @FXML
    private Button backButton;

    @FXML
    private Button addNewCustomerButton;

    @FXML
    private Button updateCustomerButton;

    @FXML
    private Button deleteCustomerButton;

    @FXML
    private TableView<Customer> customerTableView;

    @FXML
    private TableColumn<Customer, String> customerNameColumn;

    @FXML
    private TableColumn<Customer, String> addressColumn;

    @FXML
    private TableColumn<Customer, String> postalCodeColumn;

    @FXML
    private TableColumn<Customer, String> phoneNumberColumn;

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

    /*private ObservableList<?> getObservableList(Collection<?> collection){
        ObservableList<?> list = new ObservableList<collection.>();
        Iterator<?> iterator = collection.iterator();
        while (iterator.hasNext()){

        }
    }*/

    private void fillCustomerTable(){
        ObservableList<Customer> customersList = FXCollections.observableArrayList();
        Collection<Customer> customersCollection = DBCache.getInstance().getCustomerHashMap().values();
        Iterator<Customer> iterator = customersCollection.iterator();
        while (iterator.hasNext()){
            customersList.add(iterator.next());
        }
        customerTableView.setItems(customersList);
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
    }

    @FXML
    public void initialize(){
        fillCustomerTable();

        backButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                stage = getStage("../view/MainMenu.fxml", event);
                stage.show();
                setWindowPosition();
            }
        });

        addNewCustomerButton.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                stage = getStage("../view/AddCustomer.fxml", event);
                stage.show();
                setWindowPosition();
            }
        });

        updateCustomerButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                stage = getStage("../view/AddCustomer.fxml", event);
                stage.show();
                setWindowPosition();
            }
        });

        deleteCustomerButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

            }
        });
    }
}
