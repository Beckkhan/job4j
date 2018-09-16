package ru.job4j.generic;

/**
 * @author Vyacheslav Khan (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 16.09.2018
 */
public abstract class AbstractStore<T extends Base> implements Store<T> {

    private SimpleArray<T> database = new SimpleArray<>(15);

    public AbstractStore(SimpleArray<T> database) {
        this.database = database;
    }

    @Override
    public void add(T model) {
        if (model != null) {
            this.database.add(model);
        }
    }
    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        for (T element : database) {
            if (model.getId().equals(element.getId())) {
                database.set(database.getIndex(element), model);
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        for (T element : database) {
            if (element.getId().equals(id)) {
                database.delete(database.getIndex(element));
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public T findById(String id) {
        T result = null;
        for (T element : database) {
            if (element.getId().equals(id)) {
                result = element;
                break;
            }
        }
        return result;
    }
}
