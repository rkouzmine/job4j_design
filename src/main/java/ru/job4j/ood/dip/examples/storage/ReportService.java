package ru.job4j.ood.dip.examples.storage;

public class ReportService {
    private final Storage storage;

    public ReportService(Storage storage) {
        this.storage = storage;
    }

    public void createReport(String report) {
        storage.save(report);
    }
}