package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {

    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {

            Iterator<Integer> insideit = it.next();

            @Override
            public boolean hasNext() {
                while (!insideit.hasNext() && it.hasNext()) {
                    this.insideit = it.next();
                    if (insideit.hasNext()) {
                        break;
                    }
                }
                return insideit.hasNext();
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return insideit.next();
            }
        };
    }
}
