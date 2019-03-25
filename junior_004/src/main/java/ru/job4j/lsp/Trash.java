package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 25.03.2019
 */
public class Trash implements Store {

    private List<Food> trashfoods;

    public Trash() {
        this.trashfoods = new ArrayList<>();
    }

    @Override
    public boolean acceptInStock(Food food) {
        return this.trashfoods.add(food);
    }

    @Override
    public List<Food> showFoodStore() {
        return this.trashfoods;
    }
}