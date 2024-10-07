package com.imaginnovate.taxcalculator.service;

import com.imaginnovate.taxcalculator.dto.EmployeeTaxDetailsDto;
import com.imaginnovate.taxcalculator.exception.EmployeeExistsException;
import com.imaginnovate.taxcalculator.exception.EmployeeNotFoundException;
import com.imaginnovate.taxcalculator.model.Employee;
import com.imaginnovate.taxcalculator.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.slf4j.Logger;
import java.util.stream.Collectors;


/**
 * The type Employee service.
 */
@Service
@Slf4j
public class EmployeeService {

    /**
     * The Repo.
     */
    @Autowired
    EmployeeRepository repo;
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);


    /**
     * Save employee employee.
     *
     * @param employee the employee
     * @return the employee
     */
    public Employee saveEmployee(Employee employee) {
        logger.info("EmployeeService saveEmployee method called");
        if (repo.existsById(employee.getEmployeeId())) {
            throw new EmployeeExistsException("Employee ID already exists");
        }
        return repo.save(employee);
    }


    /**
     * Gets all employees.
     *
     * @return the all employees
     */
    public List<Employee> getAllEmployees() {
        logger.info("EmployeeService saveEmployee method called");
        return repo.findAll();
    }


    /**
     * Gets employee by id.
     *
     * @param employeeId the employee id
     * @return the employee by id
     */
    public Employee getEmployeeById(String employeeId) {
        logger.info("EmployeeService getEmployeeById method for get  employee by Id");
        return repo.findById(employeeId).orElseThrow(()->new EmployeeNotFoundException("Employee Not Found for id :"+employeeId));
    }

    /**
     * Calculate tax double.
     *
     * @param yearlySalary the yearly salary
     * @return the double
     */
    public double calculateTax(double yearlySalary) {
        logger.info("Calculate total Tax slab deducation");
        double tax = 0;
        if (yearlySalary <= 250000) {
            tax = 0;
        } else if (yearlySalary <= 500000) {
            tax = (yearlySalary - 250000) * 0.05;
        } else if (yearlySalary <= 1000000) {
            tax = (250000 * 0.05) + (yearlySalary - 500000) * 0.10;
        } else {
            tax = (250000 * 0.05) + (500000 * 0.10) + (yearlySalary - 1000000) * 0.20;
        }
        logger.info("Calculate total Tax slab deducation complete");
        return tax;
    }

    /**
     * Calculate cess double.
     *
     * @param yearlySalary the yearly salary
     * @return the double
     */
    public double calculateCess(double yearlySalary) {
        logger.info("Calculate total calculate Cess");
        if (yearlySalary > 2500000) {
            return (yearlySalary - 2500000) * 0.02;
        }
        return 0;
    }


    /**
     * Get tax deductions for year list.
     *
     * @return the list
     */
    public List<EmployeeTaxDetailsDto> getTaxDeductionsForYear(){
        List<Employee> employees = repo.findAll();
        logger.info("get Deducation for current finacial year");
        int currentYear = LocalDate.now().getYear();
        int startMonth = 4; // April
        int endMonth = 3; // March

        LocalDate startOfYear = LocalDate.of(currentYear - 1, startMonth, 1);
        LocalDate endOfYear = LocalDate.of(currentYear, endMonth, 31);

        return employees.stream().map(employee -> {
            double yearlySalary = calculateYearlySalary(employee, startOfYear, endOfYear);
            double taxAmount = calculateTax(yearlySalary);
            double cessAmount = calculateCess(yearlySalary);
            return new EmployeeTaxDetailsDto(employee.getEmployeeId(), employee.getFirstName(),
                    employee.getLastName(), yearlySalary, taxAmount, cessAmount);
        }).collect(Collectors.toList());
    }

    private double calculateYearlySalary(Employee employee, LocalDate startOfYear, LocalDate endOfYear) {
        LocalDate doj;
        doj = LocalDate.parse(employee.getDoj());
        double monthlySalary = employee.getSalary();
        double LossOfPayPerDay=Math.round(monthlySalary/30);
        int totalDaysWorkInFinacialyear=0;
        totalDaysWorkInFinacialyear= (int) ChronoUnit.DAYS.between(doj, endOfYear);
        double perDaySalary=LossOfPayPerDay*Math.max(totalDaysWorkInFinacialyear, 0);
        return perDaySalary;
    }

    /**
     * Gets tax deducation for employee.
     *
     * @param employeeId the employee id
     * @return the tax deducation for employee
     */
    public EmployeeTaxDetailsDto getTaxDeducationForEmployee(String employeeId) {
        Employee employee=repo.findById(employeeId).orElseThrow(()->new EmployeeNotFoundException("Employee Not Found"));
        logger.info("get Tax Deducation For Employee ");
        int currentYear = LocalDate.now().getYear();
        int startMonth = 4; // April
        int endMonth = 3; // March

        LocalDate startOfYear = LocalDate.of(currentYear - 1, startMonth, 1);
        LocalDate endOfYear = LocalDate.of(currentYear, endMonth, 31);
        double yearlySalary = calculateYearlySalary(employee, startOfYear, endOfYear);
        double taxAmount = calculateTax(yearlySalary);
        double cessAmount = calculateCess(yearlySalary);

        return new EmployeeTaxDetailsDto(employee.getEmployeeId(), employee.getFirstName(),
                employee.getLastName(), yearlySalary, taxAmount, cessAmount);
    }

}
