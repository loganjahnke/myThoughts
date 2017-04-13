package edu.uga.cs.logic;

import java.sql.Connection;

import edu.uga.cs.MyThoughtsException;
import edu.uga.cs.object.*;
import edu.uga.cs.persistence.*;

public class AdministratorController {

	private Connection con;
	private PersistenceManager pm;

	public AdministratorController() {
		this.con = DbAccessInterface.connect();
		this.pm = new PersistenceManager(this.con);
	}

	public int saveCategory(DebateCategory dc) throws MyThoughtsException {
		return pm.saveDebateCategory(dc);
	}

	public void deleteCategory(DebateCategory dc) throws MyThoughtsException {
		pm.deleteDebateCategory(dc);
	}

	public void featureTopic(int id) throws MyThoughtsException {
		pm.assignTopicIntoCategory(id, 1);
	}
	
	public void unfeatureTopic(int id) throws MyThoughtsException {
		pm.unassignTopicFromCategory(id, 1);
	}

	public void deleteTopic(int id) throws MyThoughtsException {
		DebateTopic dt = new DebateTopic();
		dt.setId(id);
		pm.deleteDebateTopic(dt);
	}

}
