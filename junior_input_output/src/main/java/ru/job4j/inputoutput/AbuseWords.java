package ru.job4j.inputoutput;

import java.io.*;
import java.util.Arrays;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 3.0
 * @since 12.04.2019
 */
public class AbuseWords {

    public void dropAbuses(InputStream in, OutputStream out, String[] abuse) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out))) {
            while (reader.ready()) {
                reader.lines()
                        .map(line -> Arrays.stream(abuse)
                                .reduce(line, (word, badword) -> word.replaceAll(badword, ""))
                        ).map(line -> line.trim()).forEach(line -> {
                    try {
                        writer.write(line);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}