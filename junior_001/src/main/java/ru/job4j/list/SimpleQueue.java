package ru.job4j.list;

/**
 * @author Vyacheslav Khan (mailto: beckkhan@mail.ru)
 * @version 2.0
 * @since 16.10.2018
 */
public class SimpleQueue<T> {

    private SimpleStack<T> source = new  SimpleStack<>();
    private SimpleStack<T> dest = new  SimpleStack<>();
    private int index = 0;

    public T poll() {
        if (dest.isEmpty()) {
            while (!source.isEmpty()) {
                dest.push(source.poll());
            }
        }
        T result = dest.poll();
        index--;
        return result;
    }

    public void push(T value) {
        source.push(value);
        index++;
    }
}
