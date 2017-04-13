package edu.uga.cs.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.uga.cs.MyThoughtsException;
import edu.uga.cs.object.*;

/**
 * VoteManager
 * @author Logan Jahnke
 * @created March 29, 2017
 */
public class VoteManager {

	Connection con;

	public VoteManager(Connection con) {
		this.con = con;
	}

	/**
	 * Checks to see if the vote is already in the database
	 * @param person - the person who voted
	 * @param comment - the comment being voted on
	 * @return true if it is in the database, false otherwise
	 * @throws MyThoughtsException
	 */
	public boolean isInDatabase(Person person, Comment comment) throws MyThoughtsException {
		String select = "SELECT COUNT(*) from comment_vote " +
						"WHERE user_id = " + person.getId() +
						" AND comment_id = " + comment.getId();

		try {
			Statement stmt = con.createStatement();
			stmt.execute(select);
			ResultSet rs = stmt.getResultSet();
			while (rs.next())
				return rs.getInt(1) == 1;
			return false;
		} catch (SQLException e) {
			throw new MyThoughtsException("VoteManager.isInDatabase: failed to see if Vote exists: " + e.getMessage());
		}
	}

	/**
	 * Checks to see if the vote is already in the database
	 * @param person - the person who voted
	 * @param topic - the debate topic being voted on
	 * @return true if it is in the database, false otherwise
	 * @throws MyThoughtsException
	 */
	public boolean isInDatabase(Person person, DebateTopic topic) throws MyThoughtsException {
		String select = "SELECT COUNT(*) from topic_vote " +
						"WHERE user_id = " + person.getId() +
						" AND topic_id = " + topic.getId();

		try {
			Statement stmt = con.createStatement();
			stmt.execute(select);
			ResultSet rs = stmt.getResultSet();
			while (rs.next())
				return rs.getInt(1) == 1;
			return false;
		} catch (SQLException e) {
			throw new MyThoughtsException("VoteManager.isInDatabase: failed to see if Vote exists: " + e.getMessage());
		}
	}

	/**
	 * Casts a vote on a comment
	 * @param person - the person voting
	 * @param comment - the comment to vote on
	 * @param upvote - if true, upvote - if false, downvote
	 * @throws MyThoughtsException
	 */
	public void vote(Person person, Comment comment, boolean upvote) throws MyThoughtsException {
		String insert = "INSERT into comment_vote " +
					   	"(user_id, comment_id, upvote, downvote) " +
					   	"VALUES (?, ?, ?, ?)";
		String update = "UPDATE comment_vote " +
					   	"SET upvote = ?, downvote = ? " +
					   	"WHERE user_id = ? AND comment_id = ?";
		PreparedStatement pstmt;
		PersistenceManager pm = new PersistenceManager(this.con);

		try {
			if (!person.isPersistent())
				person = pm.restorePerson(person);
			if (!comment.isPersistent())
				comment = pm.restoreComment(comment);

			boolean isInDatabase = isInDatabase(person, comment);

			// Check for UPDATE vs INSERT
			if (isInDatabase) {
				pstmt = con.prepareStatement(update);
				pstmt.setBoolean(1, upvote);
				pstmt.setBoolean(2, !upvote);
				pstmt.setInt(3, person.getId());
				pstmt.setInt(4, comment.getId());
			}
			else {
				pstmt = con.prepareStatement(insert);
				pstmt.setInt(1, person.getId());
				pstmt.setInt(2, comment.getId());
				pstmt.setBoolean(3, upvote);
				pstmt.setBoolean(4, !upvote);
			}

			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MyThoughtsException("VoteManager.save: failed to save Vote: " + e.getMessage());
		}
	}

	/**
	 * Casts a vote on a topic
	 * @param person - the person voting
	 * @param topic - the topic to vote on
	 * @param upvote - if true, upvote - if false, downvote
	 * @throws MyThoughtsException
	 */
	public void vote(Person person, DebateTopic topic, boolean upvote) throws MyThoughtsException {
		String insert = "INSERT into topic_vote " +
					   	"(user_id, topic_id, upvote, downvote) " +
					   	"VALUES (?, ?, ?, ?)";
		String update = "UPDATE topic_vote " +
					   	"SET upvote = ?, downvote = ? " +
					   	"WHERE user_id = ? AND topic_id = ?";
		PreparedStatement pstmt;
		PersistenceManager pm = new PersistenceManager(this.con);

		try {
			if (!person.isPersistent())
				person = pm.restorePerson(person);
			if (!topic.isPersistent())
				topic = pm.restoreDebateTopic(topic);

			boolean isInDatabase = isInDatabase(person, topic);

			// Check for UPDATE vs INSERT
			if (isInDatabase) {
				pstmt = con.prepareStatement(update);
				pstmt.setBoolean(1, upvote);
				pstmt.setBoolean(2, !upvote);
				pstmt.setInt(3, person.getId());
				pstmt.setInt(4, topic.getId());
			}
			else {
				pstmt = con.prepareStatement(insert);
				pstmt.setInt(1, person.getId());
				pstmt.setInt(2, topic.getId());
				pstmt.setBoolean(3, upvote);
				pstmt.setBoolean(4, !upvote);
			}

			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MyThoughtsException("VoteManager.save: failed to save Vote: " + e.getMessage());
		}
	}

	/**
	 * Casts a agree/disagree on a comment
	 * @param person - the person voting
	 * @param comment - the comment to agree on
	 * @param agree - if true, agree - if false, disagree
	 * @throws MyThoughtsException
	 */
	public void agree(Person person, Comment comment, boolean agrees) throws MyThoughtsException {
		String insert = "INSERT into comment_vote " +
					   	"(user_id, comment_id, agrees, disagrees) " +
					   	"VALUES (?, ?, ?, ?)";
		String update = "UPDATE comment_vote " +
					   	"SET agrees = ?, disagrees = ? " +
					   	"WHERE user_id = ? AND comment_id = ?";
		PreparedStatement pstmt;
		PersistenceManager pm = new PersistenceManager(this.con);

		try {
			if (!person.isPersistent())
				person = pm.restorePerson(person);
			if (!comment.isPersistent())
				comment = pm.restoreComment(comment);

			boolean isInDatabase = isInDatabase(person, comment);

			// Check for UPDATE vs INSERT
			if (isInDatabase) {
				pstmt = con.prepareStatement(update);
				pstmt.setBoolean(1, agrees);
				pstmt.setBoolean(2, !agrees);
				pstmt.setInt(3, person.getId());
				pstmt.setInt(4, comment.getId());
			}
			else {
				pstmt = con.prepareStatement(insert);
				pstmt.setInt(1, person.getId());
				pstmt.setInt(2, comment.getId());
				pstmt.setBoolean(3, agrees);
				pstmt.setBoolean(4, !agrees);
			}

			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MyThoughtsException("VoteManager.save: failed to save Vote: " + e.getMessage());
		}
	}

	/**
	 * Casts a agree/disagree on a topic
	 * @param person - the person voting
	 * @param topic - the topic to vote on
	 * @param agrees - if true, agree - if false, disagree
	 * @throws MyThoughtsException
	 */
	public void agree(Person person, DebateTopic topic, boolean agrees) throws MyThoughtsException {
		String insert = "INSERT into topic_vote " +
					   	"(user_id, topic_id, agrees, disagrees) " +
					   	"VALUES (?, ?, ?, ?)";
		String update = "UPDATE topic_vote " +
					   	"SET agrees = ?, disagrees = ? " +
					   	"WHERE user_id = ? AND topic_id = ?";
		PreparedStatement pstmt;
		PersistenceManager pm = new PersistenceManager(this.con);

		try {
			if (!person.isPersistent())
				person = pm.restorePerson(person);
			if (!topic.isPersistent())
				topic = pm.restoreDebateTopic(topic);

			boolean isInDatabase = isInDatabase(person, topic);

			// Check for UPDATE vs INSERT
			if (isInDatabase) {
				pstmt = con.prepareStatement(update);
				pstmt.setBoolean(1, agrees);
				pstmt.setBoolean(2, !agrees);
				pstmt.setInt(3, person.getId());
				pstmt.setInt(4, topic.getId());
			}
			else {
				pstmt = con.prepareStatement(insert);
				pstmt.setInt(1, person.getId());
				pstmt.setInt(2, topic.getId());
				pstmt.setBoolean(3, agrees);
				pstmt.setBoolean(4, !agrees);
			}

			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MyThoughtsException("VoteManager.save: failed to save Vote: " + e.getMessage());
		}
	}

	/**
	 * Restores the upvotes and agrees for a given User
	 * @param person - the User to change
	 * @return the User
	 * @throws MyThoughtsException
	 */
	public User restoreVotes(Person person) throws MyThoughtsException {
		String select = "SELECT cv.upvote, cv.downvote, cv.agrees, cv.disagrees, " +
						"c.id " +
						"FROM person p " +
						"LEFT OUTER JOIN comment_vote cv " +
							"ON p.id = cv.user_id " +
						"LEFT OUTER JOIN comment c " +
							"ON cv.comment_id = c.id " +
						"WHERE ";
		PersistenceManager pm = new PersistenceManager(this.con);

		User user = new User();

		try {
			if (!person.isPersistent())
				person = pm.restorePerson(person);
			select += "p.id = " + person.getId();
			user = (User) person;

			Statement stmt = con.createStatement();
			stmt.execute(select);
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				// Get Comment object and insert into Person's like/upvote
				Comment c = pm.restoreComment(new Comment(rs.getInt(5)));
				if (rs.getObject(1, Boolean.class) == true)
					user.addVote(c, true);
				else if (rs.getObject(2, Boolean.class) == true)
					user.addVote(c, false);
				if (rs.getObject(3, Boolean.class) == true)
					user.addAgree(c, true);
				else if (rs.getObject(4, Boolean.class) == true)
					user.addAgree(c, false);
			}
		} catch (SQLException e) {
			throw new MyThoughtsException("PersonManager.restore: failed to restore Person: " + e.getMessage());
		}

		select = "SELECT tv.upvote, tv.downvote, tv.agrees, tv.disagrees, " +
				 "dt.id " +
				 "FROM person p " +
				 "LEFT OUTER JOIN topic_vote tv " +
				 	"ON p.id = tv.user_id " +
				 "LEFT OUTER JOIN debate_topic dt " +
					"ON tv.topic_id = dt.id " +
				 "WHERE ";
		try {
			select += "p.id = " + person.getId();
			Statement stmt = con.createStatement();
			stmt.execute(select);
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				// Get DebateTopic object and insert into Person's like/upvote
				DebateTopic dt = pm.restoreDebateTopic(new DebateTopic(rs.getInt(5)));
				if (rs.getObject(1, Boolean.class) == true)
					user.addVote(dt, true);
				else if (rs.getObject(2, Boolean.class) == true)
					user.addVote(dt, false);
				if (rs.getObject(3, Boolean.class) == true)
					user.addAgree(dt, true);
				else if (rs.getObject(4, Boolean.class) == true)
					user.addAgree(dt, false);
			}
		} catch (SQLException e) {
			throw new MyThoughtsException("PersonManager.restore: failed to restore Person: " + e.getMessage());
		}
		return user;
	}

	/**
	 * Restores the number of votes for a given debate topic id
	 * @param id - the id to acquire
	 * @param dt - the debate topic to edit - inout
	 * @return the DebateTopic
	 * @throws MyThoughtsException
	 */
	public DebateTopic getTopicData(DebateTopic dt) throws MyThoughtsException {
		String select = "SELECT COUNT(tv1.upvote), COUNT(tv2.downvote) " +
						"FROM debate_topic dt " +
						"LEFT OUTER JOIN topic_vote tv1 " +
							"ON dt.id = tv1.topic_id AND tv1.upvote = true " +
						"LEFT OUTER JOIN topic_vote tv2 " +
							"ON dt.id = tv2.topic_id AND tv2.downvote = true " +
						"WHERE dt.id = " + dt.getId();

		try {
			Statement stmt = con.createStatement();
			stmt.execute(select);
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				dt.setVote(rs.getInt(1) - rs.getInt(2));
			}
		} catch (SQLException e) {
			throw new MyThoughtsException("PersonManager.restore: failed to restore Person: " + e.getMessage());
		}

		select = "SELECT COUNT(tv1.agrees), COUNT(tv2.disagrees) " +
						"FROM debate_topic dt " +
						"LEFT OUTER JOIN topic_vote tv1 " +
							"ON dt.id = tv1.topic_id AND tv1.agrees = true " +
						"LEFT OUTER JOIN topic_vote tv2 " +
							"ON dt.id = tv2.topic_id AND tv2.disagrees = true " +
						"WHERE dt.id = " + dt.getId();

		try {
			Statement stmt = con.createStatement();
			stmt.execute(select);
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				dt.setAgrees(rs.getInt(1));
				dt.setDisagrees(rs.getInt(2));
			}
		} catch (SQLException e) {
			throw new MyThoughtsException("VoteManager.restore: failed to retreive vote information: " + e.getMessage());
		}
		return dt;
	}

	/**
	 * Restores the number of votes for a given comment id
	 * @param id - the id to acquire
	 * @param c - the comment to edit - inout
	 * @return the Comment
	 * @throws MyThoughtsException
	 */
	public Comment getCommentData(Comment c) throws MyThoughtsException {
		String select = "SELECT COUNT(cv1.upvote), COUNT(cv2.downvote) " +
						"FROM comment c " +
						"LEFT OUTER JOIN comment_vote cv1 " +
							"ON c.id = cv1.topic_id AND cv1.upvote = true " +
						"LEFT OUTER JOIN comment_vote cv2 " +
							"ON c.id = cv2.topic_id AND cv2.downvote = true " +
						"WHERE c.id = " + c.getId();

		try {
			Statement stmt = con.createStatement();
			stmt.execute(select);
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				c.setVote(rs.getInt(1) - rs.getInt(2));
			}
		} catch (SQLException e) {
			throw new MyThoughtsException("PersonManager.restore: failed to restore Person: " + e.getMessage());
		}

		select = "SELECT COUNT(cv1.agrees), COUNT(cv2.disagrees) " +
						"FROM comment c " +
						"LEFT OUTER JOIN comment_vote cv1 " +
							"ON c.id = cv1.topic_id AND cv1.agrees = true " +
						"LEFT OUTER JOIN comment_vote cv2 " +
							"ON c.id = cv2.topic_id AND cv2.disagrees = true " +
						"WHERE c.id = " + c.getId();

		try {
			Statement stmt = con.createStatement();
			stmt.execute(select);
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				c.setAgrees(rs.getInt(1));
				c.setDisagrees(rs.getInt(2));
			}
		} catch (SQLException e) {
			throw new MyThoughtsException("VoteManager.restore: failed to retreive vote information: " + e.getMessage());
		}
		return c;
	}

}