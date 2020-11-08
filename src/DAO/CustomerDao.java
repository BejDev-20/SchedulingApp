package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            String sql = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone FROM customers";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int custID = rs.getInt("Customer_ID");
                String custName = rs.getString("Customer_Name");
                String custAddress = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                Customer cust = new Customer(custID, custName, custAddress, postalCode, phone);
                allCustomers.add(cust);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DBConnection.closeConnection();
        return allCustomers;
    }

    @Override
    public Customer getById(int id) {
        return null;
    }

    @Override
    public boolean add(Customer item) {
        return false;
    }

    @Override
    public boolean update(Customer item) {
        return false;
    }

    @Override
    public boolean delete(Customer item) {
        return false;
    }
}
