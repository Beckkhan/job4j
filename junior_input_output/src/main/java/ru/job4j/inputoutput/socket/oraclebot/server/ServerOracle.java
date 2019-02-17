package ru.job4j.inputoutput.socket.oraclebot.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 18.02.2019
 */
public class ServerOracle {
    private final Socket socket;
    private static final int PORT = 5000;

    public ServerOracle(Socket socket) {
        this.socket = socket;
    }

    public void start() throws IOException {
        PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        String ask = null;
        do {
            System.out.println("wait command...");
            ask = in.readLine();
            System.out.println(ask);
            if ("Hello Oracle".equals(ask)) {
                out.println("Hello, dear friend, I am Oracle.");
                out.println();
            } else if (!("exit".equals(ask))) {
                out.println("I don`t understand");
                out.println();
            }
        } while (!("exit".equals(ask)));
    }

    public static void main(String[] args) throws IOException {
        try (Socket socket = new ServerSocket(PORT).accept()) {
            new ServerOracle(socket);
        }
    }
}
