package edu.uga.cs.object;

import java.util.Date;
import java.util.HashMap;

public class User extends Person {

	private boolean isModerator;
	private int karma;
	private HashMap<Likeable, Boolean> votes;
	private HashMap<Likeable, Boolean> agrees;

	/**
	 * Convenience Constructor
	 */
	public User() {
		super();
		this.isModerator = false;
		this.karma = 0;
		this.setLikes(new HashMap<Likeable, Boolean>());
		this.setAgrees(new HashMap<Likeable, Boolean>());
	}

	/**
	 * Creates a User given the following information
	 *
	 * @param firstname
	 * @param lastname
	 * @param username
	 * @param email
	 * @param isModerator
	 * @param karma
	 */
	public User(String firstname, String lastname, String username, String password, String email, boolean isModerator, int karma, Date created, HashMap<Likeable, Boolean> votes, HashMap<Likeable, Boolean> agrees) {
		super(firstname, lastname, username, password, email, created);
		this.isModerator = isModerator;
		this.karma = karma;
		if (votes == null)
			votes = new HashMap<Likeable, Boolean>();
		this.setLikes(votes);
		if (agrees == null)
			agrees = new HashMap<Likeable, Boolean>();
		this.setAgrees(agrees);
	}

	public User(String firstname, String lastname, String username, String password, String email, boolean isModerator, int karma, HashMap<Likeable, Boolean> votes, HashMap<Likeable, Boolean> agrees) {
		super(firstname, lastname, username, password, email);
		this.isModerator = isModerator;
		this.karma = karma;
		if (votes == null)
			votes = new HashMap<Likeable, Boolean>();
		this.setLikes(votes);
		if (agrees == null)
			agrees = new HashMap<Likeable, Boolean>();
		this.setAgrees(agrees);
	}

	public User(String firstname, String lastname, String username, String password, String email) {
		super(firstname, lastname, username, password, email);
		this.isModerator = false;
		this.karma = 0;
		votes = new HashMap<Likeable, Boolean>();
		this.setLikes(votes);
		agrees = new HashMap<Likeable, Boolean>();
		this.setAgrees(agrees);
	}

	/**
	 * @return the isModerator
	 */
	public boolean isModerator() {
		return isModerator;
	}

	/**
	 * @param isModerator the isModerator to set
	 */
	public void setModerator(boolean isModerator) {
		this.isModerator = isModerator;
	}

	/**
	 * @return the karma
	 */
	public int getKarma() {
		return karma;
	}

	/**
	 * @param karma the karma to set
	 */
	public void setKarma(int karma) {
		this.karma = karma;
	}

	public HashMap<Likeable, Boolean> getLikes() {
		return votes;
	}

	public void setLikes(HashMap<Likeable, Boolean> votes) {
		this.votes = votes;
	}

	public HashMap<Likeable, Boolean> getAgrees() {
		return agrees;
	}

	public void setAgrees(HashMap<Likeable, Boolean> agrees) {
		this.agrees = agrees;
	}

	public void addVote(Likeable object, Boolean vote) {
		this.votes.put(object, vote);
	}

	public void addAgree(Likeable object, Boolean agree) {
		this.agrees.put(object, agree);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + " || User [isModerator=" + isModerator + ", karma=" + karma + ", "
				+ (votes != null ? "votes=" + votes + ", " : "") + (agrees != null ? "agrees=" + agrees : "") + "]";
	}



}
