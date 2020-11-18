package model;

import DAO.DBCache;

import java.time.LocalDateTime;
import java.util.Objects;

public class Appointment {

    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int customerId;
    private Customer customer;
    private int userId;
    private User user;
    private int contactId;
    private Contact contact;


    public Appointment(int appointmentId, String title, String description, String location, String type,
                       LocalDateTime startTime, LocalDateTime endTime, int customerId, int userId, int contactId) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.customerId = customerId;
        this.customer = DBCache.getInstance().getCustomerHashMap().get(customerId);
        this.userId = userId;
        this.user = DBCache.getInstance().getUserHashMap().get(userId);
        this.contactId = contactId;
        this.contact = DBCache.getInstance().getContactHashMap().get(contactId);
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

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Customer getCustomer(){ return customer;}

    public int getUserId() {
        return userId;
    }

    public User getUser(){ return user;}

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getContactId() {
        return contactId;
    }

    public Contact getContact(){ return contact;}

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return appointmentId == that.appointmentId &&
                customerId == that.customerId &&
                userId == that.userId &&
                contactId == that.contactId &&
                title.equals(that.title) &&
                Objects.equals(description, that.description) &&
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
                ", customerId=" + customerId +
                ", userId=" + userId +
                ", contactId=" + contactId +
                '}';
    }
}
