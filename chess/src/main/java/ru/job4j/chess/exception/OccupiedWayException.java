package ru.job4j.chess.exception;

/**
 * @author Khan Vyacheslav (beckkhan@mail.ru)
 * @version $Id$
 * @since 0.1
 */

public class OccupiedWayException extends Exception {
    public OccupiedWayException(String msg) {
        super("There is a figure on the way");
    }
}