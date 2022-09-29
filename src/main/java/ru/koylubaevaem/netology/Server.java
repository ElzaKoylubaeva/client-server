package ru.koylubaevaem.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int PORT = 8080;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) { // открываем серверный сокет
            System.out.println("Server started");

            while (true) {
                try (Socket clientSocket = serverSocket.accept();//Listens for a connection to be made to this socket and accepts it.
                     PrintWriter out = new PrintWriter(
                             clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()))) {

                    System.out.println("New connection accepted");

                    String name = in.readLine();
                    String outData = String.format("Hi %s, your port is %d", name, clientSocket.getPort());
                    System.out.println(outData);

                    out.println(outData);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
