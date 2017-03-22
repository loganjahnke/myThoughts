package edu.uga.cs.object;

import edu.uga.cs.persistence.Persistent;
import java.util.Date;

public class Comment extends Persistent {

	private String subject;
	private String argument;
	private User user;
	private Date created;
	private Comment parent;

	public Comment() {
		super();
		this.subject = "";
		this.argument = "";
		this.user = new User();
		this.created = new Date();
		this.parent = null;
	}

	public Comment(String subject, String argument, User user, Date created, Comment parent) {
		super();
		this.subject = subject;
		this.argument = argument;
		this.user = user;
		this.created = created;
		this.parent = parent;
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

}
