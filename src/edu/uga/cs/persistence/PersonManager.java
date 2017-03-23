package edu.uga.cs.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.uga.cs.MyThoughtsException;
import edu.uga.cs.object.*;

public class PersonManager {

	Connection con;

	public PersonManager(Connection con) {
		this.con = con;
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
					   	"SET firstname = ?, lastname = ?, username = ?, password = ?, email = ?, isAdmin = ?, isModerator = ?, karma = ? " +
					   	"WHERE id = ?";
		PreparedStatement pstmt;
		int personID = person.getId();

		try {
			Person p = restore(person);
			if (p != null)
				person = p;
			personID = person.getId();
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
	 * Attempts to restore a Person object from the database
	 * @param person - the person to SELECT
	 * @return the Person object (Administrator or User)
	 * @throws MyThoughtsException
	 */
	public Person restore(Person person) throws MyThoughtsException {
		String select = "SELECT id, firstname, lastname, username, email, password, created, isAdmin, isModerator, karma " +
						"FROM person " +
						"WHERE ";
		int conditionLength = 0;

		if (person.isPersistent())
			select += "id = " + person.getId();
		else if (person.getUsername() != null)
			select += "username = \'" + person.getUsername() + "\'";
		else if (person.getEmail() != null)
			select += "email = \'" + person.getEmail() + "\'";
		else {
			if (person.getFirstname() != null) {
				if (conditionLength > 0)
					select += (" AND ");
				select += ("firstname = \'" + person.getFirstname() + "\'");
				conditionLength++;
			}

			if (person.getLastname() != null) {
				if (conditionLength > 0)
					select += (" AND ");
				select += ("lastname = \'" + person.getLastname() + "\'");
				conditionLength++;
			}

			if (person.getPassword() != null) {
				if (conditionLength > 0)
					select += (" AND ");
				select += ("password = \'" + person.getPassword() + "\'");
				conditionLength++;
			}
		}

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
					a.setPassword(rs.getString(6));
					a.setCreatedDate(rs.getDate(7));
					return a;
				} else {
					User u = new User();
					u.setId(rs.getInt(1));
					u.setFirstname(rs.getString(2));
					u.setLastname(rs.getString(3));
					u.setUsername(rs.getString(4));
					u.setEmail(rs.getString(5));
					u.setPassword(rs.getString(6));
					u.setCreatedDate(rs.getDate(7));
					u.setModerator(rs.getBoolean(9));
					u.setKarma(rs.getInt(10));
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