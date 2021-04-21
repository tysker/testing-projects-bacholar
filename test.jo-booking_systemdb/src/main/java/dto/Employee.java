package dto;

import java.util.Date;

public class Employee {
    private final int id;
    private final String firstname;
    private final String lastname;
    private final Date birthday;

    public Employee(int id, String firstname, String lastname, Date birthday) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Date getBirthday() {
        return birthday;
    }
}
