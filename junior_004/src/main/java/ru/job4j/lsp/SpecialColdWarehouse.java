package ru.job4j.lsp;

import java.util.List;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 26.03.2019
 */
public class SpecialColdWarehouse extends StoreDecorator {

    private List<Food> coldfoods;

    public SpecialColdWarehouse(Store decoratedStore) {
        super(decoratedStore);
        this.coldfoods = decoratedStore.showFoodStore();
    }

    @Override
    public boolean acceptInStock(Food food) {
        return this.coldfoods.add(food);
    }

    @Override
    public List<Food> showFoodStore() {
        return this.coldfoods;
    }
}