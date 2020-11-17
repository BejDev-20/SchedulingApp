package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class AppointmentDao implements DAO<Appointment>{

    @Override
    public ObservableList<Appointment> getAll() {
        Connection conn = DBConnection.getConn();
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        try{
            String sql = "SELECT Appointment_ID, Title, Description, Location, Type, Start, End, " +
                         "appointments.Customer_ID, appointments.User_ID, Contact_ID FROM appointments, customers, users " +
                         "WHERE appointments.Customer_ID = customers.Customer_ID AND appointments.User_ID = users.User_ID";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int appId = rs.getInt("Appointment_ID");
                String appTitle = rs.getString("Title");
                String appDescription = rs.getString("Description");
                String appLocation = rs.getString("Location");
                String appType = rs.getString("Type");
                LocalDateTime appStartTime = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime appEndTime = rs.getTimestamp("End").toLocalDateTime();
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");
                Appointment app = new Appointment(appId, appTitle, appDescription, appLocation, appType, appStartTime,
                        appEndTime, customerId, userId, contactId);
                allAppointments.add(app);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allAppointments;
    }

    @Override
    public Appointment getById(int id) {
        Connection conn = DBConnection.getConn();
        Appointment appointment = null;
        try{
            String sql = "SELECT Appointment_ID, Title, Description, Location, Type, Start, End, " +
                    "appointments.Customer_ID, appointments.User_ID, Contact_ID FROM appointments, customers, users " +
                    "WHERE appointments.Customer_ID = customers.Customer_ID AND appointments.User_ID = users.User_ID " +
                    "AND Appointment_ID = " + id;
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int appId = rs.getInt("Appointment_ID");
                String appTitle = rs.getString("Title");
                String appDescription = rs.getString("Description");
                String appLocation = rs.getString("Location");
                String appType = rs.getString("Type");
                LocalDateTime appStartTime = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime appEndTime = rs.getTimestamp("End").toLocalDateTime();
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");
                appointment = new Appointment(appId, appTitle, appDescription, appLocation, appType, appStartTime,
                        appEndTime, customerId, userId, contactId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointment;
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
