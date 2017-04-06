package edu.uga.cs.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.uga.cs.MyThoughtsException;
import edu.uga.cs.object.*;

/**
 * DebateTopicManager
 * @author Logan Jahnke
 * @editor Logan Jahnke
 * @created March 19, 2017
 * @updated April 5, 2017
 */
public class DebateTopicManager {

	Connection con;

	public DebateTopicManager(Connection con) {
		this.con = con;
	}

	/**
	 * Handles the INSERT or the UPDATE of a DebateTopic object
	 * @param debateTopic - the debateTopic to INSERT or UPDATE
	 * @return the ID of the debateTopic
	 * @throws MyThoughtsException
	 */
	public int save(DebateTopic debateTopic) throws MyThoughtsException {
		String insert = "INSERT into debate_topic " +
					   	"(title, description, user_id) " +
					   	"VALUES (?, ?, ?)";
		String update = "UPDATE debate_topic " +
					   	"SET title = ?, description = ?, user_id = ? " +
					   	"WHERE id = ?";
		PreparedStatement pstmt;
		int debateTopicID = -1;
		PersonManager pManager = new PersonManager(con);

		try {
			if (debateTopic.isPersistent())
				pstmt = con.prepareStatement(update);
			else
				pstmt = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

			if (debateTopic.passesNullTest()) {
				pstmt.setString(1, debateTopic.getTitle());
				pstmt.setString(2, debateTopic.getDescription());

				if (!debateTopic.getUser().isPersistent())
					debateTopic.setUser((User) pManager.restore(debateTopic.getUser()));

				pstmt.setInt(3, debateTopic.getUser().getId());

				if (debateTopic.isPersistent())
					pstmt.setInt(4, debateTopicID);

				pstmt.executeUpdate();

				if (!debateTopic.isPersistent()) {
					ResultSet rs = pstmt.getGeneratedKeys();
	                if(rs.next()) debateTopicID = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			throw new MyThoughtsException("DebateTopicManager.save: failed to save DebateTopic: " + e.getMessage());
		}
		if (debateTopicID != -1)
			insertCategories(debateTopicID, debateTopic.getCategories());
		return debateTopicID;
	}

	/**
	 * Handles the INSERT or the UPDATE of a DebateTopic object
	 * @param id - the ID of the debateTopic
	 * @param debateTopic - the debateTopic to INSERT or UPDATE
	 * @return the ID of the debateTopic
	 * @throws MyThoughtsException
	 */
	public void insertCategories(int id, ArrayList<DebateCategory> debateCategories) throws MyThoughtsException {
		String insert = "INSERT into topic_category " +
					   	"(category_id, topic_id) " +
					   	"VALUES (?, ?)";
		PreparedStatement pstmt;
		DebateCategoryManager dcManager = new DebateCategoryManager(con);

		try {
			pstmt = con.prepareStatement(insert);
			for (DebateCategory dc : debateCategories) {
				if (!dc.isPersistent())
					dc = dcManager.restore(dc);
				if (!relationIsInDatabase(dc.getId(), id)) {
					pstmt.setInt(1, dc.getId());
					pstmt.setInt(2, id);
					pstmt.executeUpdate();
				}
			}
		} catch (SQLException e) {
			throw new MyThoughtsException("DebateTopicManager.insertCategories: failed to save many-to-many relationship: " + e.getMessage());
		}
	}

	/**
	 * Sees if the many-to-many relationship already exists
	 * @param categoryID - the ID of the category
	 * @param topicID - the ID of the topic
	 * @return true if it exists in database
	 * @throws MyThoughtsException
	 */
	public boolean relationIsInDatabase(int categoryID, int topicID) throws MyThoughtsException {
		String select = "SELECT COUNT(*) FROM topic_category WHERE ";

		select += "category_id = " + categoryID;
		select += " AND topic_id = " + topicID;

		try {
			Statement stmt = con.createStatement();
			stmt.execute(select);
			ResultSet rs = stmt.getResultSet();
			if (rs.next())
				return rs.getInt(1) > 0;
			else
				return false;
		} catch (SQLException e) {
			throw new MyThoughtsException("DebateTopicManager.insertCategories: failed to save many-to-many relationship: " + e.getMessage());
		}
	}

	/**
	 * Attempts to restore a DebateTopic object from the database
	 * @param debateTopic - the debateTopic to SELECT
	 * @return the DebateTopic object
	 * @throws MyThoughtsException
	 */
	public DebateTopic restore(DebateTopic debateTopic) throws MyThoughtsException {
		String select = "SELECT dt.id, dt.title, dt.description, dt.created, " +
						"p.id, p.firstname, p.lastname, p.username, p.password, p.email, p.created, p.isModerator, p.karma, " +
						"dc.id, dc.name, dc.description, dc.icon, dc.color, " +
						"COUNT(tv1.upvote), COUNT(tv2.downvote), COUNT(tv3.agrees), COUNT(tv4.disagrees) " + // 19, 20, 21, 22
						"FROM debate_topic dt " +
						"JOIN person p " +
							"ON dt.user_id = p.id " +
						"JOIN topic_category tc " +
							"ON dt.id = tc.topic_id " +
						"JOIN debate_category dc " +
							"ON tc.category_id = dc.id " +
						"LEFT OUTER JOIN topic_vote tv1 " +
							"ON dt.id = tv1.topic_id AND tv1.upvote = true " +
						"LEFT OUTER JOIN topic_vote tv2 " +
							"ON dt.id = tv2.topic_id AND tv2.downvote = true " +
						"LEFT OUTER JOIN topic_vote tv3 " +
							"ON dt.id = tv3.topic_id AND tv3.agrees = true " +
						"LEFT OUTER JOIN topic_vote tv4 " +
							"ON dt.id = tv4.topic_id AND tv4.disagrees = true " +
						"WHERE";
		int conditionLength = 0;

		if (debateTopic.isPersistent())
			select += " dt.id = " + debateTopic.getId();
		else {
			if (debateTopic.getTitle() != null) {
				select += " dt.title = \"" + debateTopic.getTitle() + "\"";
				conditionLength++;
			}

			if (debateTopic.getDescription() != null) {
				if (conditionLength > 0)
					select += " AND";
				select += " dt.description = \"" + debateTopic.getDescription() + "\"";
				conditionLength++;
			}

			if (debateTopic.getUser() != null && !debateTopic.getUser().isPersistent()) {
				PersonManager pm = new PersonManager(con);
				debateTopic.setUser((User) pm.restore(debateTopic.getUser()));
				if (conditionLength > 0)
					select += " AND";
				select += " dt.user_id = " + debateTopic.getUser().getId();
			}
		}

		try {
			Statement stmt = con.createStatement();
			stmt.execute(select);
			ResultSet rs = stmt.getResultSet();
			ArrayList<DebateTopic> dtList = retrieve(rs);
			if (dtList.size() > 0)
				return dtList.get(0);
			else
				return null;
		} catch (SQLException e) {
			throw new MyThoughtsException("DebateTopicManager.restore: failed to restore DebateTopic: " + e.getMessage());
		}
	}

	/**
	 * Attempts to restore DebateTopics by a certain Person from the database
	 * @param person - the person to SELECT from
	 * @return the DebateTopic object
	 * @throws MyThoughtsException
	 */
	public ArrayList<DebateTopic> restore(Person person) throws MyThoughtsException {
		String select = "SELECT dt.id, dt.title, dt.description, dt.created, " +
						"p.id, p.firstname, p.lastname, p.username, p.password, p.email, p.created, p.isModerator, p.karma, " +
						"dc.id, dc.name, dc.description, dc.icon, dc.color, " +
						"COUNT(tv1.upvote), COUNT(tv2.downvote), COUNT(tv3.agrees), COUNT(tv4.disagrees) " + // 17, 18, 19, 20
						"FROM debate_topic dt " +
						"JOIN person p " +
							"ON dt.user_id = p.id " +
						"JOIN topic_category tc " +
							"ON dt.id = tc.topic_id " +
						"JOIN debate_category dc " +
							"ON tc.category_id = dc.id " +
						"LEFT OUTER JOIN topic_vote tv1 " +
							"ON dt.id = tv1.topic_id AND tv1.upvote = true " +
						"LEFT OUTER JOIN topic_vote tv2 " +
							"ON dt.id = tv2.topic_id AND tv2.downvote = true " +
						"LEFT OUTER JOIN topic_vote tv3 " +
							"ON dt.id = tv3.topic_id AND tv3.agrees = true " +
						"LEFT OUTER JOIN topic_vote tv4 " +
							"ON dt.id = tv4.topic_id AND tv4.disagrees = true " +
						"WHERE";
		int conditionLength = 0;

		if (person.isPersistent())
			select += " p.id = " + person.getId();
		else if (person.getUsername() != null)
			select += " p.username = \"" + person.getUsername() + "\"";
		else if (person.getEmail() != null)
			select += " p.email = \"" + person.getEmail() + "\"";
		else {
			if (person.getFirstname() != null) {
				if (conditionLength > 0)
					select += (" AND");
				select += (" p.firstname = \"" + person.getFirstname() + "\"");
				conditionLength++;
			}

			if (person.getLastname() != null) {
				if (conditionLength > 0)
					select += (" AND");
				select += (" p.lastname = \"" + person.getLastname() + "\"");
				conditionLength++;
			}

			if (person.getPassword() != null) {
				if (conditionLength > 0)
					select += (" AND");
				select += (" p.password = \"" + person.getPassword() + "\"");
				conditionLength++;
			}
		}

		try {
			Statement stmt = con.createStatement();
			stmt.execute(select);
			ResultSet rs = stmt.getResultSet();
			return retrieve(rs);
		} catch (SQLException e) {
			throw new MyThoughtsException("DebateTopicManager.restore: failed to restore DebateTopic: " + e.getMessage());
		}
	}

	/**
	 * Attempts to restore DebateTopics by a certain Date from the database
	 * @param date - the date to SELECT after
	 * @return the DebateTopic object
	 * @throws MyThoughtsException
	 */
	public ArrayList<DebateTopic> restoreAfter(Date date) throws MyThoughtsException {
		String select = "SELECT dt.id, dt.title, dt.description, dt.created, " +
						"p.id, p.firstname, p.lastname, p.username, p.password, p.email, p.created, p.isModerator, p.karma, " +
						"dc.id, dc.name, dc.description, dc.icon, dc.color, " +
						"COUNT(tv1.upvote), COUNT(tv2.downvote), COUNT(tv3.agrees), COUNT(tv4.disagrees) " + // 17, 18, 19, 20
						"FROM debate_topic dt " +
						"JOIN person p " +
							"ON dt.user_id = p.id " +
						"JOIN topic_category tc " +
							"ON dt.id = tc.topic_id " +
						"JOIN debate_category dc " +
							"ON tc.category_id = dc.id " +
						"LEFT OUTER JOIN topic_vote tv1 " +
							"ON dt.id = tv1.topic_id AND tv1.upvote = true " +
						"LEFT OUTER JOIN topic_vote tv2 " +
							"ON dt.id = tv2.topic_id AND tv2.downvote = true " +
						"LEFT OUTER JOIN topic_vote tv3 " +
							"ON dt.id = tv3.topic_id AND tv3.agrees = true " +
						"LEFT OUTER JOIN topic_vote tv4 " +
							"ON dt.id = tv4.topic_id AND tv4.disagrees = true " +
						"WHERE";

		select += " dt.created > " + date;

		try {
			Statement stmt = con.createStatement();
			stmt.execute(select);
			ResultSet rs = stmt.getResultSet();
			return retrieve(rs);
		} catch (SQLException e) {
			throw new MyThoughtsException("DebateTopicManager.restore: failed to restore DebateTopic: " + e.getMessage());
		}
	}

	/**
	 * Attempts to restore DebateTopics by a certain DebateCategory from the database
	 * @param debateCategory - the DebateCategory to SELECT from
	 * @return the DebateTopic object
	 * @throws MyThoughtsException
	 */
	public ArrayList<DebateTopic> restore(DebateCategory debateCategory) throws MyThoughtsException {
		String select = "SELECT dt.id, dt.title, dt.description, dt.created, " + // 1, 2, 3, 4
						"p.id, p.firstname, p.lastname, p.username, p.password, p.email, p.created, p.isModerator, p.karma, " + // 5, 6, 7, 8, 9, 10, 11, 12, 13
						"dc.id, dc.name, dc.description, dc.icon, dc.color, " + // 14, 15, 16
						"COUNT(tv1.upvote), COUNT(tv2.downvote), COUNT(tv3.agrees), COUNT(tv4.disagrees) " + // 17, 18, 19, 20
						"FROM debate_topic dt " +
						"JOIN person p " +
							"ON dt.user_id = p.id " +
						"JOIN topic_category tc " +
							"ON dt.id = tc.topic_id " +
						"JOIN debate_category dc " +
							"ON tc.category_id = dc.id " +
						"LEFT OUTER JOIN topic_vote tv1 " +
							"ON dt.id = tv1.topic_id AND tv1.upvote = true " +
						"LEFT OUTER JOIN topic_vote tv2 " +
							"ON dt.id = tv2.topic_id AND tv2.downvote = true " +
						"LEFT OUTER JOIN topic_vote tv3 " +
							"ON dt.id = tv3.topic_id AND tv3.agrees = true " +
						"LEFT OUTER JOIN topic_vote tv4 " +
							"ON dt.id = tv4.topic_id AND tv4.disagrees = true " +
						"WHERE";

		if (debateCategory.isPersistent())
			select += " dc.id = " + debateCategory.getId();
		else if (debateCategory.getName() != null)
			select += " dc.name = \"" + debateCategory.getName() + "\"";
		else if (debateCategory.getDescription() != null)
			select += " dc.description = \"" + debateCategory.getDescription() + "\"";
		else
			throw new MyThoughtsException("DebateTopicManager.restore: cannot restore topic without proper category");

		try {
			Statement stmt = con.createStatement();
			stmt.execute(select);
			ResultSet rs = stmt.getResultSet();
			return retrieve(rs);
		} catch (SQLException e) {
			throw new MyThoughtsException("DebateTopicManager.restore: failed to restore DebateTopic: " + e.getMessage());
		}
	}

	/**
	 * Acquires all DebateTopics from a given ResultSet
	 * @param rs - the ResultSet to go by
	 * @return all Debate Topics in an ArrayList
	 * @throws MyThoughtsException
	 */
	public ArrayList<DebateTopic> retrieve(ResultSet rs) throws MyThoughtsException {
		try {
			int id = -1, currentIndex = -1;
			ArrayList<DebateTopic> dtList = new ArrayList<DebateTopic>();
			while (rs.next()) {
				if (id != rs.getInt(1)) {
					// Create Debate Topic
					DebateTopic dt = new DebateTopic();
					dt.setId(rs.getInt(1));
					dt.setTitle(rs.getString(2));
					dt.setDescription(rs.getString(3));
					dt.setCreatedDate(rs.getDate(4));
					dt.setVote(rs.getInt(19) - rs.getInt(20));
					dt.setAgrees(rs.getInt(21));
					dt.setDisagrees(rs.getInt(22));

					// Add User
					User u = new User();
					u.setId(rs.getInt(5));
					u.setFirstname(rs.getString(6));
					u.setLastname(rs.getString(7));
					u.setUsername(rs.getString(8));
					u.setEmail(rs.getString(9));
					u.setPassword(rs.getString(10));
					u.setCreatedDate(rs.getDate(11));
					u.setModerator(rs.getBoolean(12));
					u.setKarma(rs.getInt(13));
					dt.setUser(u);

					// Set ID and index
					id = dt.getId();
					currentIndex++;
					dtList.add(dt);
				} else {
					DebateCategory dc = new DebateCategory();
					dc.setId(rs.getInt(14));
					dc.setName(rs.getString(15));
					dc.setDescription(rs.getString(16));
					dc.setIcon(rs.getString(17));
					dc.setColor(rs.getString(18));
					dtList.set(currentIndex, dtList.get(currentIndex).addCategory(dc));
				}
			}
			return dtList;
		} catch (SQLException e) {
			throw new MyThoughtsException("DebateTopicManager.retrieve: failed to restore DebateTopic(s): " + e.getMessage());
		}
	}

	/**
	 * Attempts to DELETE a DebateTopic from the database
	 * @param debateTopic - the DebateTopic to delete
	 * @throws MyThoughtsException
	 */
	public void delete(DebateTopic debateTopic) throws MyThoughtsException {
		String delete = "DELETE from debate_topic " +
						"WHERE id = ";

		if (!debateTopic.isPersistent())
			debateTopic = restore(debateTopic);

		if (debateTopic != null) {
			delete += debateTopic.getId();

			try {
				Statement stmt = con.createStatement();
				stmt.execute(delete);
			} catch (SQLException e) {
				throw new MyThoughtsException("DebateTopicManager.delete: failed to delete DebateTopic: " + e.getMessage());
			}
		}
	}

}