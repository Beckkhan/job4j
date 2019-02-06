package ru.job4j.inputoutput.pack;

import org.junit.Test;
import java.io.File;
import static org.junit.Assert.assertTrue;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 06.02.2019
 */
public class PackTest {
    @Test
    public void whenFindFileThenTrue() {
        var args = new String[3];
        args[0] = "C:\\projects\\forpack";
        args[1] = ".txt";
        args[2] = "project.zip";
        Args parameters = new Args(args);
        Pack pack = new Pack();
        pack.prepareList(parameters.directory(), parameters.exclude());
        pack.archive(parameters);
        assertTrue(new File(parameters.directory() + "\\" + parameters.output()).exists());
    }
}