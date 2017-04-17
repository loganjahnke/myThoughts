package edu.uga.cs.logic;

import java.sql.Connection;

import edu.uga.cs.MyThoughtsException;
import edu.uga.cs.object.*;
import edu.uga.cs.persistence.*;

public class UserController {

	private Connection con;
	private PersistenceManager pm;

	public UserController() {
		this.con = DbAccessInterface.connect();
		this.pm = new PersistenceManager(this.con);
	}

	public User upvoteTopic(User user, int topicId) throws MyThoughtsException {
		DebateTopic dt = new DebateTopic(topicId);
		dt = pm.restoreDebateTopic(dt);
		pm.castVote(user, dt, true);
		user.addUpvote(dt);
		return user;
	}

	public User downvoteTopic(User user, int topicId) throws MyThoughtsException {
		DebateTopic dt = new DebateTopic(topicId);
		dt = pm.restoreDebateTopic(dt);
		pm.castVote(user, dt, false);
		user.addDownvote(dt);
		return user;
	}

	public User agreeWithTopic(User user, int topicId) throws MyThoughtsException {
		DebateTopic dt = new DebateTopic(topicId);
		dt = pm.restoreDebateTopic(dt);
		pm.castAgree(user, dt, true);
		user.addAgree(dt);
		return user;
	}

	public User disagreeWithTopic(User user, int topicId) throws MyThoughtsException {
		DebateTopic dt = new DebateTopic(topicId);
		dt = pm.restoreDebateTopic(dt);
		pm.castAgree(user, dt, false);
		user.addDisagree(dt);
		return user;
	}

}
