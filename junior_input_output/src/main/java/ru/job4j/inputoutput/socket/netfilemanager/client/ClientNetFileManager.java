package ru.job4j.inputoutput.socket.netfilemanager.client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 21.02.2019
 */
public class ClientNetFileManager implements ClientNFM {
    private Socket socket;

    public ClientNetFileManager(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void start() {
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {
            String request = null;
            String response = null;
            boolean userWantsContinue = true;
            do {
                response = in.readLine();
                while (!response.isEmpty()) {
                    System.out.println(response);
                    response = in.readLine();
                }
                request = scanner.nextLine();
                out.println(request);
                if (request.equals("exit")) {
                    response = in.readLine();
                    System.out.println(response);
                    userWantsContinue = false;
                }
            } while (userWantsContinue);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("junior_input_output\\src\\main\\resources\\app.properties")) {
            Properties properties = new Properties();
            properties.load(in);
            Socket socket = new Socket(InetAddress.getByName(properties.getProperty("host")), Integer.valueOf(properties.getProperty("port")));
            ClientNetFileManager clientFileManager = new ClientNetFileManager(socket);
            clientFileManager.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}