package ru.job4j.condition;

/**
 * @author Vyacheslav Khan (beckkhan@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class DummyBot {
    /**
     * Отвечает на вопросы.
     * @param question Вопрос от клиента.
     * @return Ответ.
     */
    public String answer(String question) {
        String rsl = "Это ставит меня в тупик. Спросите другой вопрос.";
        if ("Привет, Бот.".equals(question)) {
            // заменить ... на правильный ответ rsl = "ответ по заданию".
            rsl = "Привет, умник.";
        } else if ("Пока.".equals(question)) {
            // заменить ... на правильный ответ rsl = "ответ по заданию".
            rsl = "До скорой встречи.";
        } else {
            rsl = "Это ставит меня в тупик. Спросите другой вопрос.";
        }
        return rsl;
    }
}