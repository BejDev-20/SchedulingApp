package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;
import model.FirstLevelDiv;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FirstLevelDivDao implements DAO<FirstLevelDiv> {

    @Override
    public ObservableList<FirstLevelDiv> getAll() {
        DBConnection.startConnection();
        Connection conn = DBConnection.conn;
        ObservableList<FirstLevelDiv> allFirstLevelDiv = FXCollections.observableArrayList();
        try{
            String sql = "SELECT Division_ID, Division, COUNTRY_ID FROM first_level_divisions";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int divisionId = rs.getInt("Division_ID");
                String division = rs.getString("Division");
                int countryId = rs.getInt("COUNTRY_ID");
                FirstLevelDiv firstLevelDiv = new FirstLevelDiv(divisionId, division, countryId);
                allFirstLevelDiv.add(firstLevelDiv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DBConnection.closeConnection();
        return allFirstLevelDiv;
    }

    @Override
    public FirstLevelDiv getById(int id) {
        return null;
    }

    @Override
    public boolean add(FirstLevelDiv item) {
        return false;
    }

    @Override
    public boolean update(FirstLevelDiv item) {
        return false;
    }

    @Override
    public boolean delete(FirstLevelDiv item) {
        return false;
    }
}
