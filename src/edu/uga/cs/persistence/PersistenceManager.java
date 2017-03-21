package edu.uga.cs.persistence;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;

import edu.uga.cs.MyThoughtsException;
import edu.uga.cs.object.*;

public class PersistenceManager {

	PersonManager pm;
	DebateTopicManager dtm;
	DebateCategoryManager dcm;

	public PersistenceManager(Connection con) {
		this.pm = new PersonManager(con);
		this.dtm = new DebateTopicManager(con);
		this.dcm = new DebateCategoryManager(con);
	}

	// PERSON METHODS

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

}