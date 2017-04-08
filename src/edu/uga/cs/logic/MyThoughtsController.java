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

	public int createCategory(DebateCategory dc) throws MyThoughtsException {
		return pm.saveDebateCategory(dc);
	}

}
