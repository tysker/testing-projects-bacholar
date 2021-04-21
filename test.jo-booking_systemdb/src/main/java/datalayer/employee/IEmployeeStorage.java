package datalayer.employee;

import dto.Employee;
import dto.EmployeeCreation;

import java.sql.SQLException;

public interface IEmployeeStorage {
    int createEmployee(EmployeeCreation employeeCreation) throws SQLException;
    Employee getEmployeeWithId(int employeeId) throws SQLException;
}
