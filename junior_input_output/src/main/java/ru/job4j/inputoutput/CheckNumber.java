package ru.job4j.inputoutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 01.02.2019
 */
public class CheckNumber {
    public boolean isNumber(InputStream in) {
        var result = false;
        try {
            int number;
            InputStreamReader isr = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(isr);
            number = Integer.parseInt(br.readLine());
            if (number % 2 == 0) {
                result = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException nfe) {
            System.out.println("Необходимо ввести число!");
            nfe.printStackTrace();
        }
        return result;
    }
}