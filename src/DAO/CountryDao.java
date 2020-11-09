package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import model.Country;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CountryDao implements DAO<Country> {

    @Override
    public ObservableList<Country> getAll() {
        DBConnection.startConnection();
        Connection conn = DBConnection.conn;
        ObservableList<Country> allCountries = FXCollections.observableArrayList();
        try{
            String sql = "SELECT Country_ID, Country FROM countries";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int countryId = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");
                Country country = new Country(countryId, countryName);
                allCountries.add(country);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DBConnection.closeConnection();
        return allCountries;
    }

    @Override
    public Country getById(int id) {
        return null;
    }

    @Override
    public boolean add(Country item) {
        return false;
    }

    @Override
    public boolean update(Country item) {
        return false;
    }

    @Override
    public boolean delete(Country item) {
        return false;
    }
}
