package servicelayer.employee;

import datalayer.employee.IEmployeeStorage;
import dto.Employee;
import dto.EmployeeCreation;

import java.sql.SQLException;
import java.util.Date;

public class EmployeeServiceImp implements IEmployeeService {

    private IEmployeeStorage iEmployeeStorage;

    public EmployeeServiceImp(IEmployeeStorage employeeStorage) {
        this.iEmployeeStorage = employeeStorage;
    }


    @Override
    public int createEmployee(String firstName, String lastName, Date birthdate) throws EmployeeServiceException {
        try {
            return iEmployeeStorage.createEmployee( new EmployeeCreation(firstName, lastName));
        } catch (SQLException throwables) {
            throw new EmployeeServiceException(throwables.getMessage());
        }
    }

    @Override
    public Employee getEmployeeById(int employeeId) throws EmployeeServiceException {
        try {
            return iEmployeeStorage.getEmployeeWithId(employeeId);
        } catch(SQLException throwables) {
            throw new EmployeeServiceException(throwables.getMessage());
        }
    }
}
