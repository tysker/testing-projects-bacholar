# Booking System

**Requirements**
It must be possible to create customers, employees and bookings. A customermay have a phone number (this change requires a database migration script).
When booking an appointment with a customer, an SMS must be sent1.

**Data layer**

1. Create BookingStorage and BookingStorageImpl with methods

    • int createBooking(Booking booking)
  
    • Collection<Booking> getBookingsForCustomer(int customerId)
  
2. Create EmployeeStorage and EmployeeStorageImpl with methods

    • int createEmployee(Employee employee)
  
    • Collection<Employee> getEmployeeWithId(int employeeId)

**Service layer**

1. Create BookingService and BookingServiceImpl with methods

    • int createBooking(customerId, employeeId, date, start, end)
  
    • Collection<Booking> getBookingsForCustomer(customerId)
  
    • Collection<Booking> getBookingsForEmployee(employeeId)
  
2. Create EmployeeService and EmployeeServiceImpl with methods

      • int createEmployee(employee)
    
      • Employee getEmployeeById(employeeId)
