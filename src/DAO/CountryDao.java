package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;
import java.sql.*;
import java.time.LocalDateTime;

import static DAO.DBCache.getInstance;

public class CountryDao implements DAO<Country> {

    @Override
    public ObservableList<Country> getAll() {
        ObservableList<Country> allCountries = FXCollections.observableArrayList();
        try{
            String sql = "SELECT Country_ID, Country FROM countries";
            PreparedStatement ps = DBConnection.getConn().prepareStatement(sql);
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
        return allCountries;
    }

    @Override
    public Country getById(int id) {
        Country country = null;
        try{
            String sql = "SELECT Country_ID, Country FROM countries WHERE Country_ID = " + id;
            PreparedStatement ps = DBConnection.getConn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                if(id == rs.getInt("Country_ID")) {
                    int countryId = rs.getInt("Country_ID");
                    String countryName = rs.getString("Country");
                    country = new Country(countryId, countryName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (country != null) DBCache.getInstance().updateCountries();
        return country;
    }

    @Override
    public boolean add(Country item) {
        try {
            String sql = "INSERT INTO countries VALUES(NULL, ?, ?, ?, ?, ?)";
            PreparedStatement psti = DBConnection.getConn().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            psti.setString(1, item.getName());
            psti.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            psti.setString(3, getInstance().getUser().getName());
            psti.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            psti.setString(5, getInstance().getUser().getName());
            psti.execute();
            DBCache.getInstance().updateCustomers();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean update(Country item) {
        try {
            String sql = "UPDATE countries SET Country = ?, Last_Update = ?, Last_Updated_By = ? " +
                         "WHERE Country_ID = ? ;";
            PreparedStatement psti = DBConnection.getConn().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            psti.setString(1, item.getName());
            psti.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            psti.setString(3, getInstance().getUser().getName());
            psti.execute();
            DBCache.getInstance().updateCountries();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean delete(Country item) {
        try {
            String sql = "DELETE FROM countries WHERE Country_ID = ? ;";
            PreparedStatement psti = DBConnection.getConn().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            psti.setInt(1, item.getCountryId());
            psti.execute();
            DBCache.getInstance().updateCountries();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
}
