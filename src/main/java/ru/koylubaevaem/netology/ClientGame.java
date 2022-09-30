package ru.koylubaevaem.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientGame {

    private static final int PORT = 8080;//порт к которому хотим подсоединится

    private static final String LOCALHOST = "127.0.0.1"; // <- адрес по которому располагается наш сервер,всегда

    public static void main(String[] args) {
        try (
             Scanner scanner = new Scanner(System.in);
             Socket clientSocket = new Socket(LOCALHOST, PORT);//создаем клиентский сокет
             PrintWriter out = new PrintWriter(
                     clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(
                     new InputStreamReader(clientSocket.getInputStream()))) {

            String city = in.readLine();//previous city from server
            System.out.println("Previous city: " + city);
            out.println(scanner.nextLine()); // city to send
            String status = in.readLine();// status(ok or not ok)
            System.out.println("Status of adding city: " + status);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
