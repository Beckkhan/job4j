package ru.job4j.lsp;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 26.03.2019
 */
public abstract class StoreDecorator implements Store {

    protected Store decoratedStore;

    public StoreDecorator(Store decoratedStore) {
        this.decoratedStore = decoratedStore;
    }
}