package ru.job4j.ood.srp.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import java.util.Calendar;

@XmlType(propOrder = { "name", "hired", "fired", "salary" })
public class EmployeeXml {
    private String name;
    private String hired;
    private String fired;
    private double salary;

    public EmployeeXml() {
    }

    public EmployeeXml(Employee employee, DateTimeParser<Calendar> dateTimeParser) {
        this.name = employee.getName();
        this.hired = dateTimeParser.parse(employee.getHired());
        this.fired = dateTimeParser.parse(employee.getFired());
        this.salary = employee.getSalary();
    }

    @XmlElement
    public String getName() {
        return name;
    }

    @XmlElement
    public String getHired() {
        return hired;
    }

    @XmlElement
    public String getFired() {
        return fired;
    }

    @XmlElement
    public double getSalary() {
        return salary;
    }
}
