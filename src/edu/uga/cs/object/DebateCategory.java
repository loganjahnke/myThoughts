package edu.uga.cs.object;

import edu.uga.cs.persistence.Persistent;

/**
 * A DebateCategory is an organizational object that
 * a User can access to see relevant DebateTopics.
 * DebateCategories contain a fontawesome icon and
 * color for UI purposes.
 * 
 * @author loganjahnke
 */
public class DebateCategory extends Persistent {

	private String name;
	private String description;
	private String icon;
	private String color;

	/**
	 * Creates an empty DebateCategory
	 */
	public DebateCategory() {
		super();
		this.name = "";
		this.description = "";
		this.icon = "";
		this.color = "";
	}

	/**
	 * Create a DebateCategory with the given information
	 * @param name - the name of the category
	 * @param description - the description of the category
	 * @param icon - the fontawesome icon of the category
	 * @param color - the color of the icon
	 */
	public DebateCategory(String name, String description, String icon, String color) {
		super();
		this.name = name;
		this.description = description;
		this.icon = icon;
		this.color = color;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}
	
	/**
	 * @return the icon
	 */
	public String getIconWithoutFirstFA() {
		if (getIcon().length() > 3)
			return icon.substring(3);
		else
			return icon;
	}

	/**
	 * @param icon the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @return true if passes null test, false otherwise
	 */
	public boolean passesNullTest() {
		if (this.name == null)
			return false;
		if (this.icon == null)
			this.icon = "bars";
		if (this.color == null)
			this.color = "gray";
		return true;
	}

}
