package ru.job4j.lsp;

import java.time.LocalDate;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 25.03.2019
 */
public class Food {

    private String name;
    private LocalDate expireDate;
    private LocalDate createDate;
    private float price;
    private int discount;

    public Food(String name, LocalDate expireDate, LocalDate createDate, float price) {
        this.name = name;
        this.expireDate = expireDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = 0;
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
}