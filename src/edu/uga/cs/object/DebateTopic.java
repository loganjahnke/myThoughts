package edu.uga.cs.object;

import java.util.Date;
import java.util.ArrayList;

public class DebateTopic extends Likeable {

	private String title;
	private String description;
	private User user;
	private ArrayList<DebateCategory> categories;

	public DebateTopic() {
		super();
		this.title = "";
		this.description = "";
		this.user = new User();
		this.categories = new ArrayList<DebateCategory>();
	}

	public DebateTopic(int id) {
		super(id);
		this.title = "";
		this.description = "";
		this.user = new User();
		this.categories = new ArrayList<DebateCategory>();
	}

	public DebateTopic(String title, String description, int vote, int agrees, int disagrees, User user, Date created, ArrayList<DebateCategory> categories) {
		super(vote, agrees, disagrees, created);
		this.title = title;
		this.description = description;
		this.user = user;
		this.categories = categories;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the categories date
	 */
	public ArrayList<DebateCategory> getCategories() {
		return categories;
	}

	/**
	 * @param categories the categories
	 */
	public void setCategories(ArrayList<DebateCategory> categories) {
		this.categories = categories;
	}

	public DebateTopic addCategory(DebateCategory category) {
		this.categories.add(category);
		return this;
	}

	public DebateTopic addCategories(DebateCategory ... categories) {
		for (DebateCategory dc : categories)
			this.categories.add(dc);
		return this;
	}

	public boolean passesNullTest() {
		if (this.title == null)
			return false;
		if (this.user == null)
			return false;
		return true;
	}

}
