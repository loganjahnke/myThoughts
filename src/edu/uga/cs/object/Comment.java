package edu.uga.cs.object;

import edu.uga.cs.persistence.Persistent;

public class Comment extends Persistent {

	private String subject;
	private String argument;
	private User user;

	public Comment() {
		super();
		this.subject = "";
		this.argument = "";
		this.user = new User();
	}

	public Comment(String subject, String argument, User user) {
		super();
		this.subject = subject;
		this.argument = argument;
		this.user = user;
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
	
	

}
