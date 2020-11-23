package DAO;

import javafx.collections.ObservableList;
import model.*;
import java.util.HashMap;

/**
 * Think of implementing the timer to update the cache periodically
 */
public class DBCache {

    private User currentUser;
    private static HashMap<Integer, Customer> customerHashMap = new HashMap<>();
    private static HashMap<Integer, Appointment> appointmentHashMap = new HashMap<>();
    private static HashMap<Integer, User> userHashMap = new HashMap<>();
    private static HashMap<Integer, FirstLevelDiv> firstLevelDivHashMap = new HashMap<>();
    private static HashMap<Integer, Country> countryHashMap = new HashMap<>();
    private static HashMap<Integer, Contact> contactHashMap = new HashMap<>();

    private static DBCache single_instance = null;

    private DBCache(){
        currentUser = null;
    }

    public static DBCache getInstance(){
        if (single_instance == null){
            single_instance = new DBCache();
            updateAllCache();
        }
        return single_instance;
    }

    public void setUser(User user){
        this.currentUser = user;
    }

    public User getUser(){
        return currentUser;
    }

    public static void updateAllCache(){
        updateContacts();
        updateCustomers();
        updateUsers();
        updateCountries();
        updateFirstLevelDiv();
        updateAppointments();
    }

    public static void updateAppointments() {
        AppointmentDao appointmentDao = new AppointmentDao();
        ObservableList<Appointment> appointments = appointmentDao.getAll();
        appointmentHashMap.clear();
        for (Appointment appointment : appointments) {
            appointmentHashMap.put(appointment.getAppointmentId(), appointment);
        }
    }

    public static void updateFirstLevelDiv() {
        FirstLevelDivDao firstLevelDivDao = new FirstLevelDivDao();
        ObservableList<FirstLevelDiv> firstLevelDivs = firstLevelDivDao.getAll();
        firstLevelDivHashMap.clear();
        for (FirstLevelDiv firstLevelDiv : firstLevelDivs) {
            firstLevelDivHashMap.put(firstLevelDiv.getDivisionId(), firstLevelDiv);
        }
    }

    public static void updateCountries() {
        CountryDao countryDao = new CountryDao();
        ObservableList<Country> countries = countryDao.getAll();
        countryHashMap.clear();
        for (Country country : countries) {
            countryHashMap.put(country.getCountryId(), country);
        }
    }

    public static void updateUsers() {
        UsersDao usersDao = new UsersDao();
        ObservableList<User> users = usersDao.getAll();
        userHashMap.clear();
        for (User user : users) {
            userHashMap.put(user.getUserId(), user);
        }
    }

    public static void updateCustomers(){
        CustomerDao customerDao = new CustomerDao();
        ObservableList<Customer> customers = customerDao.getAll();
        customerHashMap.clear();
        for (Customer customer : customers) {
            customerHashMap.put(customer.getCustomerId(), customer);
        }
    }

    public static void updateContacts(){
        ContactDao contactDao = new ContactDao();
        ObservableList<Contact> contacts = contactDao.getAll();
        contactHashMap.clear();
        for (Contact contact : contacts) {
            contactHashMap.put(contact.getContactId(), contact);
        }
    }

    public static Appointment getAppById(int id){
        Appointment app = appointmentHashMap.get(id);
        if (app == null){
            AppointmentDao appointmentDao = new AppointmentDao();
            app = appointmentDao.getById(id);
        }
        return app;
    }

    public static Customer getCustomerById(int id){
        Customer customer = customerHashMap.get(id);
        if (customer == null){
            CustomerDao customerDao = new CustomerDao();
            customer = customerDao.getById(id);
        }
        return customer;
    }

    public static User getUserById(int id){
        User user = userHashMap.get(id);
        if (user == null){
            UsersDao usersDao = new UsersDao();
            user = usersDao.getById(id);
        }
        return user;
    }

    public static Country getCountryById(int id){
        Country country = countryHashMap.get(id);
        if (country == null){
            CountryDao countryDao = new CountryDao();
            country = countryDao.getById(id);
        }
        return country;
    }

    public static FirstLevelDiv getFirstLevelDivById(int id){
        FirstLevelDiv firstLevelDiv = firstLevelDivHashMap.get(id);
        if (firstLevelDiv == null){
            FirstLevelDivDao firstLevelDivDao = new FirstLevelDivDao();
            firstLevelDiv = firstLevelDivDao.getById(id);
        }
        return firstLevelDiv;
    }

    public static Contact getContactById(int id){
        Contact contact = contactHashMap.get(id);
        if (contact == null){
            ContactDao contactDao = new ContactDao();
            contact = contactDao.getById(id);
        }
        return contact;
    }

    public static HashMap<Integer, Customer> getCustomerHashMap() {
        return customerHashMap;
    }

    public static HashMap<Integer, Appointment> getAppointmentHashMap() {
        return appointmentHashMap;
    }

    public static HashMap<Integer, User> getUserHashMap() {
        return userHashMap;
    }

    public static HashMap<Integer, Country> getCountryHashMap(){
        return countryHashMap;
    }

    public static HashMap<Integer, FirstLevelDiv> getFirstLevelDivHashMap() {
        return firstLevelDivHashMap;
    }

    public static HashMap<Integer, Contact> getContactHashMap() {
        return contactHashMap;
    }
}
