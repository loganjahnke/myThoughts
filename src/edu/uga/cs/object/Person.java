package edu.uga.cs.object;

import edu.uga.cs.persistence.Persistent;
import java.util.Date;

/**
 * A Person is an abstract parent class that
 * both Administrator and User inherit.
 * 
 * @author loganjahnke
 */
public abstract class Person extends Persistent {

	private String firstname;
	private String lastname;
	private String username;
	private String password;
	private String email;
	private Date created;

	/**
	 * Creates an empty Person
	 */
	public Person() {
		super();
		this.firstname = "";
		this.lastname = "";
		this.username = "";
		this.password = "";
		this.email = "";
		this.created = new Date();
	}

	/**
	 * Creates a Person with the given information
	 * @param id - the persistent ID
	 */
	public Person(int id) {
		super(id);
		this.firstname = "";
		this.lastname = "";
		this.username = "";
		this.password = "";
		this.email = "";
		this.created = new Date();
	}

	/**
	 * Creates a Person with the given information
	 * @param firstname - the first name
	 * @param lastname - the last name
	 * @param username - the username
	 * @param password - the password
	 * @param email - the email
	 * @param created - the date and time of creation
	 */
	public Person(String firstname, String lastname, String username, String password, String email, Date created) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.created = created;
	}

	/**
	 * Creates a Person with the given information
	 * @param firstname - the first name
	 * @param lastname - the last name
	 * @param username - the username
	 * @param password - the password
	 * @param email - the email
	 */
	public Person(String firstname, String lastname, String username, String password, String email) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.created = new Date();
	}

	/**
	 * Creates a Person with the given information
	 * @param id - the persistent ID
	 * @param firstname - the first name
	 * @param lastname - the last name
	 * @param username - the username
	 * @param password - the password
	 * @param email - the email
	 * @param created - the date and time of creation
	 */
	public Person(int id, String firstname, String lastname, String username, String password, String email, Date created) {
		super(id);
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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

	/**
	 * @return true if passes null test, false otherwise
	 */
	public boolean passesNullTest() {
		if (this.firstname.length() == 0)
			return false;
		if (this.lastname.length() == 0)
			return false;
		if (this.username.length() == 0)
			return false;
		if (this.email.length() == 0)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Person [firstname=" + firstname + ", lastname=" + lastname + ", username=" + username + ", password="
				+ password + ", email=" + email + ", created=" + created + "]";
	}



}
