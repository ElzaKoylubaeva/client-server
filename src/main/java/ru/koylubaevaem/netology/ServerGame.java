package ru.koylubaevaem.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class ServerGame {

    private static final int PORT = 8080;

    public static void main(String[] args) {
        Set<String> cities = new HashSet<>();
        String currentCity = Constants.NO_CURRENT_CITY;

        System.out.println("Starting server...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) { // открываем серверный сокет
            System.out.println("Server started...");
            while (true) {
                try (Socket clientSocket = serverSocket.accept();//Listens for a connection to be made to this socket and accepts it.
                     PrintWriter out = new PrintWriter(
                             clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(clientSocket.getInputStream()))) {

                    out.println(currentCity);

                    String enteredCity = in.readLine();
                    if (enteredCity == null) {
                        System.out.println("City from client is NULL.");
                        out.println(Constants.STATUS_NOT_OK);
                        continue;
                    }
                    enteredCity = enteredCity.toLowerCase(Locale.ROOT);
                    System.out.println("City from client: " + enteredCity);

                    if (cities.contains(enteredCity)) {
                        System.out.println("City is repeated: " + enteredCity);
                        out.println(Constants.STATUS_NOT_OK);
                        continue;
                    }

                    if (Constants.NO_CURRENT_CITY.equals(currentCity)) {
                        cities.add(enteredCity);
                        out.println(Constants.STATUS_OK);
                        currentCity = enteredCity;
                    } else {
                        int lastLetterIdx = currentCity.length() - 1;
                        char currentCityLastLetter = currentCity.charAt(lastLetterIdx);
                        char enteredCityFirstLetter = enteredCity.charAt(0);

                        if (currentCityLastLetter == enteredCityFirstLetter) {
                            System.out.println("City is approved: " + enteredCity);
                            cities.add(enteredCity);
                            out.println(Constants.STATUS_OK);
                            currentCity = enteredCity;
                        } else {
                            System.out.println("City is rejected: " + enteredCity);
                            out.println(Constants.STATUS_NOT_OK);
                        }
                    }
                    System.out.println("Current city is: " + currentCity);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
