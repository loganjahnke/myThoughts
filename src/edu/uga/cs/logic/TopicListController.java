package edu.uga.cs.logic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;

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

	public ArrayList<DebateTopic> getRecentTopics() throws MyThoughtsException {
		long DAY_IN_MS = 1000 * 60 * 60 * 24;
		Date date = new Date(System.currentTimeMillis() - (7 * DAY_IN_MS));
		return pm.restoreDebateTopic(date);
	}

	public DebateTopic getMostRecentFeatured() throws MyThoughtsException {
		ArrayList<DebateTopic> dtList = getTopics(new DebateCategory("Featured", "", "", ""));
		if (dtList.size() > 0)
			return dtList.get(0);
		else
			return new DebateTopic();
	}

}
