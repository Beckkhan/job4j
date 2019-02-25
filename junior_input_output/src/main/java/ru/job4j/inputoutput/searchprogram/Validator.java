package ru.job4j.inputoutput.searchprogram;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 26.02.2019
 */
public class Validator {
    private final Map<String, String> parameters;

    public Validator(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public boolean validate() {
        return (setOfArgumentsIsFullAndCorrect()
                && directoryIsCorrect(parameters.get("-d"))
                && regExIsCorrect(parameters.get("-r")));
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    private boolean setOfArgumentsIsFullAndCorrect() {
        boolean result = true;
        if (!parameters.containsKey("-d") || parameters.get("-d") == null
                || !parameters.containsKey("-n") || parameters.get("-n") == null
                || !parameters.containsKey("-o") || parameters.get("-o") == null) {
            result = false;
        }
        if (this.getNumberTypeOfFileSearch().size() != 1) {
            result = false;
        }
        return result;
    }

    private List<String> getNumberTypeOfFileSearch() {
        List<String> typesFileSearch = new ArrayList<>();
        for (String key : parameters.keySet()) {
            if (key.equals("-m") || key.equals("-f") || key.equals("-r")) {
                typesFileSearch.add(key);
            }
        }
        return typesFileSearch;
    }

    private boolean directoryIsCorrect(String path) {
        File file = new File(path);
        return  (file.exists() && file.isDirectory());
    }

    private boolean regExIsCorrect(String regEx) {
        if (regEx != null) {
            try {
                Pattern.compile(regEx);
            } catch (PatternSyntaxException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}