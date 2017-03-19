package edu.uga.cs.object;

import edu.uga.cs.persistence.Persistent;
import java.util.Date;

public class DebateTopic extends Persistent {

	private String title;
	private String description;
	private int vote;
	private int agrees;
	private int disagrees;
	private User user;
	private Date created;

	public DebateTopic() {
		super();
		this.title = "";
		this.description = "";
		this.vote = 0;
		this.agrees = 0;
		this.disagrees = 0;
		this.user = new User();
		this.created = new Date();
	}

	public DebateTopic(String title, String description, int vote, int agrees, int disagrees, User user, Date created) {
		super();
		this.title = title;
		this.description = description;
		this.vote = vote;
		this.agrees = agrees;
		this.disagrees = disagrees;
		this.user = user;
		this.created = created;
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

}
