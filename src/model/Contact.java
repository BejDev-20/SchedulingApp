package model;

import java.util.Objects;

public class Contact {

    private Integer contactId;
    private String contactName;
    private String contactEmail;

    public Contact(Integer contactID, String contactName, String contactEmail){
        this.contactId = contactID;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return contactId.equals(contact.contactId) &&
                contactName.equals(contact.contactName) &&
                contactEmail.equals(contact.contactEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactId, contactName, contactEmail);
    }
}
