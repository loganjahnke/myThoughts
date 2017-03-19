package edu.uga.cs.object;

import edu.uga.cs.persistence.Persistent;
import java.util.Date;

public abstract class Person extends Persistent {

	protected String firstname;
	protected String lastname;
	protected String username;
	protected String email;
	protected Date created;

	public Person() {
		super();
		this.firstname = "";
		this.lastname = "";
		this.username = "";
		this.email = "";
		this.created = new Date();
	}

	public Person(int id) {
		super(id);
		this.firstname = "";
		this.lastname = "";
		this.username = "";
		this.email = "";
		this.created = new Date();
	}

	public Person(String firstname, String lastname, String username, String email, Date created) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.email = email;
		this.created = created;
	}

	public Person(int id, String firstname, String lastname, String username, String email, Date created) {
		super(id);
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.email = email;
		this.created = created;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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

}
