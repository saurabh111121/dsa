package com.interview.EPAM;

import java.util.*;
import java.util.stream.Collectors;

/*
create a program that processses a list of empoyee objects using java8 features.
1. define an employee class with attribute id(int) , name (String), and salary (double)
2. create a list of employyes.
3. implement the following functionalities of java 8 features :
   * print the names of employees whose salary is above a certain threshold(eg - 5000)
   * create a new list containning employees with a salary increase of 10%
   * find the employeed with the highest salary using Stream API
 */

class Employee {
    private int id;
    private String name;
    private double salary;

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', salary=" + salary + "}";
    }
}

class EmployeeProcessor {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "John", 4500),
                new Employee(2, "Alice", 6000),
                new Employee(3, "Bob", 7000),
                new Employee(4, "David", 5500)
        );

        double salaryThreshold = 5000;

        // 1. Print names of employees whose salary is above the threshold
        System.out.println("Employees earning above " + salaryThreshold + ":");
        employees.stream()
                .filter(emp -> emp.getSalary() > salaryThreshold)
                .map(Employee::getName)
                .forEach(System.out::println);

        // 2. Create a new list with a salary increase of 10%
        List<Employee> increasedSalaryEmployees = employees.stream()
                .map(emp -> new Employee(emp.getId(), emp.getName(), emp.getSalary() * 1.10))
                .collect(Collectors.toList());

        System.out.println("\nEmployees with a 10% salary increase:");
        increasedSalaryEmployees.forEach(System.out::println);

        // 3. Find the employee with the highest salary
        Optional<Employee> highestPaidEmployee = employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary));

        highestPaidEmployee.ifPresent(emp ->
                System.out.println("\nEmployee with the highest salary: " + emp));
    }
}
