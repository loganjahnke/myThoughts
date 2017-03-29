package edu.uga.cs.object;

import edu.uga.cs.persistence.Persistent;

public abstract class Likeable extends Persistent {

	private int vote;
	private int agrees;
	private int disagrees;

	public Likeable() {
		super();
		this.vote = 0;
		this.agrees = -1;
		this.disagrees = -1;
	}

	public Likeable(int vote, int agrees, int disagrees) {
		super();
		this.vote = vote;
		this.agrees = agrees;
		this.disagrees = disagrees;
	}

	public Likeable(int id) {
		super(id);
		this.vote = 0;
		this.agrees = -1;
		this.disagrees = -1;
	}

	public Likeable(int id, int vote, int agrees, int disagrees) {
		super(id);
		this.vote = 0;
		this.agrees = -1;
		this.disagrees = -1;
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

}
