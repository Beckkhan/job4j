package ru.job4j.inputoutput.socket.oraclebot.client;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.*;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 18.02.2019
 */
public class ClientOracleTest {
    private final String ln = System.lineSeparator();

    @Test
    public void whenAskExitThenOracleExit() throws IOException {
        testClient("exit", ln, "");
    }

    @Test
    public void whenExitIsSecondRequestThenOracleExitAfterFirstResponse() throws IOException {
        testClient(
                Joiner.on(ln).join("Hello Oracle", "exit"),
                Joiner.on(ln).join("Hello, dear friend, I am Oracle.", "", "", ""),
                Joiner.on(ln).join("Hello, dear friend, I am Oracle.", ""));
    }

    public void testClient(String consoleInput, String serverInput, String expected) throws IOException {
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
        ClientOracle clientOracle = new ClientOracle(socket);
        clientOracle.start();
        assertThat(consoleOutputStream.toString(), is(expected));
    }
}