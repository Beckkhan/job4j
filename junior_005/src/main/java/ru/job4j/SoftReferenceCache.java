package ru.job4j;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 16.04.2019
 */
public class SoftReferenceCache {

    private Map<String, SoftReference<String>> cache = new HashMap<>();

    private String filePath;

    public SoftReferenceCache(String filePath) {
        this.filePath = filePath;
    }

    public void addToCache(String filename) {
        Path path = Paths.get(String.format("%s/%s", this.filePath, filename));
        StringBuilder sb = new StringBuilder();
        try {
            List<String> content = Files.readAllLines(path);
            for (String string : content) {
                sb.append(string).append(System.getProperty("line.separator"));
            }
            this.cache.put(filename, new SoftReference<>(sb.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String showFileContent(String filename) {
        String result;
        if (this.cache.get(filename) != null) {
            result = this.cache.get(filename).get();
        } else {
            this.addToCache(filename);
            result = this.cache.get(filename).get();
        }
        return result;
    }
}
