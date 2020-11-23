package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import java.sql.*;
import java.time.LocalDateTime;

import static DAO.DBCache.getInstance;

public class AppointmentDao implements DAO<Appointment>{

    @Override
    public ObservableList<Appointment> getAll() {
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        try{
            String sql = "SELECT Appointment_ID, Title, Description, Location, Type, Start, End, " +
                         "appointments.Customer_ID, appointments.User_ID, Contact_ID FROM appointments, customers, users " +
                         "WHERE appointments.Customer_ID = customers.Customer_ID AND appointments.User_ID = users.User_ID";
            PreparedStatement ps = DBConnection.getConn().prepareStatement(sql);
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
        Appointment appointment = null;
        try{
            String sql = "SELECT Appointment_ID, Title, Description, Location, Type, Start, End, " +
                    "appointments.Customer_ID, appointments.User_ID, Contact_ID FROM appointments, customers, users " +
                    "WHERE appointments.Customer_ID = customers.Customer_ID AND appointments.User_ID = users.User_ID " +
                    "AND Appointment_ID = " + id;
            PreparedStatement ps = DBConnection.getConn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                if (id == rs.getInt("Appointment_ID")) {
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
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (appointment != null) DBCache.getInstance().updateAppointments();
        return appointment;
    }

    @Override
    public boolean add(Appointment item) {
        try {
            String sql = "INSERT INTO appointments VALUES(NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement psti = DBConnection.getConn().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            psti.setString(1, item.getTitle());
            psti.setString(2, item.getDescription());
            psti.setString(3, item.getLocation());
            psti.setString(4, item.getType());
            psti.setTimestamp(5, Timestamp.valueOf(item.getStartTime())); // make sure the Time zone is correct
            psti.setTimestamp(6, Timestamp.valueOf(item.getEndTime()));
            psti.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now())); // make sure the Time zone is correct
            psti.setString(8, getInstance().getUser().getName());
            psti.setTimestamp(9, Timestamp.valueOf(LocalDateTime.now()));
            psti.setString(10, getInstance().getUser().getName());
            psti.setInt(11, item.getCustomer().getCustomerId());
            psti.setInt(12, item.getUser().getUserId());
            psti.setInt(13, item.getContact().getContactId());
            psti.execute();
            DBCache cache = DBCache.getInstance();
            cache.updateAppointments();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean update(Appointment item) {
        try {
            DBConnection.getConn();
            String sql = "UPDATE appointments SET Title=?, Description=?, Location=?, Type=?, Start=?, End=?, " +
                         "Last_Update=?, Last_Updated_By=?, Customer_ID=?, User_ID=?, Contact_ID=? " +
                         "WHERE Appointment_ID = " + item.getAppointmentId();
            PreparedStatement psti = DBConnection.getConn().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            psti.setString(1, item.getTitle());
            psti.setString(2, item.getDescription());
            psti.setString(3, item.getLocation());
            psti.setString(4, item.getType());
            psti.setTimestamp(5, Timestamp.valueOf(item.getStartTime())); // make sure the Time zone is correct
            psti.setTimestamp(6, Timestamp.valueOf(item.getEndTime()));
            psti.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now())); // make sure the Time zone is correct
            psti.setString(8, getInstance().getUser().getName());
            psti.setInt(9, item.getCustomer().getCustomerId());
            psti.setInt(10, item.getUser().getUserId());
            psti.setInt(11, item.getContact().getContactId());
            psti.execute();
            DBCache cache = DBCache.getInstance();
            cache.updateAppointments();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean delete(Appointment item) {
        try {
            String sql = "DELETE FROM appointments WHERE Appointment_ID = ? ;";
            PreparedStatement psti = DBConnection.getConn().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            psti.setInt(1, item.getAppointmentId());
            psti.execute();
            DBCache.getInstance().updateAppointments();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
}
