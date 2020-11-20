package model;

/**
 *
 * @author Iulia Bejsovec
 */
public class Customer {

    private int customerId;
    private String name;
    private String address;
    private String postalCode;
    private String phoneNumber;
    private Integer divisionId;

    /**
     * @param customerId
     * @param name
     * @param address
     * @param postalCode
     * @param phoneNumber
     */
    public Customer(Integer customerId, String name, String address, String postalCode, String phoneNumber,
                    Integer divisionId) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.divisionId = divisionId;
    }

    /**
     * @return
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     *
     * @param customerId
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     *
     * @param postalCode
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     *
     * @return
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     *
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getDivisionId(){
        return divisionId;
    }

    public void setDivisionId(Integer divisionId){
        this.divisionId = divisionId;
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return phoneNumber == customer.phoneNumber &&
                name.equals(customer.name) &&
                address.equals(customer.address) &&
                postalCode.equals(customer.postalCode);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return customerId +
                " " + name;
    }
}