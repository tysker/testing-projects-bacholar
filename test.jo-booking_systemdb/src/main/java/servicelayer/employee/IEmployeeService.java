package servicelayer.employee;

import dto.Employee;

import java.util.Date;

public interface IEmployeeService {
    int createEmployee(String firstName, String lastName, Date birthdate) throws EmployeeServiceException;
    Employee getEmployeeById(int employeeId) throws EmployeeServiceException;
}
