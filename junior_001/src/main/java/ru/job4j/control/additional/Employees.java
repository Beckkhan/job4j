package ru.job4j.control.additional;

import java.util.*;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 1.0
 * @since 23.05.2019
 */
public class Employees {

    public TreeMap<Integer, ArrayList<Integer>> createStructure(int[] route) {
        int parent = route[0];
        TreeMap<Integer, ArrayList<Integer>> tree = new TreeMap<>();
        for (int i = 0; i < route.length - 1; i++) {
            if (route[i + 1] != parent) {
                if (!tree.containsKey(route[i])) {
                    tree.put(route[i], new ArrayList<>());
                    tree.get(route[i]).add(route[i + 1]);
                    Collections.sort(tree.get(route[i]));
                } else {
                    if (!tree.get(route[i]).contains(route[i + 1])) {
                        tree.get(route[i]).add(route[i + 1]);
                        Collections.sort(tree.get(route[i]));
                    }
                }
            } else {
                tree.put(route[i], new ArrayList<>());
                tree.get(route[i]).add(0);
            }
        }
        tree.put(route[route.length - 1], new ArrayList<>());
        tree.get(route[route.length - 1]).add(0);
        return tree;
    }
}