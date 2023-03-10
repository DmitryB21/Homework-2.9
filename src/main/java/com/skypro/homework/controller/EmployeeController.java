package com.skypro.homework.controller;


import com.skypro.homework.entity.Employee;
import com.skypro.homework.exception.EmployeeAlreadyAddedException;
import com.skypro.homework.exception.EmployeeNotFoundException;
import com.skypro.homework.exception.EmployeeStorageIsFullException;
import com.skypro.homework.exception.ErrorMessage;
import com.skypro.homework.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmployeeAlreadyAddedException.class)
    public ErrorMessage handleException(EmployeeAlreadyAddedException e) {
        return new ErrorMessage(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmployeeStorageIsFullException.class)
    public ErrorMessage handleException(EmployeeStorageIsFullException e) {
        return new ErrorMessage(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ErrorMessage handleException(EmployeeNotFoundException e) {
        return new ErrorMessage(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    @GetMapping(path = "/add")
    public Employee addEmployee(@RequestParam String  firstName, @RequestParam String lastName,
                                @RequestParam int department, @RequestParam float salary) {
        return employeeService.addEmployee(firstName, lastName, department, salary);
    }

    @GetMapping(path = "/find")
    public Employee findEmployee(@RequestParam String  firstName, @RequestParam String lastName) {
        return employeeService.findEmployee(firstName, lastName);
    }

    @GetMapping(path = "/remove")
    public Employee removeEmployee(@RequestParam String  firstName, @RequestParam String lastName) {
        return employeeService.removeEmployee(firstName, lastName);
    }

    @GetMapping(path = "/print")
    public Collection<Employee> printEmployee() {
        return employeeService.printEmployee();
    }

    // отделы -------------------------------------------------------------------

    @GetMapping(path = "/departments/max-salary")
    public Employee findMaxSalaryEmployeeDepartment(@RequestParam int departmentId) {
        return employeeService.findMaxSalaryEmployeeDepartment(departmentId);
    }

    @GetMapping(path = "/departments/min-salary")
    public Employee findMinSalaryEmployeeDepartment(@RequestParam int departmentId) {
        return employeeService.findMinSalaryEmployeeDepartment(departmentId);
    }

    @GetMapping(path = "/departments")
    public List<Employee> printListEmployeesDepartment(@RequestParam int departmentId) {
        return employeeService.printListEmployeesDepartment(departmentId);
    }

    @GetMapping(path = "/departments/all")
    public List<Employee> printListEmployeesPerDepartment() {
        return employeeService.printListEmployeesPerDepartment();
    }






}
