package datalayer.booking;

import dto.Booking;
import dto.BookingCreation;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BookingStorageImpl implements IBookingStorage {
    private String connectionString;
    private String username, password;

    public BookingStorageImpl(String connectionString, String username, String password) {
        this.connectionString = connectionString;
        this.username = username;
        this.password = password;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionString, username, password);
    }

    @Override
    public int createBooking(BookingCreation bookingCreation) throws SQLException {
        var sql = "insert into Bookings(customerId, employeeId, date, start, end) values(?,?,?,?,?)";
        try (var con = getConnection();
             var stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, bookingCreation.getCustomerId());
            stmt.setInt(2, bookingCreation.getEmployeeId());
            stmt.setDate(3, bookingCreation.getDate());
            stmt.setString(4, bookingCreation.getStart());
            stmt.setString(5, bookingCreation.getEnd());

            stmt.executeUpdate();

            // get the newly created id
            try (var resultSet = stmt.getGeneratedKeys()) {
                resultSet.next();
                int newId = resultSet.getInt(1);
                return newId;
            }
        }
    }

    @Override
    public List<Booking> getBookingsForCustomer(int customerId) throws SQLException {
        var sql = "select ID, customerId, employeeId, date, start, end from Bookings where customerId = ?";
        try (var con = getConnection();
             var stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            ArrayList<Booking> bookings = new ArrayList<>();

            try (var resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    var id = resultSet.getInt("ID");
                    var customerID = resultSet.getInt("customerId");
                    var employeeId = resultSet.getInt("employeeId");
                    var date = resultSet.getDate("date");
                    var start = resultSet.getString("start");
                    var end = resultSet.getString("end");
                    bookings.add(new Booking(id, customerID, employeeId, date, start, end));
                }
            }
            return bookings;
        }
    }

    @Override
    public List<Booking> getBookingsForEmployee(int employeeId) throws SQLException {
        var sql = "select ID, customerId, employeeId, date, start, end from Bookings where employeeId = ?";
        try (var con = getConnection();
             var stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            ArrayList<Booking> bookings = new ArrayList<>();

            try (var resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    var id = resultSet.getInt("ID");
                    var customerId = resultSet.getInt("customerId");
                    var employeeID = resultSet.getInt("employeeId");
                    var date = resultSet.getDate("date");
                    var start = resultSet.getString("start");
                    var end = resultSet.getString("end");
                    bookings.add(new Booking(id, customerId, employeeID, date, start, end));
                }
            }
            return bookings;
        }
    }
}
