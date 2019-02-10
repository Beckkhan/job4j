package ru.job4j.inputoutput;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 2.0
 * @since 10.02.2019
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
                /*ArrayList<String> input = new ArrayList<>(Arrays.asList(reader.readLine().split(" ")));
                for (String badword : abuse) {
                    if (input.contains(badword)) {
                        input.remove(badword);
                    }
                }
                String result = String.join(" ", input);
                writer.write(result);*/

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}