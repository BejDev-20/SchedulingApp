package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class AppointmentDao implements DAO<Appointment>{

    @Override
    public ObservableList<Appointment> getAll() {
        DBConnection.startConnection();
        Connection conn = DBConnection.conn;
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        try{
            String sql = "SELECT Appointment_ID, Title, Description, Location, Type, Start, End, " +
                         "Customer_ID, Customer_Name, Address, Postal_Code, Phone," +
                         "User_ID, User_Name, Password" +
                         "FROM appointments, customers, users " +
                         "WHERE appointments.Customer_ID = customers.Customer_ID AND appointments.User_ID = users.User_ID";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int appId = rs.getInt("Appointment_ID");
                String appTitle = rs.getString("Title");
                String appDescription = rs.getString("Description");
                String appLocation = rs.getString("Location");
                String appType = rs.getString("Type");
                LocalDateTime appStartTime = rs.getTimestamp("Start");
                String
                Appointment app = new Appointment();
                allAppointments.add(app);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DBConnection.closeConnection();
    }

    @Override
    public Appointment getById(int id) {
        return null;
    }

    @Override
    public boolean add(Appointment item) {
        return false;
    }

    @Override
    public boolean update(Appointment item) {
        return false;
    }

    @Override
    public boolean delete(Appointment item) {
        return false;
    }
}
