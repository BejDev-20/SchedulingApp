package DAO;

import model.Customer;

import java.sql.Connection;
import java.util.List;

/**
 *
 */
public class CustomerDao implements DAO<Customer>{


    @Override
    public List<Customer> getAll() {
        DBConnection.startConnection();
        Connection conn = DBConnection.conn;

        DBConnection.closeConnection();
        return null;
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
