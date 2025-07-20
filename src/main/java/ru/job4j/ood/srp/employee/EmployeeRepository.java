package ru.job4j.ood.srp.employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements Repository<Employee> {

    List<Employee> employeeList = new ArrayList<>();

    @Override
    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    @Override
    public void deleteEmployee(Employee employee) {
        employeeList.remove(employee);
    }

    @Override
    public List<Employee> findByName(String name) {
        List<Employee> result = new ArrayList<>();
        for (Employee employee : employeeList) {
            if (name != null && name.equals(employee.getName())) {
                result.add(employee);
            }
        }
        return result;
    }

    @Override
    public List<Employee> findAll() {
        return employeeList;
    }

}
