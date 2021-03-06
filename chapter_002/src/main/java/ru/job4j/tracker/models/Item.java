package ru.job4j.tracker.models;

/**
 * @author Vyacheslav Khan (beckkhan@mail.ru)
 * @version 2.0
 * @since 03.03.2019
 */
public class Item {

	private String id;

	public String name;

	public String description;

	public long create;
	
	private String comments;

	public Item() {
	}

	public Item(String name, String description, long create) {
		this.name = name;
		this.description = description;
		this.create = create;
	}

	public Item(String name, String desc) {
		this.name = name;
		this.description = desc;
	}

	public Item(String name, String desc, String id) {
		this.name = name;
		this.description = desc;
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return this.description;
	}

	public long getCreate() {
		return this.create;
	}

	public String getId() {
		return this.id;
	}

	public String getComments() {
		return this.comments;
	}

	public void setId(String id) {
		this.id = id;
	}
}