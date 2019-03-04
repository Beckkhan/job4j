package ru.job4j.tracker.start;

import ru.job4j.tracker.ITracker;

/**
 * @author Khan Vyacheslav (mailto: beckkhan@mail.ru)
 * @version 2.0
 * @since 03.03.2019
 */
public interface UserAction {
	
	int key();
	
	void execute(Input input, ITracker tracker);
	
	String info();
}