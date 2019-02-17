package ru.job4j.inputoutput.socket.oraclebot.server;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
public class ServerOracleTest {
    private static final String LN = System.getProperty("line.separator");

    @Test
    public void whenAskExitThenOracleExit() throws IOException {
        this.testServer("exit", "");
    }

    @Test
    public void whenAskHelloThenGreetOracle() throws IOException {
        this.testServer(
                Joiner.on(LN).join(
                        "Hello Oracle",
                        "exit"
                ), String.format(
                        "Hello, dear friend, I am Oracle.%s%s", LN, LN)
        );
    }

    @Test
    public void whenAskUnsupportedAskThenDontUnderstand() throws IOException {
        this.testServer(
                Joiner.on(LN).join(
                        "unsupported ask",
                        "exit"
                ), Joiner.on(LN).join("I don`t understand", "", "")
        );
    }

    private void testServer(String input, String expected) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        ServerOracle serverOracle = new ServerOracle(socket);
        serverOracle.start();
        assertThat(out.toString(), is(expected));
    }
}