package ru.job4j.inputoutput;

import java.io.File;
import java.util.*;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 03.02.2019
 */
public class FileScanner {
    private LinkedList<File> result = new LinkedList<>();

    public List<File> files(String parent, List<String> exts) {
        File start = new File(parent);
        LinkedList<File> buffer = new LinkedList<>(Arrays.asList(start.listFiles()));
        File[] forSearch;
        while (!buffer.isEmpty()) {
            File file = buffer.pollFirst();
            if (file != null && file.isDirectory()) {
                forSearch = file.listFiles();
                for (File eachFile : forSearch) {
                    buffer.addLast(eachFile);
                }
            } else {
                for (String ext : exts) {
                    if (file.getName().endsWith(ext)) {
                        result.add(file);
                    }
                }
            }
        }
        return result;
    }
}