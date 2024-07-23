package ru.job4j.io;

import java.io.*;

public class LoadingLogo {
    public static void main(String[] args) {
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(
                "C:\\Users\\krrse\\Downloads\\java_picture.png"));
             BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(
                     "data/java_picture.png"))) {
            int read;
            while ((read = in.read()) != -1) {
                out.write(read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}