package ru.job4j.lsp;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 25.03.2019
 */
public class ControlQuality {

    private List<Food> newfoods;

    private Store warehouse;
    private Store shop;
    private Store trash;

    public ControlQuality(Store warehouse, Store shop, Store trash) {
        this.newfoods = new ArrayList<>();
        this.warehouse = warehouse;
        this.shop = shop;
        this.trash = trash;
    }

    public void receiveIncomingFood(Food food) {
        this.newfoods.add(food);
    }

    public List<Food> showNewFoods() {
        return this.newfoods;
    }

    private int calculateFoodUsage(Food food) {
        LocalDate today = LocalDate.now();
        double life = Period.between(food.getCreateDate(), food.getExpireDate()).getDays();
        double elapsed = Period.between(food.getCreateDate(), today).getDays();
        return (int) ((elapsed / life) * 100);
    }

    public void selectStore() {
        for (Food food : newfoods) {
            int usage = this.calculateFoodUsage(food);
            if (usage >= 25 && usage < 75) {
                shop.acceptInStock(food);
            }
            if (usage >= 75 && usage < 100) {
                food.setDiscount(usage);
                shop.acceptInStock(food);
            }
            if (usage >= 100) {
                trash.acceptInStock(food);
            } else {
                warehouse.acceptInStock(food);
            }
        }
        this.newfoods.clear();
    }
}