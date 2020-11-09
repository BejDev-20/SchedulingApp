package DAO;

import javafx.collections.ObservableList;
import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * IMPLEMENT SINGLETON
 */
public class DBCache {

    private HashMap<Integer, Customer> customerHashMap = new HashMap<>();
    private HashMap<Integer, Appointment> appointmentHashMap = new HashMap<>();
    private HashMap<Integer, User> userHashMap = new HashMap<>();
    private HashMap<Integer, FirstLevelDiv> firstLevelDivHashMap = new HashMap<>();
    private HashMap<Integer, Country> countryHashMap = new HashMap<>();

    public DBCache(){
        loadCustomers();
        loadUsers();
        loadCountries();
        loadFirstLevelDiv();
        loadAppointments();
    }

    private void loadAppointments() {
        AppointmentDao appointmentDao = new AppointmentDao();
        ObservableList<Appointment> appointments = appointmentDao.getAll();
        for (Appointment appointment : appointments) {
            appointmentHashMap.put(appointment.getAppointmentId(), appointment);
        }
    }

    private void loadFirstLevelDiv() {
        FirstLevelDivDao firstLevelDivDao = new FirstLevelDivDao();
        ObservableList<FirstLevelDiv> firstLevelDivs = firstLevelDivDao.getAll();
        for (FirstLevelDiv firstLevelDiv : firstLevelDivs) {
            firstLevelDivHashMap.put(firstLevelDiv.getDivisionId(), firstLevelDiv);
        }
    }

    private void loadCountries() {
        CountryDao countryDao = new CountryDao();
        ObservableList<Country> countries = countryDao.getAll();
        for (Country country : countries) {
            countryHashMap.put(country.getCountryId(), country);
        }
    }

    private void loadUsers() {
        UsersDao usersDao = new UsersDao();
        ObservableList<User> users = usersDao.getAll();
        for (User user : users) {
            userHashMap.put(user.getUserId(), user);
        }
    }

    private void loadCustomers(){
        CustomerDao customerDao = new CustomerDao();
        ObservableList<Customer> customers = customerDao.getAll();
        for (Customer customer : customers) {
            customerHashMap.put(customer.getCustomerId(), customer);
        }
    }

    public HashMap<Integer, Customer> getCustomerHashMap() {
        return customerHashMap;
    }

    public HashMap<Integer, Appointment> getAppointmentHashMap() {
        return appointmentHashMap;
    }

    public HashMap<Integer, User> getUserHashMap() {
        return userHashMap;
    }

    public HashMap<Integer, Country> getCountryHashMap(){
        return countryHashMap;
    }
    public HashMap<Integer, FirstLevelDiv> getFirstLevelDivHashMap() {
        return firstLevelDivHashMap;
    }
}
