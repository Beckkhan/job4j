package ru.job4j.inputoutput.socket.netfilemanager.client;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.*;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 21.02.2019
 */
public class ClientNetFileManagerTest {
    private static final String LN = System.getProperty("line.separator");

    public void testClientNetFileManager(String consoleInput, String serverInput, String expected) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayInputStream in = new ByteArrayInputStream(serverInput.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayOutputStream consoleOutputStream = new ByteArrayOutputStream();
        PrintStream consolePrintStream = new PrintStream(consoleOutputStream);
        System.setOut(consolePrintStream);
        ByteArrayInputStream consoleInputStream = new ByteArrayInputStream(consoleInput.getBytes());
        System.setIn(consoleInputStream);
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        ClientNetFileManager clientNetFileManager = new ClientNetFileManager(socket);
        clientNetFileManager.start();
        assertThat(consoleOutputStream.toString(), is(expected));
    }

    @Test
    public void whenAskExitThenServerExit() throws IOException {
        testClientNetFileManager("exit", LN, null + LN);
    }

    @Test
    public void whenExitIsSecondRequestThenOracleExitAfterResponse() throws IOException {
        testClientNetFileManager(
                Joiner.on(LN).join(
                        "get main list",
                        "exit"
                ),
                Joiner.on(LN).join(
                        "",
                        LN
                ),
                null + LN);
    }
}