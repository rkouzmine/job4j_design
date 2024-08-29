package ru.job4j.io.socket.second;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        String response;
        try (Socket clientSocket = new Socket("127.0.0.1", 8000);
             BufferedReader in = new BufferedReader(
                     new InputStreamReader(clientSocket.getInputStream()));
             BufferedWriter out = new BufferedWriter(
                     new OutputStreamWriter(clientSocket.getOutputStream()));
             Scanner scanner = new Scanner(System.in)) {

            response = in.readLine();
            System.out.println(response);

            boolean correct = false;
            while (!correct) {
                int number = scanner.nextInt();
                out.write(number + "\n");
                out.flush();

                response = in.readLine();
                System.out.println(response);

                if (response.startsWith("Верно")) {
                    correct = true;
                }
            }
        }
    }
}
