package ru.job4j.io;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

public class WritingToFile {
    public static void main(String[] args) throws IOException {
        try (FileWriter writer = new FileWriter("data/test/test1.txt", true)) {
            writer.write("Test FileWriter\n");
        }

        try (PrintWriter writer = new PrintWriter("data/test/test2.txt")) {
            writer.println("Test PrintWriter");
        }

        try (PrintStream stream = new PrintStream("data/test/test3.txt")) {
            stream.println("Test PrintStream");
        }
    }
}