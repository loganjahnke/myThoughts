package edu.uga.cs.object;

import edu.uga.cs.persistence.Persistent;

public class DebateCategory extends Persistent {

	private String name;
	private String description;

	public DebateCategory() {
		super();
		this.name = "";
		this.description = "";
	}

	public DebateCategory(String name, String description) {
		super();
		this.name = name;
		this.description = description;
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
	
	

}
