package ru.job4j.threads;

import javafx.scene.shape.Rectangle;

/**
 * @author Vyacheslav Khan (beckkhan@mail.ru)
 * @version 1.0
 * @since 28.11.2018
 */
public class RectangleMove implements Runnable {
    private final Rectangle rect;

    public RectangleMove(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void run() {
        int deltaX = (int) (Math.random() * 17 - 6);
        int deltaY = (int) (Math.random() * 17 - 6);;
        while (true) {
            this.rect.setX(this.rect.getX() + deltaX);
            this.rect.setY(this.rect.getY() + deltaY);
            if (this.rect.getX() >= 300 || this.rect.getX() <= 0) {
                deltaX *= -1;
            }
            if (this.rect.getY() >= 300 || this.rect.getY() <= 0) {
                deltaY *= -1;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}