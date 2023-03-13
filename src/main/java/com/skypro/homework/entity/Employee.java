package com.skypro.homework.entity;

import java.util.Objects;

public class Employee {

    private static int counter = 1;
    private int id;
    private final String firstName;
     private final String lastName;
    private int department;
    private float salary;


    public Employee(String firstName, String lastName, int department, float salary) {
        this.id = counter++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.salary = salary;
    }

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getFullName() {
        return firstName + lastName;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", department=" + department +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && department == employee.department && Float.compare(employee.salary, salary) == 0 && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, department, salary);
    }
}
