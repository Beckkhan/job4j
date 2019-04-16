package ru.job4j;

import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 2.0
 * @since 16.04.2019
 */
public class SoftReferenceCacheTest {

    @Test
    public void whenCheckCorrectWork() throws IOException {
        File fileNames = new File(System.getProperty("java.io.tmpdir"), "Names.txt");
        File fileAddress = new File(System.getProperty("java.io.tmpdir"), "Address.txt");
        fileNames.createNewFile();
        fileAddress.createNewFile();
        String names = "Olaf"
                + System.getProperty("line.separator")
                + "Aragorn"
                + System.getProperty("line.separator")
                + "Frodo"
                + System.getProperty("line.separator");
        String address = "Orodruin"
                + System.getProperty("line.separator")
                + "Dol Guldur"
                + System.getProperty("line.separator")
                + "Barad Angmar"
                + System.getProperty("line.separator");
        try (FileWriter namesWriter = new FileWriter(fileNames);
             FileWriter addressWriter = new FileWriter(fileAddress)) {
            namesWriter.write(names);
            addressWriter.write(address);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        SoftReferenceCache softReferenceCache = new SoftReferenceCache(System.getProperty("java.io.tmpdir"));
        assertTrue(softReferenceCache.showFileContent(fileNames.getName()).equals(names));
        assertTrue(softReferenceCache.showFileContent(fileAddress.getName()).equals(address));
        fileNames.delete();
        fileAddress.delete();
    }

    @Test (expected = NullPointerException.class)
    public void whenFileNotPresent() {
        File filePhones = new File(System.getProperty("java.io.tmpdir"), "Phones.txt");
        SoftReferenceCache softReferenceCache = new SoftReferenceCache(System.getProperty("java.io.tmpdir"));
        softReferenceCache.showFileContent(filePhones.getName());
    }
}