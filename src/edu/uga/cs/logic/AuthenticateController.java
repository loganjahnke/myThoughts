package edu.uga.cs.logic;

import java.sql.Connection;

import edu.uga.cs.MyThoughtsException;
import edu.uga.cs.object.*;
import edu.uga.cs.persistence.*;
import edu.uga.cs.session.*;

/**
 * AuthenticateController
 * @author Logan Jahnke
 * @editor Logan Jahnke
 * @created April 6, 2017
 * @updated April 6, 2017
 */
public class AuthenticateController {

	private Connection con;
	private PersistenceManager pm;

	public AuthenticateController() {
		this.con = DbAccessInterface.connect();
		this.pm = new PersistenceManager(this.con);
	}

	public String register(Session session, String firstname, String lastname, String username, String email, String password) throws MyThoughtsException {
		User user = new User(firstname, lastname, username, password, email);
		user.setId(pm.savePerson(user));
		session.setUser(user);
		return SessionManager.storeSession(session);
	}

	public String login(Session session, String username, String password) throws MyThoughtsException {
		Person person = new User("", "", username, password, "");
		person = pm.restorePerson(person);
		if (person == null)
			throw new MyThoughtsException("User not found in system. Recheck your username/password.");
		session.setUser(person);
		return SessionManager.storeSession(session);
	}

}
