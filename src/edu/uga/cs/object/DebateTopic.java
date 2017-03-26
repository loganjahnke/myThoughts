package edu.uga.cs.object;

import edu.uga.cs.persistence.Persistent;
import java.util.Date;
import java.util.ArrayList;

public class DebateTopic extends Persistent {

	private String title;
	private String description;
	private int vote;
	private int agrees;
	private int disagrees;
	private User user;
	private Date created;
	private ArrayList<DebateCategory> categories;

	public DebateTopic() {
		super();
		this.title = "";
		this.description = "";
		this.vote = 0;
		this.agrees = -1;
		this.disagrees = -1;
		this.user = new User();
		this.created = new Date();
		this.categories = new ArrayList<DebateCategory>();
	}

	public DebateTopic(String title, String description, int vote, int agrees, int disagrees, User user, Date created, ArrayList<DebateCategory> categories) {
		super();
		this.title = title;
		this.description = description;
		this.vote = vote;
		this.agrees = agrees;
		this.disagrees = disagrees;
		this.user = user;
		this.created = created;
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
	 * @return the vote
	 */
	public int getVote() {
		return vote;
	}

	/**
	 * @param vote the vote to set
	 */
	public void setVote(int vote) {
		this.vote = vote;
	}

	/**
	 * @return the agrees
	 */
	public int getAgrees() {
		return agrees;
	}

	/**
	 * @param agrees the agrees to set
	 */
	public void setAgrees(int agrees) {
		this.agrees = agrees;
	}

	/**
	 * @return the disagrees
	 */
	public int getDisagrees() {
		return disagrees;
	}

	/**
	 * @param disagrees the disagrees to set
	 */
	public void setDisagrees(int disagrees) {
		this.disagrees = disagrees;
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
	 * @return the created date
	 */
	public Date getCreatedDate() {
		return created;
	}

	/**
	 * @param created the created date
	 */
	public void setCreatedDate(Date created) {
		this.created = created;
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

	public void incrementVote() {
		this.vote++;
	}

	public void decrementVote() {
		this.vote--;
	}

	public void incrementAgree() {
		this.agrees++;
	}

	public void decrementAgree() {
		this.agrees--;
	}

	public void incrementDisagree() {
		this.disagrees++;
	}

	public void decrementDisagree() {
		this.disagrees--;
	}

	public boolean passesNullTest() {
		if (this.title == null)
			return false;
		if (this.user == null)
			return false;
		return true;
	}

}
