package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

import java.sql.*;
import java.time.LocalDateTime;

import static DAO.DBCache.getInstance;

/**
 *
 */
public class CustomerDao implements DAO<Customer>{

    @Override
    public ObservableList<Customer> getAll() {
        DBConnection.startConnection();
        Connection conn = DBConnection.conn;
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
        try{
            String sql = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, Division_ID FROM customers";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Integer customerID = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String customerAddress = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                Integer divisionId = rs.getInt("Division_ID");
                Customer customer = new Customer(customerID, customerName, customerAddress, postalCode, phone, divisionId);
                allCustomers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DBConnection.closeConnection();
        return allCustomers;
    }

    @Override
    public Customer getById(int id) {
        DBConnection.startConnection();
        Connection conn = DBConnection.conn;
        Customer customer = null;
        try{
            String sql = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, Division_ID FROM customers" +
                         " WHERE Customer_ID = " + id;
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                int customerID = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String customerAddress = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                Integer divisionId = rs.getInt("Division_ID");
                customer = new Customer(customerID, customerName, customerAddress, postalCode, phone, divisionId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DBConnection.closeConnection();
        return customer;
    }

    @Override
    public boolean add(Customer item) {
        try {
            DBConnection.startConnection();
            String sql = "INSERT INTO customers VALUES(NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement psti = DBConnection.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            psti.setString(1, item.getName());
            psti.setString(2, item.getAddress());
            psti.setString(3, item.getPostalCode());
            psti.setString(4, item.getPhoneNumber());
            psti.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            psti.setString(6, getInstance().getUser().getName());
            psti.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            psti.setString(8, getInstance().getUser().getName());
            psti.setInt(9, item.getDivisionId());
            psti.execute();
            DBConnection.closeConnection();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean update(Customer item) {
        try {
            DBConnection.startConnection();
            String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?," +
                         " Last_Update = ?, Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = ? ;";
            PreparedStatement psti = DBConnection.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            psti.setString(1, item.getName());
            psti.setString(2, item.getAddress());
            psti.setString(3, item.getPostalCode());
            psti.setString(4, item.getPhoneNumber());
            psti.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            psti.setString(6, getInstance().getUser().getName());
            psti.setInt(7, item.getDivisionId());
            psti.setInt(8, item.getCustomerId());
            psti.execute();
            DBConnection.closeConnection();
    } catch (SQLException e){
        e.printStackTrace();
    }
        return true;
    }

    @Override
    public boolean delete(Customer item) {
        return false;
    }
}
