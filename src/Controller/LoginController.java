package Controller;
import DAO.DBCache;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Responsible for the control of the login form. Provides necessary text field for username and password.
 * Sets up login/exit buttons.
 * @author Iulia Bejsovec
 */
public class LoginController {
    @FXML
    private ChoiceBox<Locale.LanguageRange> languageDropDown;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Button loginButton;

    @FXML
    private Button exitButton;

    @FXML
    private Label localeLabel;


    private Stage stage;
    private Parent scene;
    File logFile;

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
     * Called to initialize a controller after its root element has been completely processed
     * Sets up login and exit buttons, pops alert if username or password isn't correct
     * LAMBDA USE. Lambda expressions were used by defining an anonymous functions to set up functionality for login
     * and exit buttons. It is appropriate to use lambda expression as it produces readable and concise code.
     */
    @FXML
    public void initialize() {
        logFile = new File("login_activity.txt");
        localeLabel.setText(ZoneId.systemDefault().toString());
        loginButton.setOnAction(event -> {
            try {
                String username = usernameTextField.getText();
                String password = passwordTextField.getText();
                User user = new User(0, username, password);
                if (searchForUser(user) != null) {
                    stage = getStage("../view/MainMenu.fxml", event);
                    stage.show();
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException ex) {
                var alert = new Alert(Alert.AlertType.ERROR, "Incorrect user name or password");
                alert.setTitle("Login Error");
                alert.setResizable(false);
                alert.show();
            }
        });

        exitButton.setOnAction(event -> {
            System.exit(0);
        });
    }

    /**
     * Verifies the information from the form (username/password) to the list of existing users. Returns the user
     * if found, null otherwise
     * @param user user that needs to be verified
     * @return the user if found, null if no matching user exists
     */
    private User searchForUser(User user) {
        DBCache dbCache = DBCache.getInstance();
        HashMap<Integer, User> users = dbCache.getUserHashMap();
        User userToReturn = null;
        for(User oneUser : users.values()){
            if(oneUser.equals(user)){
                DBCache.getInstance().setUser(oneUser);
                userToReturn = oneUser;
            }
        }
        try {
            LocalDateTime dateTime = LocalDateTime.now();
            ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
            ZonedDateTime localZDT = ZonedDateTime.of(dateTime.toLocalDate(), dateTime.toLocalTime(), localZoneId);
            ZoneId utcZoneId = ZoneId.of("UTC");
            Instant localToGMTInstant = localZDT.toInstant();
            ZonedDateTime localToUtc = localZDT.withZoneSameInstant(utcZoneId);

            FileWriter myWriter = new FileWriter(logFile, true);
            myWriter.append(localToUtc.toLocalDate() + "\t" + localToUtc.toLocalTime() + "\t\t" + user.getName() + "\t");
            if (userToReturn != null){
                myWriter.append("Successful login\n");
            } else {
                myWriter.append("Failed login\n");
            }
            myWriter.close();
        } catch (IOException ex){}
        return userToReturn;
    }


}
