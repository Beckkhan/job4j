package ru.job4j.list;

/**
 * @author Vyacheslav Khan (mailto: beckkhan@mail.ru)
 * @version 2.0
 * @since 16.10.2018
 */
public class SimpleQueue<T> {

    private SimpleStack<T> source = new  SimpleStack<>();
    private SimpleStack<T> dest = new  SimpleStack<>();

    public T poll() {
        while (!source.isEmpty()) {
                dest.push(source.poll());
        }
        return dest.poll();
    }

    public void push(T value) {
        source.push(value);
    }
}
