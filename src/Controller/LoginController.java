package Controller;
import DAO.DBCache;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class LoginController {
    @FXML
    private ChoiceBox<?> languageDropDown;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Button loginButton;

    @FXML
    private Button exitButton;

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
    public void initialize() {
        loginButton.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                String username = usernameTextField.getText();
                String password = passwordTextField.getText();
                User user = new User(0, username, password);
                if (searchForUser(user)){
                    stage = getStage("../view/MainMenu.fxml", event);
                    stage.show();
                }
            }
        });

        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        //passwordTextField
    }

    /**
     * WHAT DO IF USER ISN'T FOUND
     * @param user
     * @return
     */
    private boolean searchForUser(User user) {
        DBCache dbCache = DBCache.getInstance();
        HashMap<Integer, User> users = dbCache.getUserHashMap();
        for(User oneUser : users.values()){
            if(oneUser.equals(user)){
                return true;
        }
        }
        return false;
    }


}
