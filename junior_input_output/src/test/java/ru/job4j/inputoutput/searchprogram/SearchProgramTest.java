package ru.job4j.inputoutput.searchprogram;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 26.02.2019
 */
public class SearchProgramTest {
    private Map<String, String> parameters = new HashMap<>();
    private String logFileDir = System.getProperty("java.io.tmpdir") + "Search\\";
    private Validator validator;
    private SearchType searchType;
    private SearchProgram searchProgram;

    @Before
    public void setUp() {
        parameters.put("-d", logFileDir);
        parameters.put("-n", "*.jpg");
        parameters.put("-m", null);
        parameters.put("-o", logFileDir + "log.txt");
        validator = new Validator(parameters);
        searchType = new SearchType(parameters);
        searchProgram = new SearchProgram(validator, searchType);

        File[] directories = new File[]{new File(logFileDir + "Dir1/"),
                new File(logFileDir + "Dir2/subDir21/subDir221/"),
                new File(logFileDir + "Dir2/subDir22/subDir222/"),
                new File(logFileDir + "Dir3/subDir31/")};

        File[] files = new File[]{new File(logFileDir + "Dir1/notes.txt"),
                new File(logFileDir + "Dir1/main.jpg"),
                new File(logFileDir + "Dir2/books.bmp"),
                new File(logFileDir + "Dir2/subDir21/work.doc"),
                new File(logFileDir + "Dir2/subDir21/subDir221/schedule.doc"),
                new File(logFileDir + "Dir2/subDir22/subDir222/images.jpg"),
                new File(logFileDir + "Dir3/subDir31/avatar.jpg"),
                new File(logFileDir + "Dir3/subDir31/automechanic.txt")};

        for (File folder : directories) {
            if (!folder.exists()) {
                folder.mkdirs();
            }
        }
        for (File file : files) {
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void whenCheckSearchProgramWithPreparedParametersThenTrue() {
        searchProgram.start();
        try (BufferedReader br = new BufferedReader(new FileReader(logFileDir + "log.txt"))) {
            ArrayList<String> logStrings = new ArrayList<>();
            String string;
            while ((string = br.readLine()) != null) {
                logStrings.add(string);
            }
            assertTrue(logStrings.get(0).equals("Найдены файлы:"));
            assertTrue(logStrings.get(1).equals(logFileDir + "Dir1\\main.jpg"));
            assertTrue(logStrings.get(2).equals(logFileDir + "Dir3\\subDir31\\avatar.jpg"));
            assertTrue(logStrings.get(3).equals(logFileDir + "Dir2\\subDir22\\subDir222\\images.jpg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}