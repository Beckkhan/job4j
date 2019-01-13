package ru.job4j.nonblockingalgoritm;

/**
 * @author Vyacheslav Khan (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 13.01.2019
 */
public class OptimisticException extends RuntimeException {
    public OptimisticException() {
        super("Version has been changed.");
    }
}