package ru.job4j.additional;

import java.util.concurrent.Semaphore;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 05.07.2019
 */
public class FirstThread implements Runnable {

    private Switcher switcher;
    private Semaphore semaphoreFirst;
    private Semaphore semaphoreSecond;
    private int repeat;

    public FirstThread(Switcher switcher, Semaphore semaphoreFirst, Semaphore semaphoreSecond, int repeat) {
        this.switcher = switcher;
        this.semaphoreFirst = semaphoreFirst;
        this.semaphoreSecond = semaphoreSecond;
        this.repeat = repeat;
    }

    @Override
    public void run() {
        int index = 0;
        while (index < repeat) {
            try {
                semaphoreFirst.acquire();
                switcher.add(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            semaphoreSecond.release();
            index++;
        }
    }
}