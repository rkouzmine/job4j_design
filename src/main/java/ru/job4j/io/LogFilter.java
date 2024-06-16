package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        List<String> filteredLines = new ArrayList<>();
        try (BufferedReader input = new BufferedReader(new FileReader(file))) {
            String read;
            while ((read = input.readLine()) != null) {
                String[] line = read.split(" ");
                if ("404".equals(line[line.length - 2])) {
                    filteredLines.add(read);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filteredLines;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.filter().forEach(System.out::println);
    }
}