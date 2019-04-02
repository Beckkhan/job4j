package ru.job4j.lsp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 4.0
 * @since 29.03.2019
 */
public class ControlQualityTest {

    private ControlQuality controlQuality;
    private Food milk;
    private Food bread;
    private Food tomatoes;
    private Food chicken;

    @Before
    public void prepareForTest() {
        controlQuality = new ControlQuality();
        milk = new Food(
                "Milk",
                LocalDate.of(2019, Month.APRIL, 5),
                LocalDate.of(2019, Month.MARCH, 1),
                100F,
                false,
                false
        );
        bread = new Food(
                "Bread",
                LocalDate.of(2019, Month.MARCH, 6),
                LocalDate.of(2019, Month.MARCH, 1),
                50F,
                false,
                false
        );
        tomatoes = new Food(
                "Tomatoes",
                LocalDate.of(2019, Month.APRIL, 30),
                LocalDate.of(2019, Month.MARCH, 25),
                100F,
                false,
                true
        );
        chicken = new Food(
                "Chicken",
                LocalDate.of(2019, Month.MARCH, 25),
                LocalDate.of(2019, Month.FEBRUARY, 25),
                150F,
                true,
                false
        );
        controlQuality.receiveIncomingFood(milk);
        controlQuality.receiveIncomingFood(bread);
        controlQuality.receiveIncomingFood(tomatoes);
        controlQuality.receiveIncomingFood(chicken);
    }

    @After
    public void cleanAfterTest() {
        controlQuality.clearNewFoods();
    }

    @Test
    public void whenCheckFoodStorage() {
        controlQuality.selectStore();
        assertThat(controlQuality.showStore("shop").showFoodStore().get(0).getName(), is("Milk"));
        assertTrue(controlQuality.showStore("shop").showFoodStore().get(0).getDiscount() == 50);
    }

    @Test
    public void whenAddBadFoodThenTrash() {
        controlQuality.selectStore();
        assertThat(controlQuality.showStore("trash").showFoodStore().get(0).getName(), is("Bread"));
    }

    @Test
    public void whenCheckColdWarehouse() {
        controlQuality.selectStore();
        assertThat(controlQuality.showStore("cold").showFoodStore().get(0).getName(), is("Tomatoes"));
    }

    @Test
    public void whenCheckRecycleWarehouse() {
        controlQuality.selectStore();
        assertThat(controlQuality.showStore("recycle").showFoodStore().get(0).getName(), is("Chicken"));
    }
}