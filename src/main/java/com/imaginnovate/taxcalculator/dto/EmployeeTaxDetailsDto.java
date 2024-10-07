package com.imaginnovate.taxcalculator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Employee tax details dto.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeTaxDetailsDto {
    private String employeeId;
    private String firstName;
    private String lastName;
    private double yearlySalary;
    private double taxAmount;
    private double cessAmount;
}
