package main;

import datalayer.booking.BookingStorageImpl;
import datalayer.employee.EmployeeStorageImp;
import dto.BookingCreation;
import dto.Customer;
import datalayer.customer.CustomerStorageImpl;
import dto.Employee;
import dto.EmployeeCreation;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

public class Main {

    private static final String conStr = "jdbc:mysql://localhost:3307/BookingSystemDB";
    private static final String user = "root";
    private static final String pass = "testuser123";

    public static void main(String[] args) throws SQLException {
        CustomerStorageImpl customerStorage = new CustomerStorageImpl(conStr, user, pass);
        EmployeeStorageImp employeeStorage = new EmployeeStorageImp(conStr, user, pass);
        BookingStorageImpl bookingStorage = new BookingStorageImpl(conStr, user, pass);

/*
        System.out.println("Got customers: ");
        for (Customer c : customerStorage.getCustomers()) {
            System.out.println(toString(c));
        }
        System.out.println("The end.");
    }

    public static String toString(Customer c) {
        return "{" + c.getId() + ", " + c.getFirstname() + ", " + c.getLastname() + "}";
*/


        //**********************************************************************
        //Employee

        // create an Employee
        /*
        EmployeeCreation employee = new EmployeeCreation("Joerg","Oertel");
        int num = employeeStorage.createEmployee(employee);
        System.out.println(num);
        */

        // get an Employee by ID
        /*
        System.out.println(employeeStorage.getEmployeeWithId(1));
         */

        //**********************************************************************
        // Booking

        //create a Booking
        /*
        BookingCreation booking = new BookingCreation(1, 1, new Date(123456L),"12:00:00", "14:00:00");
        System.out.println(bookingStorage.createBooking(booking));
         */

        // get all bookings for a customer
        /*
        System.out.println(bookingStorage.getBookingsForCustomer(1));
         */

        // get all bookings for a employee
        /*
        System.out.println(bookingStorage.getBookingsForEmployee(1));
         */

    }
}
