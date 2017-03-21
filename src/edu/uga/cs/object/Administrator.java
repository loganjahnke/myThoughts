package edu.uga.cs.object;

import java.util.Date;

public class Administrator extends Person {

	public Administrator() {
		super();
	}

	public Administrator(String firstname, String lastname, String username, String password, String email) {
		super(firstname, lastname, username, password, email);
	}

	public Administrator(String firstname, String lastname, String username, String password, String email, Date created) {
		super(firstname, lastname, username, password, email, created);
	}

}
