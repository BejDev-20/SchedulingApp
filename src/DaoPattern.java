import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import DAO.DBConnection;
import DAO.DBQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import static javafx.application.Application.launch;

public class DaoPattern extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/LoginScreen.fxml"));
        primaryStage.setTitle("Sasquatch Consulting");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) throws SQLException {
        Connection conn = DBConnection.startConnection();
        String insertStatement = "INSERT INTO countries(Country, Create_Date, Created_By, Last_Updated_By) " +
                "VALUES(?, ?, ?, ?)";
        DBQuery.setPreparedStatement(conn, insertStatement);
        PreparedStatement ps = DBQuery.getPreparedStatement();
        String countryName;
        String createDate = "2020-10-25 00:00:00";
        String createdBy = "admin";
        String lastUpdateBy = "admin";
        Scanner keyboard = new Scanner(System.in);
        countryName = keyboard.nextLine();

        ps.setString(1, countryName);
        ps.setString(2, createDate);
        ps.setString(3, createdBy);
        ps.setString(4, lastUpdateBy);

        ps.execute();
        launch(args);
        DBConnection.closeConnection();
    }
}
