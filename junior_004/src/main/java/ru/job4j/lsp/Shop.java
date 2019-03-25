package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 25.03.2019
 */
public class Shop implements Store {

    private List<Food> shopfoods;

    public Shop() {
        this.shopfoods = new ArrayList<>();
    }

    @Override
    public boolean acceptInStock(Food food) {
        return this.shopfoods.add(food);
    }

    @Override
    public List<Food> showFoodStore() {
        return this.shopfoods;
    }
}