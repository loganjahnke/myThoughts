package edu.uga.cs.persistence;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;

import edu.uga.cs.MyThoughtsException;
import edu.uga.cs.object.*;

/**
 * PersistenceManager
 * @author Logan Jahnke
 * @created March 22, 2017
 */
public class PersistenceManager {

	PersonManager pm;
	DebateTopicManager dtm;
	DebateCategoryManager dcm;
	CommentManager cm;
	VoteManager vm;

	/**
	 * Manages all possible methods and database interactions
	 * @param con
	 */
	public PersistenceManager(Connection con) {
		this.pm = new PersonManager(con);
		this.dtm = new DebateTopicManager(con);
		this.dcm = new DebateCategoryManager(con);
		this.cm = new CommentManager(con);
		this.vm = new VoteManager(con);
	}

	// PERSON METHODS

	/**
	 * Checks for a duplicate email in the database
	 * @param email - the email to check
	 * @return true if it is in the database, false otherwise
	 * @throws MyThoughtsException
	 */
	public boolean isDuplicateEmail(String email) throws MyThoughtsException {
		return pm.isDuplicateEmail(email);
	}
	
	/**
	 * Checks for a duplicate username in the database
	 * @param username - the username to check
	 * @return true if it is in the database, false otherwise
	 * @throws MyThoughtsException
	 */
	public boolean isDuplicateUsername(String username) throws MyThoughtsException {
		return pm.isDuplicateUsername(username);
	}
	
	/**
	 * INSERTs a Person into the database
	 * @param person - the Person to INSERT
	 * @return the new person id
	 * @throws MyThoughtsException
	 */
	public int savePerson(Person person) throws MyThoughtsException {
		return pm.save(person);
	}

	/**
	 * SELECTs a Person from the database
	 * @param person - the person to SELECT
	 * @return the Person object
	 * @throws MyThoughtsException
	 */
	public Person restorePerson(Person person) throws MyThoughtsException {
		return pm.restore(person);
	}

	/**
	 * SELECTs a Person from the database
	 * @param username - the username of the Person
	 * @param password - the password of the Person
	 * @return the Person object
	 * @throws MyThoughtsException
	 */
	public Person login(String username, String password) throws MyThoughtsException {
		return pm.login(username, password);
	}

	/**
	 * DELETEs a Person from the database
	 * @param person - the person to DELETE
	 * @throws MyThoughtsException
	 */
	public void deletePerson(Person person) throws MyThoughtsException {
		pm.delete(person);
	}

	// DEBATE TOPIC METHODS

	/**
	 * INSERTs a DebateTopic into the database
	 * @param debateTopic - the topic to INSERT
	 * @return the new id
	 * @throws MyThoughtsException
	 */
	public int saveDebateTopic(DebateTopic debateTopic) throws MyThoughtsException {
		return dtm.save(debateTopic);
	}

	/**
	 * SELECTs a DebateTopic from the database
	 * @param debateTopic - the topic to SELECT
	 * @return the DebateTopic object
	 * @throws MyThoughtsException
	 */
	public DebateTopic restoreDebateTopic(DebateTopic debateTopic) throws MyThoughtsException {
		return dtm.restore(debateTopic);
	}

	/**
	 * SELECTs all DebateTopics written by a Person
	 * @param person - the author of the topics
	 * @return all DebateTopics in an ArrayList
	 * @throws MyThoughtsException
	 */
	public ArrayList<DebateTopic> restoreDebateTopic(Person person) throws MyThoughtsException {
		return dtm.restore(person);
	}

	/**
	 * SELECTs all DebateTopics under a certain DebateCategory
	 * @param debateCategory - the category
	 * @return all DebateTopics in an ArrayList
	 * @throws MyThoughtsException
	 */
	public ArrayList<DebateTopic> restoreDebateTopic(DebateCategory debateCategory) throws MyThoughtsException {
		return dtm.restore(debateCategory);
	}

	/**
	 * SELECTs all DebateTopics after a certain Date
	 * @param date - the date
	 * @return all DebateTopics in an ArrayList
	 * @throws MyThoughtsException
	 */
	public ArrayList<DebateTopic> restoreDebateTopic(Date date) throws MyThoughtsException {
		return dtm.restoreAfter(date);
	}

	/**
	 * DELETEs a DebateTopic from the database
	 * @param debateTopic - the debateTopic to delete
	 * @throws MyThoughtsException
	 */
	public void deleteDebateTopic(DebateTopic debateTopic) throws MyThoughtsException {
		dtm.delete(debateTopic);
	}

	// DEBATE CATEGORY METHODS

	/**
	 * INSERTs a topic and category relationship into the database
	 * @param topicId - the topic id
	 * @param categoryId - the category id
	 * @throws MyThoughtsException
	 */
	public void assignTopicIntoCategory(int topicId, int categoryId) throws MyThoughtsException {
		if (!dtm.relationIsInDatabase(categoryId, topicId)) {
			DebateCategory dc = new DebateCategory();
			dc.setId(categoryId);
			ArrayList<DebateCategory> dcList = new ArrayList<DebateCategory>();
			dcList.add(dc);
			dtm.insertCategories(topicId, dcList);
		}
	}
	
	/**
	 * DELETEs a topic and category relationship from the database
	 * @param topicId - the topic id
	 * @param categoryId - the category id
	 * @throws MyThoughtsException
	 */
	public void unassignTopicFromCategory(int topicId, int categoryId) throws MyThoughtsException {
		if (dtm.relationIsInDatabase(categoryId, topicId))
			dtm.removeCategory(topicId, categoryId);
	}
	
	/**
	 * SELECTs all categories from the database that a User 
	 * can assign a topic to.
	 * @return the list of categories
	 * @throws MyThoughtsException
	 */
	public ArrayList<DebateCategory> restoreAllUsableDebateCategories() throws MyThoughtsException {
		return dcm.restoreUsable();
	}
	
	/**
	 * INSERTs a DebateCategory into the database
	 * @param debateCategory - the category to INSERT
	 * @return the new id
	 * @throws MyThoughtsException
	 */
	public int saveDebateCategory(DebateCategory debateCategory) throws MyThoughtsException {
		return dcm.save(debateCategory);
	}

	/**
	 * SELECTs a DebateCategory from the database
	 * @param debateCategory - the category to SELECT
	 * @return the DebateCategory object
	 * @throws MyThoughtsException
	 */
	public DebateCategory restoreDebateCategory(DebateCategory debateCategory) throws MyThoughtsException {
		return dcm.restore(debateCategory);
	}

	/**
	 * SELECTs all DebateCategory objects from the database
	 * @return all categories in an ArrayList
	 * @throws MyThoughtsException
	 */
	public ArrayList<DebateCategory> restoreAllDebateCategories() throws MyThoughtsException {
		return dcm.restoreAll();
	}

	/**
	 * DELETEs a DebateCategory from the database
	 * @param debateCategory - the DebateCategory to DELETE
	 * @throws MyThoughtsException
	 */
	public void deleteDebateCategory(DebateCategory debateCategory) throws MyThoughtsException {
		dcm.delete(debateCategory);
	}

	// COMMENT METHODS

	/**
	 * INSERTs a Comment into the database
	 * @param comment - the topic to INSERT
	 * @return the new id
	 * @throws MyThoughtsException
	 */
	public int saveComment(Comment comment) throws MyThoughtsException {
		return cm.save(comment);
	}

	/**
	 * SELECTs a Comment from the database
	 * @param comment - the topic to SELECT
	 * @return the Comment object
	 * @throws MyThoughtsException
	 */
	public Comment restoreComment(Comment comment) throws MyThoughtsException {
		return cm.restore(comment);
	}

	/**
	 * SELECTs all Comments written by a Person
	 * @param person - the author of the topics
	 * @return all Comments in an ArrayList
	 * @throws MyThoughtsException
	 */
	public ArrayList<Comment> restoreComment(Person person) throws MyThoughtsException {
		return cm.restore(person);
	}

	/**
	 * SELECTs all Comments under a certain DebateCategory
	 * @param debateCategory - the category
	 * @return all Comments in an ArrayList
	 * @throws MyThoughtsException
	 */
	public ArrayList<Comment> restoreComment(DebateTopic debateTopic) throws MyThoughtsException {
		return cm.restore(debateTopic);
	}

	/**
	 * SELECTs all Comments after a certain Date
	 * @param date - the date
	 * @return all Comments in an ArrayList
	 * @throws MyThoughtsException
	 */
	public ArrayList<Comment> restoreComment(Date date) throws MyThoughtsException {
		return cm.restoreAfter(date);
	}

	/**
	 * DELETEs a Comment from the database
	 * @param comment - the comment to delete
	 * @throws MyThoughtsException
	 */
	public void deleteComment(Comment comment) throws MyThoughtsException {
		cm.delete(comment);
	}

	/**
	 * Votes on a Comment in the database
	 * @param person - the person voting
	 * @param comment - the Comment being voted on
	 * @param vote - the vote to cast
	 * @throws MyThoughtsException
	 */
	public User castVote(User user, Comment comment, Boolean vote) throws MyThoughtsException {
		vm.vote(user, comment, vote);
		user.addVote(comment, vote);
		return user;
	}

	/**
	 * Agree/Disagree on a Comment in the database
	 * @param person - the person voting
	 * @param comment - the Comment being voted on
	 * @param agree - the agree to cast
	 * @throws MyThoughtsException
	 */
	public User castAgree(User user, Comment comment, Boolean agree) throws MyThoughtsException {
		vm.agree(user, comment, agree);
		user.addAgree(comment, agree);
		return user;
	}

	/**
	 * Votes on a DebateTopic in the database
	 * @param person - the person voting
	 * @param topic - the DebateTopic being voted on
	 * @param vote - the vote to cast
	 * @throws MyThoughtsException
	 */
	public User castVote(User user, DebateTopic topic, Boolean vote) throws MyThoughtsException {
		vm.vote(user, topic, vote);
		user.addVote(topic, vote);
		return user;
	}

	/**
	 * Agree/Disagree on a DebateTopic in the database
	 * @param person - the person voting
	 * @param topic - the DebateTopic being voted on
	 * @param agree - the agree to cast
	 * @throws MyThoughtsException
	 */
	public User castAgree(User user, DebateTopic topic, Boolean agree) throws MyThoughtsException {
		vm.agree(user, topic, agree);
		user.addAgree(topic, agree);
		return user;
	}

}