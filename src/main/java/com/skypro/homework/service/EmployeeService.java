package com.skypro.homework.service;


import com.skypro.homework.entity.Employee;
import com.skypro.homework.exception.EmployeeAlreadyAddedException;
import com.skypro.homework.exception.EmployeeNotFoundException;
import com.skypro.homework.exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final Map<String, Employee> employees = new HashMap<>();

    public final int maxSizeEmployeeBook = 6;

    public Employee addEmployee(String firstName, String lastName, int department, float salary) {
        Employee employee = new Employee(firstName, lastName, department, salary);
        if (employees.size() == maxSizeEmployeeBook) {
            throw new EmployeeStorageIsFullException("массив сотрудников переполнен");
        }
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("такой сотрудник уже есть");
        }
        employees.put(employee.getFullName(), employee);
        return employee;
    }

    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee.getFullName())) {
            return employees.get(employee.getFullName());
        }
        throw new EmployeeNotFoundException("сотрудник не найден");
    }

    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = findEmployee(firstName, lastName);
        employees.remove(employee.getFullName());
        return employee;
    }

    public Collection<Employee> printEmployee() {
        return Collections.unmodifiableCollection(employees.values());
    }

    // отделы --------------------------------------------------------------

    public List<Employee> findMaxSalaryEmployeeDepartment(int numberDepartmentId) {
        List<Employee> employeesList = new ArrayList<>(employees.values());
        Employee max = employeesList.stream()
                .filter(e -> e.getDepartment() == numberDepartmentId)
                .max((e1, e2) -> (int) (e1.getSalary() - e2.getSalary()))
                .orElseThrow(() -> new EmployeeNotFoundException("в отделе нет сотрудников"));

        return  employeesList.stream()
                .filter(e -> e.getSalary() == max.getSalary())
                .collect(Collectors.toList());

         /* Employee employee = null;
        float max = Integer.MIN_VALUE;
        for (Employee e : employeesList) {
            if (e != null && e.getDepartment() == numberDepartmentId) {
                if (e.getSalary() > max) {
                    max = e.getSalary();
                    employee = e;
                }
            } else {
                throw new EmployeeNotFoundException("в отделе нет сотрудников");
            }
        }
        return employee;*/
    }

    public List<Employee> findMinSalaryEmployeeDepartment(int numberDepartmentId) {
        List<Employee> employeesList = new ArrayList<>(employees.values());

         Employee min = employeesList.stream()
                .filter(e -> e.getDepartment() == numberDepartmentId)
                .min((e1, e2) -> (int) (e1.getSalary() - e2.getSalary()))
                .orElseThrow(() -> new EmployeeNotFoundException("в отделе нет сотрудников"));

         return  employeesList.stream()
                 .filter(e -> e.getSalary() == min.getSalary())
                 .collect(Collectors.toList());



       /* Employee employee = null;
        float min = Integer.MAX_VALUE;
        for (Employee e : employeesList) {
            if (e != null && e.getDepartment() == numberDepartmentId) {
                if (e.getSalary() < min) {
                    min = e.getSalary();
                    employee = e;
                }
            } else {
                throw new EmployeeNotFoundException("в отделе нет сотрудников");
            }
        }
        return employee;*/
    }

    public Map<Integer, List<Employee>> printListEmployeesDepartment(Integer numberDepartmentId) {
        List<Employee> employeesList = new ArrayList<>(employees.values());
        return employeesList.stream()
                .filter(e -> numberDepartmentId == null || e.getDepartment() == numberDepartmentId)
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.mapping(e -> e, Collectors.toList())));


//                .orElseThrow(() -> new EmployeeNotFoundException("в отделе нет сотрудников"));

       /* for (Employee employee : employeesList) {
            if (employee != null && employee.getDepartment() == numberDepartment) {
                System.out.println("id = " + employee.getId() + ", ФИО сотрудника: " + employee.getFullName() +
                        ", зарплата: " + employee.getSalary());
            }
        }*/
    }

   /* public List<Employee>  printListEmployeesPerDepartment() {
        List<Employee> employeesList = new ArrayList<>(employees.values());

        return employeesList.stream()
                .sorted(Comparator.comparingInt(Employee::getDepartment))
                .collect(Collectors.toList());*/

       /* for(Integer dep : departments){
            System.out.println("Сотрудники департамента" + dep);
            for (Employee employee : employeesList) {
                if (employee.getDepartment() == dep) {
                    System.out.println(employee);
                }
            }
        }*/







}
