package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(String.format(
                    "This key: '%s' is missing", key));
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String arg : args) {
            if (!arg.contains("=")) {
                throw new IllegalArgumentException(String.format(
                        "Error: This argument '%s' does not contain an equal sign", arg));
            }

            var str = arg.split("=", 2);
            var key = str[0];
            var value = str[1];

            if (!key.startsWith("-")) {
                throw new IllegalArgumentException(String.format(
                        "Error: This argument '%s' does not start with a '-' character", arg));
            }
            if ("-".equals(key)) {
                throw new IllegalArgumentException(String.format(
                        "Error: This argument '%s' does not contain a key", arg));
            }
            if (value.isBlank()) {
                throw new IllegalArgumentException(String.format(
                        "Error: This argument '%s' does not contain a value", arg));
            }
            values.put(key.substring(1), value);
        }

    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}