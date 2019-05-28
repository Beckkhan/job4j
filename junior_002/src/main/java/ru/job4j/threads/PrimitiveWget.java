package ru.job4j.threads;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Vyacheslav Khan (beckkhan@mail.ru)
 * @version 1.0
 * @since 29.05.2019
 */
public class PrimitiveWget implements Runnable {

    private String link;
    private long speed;

    public PrimitiveWget(String link, long speed) {
        this.link = link;
        this.speed = speed;
    }

    @Override
    public void run() {
        String pref = "https://getfile.dokpub.com/yandex/get/";
        URL url = null;
        try {
            url = new URL(pref + link);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection urlCon = null;
        try {
            urlCon = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long size = urlCon.getContentLengthLong();
        String fileSize = String.format("%,.2fmb", (double) size / (1024 * 1024));
        String disposition = urlCon.getHeaderField("Content-Disposition");
        String type = urlCon.getContentType();
        String fileName = this.createName(disposition, type);

        System.out.println(String.format("Download file %s with size %s.", fileName, fileSize));
        String path = System.getProperty("java.io.tmpdir") + fileName;
        File file = new File(path);
        try (InputStream in = urlCon.getInputStream();
             FileOutputStream fos = new FileOutputStream(path)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            long startTime = System.nanoTime();
            long loaded = 0;
            while ((bytesRead = in.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
                System.out.println(String.format("%,.2fkb", (double) file.length() / 1024));
                long estimatedTime = System.nanoTime() - startTime;
                double seconds = (double) estimatedTime / 1000000000.0;
                System.out.println(String.format("%s seconds spent.", seconds));
                if (seconds >= 1) {
                    loaded = (file.length() - loaded) / 1024;
                    if (loaded >= speed) {
                        long over = loaded - speed;
                        long pause = (over / speed) * 1000;
                        System.out.println(String.format("Take a pause for %s seconds.", pause / 1000));
                        Thread.sleep(pause);
                        startTime = System.nanoTime();
                    }
                }
            }
            System.out.println("File downloaded.");
        } catch (IOException e) {
            System.out.println(e);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String createName(String disposition, String type) {
        String fileName;
        if (disposition != null) {
            fileName = disposition.substring(disposition.lastIndexOf("'") + 1,
                    disposition.length());
        } else {
            type = type.substring(type.lastIndexOf("/") + 1, type.length());
            fileName = link.substring(link.lastIndexOf("/") + 1,
                    link.length()) + "." + type;
        }
        return fileName;
    }

    public static void main(String[] args) {
        PrimitiveWget pw = new PrimitiveWget("https://yadi.sk/i/k1CSuxEkkscbR", 200);
        pw.run();
    }
}