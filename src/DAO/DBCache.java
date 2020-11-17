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
 * Think of implementing the timer to update the cache periodically
 */
public class DBCache {

    private User currentUser;
    private HashMap<Integer, Customer> customerHashMap = new HashMap<>();
    private HashMap<Integer, Appointment> appointmentHashMap = new HashMap<>();
    private HashMap<Integer, User> userHashMap = new HashMap<>();
    private HashMap<Integer, FirstLevelDiv> firstLevelDivHashMap = new HashMap<>();
    private HashMap<Integer, Country> countryHashMap = new HashMap<>();

    private static DBCache single_instance = null;

    private DBCache(){
        updateAllCache();
        currentUser = null;
    }

    public static DBCache getInstance(){
        if (single_instance == null){
            single_instance = new DBCache();
        }
        return single_instance;
    }

    public void setUser(User user){
        this.currentUser = user;
    }

    public User getUser(){
        return currentUser;
    }

    public void updateAllCache(){
        updateCustomers();
        updateUsers();
        updateCountries();
        loadFirstLevelDiv();
        updateAppointments();
    }

    public void updateAppointments() {
        AppointmentDao appointmentDao = new AppointmentDao();
        ObservableList<Appointment> appointments = appointmentDao.getAll();
        for (Appointment appointment : appointments) {
            appointmentHashMap.put(appointment.getAppointmentId(), appointment);
        }
    }

    public void loadFirstLevelDiv() {
        FirstLevelDivDao firstLevelDivDao = new FirstLevelDivDao();
        ObservableList<FirstLevelDiv> firstLevelDivs = firstLevelDivDao.getAll();
        for (FirstLevelDiv firstLevelDiv : firstLevelDivs) {
            firstLevelDivHashMap.put(firstLevelDiv.getDivisionId(), firstLevelDiv);
        }
    }

    public void updateCountries() {
        CountryDao countryDao = new CountryDao();
        ObservableList<Country> countries = countryDao.getAll();
        for (Country country : countries) {
            countryHashMap.put(country.getCountryId(), country);
        }
    }

    public void updateUsers() {
        UsersDao usersDao = new UsersDao();
        ObservableList<User> users = usersDao.getAll();
        for (User user : users) {
            userHashMap.put(user.getUserId(), user);
        }
    }

    public void updateCustomers(){
        CustomerDao customerDao = new CustomerDao();
        ObservableList<Customer> customers = customerDao.getAll();
        for (Customer customer : customers) {
            customerHashMap.put(customer.getCustomerId(), customer);
        }
    }

    public Appointment getAppById(int id){
        Appointment app = appointmentHashMap.get(id);
        if (app == null){
            AppointmentDao appointmentDao = new AppointmentDao();
            app = appointmentDao.getById(id);
        }
        return app;
    }

    public Customer getCustomerById(int id){
        Customer customer = customerHashMap.get(id);
        if (customer == null){
            CustomerDao customerDao = new CustomerDao();
            customer = customerDao.getById(id);
        }
        return customer;
    }

    public User getUserById(int id){
        User user = userHashMap.get(id);
        if (user == null){
            UsersDao usersDao = new UsersDao();
            user = usersDao.getById(id);
        }
        return user;
    }

    public Country getCountryById(int id){
        Country country = countryHashMap.get(id);
        if (country == null){
            CountryDao countryDao = new CountryDao();
            country = countryDao.getById(id);
        }
        return country;
    }

    public FirstLevelDiv getFirstLevelDivById(int id){
        FirstLevelDiv firstLevelDiv = firstLevelDivHashMap.get(id);
        if (firstLevelDiv == null){
            FirstLevelDivDao firstLevelDivDao = new FirstLevelDivDao();
            firstLevelDiv = firstLevelDivDao.getById(id);
        }
        return firstLevelDiv;
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
