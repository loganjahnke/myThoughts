package edu.uga.cs.object;

import java.util.Date;
import java.util.HashMap;

/**
 * A User can create DebateTopics and Comments.
 * 
 * @author loganjahnke
 */
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
	 * @param firstname - the first name
	 * @param lastname - the last name
	 * @param username - the username
	 * @param email - the email address
	 * @param isModerator - is moderator or not
	 * @param karma - the karma score
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

	/**
	 * 
	 * Creates a User given the following information
	 * @param firstname - the first name
	 * @param lastname - the last name
	 * @param username - the username
	 * @param email - the email address
	 * @param isModerator - is moderator or not
	 * @param karma - the karma score
	 * @param votes - HashMap of vote choice to Likeable objects
	 * @param agrees - HashMap of agreement/disagreement to Likeable objects
	 */
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

	/**
	 * Creates a User given the following information
	 * @param firstname - the first name
	 * @param lastname - the last name
	 * @param username - the username
	 * @param email - the email address
	 */
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

	/**
	 * @return the HashMap of votes
	 */
	public HashMap<Likeable, Boolean> getLikes() {
		return votes;
	}

	/**
	 * @param votes
	 */
	public void setLikes(HashMap<Likeable, Boolean> votes) {
		this.votes = votes;
	}

	/**
	 * @return the HashMap of agrees
	 */
	public HashMap<Likeable, Boolean> getAgrees() {
		return agrees;
	}

	/**
	 * @param agrees
	 */
	public void setAgrees(HashMap<Likeable, Boolean> agrees) {
		this.agrees = agrees;
	}

	/**
	 * Adds a vote to a Likeable
	 * @param object - the Likeable
	 * @param vote - true is upvote, false is downvote
	 */
	public void addVote(Likeable object, Boolean vote) {
		this.votes.put(object, vote);
	}

	/**
	 * Adds a agreement to a Likeable
	 * @param object - the Likeable
	 * @param agree - true is agree, false is disagree
	 */
	public void addAgree(Likeable object, Boolean agree) {
		this.agrees.put(object, agree);
	}
	
	/**
	 * Checks to see if the User likes a Likeable
	 * @param object - the Likeable
	 * @return true if like
	 */
	public boolean doesLike(Likeable object) {
		if (object instanceof DebateTopic) {
			DebateTopic dt = (DebateTopic) object;
			System.out.println(getFirstname() + " might like " + dt.getTitle());
			System.out.println("Does [votes] contain the object? " + (this.votes.containsKey(dt) ? "yes" : "no"));
			if (this.votes.containsKey(dt) && this.votes.get(dt) == true) {
				return true;
			}
		} else if (object instanceof Comment) {
			Comment c = (Comment) object;
			if (this.votes.containsKey(c) && this.votes.get(c) == true) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Checks to see if the User dislikes a Likeable
	 * @param object - the Likeable
	 * @return true if dislike
	 */
	public boolean doesDislike(Likeable object) {
		if (object instanceof DebateTopic) {
			DebateTopic dt = (DebateTopic) object;
			if (this.votes.containsKey(dt) && this.votes.get(dt) == false) {
				return true;
			}
		} else if (object instanceof Comment) {
			Comment c = (Comment) object;
			if (this.votes.containsKey(c) && this.votes.get(c) == false) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Checks to see if the User agrees with a Likeable
	 * @param object - the Likeable
	 * @return true if agree
	 */
	public boolean doesAgree(Likeable object) {
		if (object instanceof DebateTopic) {
			DebateTopic dt = (DebateTopic) object;
			if (this.agrees.containsKey(dt) && this.agrees.get(dt) == true) {
				return true;
			}
		} else if (object instanceof Comment) {
			Comment c = (Comment) object;
			if (this.agrees.containsKey(c) && this.agrees.get(c) == true) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Checks to see if the User disagrees with a Likeable
	 * @param object - the Likeable
	 * @return true if disagree
	 */
	public boolean doesDisagree(Likeable object) {
		if (object instanceof DebateTopic) {
			DebateTopic dt = (DebateTopic) object;
			if (this.agrees.containsKey(dt) && this.agrees.get(dt) == false) {
				return true;
			}
		} else if (object instanceof Comment) {
			Comment c = (Comment) object;
			if (this.agrees.containsKey(c) && this.agrees.get(c) == false) {
				return true;
			}
		}
		
		return false;
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
