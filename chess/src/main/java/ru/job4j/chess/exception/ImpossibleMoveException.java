package ru.job4j.chess.exception;

/**
 * @author Khan Vyacheslav (beckkhan@mail.ru)
 * @version $Id$
 * @since 0.1
 */

public class ImpossibleMoveException extends Exception {
    public ImpossibleMoveException(String msg) {
        super(msg);
    }
}