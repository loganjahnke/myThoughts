package edu.uga.cs.logic;

import java.sql.Connection;
import java.util.ArrayList;

import edu.uga.cs.MyThoughtsException;
import edu.uga.cs.object.*;
import edu.uga.cs.persistence.*;

public class MyThoughtsController {

	private Connection con;
	private PersistenceManager pm;

	public MyThoughtsController() {
		this.con = DbAccessInterface.connect();
		this.pm = new PersistenceManager(this.con);
	}

	public ArrayList<DebateCategory> getCategories() throws MyThoughtsException {
		return pm.restoreAllDebateCategories();
	}

	public int saveCategory(DebateCategory dc) throws MyThoughtsException {
		return pm.saveDebateCategory(dc);
	}
	
	public void deleteCategory(DebateCategory dc) throws MyThoughtsException {
		pm.deleteDebateCategory(dc);
	}

	public DebateCategory getCategory(String name) throws MyThoughtsException {
		DebateCategory dc = new DebateCategory();
		dc.setName(name);
		return pm.restoreDebateCategory(dc);
	}

	public User getUser(String username) throws MyThoughtsException {
		User user = new User();
		user.setUsername(username);
		return (User) pm.restorePerson(user);
	}

	public int saveTopic(DebateTopic dt) throws MyThoughtsException {
		return pm.saveDebateTopic(dt);
	}

	public ArrayList<DebateCategory> getUsableCategories() throws MyThoughtsException {
		return pm.restoreAllUsableDebateCategories();
	}
	
	public ArrayList<Comment> getCommentsForTopic(DebateTopic topic) throws MyThoughtsException {
		return pm.restoreComment(topic);
	}
	
	public ArrayList<Comment> sortAgreeComment(ArrayList<Comment> comments) {
		ArrayList<Comment> arrlist = new ArrayList<Comment>();
		for(Comment c : comments) {
			if(c.doesAgreeWithTopic(c.getDebateTopic().getId())) {
				arrlist.add(c);
			}
		}
		return arrlist;
	}
	
	public ArrayList<Comment> sortDisagreeComment(ArrayList<Comment> comments) {
		ArrayList<Comment> arrlist = new ArrayList<Comment>();
		for(Comment c : comments) {
			if(!c.doesAgreeWithTopic(c.getDebateTopic().getId())) {
				arrlist.add(c);
			}
		}
		return arrlist;
	}
}
