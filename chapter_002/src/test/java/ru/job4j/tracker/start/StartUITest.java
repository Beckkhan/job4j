package ru.job4j.tracker.start;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.tracker.models.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class StartUITest {
    // получаем ссылку на стандартный вывод в консоль
    PrintStream stdout = System.out;
    // создаем буфер для хранения вывода
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    @Before
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
    }
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();     // создаём Tracker
        Input input = new StubInput(new String[]{"0", "test name", "desc", "y"});   //создаём StubInput с последовательностью действий
        new StartUI(input, tracker).init();     //   создаём StartUI и вызываем метод init()
        assertThat(tracker.getAll().get(0).getName(), is("test name")); // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        // создаём Tracker
        Tracker tracker = new Tracker();
        //Напрямую добавляем заявку
        Item item = tracker.add(new Item());
        //создаём StubInput с последовательностью действий
        Input input = new StubInput(new String[]{"2", item.getId(), "test name", "desc", "y"});
        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();
        // проверяем, что отредактированный элемент массива в трекере содержит имя, введённое при эмуляции.
        assertThat(tracker.findById(item.getId()).getName(), is("test name"));
    }

    @Test
    public void whenDeleteThenTrackerHasDeletedValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item());
        Input input = new StubInput(new String[]{"3", item.getId(), "y"});
        new StartUI(input, tracker).init();
        assertNull(tracker.findById(item.getId()));
    }

    @Test
    public  void whenTrackerShowAllItems() {
        Tracker tracker = new Tracker();
        Item one = new Item("one", "testOne");
        Item two = new Item("two", "testTwo");
        Item three = new Item("three", "testThree");
        tracker.add(one);
        tracker.add(two);
        tracker.add(three);
        Input input = new StubInput(new String[]{"1", "y"});
        new StartUI(input, tracker).init();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("0: Add the new item.\r\n1: Show all items.\r\n2: Edit item.\r\n3: Delete item.\r\n4: Find item by id.\r\n5: Find item by name.")
                                .append(System.lineSeparator())
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
        Tracker tracker = new Tracker();
        Item first = new Item("first", "test1");
        Item second = new Item("second", "test2");
        Item third = new Item("third", "test3");
        tracker.add(first);
        tracker.add(second);
        tracker.add(third);
        Input input = new StubInput(new String[]{"4", second.getId(), "y"});
        new StartUI(input, tracker).init();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("0: Add the new item.\r\n1: Show all items.\r\n2: Edit item.\r\n3: Delete item.\r\n4: Find item by id.\r\n5: Find item by name.")
                                .append(System.lineSeparator())
                                .append("Found item with id: " + second.getId() + " and name: " + second.getName())
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }
    @Test
    public void whenUserFindItemBNameThenTrackerFindItemByName() {
        Tracker tracker = new Tracker();
        Item one = new Item("one", "testOne");
        Item two = new Item("two", "testTwo");
        Item three = new Item("three", "testThree");
        tracker.add(one);
        tracker.add(two);
        tracker.add(three);
        Input input = new StubInput(new String[]{"5", three.getName(), "y"});
        new StartUI(input, tracker).init();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("0: Add the new item.\r\n1: Show all items.\r\n2: Edit item.\r\n3: Delete item.\r\n4: Find item by id.\r\n5: Find item by name.")
                                .append(System.lineSeparator())
                                .append("Found item with name: " + three.getName() + " and id: " + three.getId())
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }

    @Test
    public void whenAddItemWithStubInputThenTrackerAddItem() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "newtestname", "newtestdescription", "y"});
        new StartUI(input, tracker).init();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("0: Add the new item.\r\n1: Show all items.\r\n2: Edit item.\r\n3: Delete item.\r\n4: Find item by id.\r\n5: Find item by name.")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }
}