package com.imaginnovate.taxcalculator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Global exception handler.
 */
@ControllerAdvice
public class GlobalExceptionHandler {


    /**
     * Employee exception response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(value = EmployeeNotFoundException.class)
    public final ResponseEntity<Object> employeeException(EmployeeNotFoundException ex) {

        ErrorResponse errorResponse = new ErrorResponse( "Not Found",HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<Object>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Employee exists exception response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(value = EmployeeExistsException.class)
    public final ResponseEntity<Object> employeeExistsException(EmployeeNotFoundException ex) {

        ErrorResponse errorResponse = new ErrorResponse( "Employee ID already exists",HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<Object>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle validation exceptions response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {

        ErrorResponse errorResponse = new ErrorResponse( "Validation error"+ex.getBindingResult().getAllErrors(),HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
