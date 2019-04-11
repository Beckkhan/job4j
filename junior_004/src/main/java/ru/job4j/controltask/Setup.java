package ru.job4j.controltask;

import ru.job4j.controltask.io.*;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 11.04.2019
 */
public class Setup {

    private final int defaultSize = 3;
    private Input input;
    private Output output;

    /**
     * Constructor for a class Setup that is a preparation for the game.
     * As parameters takes the input and the output objects.
     * @param input
     * @param output
     */
    public Setup(Input input, Output output) {
        this.input = input;
        this.output = output;
    }

    /**
     * This method determines the size of the field on which the game will be played.
     * This is done from the user by using the I/o objects that we accept as constructor parameters.
     * @return The method returns the size of the playing field.
     */
    public int clarifyBoardSize() {
        boolean valid = false;
        int size = 0;
        this.output.write("Enter board size. To use a board of size equal to 3, simply press Enter button without anything. : ");
        while (!valid) {
            String fieldSize = this.input.read().trim();
            if (!fieldSize.trim().isEmpty()) {
                try {
                    size = Integer.parseInt(fieldSize);
                    if (size < defaultSize) {
                        throw new Exception("Size should be a positive odd number greater or equal 3.");
                    }
                    if (size >= defaultSize && size % 2 == 1) {
                        valid = true;
                    } else {
                        valid = false;
                        throw new Exception("Size should be an odd number.");
                    }
                } catch (NumberFormatException nfe) {
                    this.output.write("Enter correct board size. Size should be a positive odd number greater or equal 3.");
                    nfe.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                size = defaultSize;
                valid = true;
            }
        }
        return size;
    }

    /**
     * This method determines which player starts the game first by asking the user.
     * Input/output objects are used.
     * @return The method returns true if the Computer starts the game.
     */
    public boolean clarifyBeginner() {
        this.output.write(
                "If you want to start the game, enter any symbol and press Enter."
                + " If you press Enter without anything the game will start the Computer.: "
        );
        return this.input.read().trim().isEmpty();
    }
}