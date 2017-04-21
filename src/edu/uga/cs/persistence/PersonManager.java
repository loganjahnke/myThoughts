package edu.uga.cs.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import edu.uga.cs.MyThoughtsException;
import edu.uga.cs.object.*;

/**
 * PersonManager
 * @author Logan Jahnke
 * @created March 19, 2017
 */
public class PersonManager {

	Connection con;

	public PersonManager(Connection con) {
		this.con = con;
	}

	/**
	 * @author Lucy Bradley
	 * Used by the user to change their password
	 * @param username 
	 * @param oldPswd		the original password
	 * @param newPswd		the password to set
	 * @return 				true if successful, false if the statement somehow fails
	 * @throws MyThoughtsException
	 */
	public boolean confirmChangePswd(String username, String oldPswd, String newPswd) throws MyThoughtsException{
		String query = "SELECT password FROM person WHERE username = \"" + username.trim() + "\";";
		try {
			Statement stmt = con.createStatement();
			stmt.execute(query);
			ResultSet rs = stmt.getResultSet();
			if (rs.next()){
				if (rs.getString(1).equals(oldPswd)){
					String update = "UPDATE person SET password = \"" + newPswd + "\" WHERE username = \"" + username +"\";";
					Statement stmt2 = con.createStatement();
					if(stmt2.executeUpdate(update) == 1)
						return true;
				}
			}
			return false;
		} catch (Exception e) {
			throw new MyThoughtsException("PersonManager.confirmChangePswd: error in something... " + e);
		}
	}
	
	/**
	 * Checks the database for a specific email duplication
	 * @param email - the email to check
	 * @return true if duplicate, false otherwise
	 * @throws MyThoughtsException
	 */
	public boolean isDuplicateEmail(String email) throws MyThoughtsException {
		String query = "SELECT COUNT(*) FROM person WHERE email = \"" + email + "\"";
		try {
			Statement stmt = con.createStatement();
			stmt.execute(query);
			ResultSet rs = stmt.getResultSet();
			if (rs.next())
				if (rs.getInt(1) > 0)
					return true;
			return false;
		} catch (Exception e) {
			throw new MyThoughtsException("PersonManager.isDuplicateEmail: error in something... " + e);
		}
	}
	
	/**
	 * Checks the database for a specific username duplication
	 * @param username - the username to check
	 * @return true if duplicate, false otherwise
	 * @throws MyThoughtsException
	 */
	public boolean isDuplicateUsername(String username) throws MyThoughtsException {
		String query = "SELECT COUNT(*) FROM person WHERE username = \"" + username + "\"";
		try {
			Statement stmt = con.createStatement();
			stmt.execute(query);
			ResultSet rs = stmt.getResultSet();
			if (rs.next())
				if (rs.getInt(1) > 0)
					return true;
			return false;
		} catch (Exception e) {
			throw new MyThoughtsException("PersonManager.isDuplicateUsername: error in something... " + e);
		}
	}
	
	/**
	 * SELECTs all the Users in the database so admins can
	 * assign/unassign moderators
	 * @return all Users
	 * @throws MyThoughtsException
	 */
	public ArrayList<User> restoreUsers() throws MyThoughtsException {
		String select = "SELECT id, firstname, lastname, username, email, created, isAdmin, isModerator, karma " +
						"FROM person";

		ArrayList<User> users = new ArrayList<User>();
		
		try {
			Statement stmt = con.createStatement();
			stmt.execute(select);
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				if (!rs.getBoolean(7)) {
					User u = new User();
					u.setId(rs.getInt(1));
					u.setFirstname(rs.getString(2));
					u.setLastname(rs.getString(3));
					u.setUsername(rs.getString(4));
					u.setEmail(rs.getString(5));
					u.setCreatedDate(rs.getDate(6));
					u.setModerator(rs.getBoolean(8));
					u.setKarma(rs.getInt(9));
					users.add(u);
				}
			}
		} catch (SQLException e) {
			throw new MyThoughtsException("PersonManager.restoreUsers: failed to restore all Users: " + e.getMessage());
		}
		
		return users;
	}
	
	/**
	 * Handles the INSERT or the UPDATE of a Person object
	 * @param person - the person to INSERT or UPDATE
	 * @return the ID of the person
	 * @throws MyThoughtsException
	 */
	public int save(Person person) throws MyThoughtsException {
		String insert = "INSERT into person " +
					   	"(firstname, lastname, username, password, email, isAdmin, isModerator, karma) " +
					   	"VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		String update = "UPDATE person " +
					   	"SET firstname = ?, lastname = ?, username = ?, password = ?, email = \"?\", isAdmin = ?, isModerator = ?, karma = ? " +
					   	"WHERE id = ?";
		PreparedStatement pstmt;
		int personID = person.getId();

		try {
			if (person.isPersistent())
				pstmt = con.prepareStatement(update);
			else
				pstmt = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

			if (person.passesNullTest()) {
				pstmt.setString(1, person.getFirstname());
				pstmt.setString(2, person.getLastname());
				pstmt.setString(3, person.getUsername());
				pstmt.setString(4, person.getPassword());
				pstmt.setString(5, person.getEmail());

				if (person instanceof Administrator) {
					pstmt.setBoolean(6, true);
					pstmt.setNull(7, 0);
					pstmt.setNull(8, 0);
				} else {
					pstmt.setBoolean(6, false);
					pstmt.setBoolean(7, ((User) person).isModerator());
					pstmt.setInt(8, ((User) person).getKarma());
				}

				if (person.isPersistent())
					pstmt.setInt(9, personID);

				pstmt.executeUpdate();

				if (!person.isPersistent()) {
					ResultSet rs = pstmt.getGeneratedKeys();
	                if(rs.next()) personID = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			throw new MyThoughtsException("PersonManager.save: failed to save Person: " + e.getMessage());
		}
		return personID;
	}
	
	/**
	 * Handles the INSERT or the UPDATE of a Person object
	 * @param person - the person to INSERT or UPDATE
	 * @return the ID of the person
	 * @throws MyThoughtsException
	 */
	public int saveWithoutPassword(Person person) throws MyThoughtsException {
		String insert = "INSERT into person " +
					   	"(firstname, lastname, username, email, isAdmin, isModerator, karma) " +
					   	"VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		String update = "UPDATE person " +
					   	"SET firstname = ?, lastname = ?, username = ?, email = ?, isAdmin = ?, isModerator = ?, karma = ? " +
					   	"WHERE id = ?";
		PreparedStatement pstmt;
		int personID = person.getId();

		try {
			if (person.isPersistent())
				pstmt = con.prepareStatement(update);
			else
				pstmt = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

			if (person.passesNullTest()) {
				pstmt.setString(1, person.getFirstname());
				pstmt.setString(2, person.getLastname());
				pstmt.setString(3, person.getUsername());
				pstmt.setString(4, person.getEmail());

				if (person instanceof Administrator) {
					pstmt.setBoolean(5, true);
					pstmt.setNull(6, 0);
					pstmt.setNull(7, 0);
				} else {
					pstmt.setBoolean(5, false);
					pstmt.setBoolean(6, ((User) person).isModerator());
					pstmt.setInt(7, ((User) person).getKarma());
				}

				if (person.isPersistent())
					pstmt.setInt(9, personID);

				pstmt.executeUpdate();

				if (!person.isPersistent()) {
					ResultSet rs = pstmt.getGeneratedKeys();
	                if(rs.next()) personID = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			throw new MyThoughtsException("PersonManager.save: failed to save Person: " + e.getMessage());
		}
		return personID;
	}

	/**
	 * Attempts to restore a Person object from the database
	 * @param person - the person to SELECT
	 * @return the Person object (Administrator or User)
	 * @throws MyThoughtsException
	 */
	public Person restore(Person person) throws MyThoughtsException {
		String select = "SELECT id, firstname, lastname, username, email, created, isAdmin, isModerator, karma " +
						"FROM person " +
						"WHERE ";
		int conditionLength = 0;

		if (person.isPersistent())
			select += "id = " + person.getId();
		else if (person.getUsername() != null)
			select += "username = \"" + person.getUsername() + "\"";
		else if (person.getEmail() != null)
			select += "email = \"" + person.getEmail() + "\"";
		else {
			if (person.getFirstname() != null) {
				if (conditionLength > 0)
					select += (" AND ");
				select += ("firstname = \"" + person.getFirstname() + "\"");
				conditionLength++;
			}

			if (person.getLastname() != null) {
				if (conditionLength > 0)
					select += (" AND ");
				select += ("lastname = \"" + person.getLastname() + "\"");
				conditionLength++;
			}
		}

		VoteManager vm = new VoteManager(this.con);

		try {
			Statement stmt = con.createStatement();
			stmt.execute(select);
			ResultSet rs = stmt.getResultSet();
			if (rs.next()) {
				if (rs.getBoolean(7)) {
					Administrator a = new Administrator();
					a.setId(rs.getInt(1));
					a.setFirstname(rs.getString(2));
					a.setLastname(rs.getString(3));
					a.setUsername(rs.getString(4));
					a.setEmail(rs.getString(5));
					a.setCreatedDate(rs.getDate(6));
					return a;
				} else {
					User u = new User();
					u.setId(rs.getInt(1));
					u.setFirstname(rs.getString(2));
					u.setLastname(rs.getString(3));
					u.setUsername(rs.getString(4));
					u.setEmail(rs.getString(5));
					u.setCreatedDate(rs.getDate(6));
					u.setModerator(rs.getBoolean(8));
					u.setKarma(rs.getInt(9));
					u = vm.restoreVotes(u);
					return u;
				}
			} else
				return null;
		} catch (SQLException e) {
			throw new MyThoughtsException("PersonManager.restore: failed to restore Person: " + e.getMessage());
		}
	}

	/**
	 * Attempts to restore a Person object from the database
	 * @param username - the username of the Person
	 * @param password - the password of the Person
	 * @return the Person object (Administrator or User)
	 * @throws MyThoughtsException
	 */
	public Person login(String username, String password) throws MyThoughtsException {
		String select = "SELECT id, firstname, lastname, username, email, password, created, isAdmin, isModerator, karma " +
						"FROM person " +
						"WHERE ";

		select += "username = \'" + username + "\' AND password = \'" + password + "\'";

		VoteManager vm = new VoteManager(this.con);

		try {
			Statement stmt = con.createStatement();
			stmt.execute(select);
			ResultSet rs = stmt.getResultSet();
			if (rs.next()) {
				if (rs.getBoolean(8)) {
					Administrator a = new Administrator();
					a.setId(rs.getInt(1));
					a.setFirstname(rs.getString(2));
					a.setLastname(rs.getString(3));
					a.setUsername(rs.getString(4));
					a.setEmail(rs.getString(5));
					a.setCreatedDate(rs.getDate(7));
					return a;
				} else {
					User u = new User();
					u.setId(rs.getInt(1));
					u.setFirstname(rs.getString(2));
					u.setLastname(rs.getString(3));
					u.setUsername(rs.getString(4));
					u.setEmail(rs.getString(5));
					u.setCreatedDate(rs.getDate(7));
					u.setModerator(rs.getBoolean(9));
					u.setKarma(rs.getInt(10));
					u = vm.restoreVotes(u);
					return u;
				}
			} else
				return null;
		} catch (SQLException e) {
			throw new MyThoughtsException("PersonManager.restore: failed to restore Person: " + e.getMessage());
		}
	}

	/**
	 * Attempts to DELETE a Person from the database
	 * @param person - the Person to delete
	 * @throws MyThoughtsException
	 */
	public void delete(Person person) throws MyThoughtsException {
		String delete = "DELETE from person " +
						"WHERE id = ";

		if (!person.isPersistent())
			person = restore(person);

		delete += person.getId();

		try {
			Statement stmt = con.createStatement();
			stmt.execute(delete);
		} catch (SQLException e) {
			throw new MyThoughtsException("PersonManager.delete: failed to delete Person: " + e.getMessage());
		}
	}

}