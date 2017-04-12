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
		Person person = pm.login(username, password);
		if (person == null)
			throw new MyThoughtsException("Hmmm... Recheck your username and password.");
		session.setUser(person);
		return SessionManager.storeSession(session);
	}

	public boolean precheckLogin(String username, String password) throws MyThoughtsException {
		Person person = pm.login(username, password);
		if (person == null)
			return true;
		else
			return false;
	}

	public boolean precheckEmail(String email) throws MyThoughtsException {
		return pm.isDuplicateEmail(email);
	}

	public boolean precheckUsername(String username) throws MyThoughtsException {
		return pm.isDuplicateUsername(username);
	}

}
