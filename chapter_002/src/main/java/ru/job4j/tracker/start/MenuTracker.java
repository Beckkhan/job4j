package ru.job4j.tracker.start;

import ru.job4j.tracker.models.*;
import ru.job4j.tracker.models.Item;

class EditItem implements UserAction {
	public int key() {
			return 2;
		}

		public void execute(Input input, Tracker tracker) {
			String id = input.ask("Enter the id of the item to be edited:");
			String name = input.ask("Enter the name of the item to be edited:");
			String desc = input.ask("Enter the description of the item to be edited:");
			tracker.replace(new Item(name, desc, id));
		}

		public String info() {
			return String.format("%s. %s", this.key(), "Edit item. ");
		}
}

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
		this.actions[2] = new EditItem();
		this.actions[3] = this.new DeleteItem();
		this.actions[4] = new MenuTracker.FindItemById();
		this.actions[5] = new MenuTracker.FindItemByName();
	}

	public void select(int key) {
		this.actions[key].execute(this.input, this.tracker);
	}
	
	public int[] getActionsNum() {
		int[] range = new int[this.actions.length];
		for (int i = 0; i < this.actions.length; i++) {
			range[i] = i;
		}
		return range;
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
			return 0;
		}

		public void execute(Input input, Tracker tracker) {
			String name = input.ask("Please, enter the task name: ");
			String desc = input.ask("Please, enter the task description: ");
			tracker.add(new Item(name, desc));
		}

		public String info() {
			return String.format("%s. %s", this.key(), "Add the new item. ");
		}

	}

	private static class ShowItems implements UserAction {

		public int key() {
			return 1;
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

	private class DeleteItem implements UserAction {

		public int key() {
			return 3;
		}

		public void execute(Input input, Tracker tracker) {
			String id = input.ask("Please enter the id of the item to be deleted: ");
			tracker.delete(id);
		}

		public String info() {
			return String.format("%s. %s", this.key(), "Delete item. ");
		}
	}

	private static class FindItemById implements UserAction {

		public int key() {
			return 4;
		}

		public void execute(Input input, Tracker tracker) {
			String id = input.ask("Enter the ID to search for the item:");
			Item item = tracker.findById(id);
			System.out.println("Found item with id: " + item.getId() + " and name: " + item.getName());
		}

		public String info() {
			return String.format("%s. %s", this.key(), "Find item by id. ");
		}
	}

	private class FindItemByName implements UserAction {

		public int key() {
			return 5;
		}

		public void execute(Input input, Tracker tracker) {
			String name = input.ask("Enter the name to search for the item:");
			for (Item item : tracker.findByName(name)) {
				System.out.println("Found item with name: " + name + " and id: " + item.getId());
			}
		}

		public String info() {
			return String.format("%s. %s", this.key(), "Find item by name. ");
		}
	}
}