package com.imaginnovate.taxcalculator.util;


import com.imaginnovate.taxcalculator.model.Employee;
import org.springframework.stereotype.Component;

/**
 * The type Validation.
 */
@Component
public class Validation {

    /**
     * Validate employee.
     *
     * @param employee the employee
     */
    public void validateEmployee(Employee employee) {
        if (employee.getEmployeeId() == null || employee.getFirstName() == null ||
            employee.getLastName() == null || employee.getEmail() == null ||
            employee.getPhoneNumbers() == null || employee.getDoj() == null ||
            employee.getSalary() <= 0) {
            throw new IllegalArgumentException("Invalid employee data");
        }
    }
}
