package ru.job4j.ood.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.model.EmployeeJson;
import ru.job4j.ood.srp.store.Store;

import java.util.*;
import java.util.function.Predicate;

public class JsonReportEngine implements Report {
    private final Store store;
    private final Gson gson;
    private final DateTimeParser<Calendar> dateTimeParser;

    public JsonReportEngine(Store store) {
        this.store = store;
        this.dateTimeParser = new ReportDateTimeParser();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public String generate(Predicate<Employee> filter) {
       List<EmployeeJson> employeeList = new ArrayList<>();
        for (Employee employee : store.findBy(filter)) {
            employeeList.add(new EmployeeJson(employee, dateTimeParser));
        }
        return gson.toJson(employeeList);
    }
}
