package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDao implements DAO<User> {

    @Override
    public ObservableList<User> getAll() {
        Connection conn = DBConnection.getConn();
        ObservableList<User> allUsers = FXCollections.observableArrayList();
        try{
            String sql = "SELECT User_ID, User_Name, Password FROM users";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int userId = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String password = rs.getString("Password");
                User user = new User(userId, userName, password);
                allUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allUsers;
    }

    @Override
    public User getById(int id) {
        return null;
    }

    @Override
    public boolean add(User item) {
        return false;
    }

    @Override
    public boolean update(User item) {
        return false;
    }

    @Override
    public boolean delete(User item) {
        return false;
    }
}
