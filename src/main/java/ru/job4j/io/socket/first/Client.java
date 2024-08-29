package ru.job4j.io.socket.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket("127.0.0.1", 8000);

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        OutputStreamWriter writer = new OutputStreamWriter(clientSocket.getOutputStream());

        writer.write("Get me some information\n");
        writer.flush();

        String msg = reader.readLine();
        System.out.println(msg);

        writer.close();
        reader.close();
        clientSocket.close();
    }
}
