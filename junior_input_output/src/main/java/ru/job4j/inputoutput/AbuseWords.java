package ru.job4j.inputoutput;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 01.02.2019
 */
public class AbuseWords {
    public void dropAbuses(InputStream in, OutputStream out, String[] abuse) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out))) {
            while (reader.ready()) {
                ArrayList<String> input = new ArrayList<>(Arrays.asList(reader.readLine().split(" ")));
                for (String badword : abuse) {
                    if (input.contains(badword)) {
                        input.remove(badword);
                    }
                }
                String result = String.join(" ", input);
                writer.write(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}