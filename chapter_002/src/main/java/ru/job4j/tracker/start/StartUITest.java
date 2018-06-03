package ru.job4j.tracker.start;

import ru.job4j.tracker.models.*;

public class StartUITest {
	
	public static void main(String[] args) {
		Input input = new StubInput(new String[] {"create stub task"});
		new StartUI(input, new Tracker()).init();
	}
}