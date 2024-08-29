package ru.job4j.io.socket.first;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        int count = 0;
        ServerSocket serverSocket = new ServerSocket(8000);

        while (true) {
            System.out.println("Server's up!");
            Socket clientSocket = serverSocket.accept();

            System.out.println("Client accepted" + ++count + "\n");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            OutputStreamWriter writer = new OutputStreamWriter(clientSocket.getOutputStream());

            String request = reader.readLine();
            String response = "Client #" + count + ", your message length is: " + request.length() + "\n";
            writer.write(response);
            writer.flush();

            reader.close();
            writer.close();
            clientSocket.close();
        }
    }
}
