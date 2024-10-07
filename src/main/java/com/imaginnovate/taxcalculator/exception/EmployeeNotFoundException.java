package com.imaginnovate.taxcalculator.exception;

/**
 * The type Employee not found exception.
 */
public class EmployeeNotFoundException extends RuntimeException {
    /**
     * Instantiates a new Employee not found exception.
     *
     * @param message the message
     */
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
