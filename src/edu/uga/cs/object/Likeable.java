package edu.uga.cs.object;

import edu.uga.cs.persistence.Persistent;
import java.util.Date;

/**
 * Likeable is an abstract parent class that both
 * DebateTopic and Comment inherit. This contains
 * functionality to vote, agree, and disagree on
 * an object.
 * 
 * @author loganjahnke
 */
public abstract class Likeable extends Persistent implements Comparable<Likeable>  {

	private int vote;
	private int agrees;
	private int disagrees;
	private Date created;

	/**
	 * Creates a blank Likeable object
	 */
	public Likeable() {
		super();
		this.vote = 0;
		this.agrees = -1;
		this.disagrees = -1;
		this.created = new Date();
	}
	
	/** 
	 * Creates a Likeable object with the given information
	 * @param created - the date and time of creation
	 */
	public Likeable(Date created) {
		super();
		this.vote = 0;
		this.agrees = -1;
		this.disagrees = -1;
		this.created = created;
	}

	/**
	 * Creates a Likeable object with the given information
	 * @param vote - the vote count
	 * @param agrees - the agreement count
	 * @param disagrees - the disagreement count
	 * @param created - the date and time of creation
	 */
	public Likeable(int vote, int agrees, int disagrees, Date created) {
		super();
		this.vote = vote;
		this.agrees = agrees;
		this.disagrees = disagrees;
		this.created = created;
	}

	/**
	 * Creates a Likeable object with the given information
	 * @param id - the persistent ID
	 */
	public Likeable(int id) {
		super(id);
		this.vote = 0;
		this.agrees = -1;
		this.disagrees = -1;
		this.created = new Date();
	}

	/**
	 * Creates a Likeable object with the given information
	 * @param id - the persistent ID
	 * @param vote - the vote count
	 * @param agrees - the agreement count
	 * @param disagrees - the disagreement count
	 * @param created - the date and time of creation
	 */
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

	/**
	 * increments the vote by one
	 */
	public void incrementVote() {
		this.vote++;
	}

	/**
	 * decrements the vote by one
	 */
	public void decrementVote() {
		this.vote--;
	}

	/**
	 * increments the agreement count by one
	 */
	public void incrementAgree() {
		this.agrees++;
	}

	/**
	 * decrements the agreement count by one
	 */
	public void decrementAgree() {
		this.agrees--;
	}

	/**
	 * increments the disagreement count by one
	 */
	public void incrementDisagree() {
		this.disagrees++;
	}

	/**
	 * decrements the disagreement count by one
	 */
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
	
	@Override
    public int compareTo(Likeable l) {
		if (l.vote > this.vote)
			return 1;
		else if (l.vote == this.vote)
			return 0;
		else
			return -1;
    }

}
