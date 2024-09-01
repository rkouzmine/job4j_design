package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String string = input.readLine();
                    if (string != null && string.contains("msg=Hello")) {
                        output.write("Hello".getBytes());
                    } else if (string.contains("msg=Exit")) {
                        server.close();
                    } else {
                        output.write("What".getBytes());
                    }
                    System.out.println(string);
                    output.flush();
                }
            }
        } catch (IOException e) {
            LOG.error("Exception", e);
        }
    }
}