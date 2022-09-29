package ru.koylubaevaem.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private static final int PORT = 8080;//порт к которому хотим подсоединится

    private static final String LOCALHOST = "127.0.0.1"; // <- адрес по которому располагается наш сервер,всегда

    public static void main(String[] args) {
        try (Socket clientSocket = new Socket(LOCALHOST, PORT);//создаем клиентский сокет
             PrintWriter out = new PrintWriter(
                     clientSocket.getOutputStream(), true);

             BufferedReader in = new BufferedReader(
                     new InputStreamReader(clientSocket.getInputStream()))) {

            out.println("Elza"); // json, xml, java serialized data
            System.out.println(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
