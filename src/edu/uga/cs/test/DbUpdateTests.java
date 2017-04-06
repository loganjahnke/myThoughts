package edu.uga.cs.test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;

import edu.uga.cs.MyThoughtsException;
import edu.uga.cs.object.Administrator;
import edu.uga.cs.object.Comment;
import edu.uga.cs.object.DebateCategory;
import edu.uga.cs.object.DebateTopic;
import edu.uga.cs.object.User;
import edu.uga.cs.persistence.DbAccessInterface;
import edu.uga.cs.persistence.PersistenceManager;

public class DbUpdateTests {

	public static void main(String[] args) {
		Connection con = DbAccessInterface.connect();
		PersistenceManager pm = new PersistenceManager(con);

		// UPDATE administrator

		System.out.print("Updating Administrator: ");
		try {
			Administrator admin = new Administrator("Jerry",
												"Jones",
												"jones",
												"password",
												"jerry@cowboys.com");
			admin = (Administrator) pm.restorePerson(admin);
			admin.setPassword("indigo");
			admin.setId(pm.savePerson(admin));
		} catch (MyThoughtsException e) {
			System.out.println("FAILED");
			e.printStackTrace();
			DbAccessInterface.disconnect(con);
			return;
		}
		System.out.println("success");

		// UPDATE users

		System.out.print("Updating Users: ");
		User user1 = new User("Phillip",
				  "Rodgers",
				  "rodgers",
				  "password",
				  "rodgers@phillip.com",
				  false,
				  0,
				  null,
				  null);
		User user2 = new User("Darth",
				  "Vader",
				  "vader",
				  "password",
				  "darth.vader@deathstar.com",
				  true,
				  82,
				  null,
				  null);
		try {
			user1 = (User) pm.restorePerson(user1);
			user1.setPassword("indigo");
			user2 = (User) pm.restorePerson(user2);
			user2.setPassword("indigo");
			User user3 = new User("Simon",
								  "Sayz",
								  "sayz",
								  "password",
								  "simon@sayz.com",
								  false,
								  6,
								  null,
								  null);
			user3 = (User) pm.restorePerson(user3);
			user3.setPassword("indigo");
			user1.setId(pm.savePerson(user1));
			user2.setId(pm.savePerson(user2));
			user3.setId(pm.savePerson(user3));
		} catch (MyThoughtsException e) {
			System.out.println("FAILED");
			e.printStackTrace();
			DbAccessInterface.disconnect(con);
			return;
		}
		System.out.println("success");

		// UPDATE DEBATE CATEGORIES

		System.out.print("Updating Debate Categories: ");
		DebateCategory dc2 = new DebateCategory("Religion",
												"When I admire the wonders of a sunset or the beauty of the moon, my soul expands in the worship of the creator. - Mahatma Gandhi",
												"book",
												"blue");
		try {
			dc2 = pm.restoreDebateCategory(dc2);
			dc2.setColor("green");
			dc2.setId(pm.saveDebateCategory(dc2));
		} catch (MyThoughtsException e) {
			System.out.println("FAILED");
			e.printStackTrace();
			DbAccessInterface.disconnect(con);
			return;
		}
		System.out.println("success");

		// UPDATE DEBATE TOPICS

		System.out.print("Updating Debate Topics: ");
		DebateTopic dt1 = new DebateTopic("Women should be allowed to Pastor a church.",
				  "",
				  0,
				  0,
				  0,
				  user2,
				  new Date(),
				  new ArrayList<DebateCategory>());
		try {
			dt1.addCategories(dc2);
			dt1 = pm.restoreDebateTopic(dt1);
			dt1.incrementVote();
			DebateTopic dt2 = new DebateTopic("The North Dakota Pipeline should not be built.",
											  "",
											  0,
											  0,
											  0,
											  user2,
											  new Date(),
											  new ArrayList<DebateCategory>());
			dt2 = pm.restoreDebateTopic(dt2);
			dt2.incrementAgree();
			dt1.setId(pm.saveDebateTopic(dt1));
			dt2.setId(pm.saveDebateTopic(dt2));
		} catch (MyThoughtsException e) {
			System.out.println("FAILED");
			e.printStackTrace();
			DbAccessInterface.disconnect(con);
			return;
		}
		System.out.println("success");

		// UPDATE COMMENTS

		System.out.print("Updating Comments: ");
		try {
			Comment c1 = new Comment("Absurd!",
					 				 "The reason Pastors are men is laid out clearly in the Bible.",
					 				 user1,
					 				 new Date(),
					 				 null,
					 				 dt1);
			c1 = pm.restoreComment(c1);
			System.out.println(c1.getId());
			c1.setSubject("Unbearable!");
			Comment c2 = new Comment("Humans have grown past this...",
							 		 "Women should be allowed to pastor!",
							 		 user2,
							 		 new Date(),
							 		 c1,
							 		 dt1);
			c2 = pm.restoreComment(c2);
			c2.setArgument("WOMEN!!!");
			c1.setId(pm.saveComment(c1));
			c2.setId(pm.saveComment(c2));
		} catch (MyThoughtsException e) {
			System.out.println("FAILED");
			e.printStackTrace();
			DbAccessInterface.disconnect(con);
			return;
		}
		System.out.println("success");

		System.out.println("Testing Completed!");
		return;
	}

}
