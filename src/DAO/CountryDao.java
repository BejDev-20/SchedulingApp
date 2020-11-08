package DAO;

import javafx.collections.ObservableList;
import model.Appointment;
import model.Country;

import java.util.List;

public class CountryDao implements DAO<Country> {

    @Override
    public ObservableList<Country> getAll() {
        return null;
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
