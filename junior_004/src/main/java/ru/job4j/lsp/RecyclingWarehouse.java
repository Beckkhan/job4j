package ru.job4j.lsp;

import java.util.List;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 26.03.2019
 */
public class RecyclingWarehouse extends StoreDecorator {

    private List<Food> recyclefoods;

    public RecyclingWarehouse(Store decoratedStore) {
        super(decoratedStore);
        this.recyclefoods = decoratedStore.showFoodStore();
    }

    @Override
    public boolean acceptInStock(Food food) {
        return this.recyclefoods.add(food);
    }

    @Override
    public List<Food> showFoodStore() {
        return this.recyclefoods;
    }
}