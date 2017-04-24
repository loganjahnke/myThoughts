package edu.uga.cs.logic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import edu.uga.cs.MyThoughtsException;
import edu.uga.cs.object.*;
import edu.uga.cs.persistence.*;

/**
 * TopicListController
 * @author Logan Jahnke
 * @editor Logan Jahnke
 * @created April 10, 2017
 * @updated April 10, 2017
 */
public class TopicListController {

	private Connection con;
	private PersistenceManager pm;

	public TopicListController() {
		this.con = DbAccessInterface.connect();
		this.pm = new PersistenceManager(this.con);
	}

	public ArrayList<DebateTopic> getTopics(DebateCategory dc) throws MyThoughtsException {
		return pm.restoreDebateTopic(dc);
	}

	public ArrayList<DebateTopic> getTopics(User user) throws MyThoughtsException {
		return pm.restoreDebateTopic(user);
	}
	
	
	public ArrayList<DebateTopic> getCommentedTopics(User user) throws MyThoughtsException{
		return pm.restoreCommentedDebateTopic(user);
	}

	public ArrayList<DebateTopic> getRecentTopics() throws MyThoughtsException {
		long DAY_IN_MS = 1000 * 60 * 60 * 24;
		Date date = new Date(System.currentTimeMillis() - (7 * DAY_IN_MS));
		return pm.restoreDebateTopic(date);
	}

	public DebateTopic getFeatured() throws MyThoughtsException {
		ArrayList<DebateTopic> dtList = getTopics(new DebateCategory("Featured", "", "", ""));
		if (dtList.size() > 0)
			return dtList.get(ThreadLocalRandom.current().nextInt(0, dtList.size()));
		else
			return new DebateTopic();
	}

	public ArrayList<DebateTopic> getTrendingTopics() throws MyThoughtsException {
		long DAY_IN_MS = 1000 * 60 * 60 * 24;
		Date date = new Date(System.currentTimeMillis() - (7 * DAY_IN_MS));
		return pm.restoreTrendingDebateTopic(date);
	}
	
	public DebateTopic getTopic(int id) throws MyThoughtsException {
		DebateTopic dt = new DebateTopic();
		dt.setId(id);
		return pm.restoreDebateTopic(dt);
	}

	public ArrayList<DebateTopic> getTopicsContaining(String query) throws MyThoughtsException {
		return pm.restoreDebateTopicsContaining(query);
	}

}
