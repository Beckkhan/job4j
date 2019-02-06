package ru.job4j.inputoutput.pack;

import ru.job4j.tracker.start.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 2.0
 * @since 06.02.2019
 */
public class Pack {

    public static void main(String[] args) {
        args = new String[3];
        ConsoleInput input = new ConsoleInput();
        args[0] = input.ask("Введите директорию для архивирования в формате c:\\abc\\def...:");
        args[1] = input.ask("Введите расширение для исключаемых файлов в формате .*:");
        args[2] = input.ask("Введите имя для zip-файла, в который упаковываем проект:");
        Args parameters = new Args(args);
        Pack pack = new Pack();
        pack.prepareList(parameters.directory(), parameters.exclude());
        pack.archive(parameters);
    }

    public void archive(Args parameters) {
        LinkedList<File> listToZip = this.prepareList(parameters.directory(), parameters.exclude());
        try (ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(parameters.directory() + "\\" + parameters.output()))) {
            for (File file : this.prepareList(parameters.directory(), parameters.exclude())) {
                zip.putNextEntry(new ZipEntry(file.getAbsolutePath().substring(parameters.directory().length())));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LinkedList<File> prepareList(String parent, String exclude) {
        LinkedList<File> result = new LinkedList<>();
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
                if (!file.getName().endsWith(exclude)) {
                    result.add(file);
                }
            }
        }
        return result;
    }
}