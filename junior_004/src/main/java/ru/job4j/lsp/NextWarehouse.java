package ru.job4j.lsp;

import java.util.List;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 26.03.2019
 */
public class NextWarehouse extends StoreDecorator {
    private List<Food> nwhfoods;

    public NextWarehouse(Store decoratedStore) {
        super(decoratedStore);
        this.nwhfoods = decoratedStore.showFoodStore();
    }

    @Override
    public boolean acceptInStock(Food food) {
        return this.nwhfoods.add(food);
    }

    @Override
    public List<Food> showFoodStore() {
        return this.nwhfoods;
    }
}