package ru.job4j.ood.srp.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "employees")
public class EmployeesXml {
    private List<EmployeeXml> employeesList;

    public EmployeesXml() {
    }

    public EmployeesXml(List<EmployeeXml> employeeXmlList) {
        this.employeesList = employeeXmlList;
    }

    @XmlElement(name = "employee")
    public List<EmployeeXml> getEmployeeXmlList() {
        return employeesList;
    }

    public void setEmployeeXmlList(List<EmployeeXml> employeeXmlList) {
        this.employeesList = employeeXmlList;
    }
}
