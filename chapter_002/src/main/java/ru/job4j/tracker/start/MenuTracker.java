package ru.job4j.tracker.start;

import ru.job4j.tracker.models.*;

public class MenuTracker {

	private Input input;
	private Tracker tracker;
	private UserAction[] actions = new UserAction[6];

	public MenuTracker(Input input, Tracker tracker) {
		this.input = input;
		this.tracker = tracker;
	}

	public void fillActions() {
		this.actions[0] = this.new AddItem();
		this.actions[1] = new MenuTracker.ShowItems();
	}

	public void select(int key) {
		this.actions[key].execute(this.input, this.tracker);
	}

	public void show() {
		for (UserAction action : this.actions) {
			if (action != null) {
				System.out.println(action.info());
			}
		}
	}

	private class AddItem implements UserAction {

		public int key() {
			return 1;
		}

		public void execute(Input input, Tracker tracker) {
			String name = input.ask("Please, enter the task name: ");
			String desc = input.ask("Please, enter the task description: ");
			tracker.add(new Task(name, "first description"));

		}

		public String info() {
			return String.format("%s. %s", this.key(), "Add the new item. ");
		}

	}

	private static class ShowItems implements UserAction {

		public int key() {
			return 2;
		}

		public void execute(Input input, Tracker tracker) {
			for (Item item : tracker.getAll()) {
				System.out.println(String.format("%s. %s", item.getId(), item.getName()));
			}
		}

		public String info() {
			return String.format("%s. %s", this.key(), "Show all items. ");
		}

	}

}