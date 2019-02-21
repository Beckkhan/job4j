package ru.job4j.inputoutput.socket.netfilemanager.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.function.*;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 21.02.2019
 */
public class DispatcherNFM {
    private Map<String, Function<String, Boolean>> dispatch = new HashMap<>();
    private PrintWriter out;
    private BufferedReader in;
    private String mainDirectory;
    private File checkpoint;

    public DispatcherNFM(PrintWriter out, BufferedReader in, String mainDirectory) {
        this.out = out;
        this.in = in;
        this.mainDirectory = mainDirectory;
        this.init();
    }

    public void setDispatch(String type, Function<String, Boolean> handle) {
        this.dispatch.put(type, handle);
    }

    public DispatcherNFM init() {
        this.setDispatch("get main list", this.getParentList());
        this.setDispatch("go to", this.goToSubDir());
        this.setDispatch("download", this.letClientDownload());
        this.setDispatch("upload", this.letClientUpload());
        this.setDispatch("exit", this.goToExit());
        return this;
    }

    public boolean checkUserRequest(final String userRequest) {
        boolean result = true;
        if (this.dispatch.keySet().contains(userRequest)) {
            result = this.dispatch.get(userRequest).apply(userRequest);
        } else {
            out.println("Введите корректную команду");
        }
        return result;
    }

    public Function<String, Boolean> goToExit() {
        return exit -> {
            out.println("Работа завершена");
            out.println();
            return false;
        };
    }

    public Function<String, Boolean> getParentList() {
        return getMainList -> {
            out.println("Cписок корневого каталога:");
            File parentList = new File(mainDirectory);
            checkpoint = parentList;
            this.presentDirectory(parentList);
            return true;
        };
    }

    public void presentDirectory(File currentFile) {
        int number = 0;
        for (File file : currentFile.listFiles()) {
            out.println(number++ + " " + file.toString());
        }
    }

    public Function<String, Boolean> goToSubDir() {
        return goToSubDir -> {
            try {
                File current = checkpoint;
                this.presentDirectory(current);
                out.println("Введите номер директории, в которую хотите перейти:");
                out.println();
                String userRequest = in.readLine();
                while (!checkUserInputNumber(userRequest)) {
                    out.println("Введите корректный номер директории");
                    userRequest = in.readLine();
                }
                current = this.getFileFromList(Integer.valueOf(userRequest), checkpoint);
                checkpoint = current;
                if (current.isDirectory()) {
                    this.presentDirectory(current);
                } else {
                    out.println(current.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        };
    }

    public boolean checkUserInputNumber(String userRequest) {
        boolean result = true;
        if (userRequest == null) {
            result = false;
        }
        if (Integer.valueOf(userRequest) >= checkpoint.listFiles().length
                && Integer.valueOf(userRequest) < 0) {
            result = false;
        }
        return result;
    }

    public File getFileFromList(int userNumber, File checkpoint) {
        File result;
        File[] files = checkpoint.listFiles();
        if (userNumber == 0) {
            result = checkpoint.getParentFile();
        } else {
            result = files[userNumber];
        }
        return result;
    }

    public Function<String, Boolean> letClientDownload() {
        return download -> {
            try {
                this.presentDirectory(checkpoint);
                out.println("Введите номер скачиваемого файла:");
                String userRequest = in.readLine();
                while (!checkUserInputNumber(userRequest)) {
                    out.println("Введите корректный номер");
                    userRequest = in.readLine();
                }
                File fileForDownLoad = this.getFileFromList(Integer.valueOf(userRequest), checkpoint);
                String pathOfCopiedFile = fileForDownLoad.toString().substring(fileForDownLoad.toString().lastIndexOf("\\"));
                out.println("Вами выбран файл: " + fileForDownLoad.getName());
                out.println("Введите путь для копирования файла в формате C:\\...\\...\\:");
                userRequest = in.readLine();
                File targetPlace = new File(userRequest + pathOfCopiedFile);
                this.downUpLoad(fileForDownLoad, targetPlace);
                out.println("Файл скопирован в указанную директорию");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        };
    }


    public void downUpLoad(File fileForDownLoad, File targetPlace) {
        try {
            Files.copy(fileForDownLoad.toPath(), targetPlace.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Function<String, Boolean> letClientUpload() {
        return download -> {
            try {
                this.presentDirectory(checkpoint);
                out.println("Введите путь копируемого файла в формате C:\\...\\...\\...*.*:");
                out.println();
                String userRequest = in.readLine();
                File fileForUpLoad = new File(userRequest);
                String pathOfCopiedFile = fileForUpLoad.toString().substring(fileForUpLoad.toString().lastIndexOf("\\"));
                out.println("Вы копируете файл: " + pathOfCopiedFile);
                File targetPlace = new File(this.checkpoint + pathOfCopiedFile);
                this.downUpLoad(fileForUpLoad, targetPlace);
                out.println("Файл скопирован в указанную директорию");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        };
    }
}