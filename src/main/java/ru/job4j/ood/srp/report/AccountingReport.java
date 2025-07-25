package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class AccountingReport implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final CurrencyConverter currencyConverter;
    private final Currency source;
    private final Currency target;

    public AccountingReport(Store store, DateTimeParser<Calendar> dateTimeParser, CurrencyConverter currencyConverter, Currency source, Currency target) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.currencyConverter = currencyConverter;
        this.source = source;
        this.target = target;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append(String.format("Name; Hired; Fired; Salary(%s);", target))
                .append(System.lineSeparator());

        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(" ")
                    .append(dateTimeParser.parse(employee.getHired())).append(" ")
                    .append(dateTimeParser.parse(employee.getFired())).append(" ")
                    .append(String.format("%.2f", currencyConverter.convert(source, employee.getSalary(), target)))
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
