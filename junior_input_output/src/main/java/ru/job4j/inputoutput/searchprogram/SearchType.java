package ru.job4j.inputoutput.searchprogram;

import java.io.File;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 26.02.2019
 */
public class SearchType {
    private final Map<String, String> parameters;

    public SearchType(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public List<File> findFiles() {
        List<File> result = new ArrayList<>();
        String typeOfSearch = getTypeOfSearch();
        Queue<File> queueDir = new LinkedList<>();
        queueDir.offer(new File(parameters.get("-d")));
        while (!queueDir.isEmpty()) {
            for (File file : queueDir.poll().listFiles()) {
                if (file.isDirectory()) {
                    queueDir.offer(file);
                } else {
                    if (checkFile(file, typeOfSearch)) {
                        result.add(file);
                    }
                }
            }
        }
        return result;
    }

    private String getTypeOfSearch() {
        for (String key : parameters.keySet()) {
            switch (key) {
                case "-m": return "-m";
                case "-f": return "-f";
                case "-r": return "-r";
                default: break;
            }
        }
        return null;
    }

    private boolean checkFile(File file, String typeOfSearch) {
        switch (typeOfSearch) {
            case "-m": return checkFileByMask(file, parameters.get("-n"));
            case "-f": return checkFileByFullName(file, parameters.get("-n"));
            case "-r": return checkFileByRegularEx(file, parameters.get("-r"));
            default: break;
        }
        return false;
    }

    private boolean checkFileByMask(File file, String mask) {
        String fileName = file.getName();
        if (!mask.contains("*")) {
            return this.checkFileByFullName(file, mask);
        } else {
            String[] parts = mask.split("\\*");
            for (String part : parts) {
                if (part.isEmpty()) {
                    continue;
                }
                if (!fileName.contains(part)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkFileByFullName(File file, String fullName) {
        return file.getName().equalsIgnoreCase(fullName);
    }

    private boolean checkFileByRegularEx(File file, String regEx) {
        return Pattern.matches(regEx, file.getName());
    }
}