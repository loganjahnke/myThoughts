package edu.uga.cs.object;

public class User extends Person {

	private boolean isModerator;
	private int karma;

	/**
	 * Convenience Constructor
	 */
	public User() {
		super();
		this.firstname = "";
		this.lastname = "";
		this.username = "";
		this.email = "";
		this.isModerator = false;
		this.karma = 0;
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
	public User(String firstname, String lastname, String username, String email, boolean isModerator, int karma) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.email = email;
		this.isModerator = isModerator;
		this.karma = karma;
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



}
