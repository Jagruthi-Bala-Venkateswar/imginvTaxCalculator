package com.imaginnovate.taxcalculator.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

/**
 * The type Employee.
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name = "employee")
public class Employee {
    @NotBlank(message = "Employee ID cannot be empty")
    @Id
    @Column(name = "employee_id")
    private String employeeId;

    @NotBlank(message = "First name cannot be empty")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    @Column(name = "email")
    private String email;

    @ElementCollection
    @CollectionTable(name = "phone_numbers")
    @Column(name = "phone_number")
    private List<@Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number should be valid")String> phoneNumbers;

    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date of joining should be in YYYY-MM-DD format")
    @Column(name = "doj")
    private String doj;

    @NotNull(message = "Salary cannot be null")
    @Positive(message = "Salary should be a positive number")
    @Column(name = "salary")
    private double salary;
}
