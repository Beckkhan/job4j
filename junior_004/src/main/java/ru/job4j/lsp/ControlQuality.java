package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 4.0
 * @since 31.03.2019
 */
public class ControlQuality {

    private List<Food> arrivals = new ArrayList<>();

    private Map<String, Store> stores;

    private final DispatchStore ds;

    public ControlQuality() {
        this.ds = new DispatchStore();
        this.stores = ds.showStores();
    }

    public void receiveIncomingFood(Food food) {
        this.arrivals.add(food);
    }

    public void selectStore() {
        for (Food food : this.arrivals) {
            ds.checkFood(food);
        }
        this.clearNewFoods();
    }

    public void clearNewFoods() {
        this.arrivals.clear();
    }

    public Store showStore(String storeName) {
        return this.stores.get(storeName);
    }

    public void resort() {
        for (Store store : stores.values()) {
            for (Food food : store.showFoodStore()) {
                this.receiveIncomingFood(food);
            }
        }
        this.selectStore();
    }
}