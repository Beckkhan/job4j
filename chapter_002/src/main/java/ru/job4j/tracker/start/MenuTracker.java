package ru.job4j.tracker.start;

import ru.job4j.tracker.ITracker;
import ru.job4j.tracker.models.Item;

import java.util.function.Consumer;

/**
 * Класс MenuTracker с заменой вывода в консоль на Consumer.
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 3.0
 * @since 03.03.2019
 **/
class EditItem extends BaseAction {

    public EditItem(int key, String name) {
        super(key, name);
    }

    public void execute(Input input, ITracker tracker) {
        String id = input.ask("Enter the id of the item to be edited:");
        String name = input.ask("Enter the name of the item to be edited:");
        String desc = input.ask("Enter the description of the item to be edited:");
        Item item = new Item(name, desc, id);
        tracker.replace(item.getId(), item);
    }
}

public class MenuTracker {

    private Input input;
    private ITracker tracker;
    private final Consumer<String> output;
    private UserAction[] actions = new UserAction[6];

    public MenuTracker(Input input, ITracker tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
    }

    public void fillActions() {
        this.actions[0] = this.new AddItem(0, "Add the new item.");
        this.actions[1] = new MenuTracker.ShowItems(1, "Show all items.");
        this.actions[2] = new EditItem(2, "Edit item.");
        this.actions[3] = this.new DeleteItem(3, "Delete item.");
        this.actions[4] = new MenuTracker.FindItemById(4, "Find item by id.");
        this.actions[5] = new MenuTracker.FindItemByName(5, "Find item by name.");
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
                output.accept(action.info());
            }
        }
    }

    private class AddItem extends BaseAction {

        public AddItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, ITracker tracker) {
            String name = input.ask("Please, enter the task name: ");
            String desc = input.ask("Please, enter the task description: ");
            tracker.add(new Item(name, desc));
        }
    }

    private class ShowItems extends BaseAction {

        public ShowItems(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, ITracker tracker) {
            for (Item item : tracker.getAll()) {
                output.accept(String.format("%s. %s", item.getId(), item.getName()));
            }
        }
    }

    private class DeleteItem extends BaseAction {

        public DeleteItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, ITracker tracker) {
            String id = input.ask("Please enter the id of the item to be deleted: ");
            tracker.delete(id);
        }
    }

    private class FindItemById extends BaseAction {

        public FindItemById(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, ITracker tracker) {
            String id = input.ask("Enter the ID to search for the item:");
            Item item = tracker.findById(id);
            output.accept("Found item with id: " + item.getId() + " and name: " + item.getName());
        }
    }

    private class FindItemByName extends BaseAction {

        public FindItemByName(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, ITracker tracker) {
            String name = input.ask("Enter the name to search for the item:");
            for (Item item : tracker.findByName(name)) {
                output.accept("Found item with name: " + name + " and id: " + item.getId());
            }
        }
    }
}