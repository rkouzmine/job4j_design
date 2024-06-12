package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class Multiple {
    public static void main(String[] args) {
        try (FileOutputStream outputStream = new FileOutputStream("data/multipleresult.txt")) {
            outputStream.write("1 * 2 = 2".getBytes());
            outputStream.write(System.lineSeparator().getBytes());
            outputStream.write("1 * 3 = 3".getBytes());
            outputStream.write(System.lineSeparator().getBytes());
            outputStream.write("1 * 4 = 4".getBytes());
            outputStream.write(System.lineSeparator().getBytes());
            outputStream.write("1 * 5 = 5".getBytes());
            outputStream.write(System.lineSeparator().getBytes());
            outputStream.write("1 * 6 = 6".getBytes());
            outputStream.write(System.lineSeparator().getBytes());
            outputStream.write("1 * 7 = 7".getBytes());
            outputStream.write(System.lineSeparator().getBytes());
            outputStream.write("1 * 8 = 8".getBytes());
            outputStream.write(System.lineSeparator().getBytes());
            outputStream.write("1 * 9 = 9".getBytes());
            outputStream.write(System.lineSeparator().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
