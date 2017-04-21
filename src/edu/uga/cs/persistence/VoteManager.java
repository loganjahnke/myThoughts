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

	public static final int COMMENT_UPVOTE = 1;
	public static final int COMMENT_DOWNVOTE = -3;
	public static final int TOPIC_UPVOTE = 3;
	public static final int TOPIC_DOWNVOTE = -5;
	public static final int USER_DOWNVOTE_PENALTY = -1;

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
			if (person instanceof User)
				modifyKarma(person, comment, upvote, isInDatabase);

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
			if (person instanceof User)
				modifyKarma(person, topic, upvote, isInDatabase);

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
					user.addUpvote(c);
				else if (rs.getObject(2, Boolean.class) == true)
					user.addDownvote(c);
				if (rs.getObject(3, Boolean.class) == true)
					user.addAgree(c);
				else if (rs.getObject(4, Boolean.class) == true)
					user.addDisagree(c);
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
					user.addUpvote(dt);
				else if (rs.getObject(2, Boolean.class) == true)
					user.addDownvote(dt);
				if (rs.getObject(3, Boolean.class) == true)
					user.addAgree(dt);
				else if (rs.getObject(4, Boolean.class) == true)
					user.addDisagree(dt);
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
		String selectUpvotes = "SELECT COUNT(*) FROM topic_vote WHERE upvote = 1 AND topic_id = " + dt.getId();
		String selectDownvotes = "SELECT COUNT(*) FROM topic_vote WHERE downvote = 1 AND topic_id = " + dt.getId();

		try {
			int upvotes = 0;
			int downvotes = 0;
			Statement stmt = con.createStatement();
			stmt.execute(selectUpvotes);
			ResultSet rs = stmt.getResultSet();
			if (rs.next()) {
				upvotes = rs.getInt(1);
			}

			stmt.execute(selectDownvotes);
			rs = stmt.getResultSet();
			if (rs.next()) {
				downvotes = rs.getInt(1);
			}

			dt.setVote(upvotes - downvotes);
		} catch (SQLException e) {
			throw new MyThoughtsException("PersonManager.restore: failed to restore Person: " + e.getMessage());
		}

		String selectAgrees = "SELECT COUNT(*) FROM topic_vote WHERE agrees = 1 AND topic_id = " + dt.getId();
		String selectDisagrees = "SELECT COUNT(*) FROM topic_vote WHERE disagrees = 1 AND topic_id = " + dt.getId();

		try {
			int agrees = 0;
			int disagrees = 0;
			Statement stmt = con.createStatement();
			stmt.execute(selectAgrees);
			ResultSet rs = stmt.getResultSet();
			if (rs.next()) {
				agrees = rs.getInt(1);
			}

			stmt.execute(selectDisagrees);
			rs = stmt.getResultSet();
			if (rs.next()) {
				disagrees = rs.getInt(1);
			}

			dt.setAgrees(agrees);
			dt.setDisagrees(disagrees);
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

	/**
	 * Modifies the karma of the both users for upvoting
	 * or downvoting a topic or comment
	 * @param person - the person voting
	 * @param comment - the comment to vote on
	 * @param upvote - if true, upvote - if false, downvote
	 * @param change - if true, user is changing their vote
	 * @throws MyThoughtsException
	 */
	public void modifyKarma(Person person, Likeable likeable, boolean upvote, boolean change) throws MyThoughtsException {
		String update = "UPDATE person SET karma = ? WHERE id = ?";
		PreparedStatement pstmt;

		try {
			// Update voter's karma
			User user = (User) person;
			int newKarma = user.getKarma();
			if (change) {
				if (upvote)
					newKarma -= USER_DOWNVOTE_PENALTY;
				else
					newKarma += USER_DOWNVOTE_PENALTY;
			} else {
				if (!upvote)
					newKarma += USER_DOWNVOTE_PENALTY;
			}

			pstmt = con.prepareStatement(update);
			pstmt.setInt(1, newKarma);
			pstmt.setInt(2, user.getId());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MyThoughtsException("VoteManager.modifyKarma: failed to update voter's karma: " + e.getMessage());
		}

		update = "UPDATE person SET karma = ? WHERE id = ?";

		try {
			// Update receiver's karma
			User user = likeable.getUser();
			int newKarma = user.getKarma();
			if (change) {
				if (upvote)
					newKarma += COMMENT_UPVOTE - COMMENT_DOWNVOTE;
				else
					newKarma += COMMENT_DOWNVOTE - COMMENT_UPVOTE;
			} else {
				if (upvote)
					newKarma += COMMENT_UPVOTE;
				else
					newKarma += COMMENT_DOWNVOTE;
			}

			pstmt = con.prepareStatement(update);
			pstmt.setInt(1, newKarma);
			pstmt.setInt(2, user.getId());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MyThoughtsException("VoteManager.modifyKarma: failed to update receiver's karma: " + e.getMessage());
		}
	}

}