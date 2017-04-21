package edu.uga.cs.object;

import java.util.Date;
import java.util.ArrayList;

/**
 * A DebateTopic is the main feature of myThoughts.
 * DebateTopics contain many Comments and arguments.
 * 
 * @author loganjahnke
 */
public class DebateTopic extends Likeable {

	private String title;
	private String description;
	private ArrayList<DebateCategory> categories;

	/**
	 * Creates a blank DebateTopic object
	 */
	public DebateTopic() {
		super();
		this.title = "";
		this.description = "";
		this.categories = new ArrayList<DebateCategory>();
	}

	/**
	 * Create a DebateTopic with the given information
	 * @param id - the persistent ID of the topic
	 */
	public DebateTopic(int id) {
		super(id);
		this.title = "";
		this.description = "";
		this.categories = new ArrayList<DebateCategory>();
	}

	/**
	 * Create a DebateTopic with the given information
	 * @param title - the title of the topic
	 * @param description - the optional description of the topic
	 * @param vote - the vote count of the topic
	 * @param agrees - the agreement count of the topic
	 * @param disagrees - the disagreement count of the topic
	 * @param user - the User who posted the topic
	 * @param created - the date and time of creation
	 * @param categories - the categories assigned to the topic
	 */
	public DebateTopic(String title, String description, int vote, int agrees, int disagrees, User user, Date created, ArrayList<DebateCategory> categories) {
		super(vote, agrees, disagrees, user, created);
		this.title = title;
		this.description = description;
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

	/**
	 * @param category - the category to add
	 * @return self
	 */
	public DebateTopic addCategory(DebateCategory category) {
		this.categories.add(category);
		return this;
	}

	/**
	 * @param categories - the variadic list of categories to add
	 * @return self
	 */
	public DebateTopic addCategories(DebateCategory ... categories) {
		for (DebateCategory dc : categories)
			this.categories.add(dc);
		return this;
	}
	
	/**
	 * @param name - the name to check for
	 * @return true if the topic contains the category
	 */
	public boolean containsCategory(String name) {
		for (DebateCategory dc : categories)
			if (dc.getName().equals(name))
				return true;
		return false;
	}

	/**
	 * @return true if pass null test, false otherwise
	 */
	public boolean passesNullTest() {
		if (this.title == null)
			return false;
		return true;
	}

}
