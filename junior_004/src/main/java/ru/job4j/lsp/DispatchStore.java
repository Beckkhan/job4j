package ru.job4j.lsp;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 29.03.2019
 */
public class DispatchStore {

    private final Map<String, Function<Food, Boolean>> dispatch = new HashMap<>();

    private Map<String, Store> stores = new HashMap<>();

    public DispatchStore() {
        this.init();
    }

    private void init() {
        this.dispatch.put("shopAccept", shopAccept());
        this.dispatch.put("trashAccept", trashAccept());
        this.dispatch.put("warehouseAccept", warehouseAccept());
        this.dispatch.put("coldAccept", coldAccept());
        this.dispatch.put("recycleAccept", recycleAccept());
        this.stores.put("warehouse", new Warehouse());
        this.stores.put("shop", new Shop());
        this.stores.put("trash", new Trash());
        this.stores.put("cold", new SpecialColdWarehouse(new Warehouse()));
        this.stores.put("recycle", new RecyclingWarehouse(new Trash()));
        this.stores.put("new", new NextWarehouse(new Warehouse()));
    }

    public Map<String, Store> showStores() {
        return this.stores;
    }

    public Function<Food, Boolean> shopAccept() {
        return food -> {
            boolean result = false;
            int usage = food.calculateUsage();
            if (usage >= 75 && usage < 100) {
                food.setDiscount(50);
                this.stores.get("shop").acceptInStock(food);
                result = true;
            }
            if (usage >= 25 && usage < 75) {
                this.stores.get("shop").acceptInStock(food);
                result = true;
            }
            return result;
        };
    }

    public Function<Food, Boolean> trashAccept() {
        return food -> {
            boolean result = false;
            int usage = food.calculateUsage();
            if (usage >= 100) {
                this.stores.get("trash").acceptInStock(food);
                result = true;
            }
            return result;
        };
    }

    public Function<Food, Boolean> warehouseAccept() {
        return food -> {
            boolean result = false;
            int usage = food.calculateUsage();
            if (!food.isVegetable() && (usage < 25)) {
                this.stores.get("warehouse").acceptInStock(food);
                result = true;
            }
            return result;
        };
    }

    public Function<Food, Boolean> coldAccept() {
        return food -> {
            boolean result = false;
            int usage = food.calculateUsage();
            if (food.isVegetable() && (usage < 25)) {
                this.stores.get("cold").acceptInStock(food);
                result = true;
            }
            return result;
        };
    }

    public Function<Food, Boolean> recycleAccept() {
        return food -> {
            boolean result = false;
            int usage = food.calculateUsage();
            if ((usage >= 100) && food.isCanReproduct()) {
                this.stores.get("recycle").acceptInStock(food);
                result = true;
            }
            return result;
        };
    }

    public void checkFood(Food food) {
        for (Function function : this.dispatch.values()) {
            function.apply(food);
        }
    }
}
