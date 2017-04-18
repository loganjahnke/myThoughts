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
	private HashMap<Integer, Boolean> topicVotes;
	private HashMap<Integer, Boolean> topicAgrees;
	private HashMap<Integer, Boolean> commentVotes;
	private HashMap<Integer, Boolean> commentAgrees;

	/**
	 * Convenience Constructor
	 */
	public User() {
		super();
		this.isModerator = false;
		this.karma = 0;
		this.topicVotes = new HashMap<Integer, Boolean>();
		this.topicAgrees = new HashMap<Integer, Boolean>();
		this.commentVotes = new HashMap<Integer, Boolean>();
		this.commentAgrees = new HashMap<Integer, Boolean>();
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
	public User(String firstname, String lastname, String username, String password, String email, boolean isModerator, int karma, Date created) {
		super(firstname, lastname, username, password, email, created);
		this.isModerator = isModerator;
		this.karma = karma;
		this.topicVotes = new HashMap<Integer, Boolean>();
		this.topicAgrees = new HashMap<Integer, Boolean>();
		this.commentVotes = new HashMap<Integer, Boolean>();
		this.commentAgrees = new HashMap<Integer, Boolean>();
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
	 * @param votes - HashMap of vote choice to Integer objects
	 * @param agrees - HashMap of agreement/disagreement to Integer objects
	 */
	public User(String firstname, String lastname, String username, String password, String email, boolean isModerator, int karma) {
		super(firstname, lastname, username, password, email);
		this.isModerator = isModerator;
		this.karma = karma;
		this.topicVotes = new HashMap<Integer, Boolean>();
		this.topicAgrees = new HashMap<Integer, Boolean>();
		this.commentVotes = new HashMap<Integer, Boolean>();
		this.commentAgrees = new HashMap<Integer, Boolean>();
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
		this.topicVotes = new HashMap<Integer, Boolean>();
		this.topicAgrees = new HashMap<Integer, Boolean>();
		this.commentVotes = new HashMap<Integer, Boolean>();
		this.commentAgrees = new HashMap<Integer, Boolean>();
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
	public HashMap<Integer, Boolean> getTopicLikes() {
		return topicVotes;
	}

	/**
	 * @param votes
	 */
	public void setTopicLikes(HashMap<Integer, Boolean> topicVotes) {
		this.topicVotes = topicVotes;
	}

	/**
	 * @return the HashMap of agrees
	 */
	public HashMap<Integer, Boolean> getTopicAgrees() {
		return topicAgrees;
	}

	/**
	 * @param agrees
	 */
	public void setTopicAgrees(HashMap<Integer, Boolean> topicAgrees) {
		this.topicAgrees = topicAgrees;
	}

	/**
	 * @return the HashMap of votes
	 */
	public HashMap<Integer, Boolean> getCommentLikes() {
		return commentVotes;
	}

	/**
	 * @param votes
	 */
	public void setCommentLikes(HashMap<Integer, Boolean> commentVotes) {
		this.commentVotes = commentVotes;
	}

	/**
	 * @return the HashMap of agrees
	 */
	public HashMap<Integer, Boolean> getCommentAgrees() {
		return commentAgrees;
	}

	/**
	 * @param agrees
	 */
	public void setCommentAgrees(HashMap<Integer, Boolean> commentAgrees) {
		this.commentAgrees = commentAgrees;
	}

	/**
	 * Adds an upvote to a Likeable
	 * @param object - the Likeable
	 */
	public void addUpvote(Likeable object) {
		if (object instanceof DebateTopic)
			this.topicVotes.put(object.getId(), true);
		else
			this.commentVotes.put(object.getId(), true);
	}

	/**
	 * Adds a downvote to a Likeable
	 * @param object - the Likeable
	 */
	public void addDownvote(Likeable object) {
		if (object instanceof DebateTopic)
			this.topicVotes.put(object.getId(), false);
		else
			this.commentVotes.put(object.getId(), false);
	}

	/**
	 * Adds an agreement to a Likeable
	 * @param object - the Likeable
	 */
	public void addAgree(Likeable object) {
		if (object instanceof DebateTopic)
			this.topicAgrees.put(object.getId(), true);
		else
			this.commentAgrees.put(object.getId(), true);
	}

	/**
	 * Adds a disagreement to a Likeable
	 * @param object - the Likeable
	 */
	public void addDisagree(Likeable object) {
		if (object instanceof DebateTopic)
			this.topicAgrees.put(object.getId(), false);
		else
			this.commentAgrees.put(object.getId(), false);
	}

	/**
	 * Checks to see if the User likes a Likeable
	 * @param object - the Likeable
	 * @return true if like
	 */
	public boolean doesLike(Likeable object) {
		if (object instanceof DebateTopic)
			if (topicVotes.containsKey(object.getId()))
				return topicVotes.get(object.getId());
		else if (object instanceof Comment)
			if (commentVotes.containsKey(object.getId()))
				return commentVotes.get(object.getId());
		return false;
	}

	/**
	 * Checks to see if the User dislikes a Likeable
	 * @param object - the Likeable
	 * @return true if dislike
	 */
	public boolean doesDislike(Likeable object) {
		if (object instanceof DebateTopic)
			if (topicVotes.containsKey(object.getId()))
				return !topicVotes.get(object.getId());
		else if (object instanceof Comment)
			if (commentVotes.containsKey(object.getId()))
				return !commentVotes.get(object.getId());
		return false;
	}

	/**
	 * Checks to see if the User agrees with a Likeable
	 * @param object - the Likeable
	 * @return true if agree
	 */
	public boolean doesAgree(Likeable object) {
		if (object instanceof DebateTopic)
			if (topicAgrees.containsKey(object.getId()))
				return topicAgrees.get(object.getId());
		else if (object instanceof Comment)
			if (commentAgrees.containsKey(object.getId()))
				return commentAgrees.get(object.getId());
		return false;
	}

	/**
	 * Checks to see if the User disagrees with a Likeable
	 * @param object - the Likeable
	 * @return true if disagree
	 */
	public boolean doesDisagree(Likeable object) {
		if (object instanceof DebateTopic)
			if (topicAgrees.containsKey(object.getId()))
				return !topicAgrees.get(object.getId());
		else if (object instanceof Comment)
			if (commentAgrees.containsKey(object.getId()))
				return !commentAgrees.get(object.getId());
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + " || User [isModerator=" + isModerator + ", karma=" + karma + "]";
	}



}
