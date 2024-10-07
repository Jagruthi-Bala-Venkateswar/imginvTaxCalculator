package com.imaginnovate.taxcalculator.repository;

import com.imaginnovate.taxcalculator.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Employee repository.
 */
public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
