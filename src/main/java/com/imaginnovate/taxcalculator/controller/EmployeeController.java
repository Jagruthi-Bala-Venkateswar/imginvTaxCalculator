package com.imaginnovate.taxcalculator.controller;

import com.imaginnovate.taxcalculator.dto.EmployeeTaxDetailsDto;
import com.imaginnovate.taxcalculator.model.Employee;
import com.imaginnovate.taxcalculator.service.EmployeeService;
import com.imaginnovate.taxcalculator.util.Validation;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Employee controller.
 */
@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    @Autowired
    private  EmployeeService employeeService;
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    private  Validation validate;


    /**
     * Save employee response entity.
     *
     * @param employee the employee
     * @return the response entity
     */
    @PostMapping(value = "/create")
    public ResponseEntity<String> saveEmployee(@Valid @RequestBody Employee employee) {
        logger.info("Entered create API for employee");
        try {
            validate.validateEmployee(employee);
            employeeService.saveEmployee(employee);
            logger.info("Employee created successfully");
            return ResponseEntity.ok("Employee created successfully");
        }
        catch (ValidationException validateException) {
            logger.error("Exception in saveEmployee method: " + validateException.getMessage());
            return ResponseEntity.badRequest().body(validateException.getMessage());
        }
        catch (IllegalArgumentException e) {
            logger.error("Exception in saveEmployee method for IllegalArgumentException "+e.toString());
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e) {
            logger.error("Exception in saveEmployee method for "+e.toString());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Gets all employees deduction list.
     *
     * @return the all employees deduction list
     */
    @GetMapping("/getAllEmployeesDeductionList")
    public ResponseEntity<?> getAllEmployeesDeductionList() {
        try {
            List<EmployeeTaxDetailsDto> responses = employeeService.getTaxDeductionsForYear();
            return ResponseEntity.ok(responses);
        }
        catch (Exception e) {
            logger.error("Exception in getAllEmployeesDeductionList method for "+e.toString());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Gets tax deductions of employee.
     *
     * @param employeeId the employee id
     * @return the tax deductions of employee
     */
    @GetMapping("{employeeId}/tax-deductions")
    public ResponseEntity<?> getTaxDeductionsOfEmployee(@PathVariable String employeeId) {
        try {
            EmployeeTaxDetailsDto responses = employeeService.getTaxDeducationForEmployee(employeeId);
            return ResponseEntity.ok(responses);
        }
        catch (Exception e) {
            logger.error("Exception in getTaxDeductionsOfEmployee method for "+e.toString());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

