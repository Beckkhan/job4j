package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.junit.Assert.assertThat;

public class ArrayDuplicateTest {
    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
        ArrayDuplicate duplicates = new ArrayDuplicate();
        String[] input = {"Привет", "Мир", "Привет", "Супер", "Мир"};
        String[] result = duplicates.remove(input);
        String[] expect = {"Привет", "Мир", "Супер"};
        assertThat(result, arrayContainingInAnyOrder(expect));
    }

    public void whenArrayHasAllDuplicates() {
        ArrayDuplicate duplicates = new ArrayDuplicate();
        String[] input = {"Привет", "Привет", "Привет", "Привет", "Привет"};
        String[] result = duplicates.remove(input);
        String[] expect = {"Привет"};
        assertThat(result, arrayContainingInAnyOrder(expect));
    }
}