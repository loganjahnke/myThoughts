package edu.uga.cs.object;

import edu.uga.cs.persistence.Persistent;
import java.util.Date;

public abstract class Likeable extends Persistent {

	private int vote;
	private int agrees;
	private int disagrees;
	private Date created;

	public Likeable() {
		super();
		this.vote = 0;
		this.agrees = -1;
		this.disagrees = -1;
		this.created = new Date();
	}
	
	public Likeable(Date created) {
		super();
		this.vote = 0;
		this.agrees = -1;
		this.disagrees = -1;
		this.created = created;
	}

	public Likeable(int vote, int agrees, int disagrees, Date created) {
		super();
		this.vote = vote;
		this.agrees = agrees;
		this.disagrees = disagrees;
		this.created = created;
	}

	public Likeable(int id) {
		super(id);
		this.vote = 0;
		this.agrees = -1;
		this.disagrees = -1;
		this.created = new Date();
	}

	public Likeable(int id, int vote, int agrees, int disagrees, Date created) {
		super(id);
		this.vote = 0;
		this.agrees = -1;
		this.disagrees = -1;
		this.created = created;
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
