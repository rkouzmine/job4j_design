package ru.job4j.io;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.zip.*;

import static ru.job4j.io.ArgsName.*;

public class Zip {
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toString()));
                try (BufferedInputStream output = new BufferedInputStream(
                        new FileInputStream(source.toFile()))) {
                    zip.write(output.readAllBytes());
                }
                zip.closeEntry();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream output = new BufferedInputStream(
                    new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validateArgs(String[] args, ArgsName values) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Not all arguments are specified");
        }
        if (!Files.isDirectory(Path.of(values.get("d")))) {
            throw new IllegalArgumentException("The specified directory does not exist");
        }
        if (!values.get("e").startsWith(".")) {
            throw new IllegalArgumentException("Invalid exclusion extension specified");
        }
        if (!values.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException("Invalid archive extension specified");
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName values = ArgsName.of(args);
        validateArgs(args, values);
        String source = values.get("d");
        String exclude = values.get("e");
        String target = values.get("o");
        List<Path> sources = Search.search(Path.of(source),
                path -> !path.toFile().getName().endsWith(exclude));
        Zip zip = new Zip();
        zip.packFiles(sources, new File(target));
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
    }
}