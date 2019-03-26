package ru.job4j.lsp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 2.0
 * @since 26.03.2019
 */
public class ControlQuality {

    private List<Food> newfoods;

    private Store warehouse;
    private Store shop;
    private Store trash;
    private Store cold;
    private Store recycle;
    private Store nextwarehouse;

    public ControlQuality() {
        this.init();
    }

    public void init() {
        this.newfoods = new ArrayList<>();
        this.warehouse = new Warehouse();
        this.shop = new Shop();
        this.trash = new Trash();
        this.cold = new SpecialColdWarehouse(new Warehouse());
        this.recycle = new RecyclingWarehouse(new Trash());
        this.nextwarehouse = new NextWarehouse(new Warehouse());
    }

    public void receiveIncomingFood(Food food) {
        this.newfoods.add(food);
    }

    public List<Food> showNewFoods() {
        return this.newfoods;
    }

    public int calculateFoodUsage(Food food) {
        LocalDate today = LocalDate.now();
        double life = (int) DAYS.between(food.getCreateDate(), food.getExpireDate());
        double elapsed = (int) DAYS.between(food.getCreateDate(), today);
        return (int) ((elapsed / life) * 100);
    }

    public void selectStore(Food food) {
        int usage = this.calculateFoodUsage(food);
        if (usage >= 25 && usage < 75) {
            shop.acceptInStock(food);
        }
        if (usage >= 75 && usage < 100) {
            food.setDiscount(50);
            shop.acceptInStock(food);
        }
        if (usage >= 100) {
            trash.acceptInStock(food);
        } else {
            warehouse.acceptInStock(food);
        }
    }

    public void selectStoreForAllTypesFood() {
        for (Food food : newfoods) {
            if (food.isVegetable() && (this.calculateFoodUsage(food) < 25)) {
                cold.acceptInStock(food);
            }
            if ((this.calculateFoodUsage(food) >= 100) && food.isCanReproduct()) {
                recycle.acceptInStock(food);
            } else {
                this.selectStore(food);
            }
        }
    }

    public void clearNewfoods() {
        this.newfoods.clear();
    }

    public Store getWarehouse() {
        return warehouse;
    }

    public Store getShop() {
        return shop;
    }

    public Store getTrash() {
        return trash;
    }

    public Store getCold() {
        return cold;
    }

    public Store getRecycle() {
        return recycle;
    }

    public Store getNextwarehouse() {
        return nextwarehouse;
    }
}