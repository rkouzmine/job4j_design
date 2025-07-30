package ru.job4j.ood.srp.report;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.model.EmployeeXml;
import ru.job4j.ood.srp.model.EmployeesXml;
import ru.job4j.ood.srp.store.Store;

import java.io.StringWriter;
import java.util.*;
import java.util.function.Predicate;

public class XmlReportEngine implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;

    public XmlReportEngine(Store store) {
        this.store = store;
        this.dateTimeParser = new ReportDateTimeParser();
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<EmployeeXml> employees = new ArrayList<>();
        for (Employee e : store.findBy(filter)) {
            employees.add(new EmployeeXml(e, dateTimeParser));
        }
        EmployeesXml employeesXml = new EmployeesXml(employees);
        String xml = "";
        try {
            JAXBContext context = JAXBContext.newInstance(EmployeesXml.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            try (StringWriter writer = new StringWriter()) {
                marshaller.marshal(employeesXml, writer);
                xml = writer.getBuffer().toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xml;
    }
}