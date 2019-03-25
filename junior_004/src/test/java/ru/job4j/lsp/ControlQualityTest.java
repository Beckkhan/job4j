package ru.job4j.lsp;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 25.03.2019
 */
public class ControlQualityTest {
    @Test
    public void whenCheckFoodStorage() {
        Store warehouse = new Warehouse();
        Store trash = new Trash();
        Store shop = new Shop();
        Food milk = new Food(
                "Milk",
                LocalDate.of(2019, Month.MARCH, 30),
                LocalDate.of(2019, Month.MARCH, 1),
                100F
        );
        ControlQuality cq = new ControlQuality(warehouse, shop, trash);
        cq.receiveIncomingFood(milk);
        cq.selectStore();
        assertThat(shop.showFoodStore().get(0).getName(), is("Milk"));
        assertTrue(shop.showFoodStore().get(0).getDiscount() > 75);
    }

    @Test
    public void whenAddBadFoodThenTrash() {
        Store warehouse = new Warehouse();
        Store trash = new Trash();
        Store shop = new Shop();
        Food milk = new Food(
                "Bread",
                LocalDate.of(2019, Month.MARCH, 6),
                LocalDate.of(2019, Month.MARCH, 1),
                100F
        );
        ControlQuality cq = new ControlQuality(warehouse, shop, trash);
        cq.receiveIncomingFood(milk);
        cq.selectStore();
        assertThat(trash.showFoodStore().get(0).getName(), is("Bread"));
    }
}