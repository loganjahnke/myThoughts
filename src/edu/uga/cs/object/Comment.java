package edu.uga.cs.object;

import java.util.Date;

/**
 * A Comment is a User submitted argument which
 * can be attached to a DebateTopic of another
 * Comment. Comments contain a vote count and a
 * agreement/disagreement count.
 *
 * @author loganjahnke
 */
public class Comment extends Likeable {

	private String subject;
	private String argument;
	private User user;
	private DebateTopic topic;
	private Comment parent;

	/**
	 * Creates a blank Comment object
	 */
	public Comment() {
		super();
		this.subject = "";
		this.argument = "";
		this.user = null;
		this.topic = null;
		this.parent = null;
	}

	/**
	 * Creates a Comment object from the given information
	 * @param subject - the subject of the comment
	 * @param argument - the optional argument of the comment
	 * @param user - the User who posted the comment
	 * @param created - the date and time the comment was made
	 * @param parent - the parent Comment
	 * @param topic - the parent DebateTopic
	 */
	public Comment(String subject, String argument, User user, Date created, Comment parent, DebateTopic topic) {
		super(created);
		this.subject = subject;
		this.argument = argument;
		this.user = user;
		this.topic = topic;
		this.parent = parent;
	}

	/**
	 * Creates a Comment object from the given information
	 * @param subject - the subject of the comment
	 * @param argument - the optional argument of the comment
	 * @param user - the User who posted the comment
	 * @param created - the date and time the comment was made
	 * @param parent - the parent Comment
	 * @param topic - the parent DebateTopic
	 * @param vote - the vote count of the comment
	 * @param agrees - the agreement count of the comment
	 * @param disagrees - the disagreement count of the comment
	 */
	public Comment(String subject, String argument, User user, Date created, Comment parent, DebateTopic topic, int vote, int agrees, int disagrees) {
		super(vote, agrees, disagrees, created);
		this.subject = subject;
		this.argument = argument;
		this.user = user;
		this.topic = topic;
		this.parent = parent;
	}

	/**
	 * Creates a Comment with an ID
	 * @param id - the persistent ID
	 */
	public Comment(int id) {
		super(id);
		this.subject = "";
		this.argument = "";
		this.user = null;
		this.topic = null;
		this.parent = null;
	}

	/**
	 * @return the subject of the Comment
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject - the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the argument of the Comment
	 */
	public String getArgument() {
		return argument;
	}

	/**
	 * @param argument - the argument to set
	 */
	public void setArgument(String argument) {
		this.argument = argument;
	}

	/**
	 * @return the User who posted the Comment
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user - the User to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the DebateTopic of the Comment
	 */
	public DebateTopic getDebateTopic() {
		return this.topic;
	}

	/**
	 * @param debateTopic - the DebateTopic of the Comment
	 */
	public void setDebateTopic(DebateTopic debateTopic) {
		this.topic = debateTopic;
	}

	/**
	 * @return the parent Comment
	 */
	public Comment getParentComment() {
		return this.parent;
	}

	/**
	 * @param parent - the parent Comment
	 */
	public void setParentComment(Comment parent) {
		this.parent = parent;
	}

	/**
	 * @return
	 */
	public boolean doesAgreeWithTopic(int debateTopicId) {
		if (this.user.getTopicAgrees().containsKey(debateTopicId))
			return this.user.getTopicAgrees().get(debateTopicId);
		return false;
	}

	/**
	 * @return true if passes null test, false otherwise
	 */
	public boolean passesNullTest() {
		if (this.subject == null)
			return false;
		if (this.argument == null)
			return false;
		if (this.user == null)
			return false;
		if (this.topic == null)
			return false;
		return true;
	}

}
