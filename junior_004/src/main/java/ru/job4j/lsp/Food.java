package ru.job4j.lsp;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 2.0
 * @since 29.03.2019
 */
public class Food {

    private String name;
    private LocalDate expireDate;
    private LocalDate createDate;
    private float price;
    private int discount;
    private boolean canReproduct;
    private boolean isVegetable;

    public Food(String name, LocalDate expireDate, LocalDate createDate, float price, boolean canReproduct, boolean isVegetable) {
        this.name = name;
        this.expireDate = expireDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = 0;
        this.canReproduct = canReproduct;
        this.isVegetable = isVegetable;
    }

    @Override
    public String toString() {
        return String.format(
                "Food: name %s"
                        + ", expireDate %s"
                        + ", createDate %s"
                        + ", price %s"
                        + ", discount %s",
                name,
                expireDate,
                createDate,
                price,
                discount
        );
    }

    public int calculateUsage() {
        LocalDate today = LocalDate.now();
        double life = (int) DAYS.between(this.getCreateDate(), this.getExpireDate());
        double elapsed = (int) DAYS.between(this.getCreateDate(), today);
        return (int) ((elapsed / life) * 100);
    }

    public String getName() {
        return name;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float newprice) {
        this.price = newprice;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public boolean isCanReproduct() {
        return canReproduct;
    }

    public boolean isVegetable() {
        return isVegetable;
    }
}