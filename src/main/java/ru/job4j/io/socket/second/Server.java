package ru.job4j.io.socket.second;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Server {
    private static final String EXIT = "exit".toLowerCase().toLowerCase();

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(8000)) {
            System.out.println("Server's up");
            while (!serverSocket.isClosed()) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client accepted");

                try (BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
                     BufferedWriter out = new BufferedWriter(
                             new OutputStreamWriter(clientSocket.getOutputStream()))) {

                    out.write("Привет! Угадай число от 1 до 100\n");
                    out.flush();

                    Random random = new Random();
                    int randomNum = random.nextInt(1, 101);

                    boolean isCorrect = false;
                    while (!isCorrect) {
                        int numUser = Integer.parseInt(in.readLine());
                        if (numUser < 1 || numUser > 100) {
                            out.write("Число должно быть от 1 до 100\n");
                            out.flush();
                            continue;
                        }
                        String answer;
                        if (numUser == randomNum) {
                            answer = "Верно\n";
                            isCorrect = true;
                        } else {
                            answer = "Попробуй еще!\n";
                        }
                        out.write(answer);
                        out.flush();
                    }
                }
            }
        }
    }
}

