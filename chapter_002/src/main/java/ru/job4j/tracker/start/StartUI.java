package ru.job4j.tracker.start;

import ru.job4j.tracker.models.*;

public class StartUI {
	
	public static void main(String[] args) {
		Tracker tracker = new Tracker();
		tracker.add(new Task("first task", "first description"));
		for (Item item : tracker.getAll()) {
			System.out.println(item.getName());
		}
	}
}