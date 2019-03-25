package ru.job4j.lsp;

import java.util.List;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 25.03.2019
 */
public interface Store {

    boolean acceptInStock(Food food);

    List<Food> showFoodStore();
}