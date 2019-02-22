package ru.job4j.inputoutput.socket.netfilemanager.server;

import com.google.common.base.Joiner;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 8.0
 * @since 22.02.2019
 */
public class ServerNetFileManagerTest {
    private static final String LN = System.getProperty("line.separator");
    private String path;
    private File[] directory;
    private File fileForCopy;

    @Before
    public void setUp() throws IOException {
        path = System.getProperty("java.io.tmpdir") + "filenetmanager\\";
        File main = new File(path);
        if (!main.exists()) {
            main.mkdirs();
        }

        fileForCopy = new File(path + "fileForCopy.txt");

        directory = new File[]{new File(path + "Dir1/"),
                new File(path + "Dir2/subDir2/subDir22/"),
                new File(path + "Dir2/subDir21/subDir212/"),
                new File(path + "Dir3/subDir3/")};

        for (File folder : directory) {
            if (!folder.exists()) {
                folder.mkdirs();
            }
        }
    }

    private void serverFileManagerTest(String input, String expected) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        ServerNetFileManager server = new ServerNetFileManager(socket);
        server.start(path);
        assertThat(out.toString(), is(expected));
    }

    @Test
    public void whenAskExitThenExit() throws IOException {
        this.serverFileManagerTest("exit",
                Joiner.on(LN).join(
                        "Для получения списка корневого каталога введите команду get main list",
                        "Для перехода в подкаталог введите команду go to",
                        "Для скачивания файла введите команду download",
                        "Для загрузки файла введите команду upload",
                        "Для завершения работы введите команду exit",
                        "Ожидаю ввод команды...",
                        "",
                        "Работа завершена",
                        LN
                )
        );
    }

    @Test
    public void whenAskSomeCommandsThenShowActionsOfServer() {
        try {
            fileForCopy.createNewFile();
            this.serverFileManagerTest(
                    Joiner.on(LN).join(
                            "get main list",
                            "go to",
                            "1",
                            "exit"
                    ),
                    Joiner.on(LN).join(
                            "Для получения списка корневого каталога введите команду get main list",
                            "Для перехода в подкаталог введите команду go to",
                            "Для скачивания файла введите команду download",
                            "Для загрузки файла введите команду upload",
                            "Для завершения работы введите команду exit",
                            "Ожидаю ввод команды...",
                            "",
                            "Cписок корневого каталога:",
                            "0 " + path + "Dir1",
                            "1 " + path + "Dir2",
                            "2 " + path + "Dir3",
                            "3 " + path + "fileForCopy.txt",
                            "Ожидаю ввод команды...",
                            "",
                            "0 " + path + "Dir1",
                            "1 " + path + "Dir2",
                            "2 " + path + "Dir3",
                            "3 " + path + "fileForCopy.txt",
                            "Введите номер директории, в которую хотите перейти:",
                            "",
                            "0 " + path + "Dir2\\subDir2",
                            "1 " + path + "Dir2\\subDir21",
                            "Ожидаю ввод команды...",
                            "",
                            "Работа завершена",
                            LN
                    )
            );
            fileForCopy.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenAskCopyFileThenServerCopyFile() {
        File newFile = new File(path + "Dir1\\fileForCopy.txt");
        try {
            fileForCopy.createNewFile();
            assertFalse(newFile.exists());
            this.serverFileManagerTest(
                    Joiner.on(LN).join(
                            "get main list",
                            "download",
                            "3",
                            path + "Dir1\\",
                            "exit"
                    ),
                    Joiner.on(LN).join(
                            "Для получения списка корневого каталога введите команду get main list",
                            "Для перехода в подкаталог введите команду go to",
                            "Для скачивания файла введите команду download",
                            "Для загрузки файла введите команду upload",
                            "Для завершения работы введите команду exit",
                            "Ожидаю ввод команды...",
                            "",
                            "Cписок корневого каталога:",
                            "0 " + path + "Dir1",
                            "1 " + path + "Dir2",
                            "2 " + path + "Dir3",
                            "3 " + path + "fileForCopy.txt",
                            "Ожидаю ввод команды...",
                            "",
                            "0 " + path + "Dir1",
                            "1 " + path + "Dir2",
                            "2 " + path + "Dir3",
                            "3 " + path + "fileForCopy.txt",
                            "Введите номер скачиваемого файла:",
                            "Вами выбран файл: " + "fileForCopy.txt",
                            "Введите путь для копирования файла в формате C:\\...\\...\\:",
                            "Файл скопирован в указанную директорию",
                            "Ожидаю ввод команды...",
                            "",
                            "Работа завершена",
                            LN

                    )
            );
            assertTrue(newFile.exists());
            newFile.delete();
            fileForCopy.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}