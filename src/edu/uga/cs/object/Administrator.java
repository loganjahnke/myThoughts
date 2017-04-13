package edu.uga.cs.object;

import java.util.Date;

/**
 * An Administrator is a Person object that has special
 * functionality. Administrators can create and delete
 * DebateCategories and delete and feature DebateTopics.
 * Administrators can also assign moderators.
 * 
 * @author Logan Jahnke
 */
public class Administrator extends Person {

	/**
	 * Creates an empty Administrator object
	 */
	public Administrator() {
		super();
	}

	/**
	 * Creates an Administrator object with the given information
	 * @param firstname - the first name of the admin
	 * @param lastname - the last name of the admin
	 * @param username - the username of the admin
	 * @param password - the password of the admin
	 * @param email - the email of the admin
	 */
	public Administrator(String firstname, String lastname, String username, String password, String email) {
		super(firstname, lastname, username, password, email);
	}

	/**
	 * Creates an Administrator object with the given information
	 * @param firstname - the first name of the admin
	 * @param lastname - the last name of the admin
	 * @param username - the username of the admin
	 * @param password - the password of the admin
	 * @param email - the email of the admin
	 * @param created - the date and time the admin was created
	 */
	public Administrator(String firstname, String lastname, String username, String password, String email, Date created) {
		super(firstname, lastname, username, password, email, created);
	}

}
