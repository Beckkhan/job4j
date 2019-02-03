package ru.job4j.inputoutput;

import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 03.02.2019
 */
public class FileScannerTest {
    @Test
    public void whenSearchTxtFiles() throws IOException {
        FileScanner search = new FileScanner();
        String dir = System.getProperty("java.io.tmpdir");
        File parentDir = new File(dir + "Main");
        parentDir.mkdir();
        File subDir = new File(parentDir.getPath() + "/Subdir");
        subDir.mkdir();
        File firstFile = new File(parentDir, "firstFile.txt");
        File secondFile = new File(parentDir, "secondFile.txt");
        File thirdFile = new File(parentDir, "thirdFile.doc");
        File inSubDirFile = new File(subDir, "subDirFile.txt");
        firstFile.createNewFile();
        secondFile.createNewFile();
        thirdFile.createNewFile();
        inSubDirFile.createNewFile();
        List<String> ext = Arrays.asList("txt");
        List<File> list = search.files(parentDir.getPath(), ext);
        List<File> expect = Arrays.asList(firstFile, secondFile, inSubDirFile);
        assertThat(list.toString(), is(expect.toString()));
    }
}