package Controller;

import java.time.Month;

public class TableData {

    private Integer numberOfAppointments;
    private Month month;
    private String type;

    public TableData(int numberOfAppointments, Month month, String type) {
        this.numberOfAppointments = numberOfAppointments;
        this.month = month;
        this.type = type;
    }

    public int getNumberOfAppointments() {
        return numberOfAppointments;
    }

    public void setNumberOfAppointments(int numberOfAppointments) {
        this.numberOfAppointments = numberOfAppointments;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
