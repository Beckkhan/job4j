package ru.job4j.tracker.start;

import org.junit.Test;
import ru.job4j.tracker.ITracker;
import ru.job4j.tracker.models.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Consumer;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Тестирование StartUITest с применением функционального интерфейса Consumer.
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 3.0
 * @since 03.03.2019
 **/
public class StartUITest {
    // создаем буфер для хранения вывода
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final Consumer<String> output = new Consumer<String>() {
        // получаем ссылку на стандартный вывод в консоль
        private final PrintStream stdout = new PrintStream(out);
        @Override
        public void accept(String s) {
            stdout.println(s);
        }
    };
    private final static String MENU = new StringBuilder()
            .append("0: Add the new item.")
            .append(System.lineSeparator())
            .append("1: Show all items.")
            .append(System.lineSeparator())
            .append("2: Edit item.")
            .append(System.lineSeparator())
            .append("3: Delete item.")
            .append(System.lineSeparator())
            .append("4: Find item by id.")
            .append(System.lineSeparator())
            .append("5: Find item by name.")
            .append(System.lineSeparator())
            .toString();
    /*@Before
    public void loadOutput() {
        System.out.println("execute before method");
        // заменяем стандартный вывод на вывод в память для тестирования
        System.setOut(new PrintStream(this.out));
    }
    @After
    public void backOutput() {
        // возвращаем обратно стандартный вывод в консоль
        System.setOut(this.stdout);
        System.out.println("execute after method");
    }*/
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        ITracker tracker = new Tracker();     // создаём Tracker
        Input input = new StubInput(new String[]{"0", "test name", "desc", "y"});   //создаём StubInput с последовательностью действий
        new StartUI(input, tracker, output).init();     //   создаём StartUI и вызываем метод init()
        assertThat(tracker.getAll().get(0).getName(), is("test name")); // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
    }
    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        // создаём Tracker
        ITracker tracker = new Tracker();
        //Напрямую добавляем заявку
        Item item = tracker.add(new Item());
        //создаём StubInput с последовательностью действий
        Input input = new StubInput(new String[]{"2", item.getId(), "test name", "desc", "y"});
        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker, output).init();
        // проверяем, что отредактированный элемент массива в трекере содержит имя, введённое при эмуляции.
        assertThat(tracker.findById(item.getId()).getName(), is("test name"));
    }
    @Test
    public void whenDeleteThenTrackerHasDeletedValue() {
        ITracker tracker = new Tracker();
        Item item = tracker.add(new Item());
        Input input = new StubInput(new String[]{"3", item.getId(), "y"});
        new StartUI(input, tracker, output).init();
        assertNull(tracker.findById(item.getId()));
    }
    @Test
    public  void whenTrackerShowAllItems() {
        ITracker tracker = new Tracker();
        Item one = new Item("one", "testOne");
        Item two = new Item("two", "testTwo");
        Item three = new Item("three", "testThree");
        tracker.add(one);
        tracker.add(two);
        tracker.add(three);
        Input input = new StubInput(new String[]{"1", "y"});
        new StartUI(input, tracker, output).init();
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                        .append(MENU)
                        .append(one.getId() + ". " + one.getName())
                        .append(System.lineSeparator())
                        .append(two.getId() + ". " + two.getName())
                        .append(System.lineSeparator())
                        .append(three.getId() + ". " + three.getName())
                        .append(System.lineSeparator())
                        .toString()
                )
        );
    }
    @Test
    public  void whenUserFindByItemByIdThenTrackerFindByItemById() {
        ITracker tracker = new Tracker();
        Item first = new Item("first", "test1");
        Item second = new Item("second", "test2");
        Item third = new Item("third", "test3");
        tracker.add(first);
        tracker.add(second);
        tracker.add(third);
        Input input = new StubInput(new String[]{"4", second.getId(), "y"});
        new StartUI(input, tracker, output).init();
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                        .append(MENU)
                        .append("Found item with id: ")
                        .append(second.getId() + " and name: ")
                        .append(second.getName())
                        .append(System.lineSeparator())
                        .toString()
                )
        );
    }
    @Test
    public void whenUserFindItemBNameThenTrackerFindItemByName() {
        ITracker tracker = new Tracker();
        Item one = new Item("one", "testOne");
        Item two = new Item("two", "testTwo");
        Item three = new Item("three", "testThree");
        tracker.add(one);
        tracker.add(two);
        tracker.add(three);
        Input input = new StubInput(new String[]{"5", three.getName(), "y"});
        new StartUI(input, tracker, output).init();
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                        .append(MENU)
                        .append("Found item with name: ")
                        .append(three.getName() + " and id: ")
                        .append(three.getId())
                        .append(System.lineSeparator())
                        .toString()
                )
        );
    }

    @Test
    public void whenAddItemWithStubInputThenTrackerAddItem() {
        ITracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "newtestname", "newtestdescription", "y"});
        new StartUI(input, tracker, output).init();
        assertThat(new String(out.toByteArray()), is(MENU));
    }
}