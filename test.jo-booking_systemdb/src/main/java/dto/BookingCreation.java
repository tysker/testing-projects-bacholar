package dto;

import java.util.Calendar;
import java.sql.Date;

public class BookingCreation {

    public final int customerId, employeeId;
    public final String start, end;
    public final Date date;

    public BookingCreation(int customerId, int employeeId, Date date, String start, String end) {
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.start = start;
        this.end = end;
        this.date = date;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public Date getDate() { return date; }
}
