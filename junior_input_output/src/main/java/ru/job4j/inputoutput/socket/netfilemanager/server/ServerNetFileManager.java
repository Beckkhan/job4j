package ru.job4j.inputoutput.socket.netfilemanager.server;

import com.google.common.base.Joiner;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 2.0
 * @since 22.02.2019
 */
public class ServerNetFileManager implements ServerNFM {
    private final Socket socket;

    public ServerNetFileManager(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void start(String mainDirectory) {

        try (PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));) {
            String hello = Joiner.on(System.getProperty("line.separator")).join(
                    "Для получения списка корневого каталога введите команду get main list",
                    "Для перехода в подкаталог введите команду go to",
                    "Для скачивания файла введите команду download",
                    "Для загрузки файла введите команду upload",
                    "Для завершения работы введите команду exit"
            );
            out.println(hello);
            String userRequest = null;
            boolean userWantsContinue = true;
            DispatcherNFM dispatcherNFM = new DispatcherNFM(out, in, mainDirectory);
            do {
                out.println("Ожидаю ввод команды...");
                out.println();
                userRequest = in.readLine();
                System.out.println(userRequest);
                userWantsContinue = dispatcherNFM.checkUserRequest(userRequest);
            } while (userWantsContinue);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream(ServerNetFileManager.class.getClassLoader().getResource("app.properties").getPath())) {
            Properties properties = new Properties();
            properties.load(in);
            ServerSocket serverSocket = new ServerSocket(Integer.valueOf(properties.getProperty("port")));
            Socket socket = serverSocket.accept();
            ServerNetFileManager server = new ServerNetFileManager(socket);
            server.start(properties.getProperty("root"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}