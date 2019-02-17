package ru.job4j.inputoutput.socket.oraclebot.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 18.02.2019
 */
public class ClientOracle {
    private Socket socket;
    private static final int PORT = 5000;
    private static final String IP = "127.0.0.1";

    public ClientOracle(Socket socket) throws IOException {
        this.socket = socket;
    }

    public void start() throws IOException {
        PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        Scanner scanner = new Scanner(System.in);
        String request = null;
        String response = null;
        do {
            //sending message to server
            request = scanner.nextLine();
            out.println(request);
            //reading from inputstream
            if (!"exit".equals(request)) {
                response = in.readLine();
                while (!response.isEmpty()) {
                    System.out.println(response);
                    response = in.readLine();
                }
            }
        } while (!("exit".equals(request)));
    }

    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket(InetAddress.getByName(IP), PORT)) {
            new ClientOracle(socket);
        }
    }
}