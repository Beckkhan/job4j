package ru.job4j.tracker.start;

import ru.job4j.tracker.models.*;

public class StartUI {
	
	private int[] ranges = new int[] {1, 2, 3, 4};
	private Input input;
	private Tracker tracker;
	
	public StartUI(Input input, Tracker tracker) {
		this.input = input;
		this.tracker = tracker;
	}
	
	public void init() {
	    MenuTracker menu = new MenuTracker(this.input, tracker);
		menu.fillActions();
		
		do {
			menu.show();
			menu.select(input.ask("Select:", menu.getActionsNum()));
		}
		while (!"y".equals(this.input.ask("Exit?(y):")));
	}
	
	public static void main(String[] args) {
        new StartUI(new ValidateInput(), new Tracker()).init();
    }


//	Ниже приведен код до использования внутренних классов	
	/**
     * Константа меню для добавления новой заявки.
     */
//    private static final String ADD = "0";

    /**
     * Константа меню для отображения всех заявок.
     */
//    private static final String ALL = "1";

    /**
     * Константа меню для редактирования заявки.
     */
//    private static final String EDIT = "2";

    /**
     * Константа меню для удаления заявки.
     */
//    private static final String DEL = "3";

    /**
     * Константа меню для поиска заявки по ID.
     */
//    private static final String FINDBYID = "4";

    /**
     * Константа меню для поиска заявки по имени.
     */
//    private static final String FINDBYNAME = "5";

	/**
     * Константа для выхода из цикла.
     */
//    private static final String EXIT = "6";
	
	/**
     * Получение данных от пользователя.
     */
//    private final Input input;
	
	/**
     * Хранилище заявок.
     */
//    private final Tracker tracker;
	
	/**
     * Конструтор инициализирующий поля.
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     */
//    public StartUI(Input input, Tracker tracker) {
//        this.input = input;
//        this.tracker = tracker;
//    }
	
	/**
     * Основой цикл программы.
     */	
//    public void init() {
//        boolean exit = false;
//        while (!exit) {
//            this.showMenu();
//            String answer = this.input.ask("Введите пункт меню : ");
//            if (ADD.equals(answer)) {
//                //добавление заявки вынесено в отдельный метод.
//                this.createItem();
//            } else if (ALL.equals(answer)) {
//                this.showAllItems();
//            }  else if (EDIT.equals(answer)) {
//                this.editItem();
//            }  else if (DEL.equals(answer)) {
//                this.deleteItem();
//            }  else if (FINDBYID.equals(answer)) {
//                this.findByIdItem();
//            }  else if (FINDBYNAME.equals(answer)) {
//                this.findByNameItem();
//            } else if (EXIT.equals(answer)) {
//                exit = true;
//            }
//        }
//    }

    /**
     * Метод реализует добавление новой заявки в хранилище.
     */
//    private void createItem() {
//        System.out.println("------------ Добавление новой заявки --------------");
//        String name = this.input.ask("Введите имя новой заявки :");
//        String desc = this.input.ask("Введите описание новой заявки :");
//        Item item = new Item(name, desc);
//        this.tracker.add(item);
//        System.out.println("------------ Новая заявка с getId : " + item.getId() + "-----------");
//    }

    /**
     * Метод реализует отображение всех заявок в хранилище.
     */
//    private void showAllItems() {
//        System.out.println("------------ Отображение всех заявок --------------");
//        for (Item item : this.tracker.getAll()) {
//	           System.out.println("Имя заявки: " + item.getName() + ". Id заявки: " + item.getId());
//	       }
//    }

//    private void editItem() {
//        System.out.println("------------ Редактирование заявки --------------");
//        String id = this.input.ask("Введите id редактируемой заявки :");
//        String name = this.input.ask("Введите имя редактируемой заявки :");
//        String desc = this.input.ask("Введите описание редактируемой заявки :");
//        Item item = new Item(name, desc, id);
//        this.tracker.replace(id, item);
//        System.out.println("------------ Отредактирована заявка с Id : " + item.getId() + "-----------");
//    }
//
//    private void deleteItem() {
//        System.out.println("------------ Удаление заявки --------------");
//        String id = this.input.ask("Введите id удаляемой заявки :");
//        this.tracker.delete(id);
//        System.out.println("------------ Удалена заявка с Id : " + id + "-----------");
//    }
//
//    private void findByIdItem() {
//        System.out.println("------------ Поиск заявки по id --------------");
//        String id = this.input.ask("Введите id для поиска заявки :");
//        Item item = this.tracker.findById(id);
//        System.out.println("--- Найдена заявка с Id: " + item.getId() + "--- имя заявки: " + item.getName());
//    }
//
//    private void findByNameItem() {
//        System.out.println("------------ Поиск заявки по имени --------------");
//        String name = this.input.ask("Введите имя для поиска заявки :");
//        for (Item item : this.tracker.findByName(name)) {
//            System.out.println("--- Найдена заявка с именем: " + name + "- id - " + item.getId());
//        }
//    }
//
//	private void showMenu() {
//        System.out.println("Меню:\n0. Add new Item\n1. Show all items\n2. Edit item\n3. Delete item\n4. Find item by Id\n5. Find items by name\n6. Exit Program\nSelect");
//    }
//		
	/**
     * Запуск программы.
     * @param args
     */
//    public static void main(String[] args) {
//        new StartUI(new ConsoleInput(), new Tracker()).init();
//    }
}