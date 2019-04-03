package ru.job4j.tdd;

import java.util.HashSet;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 3.0
 * @since 03.04.2019
 */
public class SimpleGenerator {

    private static final Pattern P = Pattern.compile("\\$[{a-z}]*");

    public String generate(String template, Map<String, String> map) throws Exception {
        String result = template;
        Matcher matcher = P.matcher(result);
        HashSet<String> keys = new HashSet<>();
        while (matcher.find()) {
            String key = matcher.group().substring(2, matcher.group().length() - 1);
            keys.add(key);
            if (!map.containsKey(key)) {
                throw new Exception("Такого ключа в карте нет.");
            }
            result = matcher.replaceFirst(map.get(key));
            matcher = P.matcher(result);
        }
        if (keys.size() < map.keySet().size()) {
            throw new Exception("Обнаружены лишние ключи в карте.");
        }
        return result;
    }
}