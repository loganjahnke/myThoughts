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
 * CommentManager
 * @author Logan Jahnke
 * @editor Logan Jahnke
 * @created March 23, 2017
 * @updated March 23, 2017
 */
public class CommentManager {

	Connection con;

	public CommentManager(Connection con) {
		this.con = con;
	}

	/**
	 * Handles the INSERT or the UPDATE of a Comment object
	 * @param comment - the comment to INSERT or UPDATE
	 * @return the ID of the comment
	 * @throws MyThoughtsException
	 */
	public int save(Comment comment) throws MyThoughtsException {
		String insert = "INSERT into comment " +
					   	"(subject, argument, user_id, topic_id, parent_id) " +
					   	"VALUES (?, ?, ?, ?, ?)";
		String update = "UPDATE comment " +
					   	"SET subject = ?, argument = ?, user_id = ?, topic_id = ?, parent_id = ? " +
					   	"WHERE id = ?";
		PreparedStatement pstmt;
		int commentID = -1;
		PersistenceManager pm = new PersistenceManager(con);

		try {
			// See if the Comment is already in the database
			Comment dt = restore(comment);
			if (dt != null)
				comment = dt;
			commentID = comment.getId();

			// Check for UPDATE vs INSERT
			if (comment.isPersistent())
				pstmt = con.prepareStatement(update);
			else
				pstmt = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

			// Input ?'s
			if (comment.passesNullTest()) {
				pstmt.setString(1, comment.getSubject());
				pstmt.setString(2, comment.getArgument());

				// Retrieve user id
				if (!comment.getUser().isPersistent())
					comment.setUser((User) pm.restorePerson(comment.getUser()));
				pstmt.setInt(3, comment.getUser().getId());

				// Retrieve topic id
				if (!comment.getDebateTopic().isPersistent())
					comment.setDebateTopic(pm.restoreDebateTopic(comment.getDebateTopic()));
				pstmt.setInt(4, comment.getDebateTopic().getId());

				// Retrieve user id
				if (comment.getParentComment() != null) {
					if (!comment.getUser().isPersistent())
						comment.setUser((User) pm.restorePerson(comment.getUser()));
					pstmt.setInt(5, comment.getUser().getId());
				} else {
					pstmt.setNull(5, 0);
				}

				if (comment.isPersistent())
					pstmt.setInt(6, commentID);

				pstmt.executeUpdate();

				if (!comment.isPersistent()) {
					ResultSet rs = pstmt.getGeneratedKeys();
	                if(rs.next()) commentID = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			throw new MyThoughtsException("CommentManager.save: failed to save Comment: " + e.getMessage());
		}
		return commentID;
	}

	/**
	 * Attempts to restore a Comment object from the database
	 * @param comment - the comment to SELECT
	 * @return the Comment object
	 * @throws MyThoughtsException
	 */
	public Comment restore(Comment comment) throws MyThoughtsException {
		String select = "SELECT c.subject, c.argument, c.created, c.parent_id, " +
						"p.id, " +
						"dt.id " +
						"FROM comment c " +
						"JOIN person p " +
							"ON c.user_id = p.id " +
						"JOIN debate_topic dt " +
							"ON c.topic_id = dt.id " +
						"WHERE";
		int conditionLength = 0;

		if (comment.isPersistent())
			select += " c.id = " + comment.getId();
		else {
			if (comment.getSubject() != null) {
				select += " c.subject = \'" + comment.getSubject() + "\'";
				conditionLength++;
			}

			if (comment.getArgument() != null) {
				if (conditionLength > 0)
					select += " AND";
				select += " c.argument = \'" + comment.getArgument() + "\'";
				conditionLength++;
			}

			if (comment.getUser() != null && comment.getUser().isPersistent()) {
				if (conditionLength > 0)
					select += " AND";
				select += " c.user_id = " + comment.getUser().getId();
				conditionLength++;
			}

			if (comment.getDebateTopic() != null && comment.getDebateTopic().isPersistent()) {
				if (conditionLength > 0)
					select += " AND";
				select += " c.debate_id = " + comment.getDebateTopic().getId();
				conditionLength++;
			}

			if (comment.getParentComment() != null && comment.getParentComment().isPersistent()) {
				if (conditionLength > 0)
					select += " AND";
				select += " c.parent_id = " + comment.getParentComment().getId();
				conditionLength++;
			}
		}

		try {
			Statement stmt = con.createStatement();
			stmt.execute(select);
			ResultSet rs = stmt.getResultSet();
			ArrayList<Comment> commentList = retrieve(rs);
			if (commentList.size() > 0)
				return commentList.get(0);
			else
				return new Comment();
		} catch (SQLException e) {
			throw new MyThoughtsException("CommentManager.restore: failed to restore Comment: " + e.getMessage());
		}
	}

	/**
	 * Attempts to restore Comments by a certain Person from the database
	 * @param person - the person to SELECT from
	 * @return the Comment object
	 * @throws MyThoughtsException
	 */
	public ArrayList<Comment> restore(Person person) throws MyThoughtsException {
		String select = "SELECT c.subject, c.argument, c.created, c.parent_id, " +
						"p.id, " +
						"dt.id " +
						"FROM comment c " +
						"JOIN person p " +
							"ON c.user_id = p.id " +
						"JOIN debate_topic dt " +
							"ON c.topic_id = dt.id " +
						"WHERE";
		int conditionLength = 0;

		if (person.isPersistent())
			select += " p.id = " + person.getId();
		else if (person.getUsername() != null)
			select += " p.username = \'" + person.getUsername() + "\'";
		else if (person.getEmail() != null)
			select += " p.email = \'" + person.getEmail() + "\'";
		else {
			if (person.getFirstname() != null) {
				if (conditionLength > 0)
					select += (" AND");
				select += (" p.firstname = \'" + person.getFirstname() + "\'");
				conditionLength++;
			}

			if (person.getLastname() != null) {
				if (conditionLength > 0)
					select += (" AND");
				select += (" p.lastname = \'" + person.getLastname() + "\'");
				conditionLength++;
			}

			if (person.getPassword() != null) {
				if (conditionLength > 0)
					select += (" AND");
				select += (" p.password = \'" + person.getPassword() + "\'");
				conditionLength++;
			}
		}

		try {
			Statement stmt = con.createStatement();
			stmt.execute(select);
			ResultSet rs = stmt.getResultSet();
			return retrieve(rs);
		} catch (SQLException e) {
			throw new MyThoughtsException("CommentManager.restore: failed to restore Comment: " + e.getMessage());
		}
	}

	/**
	 * Attempts to restore DebateTopics by a certain Date from the database
	 * @param date - the date to SELECT after
	 * @return the Comment object
	 * @throws MyThoughtsException
	 */
	public ArrayList<Comment> restoreAfter(Date date) throws MyThoughtsException {
		String select = "SELECT c.subject, c.argument, c.created, c.parent_id, " +
						"p.id, " +
						"dt.id " +
						"FROM comment c " +
						"JOIN person p " +
							"ON c.user_id = p.id " +
						"JOIN debate_topic dt " +
							"ON c.topic_id = dt.id " +
						"WHERE";

		select += " comment.created > " + date;

		try {
			Statement stmt = con.createStatement();
			stmt.execute(select);
			ResultSet rs = stmt.getResultSet();
			return retrieve(rs);
		} catch (SQLException e) {
			throw new MyThoughtsException("CommentManager.restore: failed to restore Comment: " + e.getMessage());
		}
	}

	/**
	 * Attempts to restore Comment by a certain DebateTopic from the database
	 * @param debateTopic - the DebateTopic to SELECT from
	 * @return the Comment object
	 * @throws MyThoughtsException
	 */
	public ArrayList<Comment> restore(DebateTopic debateTopic) throws MyThoughtsException {
		String select = "SELECT c.subject, c.argument, c.created, c.parent_id, " +
						"p.id, " +
						"dt.id " +
						"FROM comment c " +
						"JOIN person p " +
							"ON c.user_id = p.id " +
						"JOIN debate_topic dt " +
							"ON c.topic_id = dt.id " +
						"WHERE";
		int conditionLength = 0;

		if (debateTopic.isPersistent())
			select += " dt.id = " + debateTopic.getId();
		else {
			if (debateTopic.getTitle() != null) {
				select += " dt.title = \'" + debateTopic.getTitle() + "\'";
				conditionLength++;
			}

			if (debateTopic.getDescription() != null) {
				if (conditionLength > 0)
					select += " AND";
				select += " dt.description = \'" + debateTopic.getDescription() + "\'";
				conditionLength++;
			}

			if (conditionLength > 0)
				select += " AND";
			select += " dt.vote = " + debateTopic.getVote();
			conditionLength++;

			if (debateTopic.getAgrees() > -1) {
				if (conditionLength > 0)
					select += " AND";
				select += " dt.agrees = " + debateTopic.getAgrees();
				conditionLength++;
			}

			if (debateTopic.getDisagrees() > -1) {
				if (conditionLength > 0)
					select += " AND";
				select += " dt.disagrees = " + debateTopic.getDisagrees();
				conditionLength++;
			}
		}

		try {
			Statement stmt = con.createStatement();
			stmt.execute(select);
			ResultSet rs = stmt.getResultSet();
			return retrieve(rs);
		} catch (SQLException e) {
			throw new MyThoughtsException("CommentManager.restore: failed to restore Comment: " + e.getMessage());
		}
	}

	/**
	 * Acquires all Comments from a given ResultSet
	 * @param rs - the ResultSet to go by
	 * @return all Comments in an ArrayList
	 * @throws MyThoughtsException
	 */
	public ArrayList<Comment> retrieve(ResultSet rs) throws MyThoughtsException {
		try {
			PersistenceManager pm = new PersistenceManager(con);
			ArrayList<Comment> commentList = new ArrayList<Comment>();
			while (rs.next()) {
				Comment comment = new Comment();
				comment.setSubject(rs.getString(1));
				comment.setArgument(rs.getString(2));
				comment.setCreatedDate(rs.getDate(3));

				User user = new User();
				user.setId(rs.getInt(5));
				comment.setUser((User) pm.restorePerson(user));

				DebateTopic topic = new DebateTopic();
				topic.setId(rs.getInt(6));
				comment.setDebateTopic(pm.restoreDebateTopic(topic));

				if (rs.getInt(4) > 0) {
					Comment parent = new Comment();
					comment.setId(rs.getInt(4));
					comment.setParentComment(restore(parent));
				}

				commentList.add(comment);
			}
			return commentList;
		} catch (SQLException e) {
			throw new MyThoughtsException("CommentManager.retrieve: failed to retrieve Comment(s) from ResultSet: " + e.getMessage());
		}
	}

	/**
	 * Attempts to DELETE a Comment from the database
	 * @param comment - the Comment to delete
	 * @throws MyThoughtsException
	 */
	public void delete(Comment comment) throws MyThoughtsException {
		String delete = "DELETE from debate_topic " +
						"WHERE id = ";

		if (!comment.isPersistent())
			comment = restore(comment);

		delete += comment.getId();

		try {
			Statement stmt = con.createStatement();
			stmt.execute(delete);
		} catch (SQLException e) {
			throw new MyThoughtsException("CommentManager.delete: failed to delete Comment: " + e.getMessage());
		}
	}

}