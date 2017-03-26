package edu.uga.cs.persistence;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.uga.cs.MyThoughtsException;
import edu.uga.cs.object.*;

/**
 * DebateCategoryManager
 * @author Logan Jahnke
 * @editor Logan Jahnke
 * @created March 19, 2017
 * @updated March 23, 2017
 */
public class DebateCategoryManager {

	Connection con;

	public DebateCategoryManager(Connection con) {
		this.con = con;
	}

	/**
	 * Handles the INSERT or the UPDATE of a DebateCategory object
	 * @param debateCategory - the debateCategory to INSERT or UPDATE
	 * @return the ID of the debateCategory
	 * @throws MyThoughtsException
	 */
	public int save(DebateCategory debateCategory) throws MyThoughtsException {
		String insert = "INSERT into debate_category " +
					   	"(name, description) " +
					   	"VALUES (?, ?)";
		String update = "UPDATE debate_category " +
					   	"SET name = ?, description = ? " +
					   	"WHERE id = ?";
		PreparedStatement pstmt;
		int debateCategoryID = debateCategory.getId();

		try {
			if (debateCategory.isPersistent())
				pstmt = con.prepareStatement(update);
			else
				pstmt = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

			if (debateCategory.passesNullTest()) {
				pstmt.setString(1, debateCategory.getName());
				pstmt.setString(2, debateCategory.getDescription());

				if (debateCategory.isPersistent())
					pstmt.setInt(3, debateCategoryID);

				pstmt.executeUpdate();

				if (!debateCategory.isPersistent()) {
					ResultSet rs = pstmt.getGeneratedKeys();
	                if(rs.next()) debateCategoryID = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			throw new MyThoughtsException("DebateCategoryManager.save: failed to save DebateCategory: " + e.getMessage());
		}
		return debateCategoryID;
	}

	/**
	 * Attempts to restore a DebateCategory object from the database
	 * @param debateCategory - the debateCategory to SELECT
	 * @return the DebateCategory object
	 * @throws MyThoughtsException
	 */
	public DebateCategory restore(DebateCategory debateCategory) throws MyThoughtsException {
		String select = "SELECT id, name, description " +
						"FROM debate_category " +
						"WHERE ";

		if (debateCategory.isPersistent())
			select += "id = " + debateCategory.getId();
		else if (debateCategory.getName() != null)
			select += "name = \"" + debateCategory.getName() + "\"";
		else if (debateCategory.getDescription() != null)
			select += "description = \"" + debateCategory.getDescription() + "\"";
		else
			throw new MyThoughtsException("DebateCategoryManager: cannot SELECT specific category without any information given!");

		try {
			Statement stmt = con.createStatement();
			stmt.execute(select);
			ResultSet rs = stmt.getResultSet();
			if (rs.next()) {
				DebateCategory dc = new DebateCategory();
				dc.setId(rs.getInt(1));
				dc.setName(rs.getString(2));
				dc.setDescription(rs.getString(3));
				return dc;
			} else
				return null;
		} catch (SQLException e) {
			throw new MyThoughtsException("DebateCategoryManager.restore: failed to restore DebateCategory: " + e.getMessage());
		}
	}

	/**
	 * Attempts to restore all DebateCategory objects from the database
	 * @return the DebateCategory objects
	 * @throws MyThoughtsException
	 */
	public ArrayList<DebateCategory> restoreAll() throws MyThoughtsException {
		String select = "SELECT id, name, description " +
						"FROM debate_category";

		ArrayList<DebateCategory> dcList = new ArrayList<DebateCategory>();

		try {
			Statement stmt = con.createStatement();
			stmt.execute(select);
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				DebateCategory dc = new DebateCategory();
				dc.setId(rs.getInt(1));
				dc.setName(rs.getString(2));
				dc.setDescription(rs.getString(3));
				dcList.add(dc);
			}
		} catch (SQLException e) {
			throw new MyThoughtsException("DebateCategoryManager.restore: failed to restore DebateCategory: " + e.getMessage());
		}
		return dcList;
	}

	/**
	 * Attempts to DELETE a DebateCategory from the database
	 * @param debateCategory - the DebateCategory to delete
	 * @throws MyThoughtsException
	 */
	public void delete(DebateCategory debateCategory) throws MyThoughtsException {
		String delete = "DELETE from debate_category " +
						"WHERE id = ";

		if (!debateCategory.isPersistent())
			debateCategory = restore(debateCategory);

		delete += debateCategory.getId();

		try {
			Statement stmt = con.createStatement();
			stmt.execute(delete);
		} catch (SQLException e) {
			throw new MyThoughtsException("DebateCategoryManager.delete: failed to delete DebateCategory: " + e.getMessage());
		}
	}

}