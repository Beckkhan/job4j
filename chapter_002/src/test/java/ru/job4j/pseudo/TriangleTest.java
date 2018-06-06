package ru.job4j.pseudo;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Vyacheslav Khan (mailto:beckkhan@mail.ru)
 * @version $Id$
 * @since 0.1
 */

public class TriangleTest {
	@Test
	public void whenDrawTriangle() {
		Triangle trg = new Triangle();
		assertThat(
				trg.draw(),
				is(
						new StringBuilder()
								.append("+")
								.append("++")
								.append("+ +")
								.append("++++")
								.toString()
				)
		);
	}
}