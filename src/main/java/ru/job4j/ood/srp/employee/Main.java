package ru.job4j.ood.srp.employee;

public class Main {
    public static void main(String[] args) {
        Employee firstEmp = new Employee("Alice", "IT", 5000);
        Employee secondEmp = new Employee("Alex", "HR", 2000);
        Employee thirdEmp = new Employee("Bob", "CEO", 3000);
        Employee fourthEmp = new Employee("Alice", "CSO", 4000);

        EmployeeRepository employeeRepository = new EmployeeRepository();
        employeeRepository.addEmployee(firstEmp);
        employeeRepository.addEmployee(secondEmp);
        employeeRepository.addEmployee(thirdEmp);
        employeeRepository.addEmployee(fourthEmp);

        SalaryCalculator salaryCalculator = new SalaryCalculator();
        EmployeePrinter employeePrinter = new EmployeePrinter(employeeRepository);

        System.out.println(salaryCalculator.calculateSalary(firstEmp, 2.0, 100000));
        employeePrinter.printAllEmployee();

        employeeRepository.deleteEmployee(thirdEmp);
        employeePrinter.printAllEmployee();

        System.out.println(employeeRepository.findByName("Alice"));
    }
}
