package edu.uga.cs.object;

import java.util.Date;

public class Comment extends Likeable {

	private String subject;
	private String argument;
	private User user;
	private Date created;
	private DebateTopic topic;
	private Comment parent;

	public Comment() {
		super();
		this.subject = "";
		this.argument = "";
		this.user = null;
		this.created = new Date();
		this.topic = null;
		this.parent = null;
	}

	public Comment(String subject, String argument, User user, Date created, Comment parent, DebateTopic topic) {
		super();
		this.subject = subject;
		this.argument = argument;
		this.user = user;
		this.created = created;
		this.topic = topic;
		this.parent = parent;
	}

	public Comment(String subject, String argument, User user, Date created, Comment parent, DebateTopic topic, int vote, int agrees, int disagrees) {
		super(vote, agrees, disagrees);
		this.subject = subject;
		this.argument = argument;
		this.user = user;
		this.created = created;
		this.topic = topic;
		this.parent = parent;
	}

	public Comment(int id) {
		super(id);
		this.subject = "";
		this.argument = "";
		this.user = null;
		this.created = new Date();
		this.topic = null;
		this.parent = null;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the argument
	 */
	public String getArgument() {
		return argument;
	}

	/**
	 * @param argument the argument to set
	 */
	public void setArgument(String argument) {
		this.argument = argument;
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
	 * @return the DebateTopic
	 */
	public DebateTopic getDebateTopic() {
		return this.topic;
	}

	/**
	 * @param debateTopic the DebateTopic
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
	 * @param parent the parent Comment
	 */
	public void setParentComment(Comment parent) {
		this.parent = parent;
	}

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
