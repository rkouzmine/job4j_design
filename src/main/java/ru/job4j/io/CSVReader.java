package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {

    public static final String PATH = "path";
    public static final String DELIMITER = "delimiter";
    public static final String OUT = "out";
    public static final String FILTER = "filter";

    public static void handle(ArgsName argsName) throws Exception {
        String delimiter = argsName.get(DELIMITER);
        String[] filters = argsName.get(FILTER).split(",");
        Path filePath = Path.of(argsName.get(PATH));

        List<String> headers = new ArrayList<>();
        List<String> filteredLines = new ArrayList<>();

        try (Scanner scanner = new Scanner(filePath)) {
            if (scanner.hasNextLine()) {
                String headerLine = scanner.nextLine();
                String[] columns = headerLine.split(delimiter);
                for (String column : columns) {
                    headers.add(column);
                }

                List<String> headerFilter = new ArrayList<>();
                for (String filter : filters) {
                    int index = headers.indexOf(filter);
                    if (index != -1) {
                        headerFilter.add(headers.get(index));
                    }
                }
                filteredLines.add(String.join(delimiter, headerFilter));

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    columns = line.split(delimiter);
                    List<String> filteredColumns = new ArrayList<>();
                    for (String filter : filters) {
                        int index = headers.indexOf(filter);
                        if (index != -1) {
                            filteredColumns.add(columns[index]);
                        }
                    }
                    filteredLines.add(String.join(delimiter, filteredColumns));
                }
            }
        }
        writeOutput(argsName.get(OUT), filteredLines);
    }

    private static void writeOutput(String out, List<String> data) throws IOException {
        if ("stdout".equals(out)) {
            for (String line : data) {
                System.out.println(line);
            }
        } else {
            Files.write(Path.of(out), data);
        }
    }

    private static void validateArgs(String[] args, ArgsName argsName) {
        if (args.length < 4) {
            throw new IllegalArgumentException("Not all arguments are specified");
        }
        if (!Files.exists(Path.of(argsName.get(PATH)))) {
            throw new IllegalArgumentException("The source data file is missing");
        }
        if (!argsName.get(OUT).endsWith(".csv") && !"stdout".equals(argsName.get(OUT))) {
            throw new IllegalArgumentException("The path for writing data is not specified correctly");
        }
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        validateArgs(args, argsName);
        handle(argsName);
    }
}