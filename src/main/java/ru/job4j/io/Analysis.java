package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader input = new BufferedReader(new FileReader(source));
             PrintWriter output = new PrintWriter(new FileOutputStream(target))) {
            StringBuilder result = new StringBuilder();
            String ln = System.lineSeparator();
            String read;
            String start = null;
            String end = null;
            while ((read = input.readLine()) != null) {
                String[] line = read.split(" ", 2);
                int status = Integer.parseInt(line[0]);
                String data = line[1];
                if (status == 400 || status == 500) {
                    if (start == null) {
                        start = data;
                    }
                } else if (start != null) {
                    end = data;
                    result.append(start).append(";").append(end).append(ln);
                    start = null;
                    end = null;
                }
            }
            output.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}