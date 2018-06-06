package ru.job4j.pseudo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Vyacheslav Khan (mailto:beckkhan@mail.ru)
 * @version $Id$
 * @since 0.1
 */

public class PaintTest {
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
	public void whenDrawSquare() {
		// выполняем действия, пишущие в консоль
		new Paint().draw(new Square());
		// проверяем результат вычисления
		assertThat(
				new String(out.toByteArray()),
				is(
						new StringBuilder()
								.append("++++")
								.append("+  +")
								.append("+  +")
								.append("++++")
								.append(System.lineSeparator())
								.toString()
				)
		);
	}
	@Test
	public void whenDrawTriangle() {
		// выполняем действия, пишущие в консоль
		new Paint().draw(new Triangle());
		// проверяем результат вычисления
		assertThat(
				new String(out.toByteArray()),
				is(
						new StringBuilder()
								.append("+")
								.append("++")
								.append("+ +")
								.append("++++")
								.append(System.lineSeparator())
								.toString()
				)
		);
	}
}