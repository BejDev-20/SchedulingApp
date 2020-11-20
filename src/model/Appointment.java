package model;

import DAO.DBCache;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Appointment {

    private static final LocalTime START_HOUR = LocalTime.of(8, 0);
    private static final LocalTime END_HOUR = LocalTime.of(22, 0);
    private Integer appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Customer customer;
    private User user;
    private Contact contact;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Appointment(int appointmentId, String title, String description, String location, String type,
                       LocalDateTime startTime, LocalDateTime endTime, int customerId, int userId, int contactId) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.customer = DBCache.getCustomerHashMap().get(customerId);
        this.user = DBCache.getUserHashMap().get(userId);
        this.contact = DBCache.getContactHashMap().get(contactId);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public static LocalTime getSTART_HOUR() {
        return START_HOUR;
    }

    public static LocalTime getEND_HOUR() {
        return END_HOUR;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return customer.equals(that.customer) &&
                user.equals(that.user) &&
                contact.equals(that.contact) &&
                title.equals(that.title) &&
                description.equals(that.description) &&
                location.equals(that.location) &&
                type.equals(that.type) &&
                startTime.equals(that.startTime) &&
                endTime.equals(that.endTime);
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId=" + appointmentId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", type='" + type + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", customer=" + customer +
                ", user=" + user +
                ", contact=" + contact +
                '}';
    }
}
