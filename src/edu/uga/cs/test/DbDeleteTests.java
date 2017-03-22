package edu.uga.cs.test;

import edu.uga.cs.persistence.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;

import edu.uga.cs.MyThoughtsException;
import edu.uga.cs.object.*;

public class DbDeleteTests {

	public static void main() {
		Connection con = DbAccessInterface.connect();
		PersistenceManager pm = new PersistenceManager(con);

		// INSERT administrator

		System.out.print("Deleting Administrator: ");
		Administrator admin = new Administrator("Jerry",
												"Jones",
												"jones",
												"password",
												"jerry@cowboys.com");
		try {
			pm.deletePerson(admin);
		} catch (MyThoughtsException e) {
			System.out.println("FAILED");
			e.printStackTrace();
			DbAccessInterface.disconnect(con);
			return;
		}
		System.out.println("success");

		// INSERT users

		System.out.print("Deleting Users: ");
		User user1 = new User("Phillip",
							  "Rodgers",
							  "rodgers",
							  "password",
							  "rodgers@phillip.com",
							  false,
							  0);
		User user2 = new User("Darth",
							  "Vader",
							  "vader",
							  "password",
							  "darth.vader@deathstar.com",
							  true,
							  82);
		User user3 = new User("Simon",
							  "Sayz",
							  "sayz",
							  "password",
							  "simon@sayz.com",
							  false,
							  6);
		try {
			pm.deletePerson(user1);
			pm.deletePerson(user2);
			pm.deletePerson(user3);
		} catch (MyThoughtsException e) {
			System.out.println("FAILED");
			e.printStackTrace();
			DbAccessInterface.disconnect(con);
			return;
		}
		System.out.println("success");

		// INSERT DEBATE CATEGORIES

		System.out.print("Deleting Debate Categories: ");
		DebateCategory dc1 = new DebateCategory("Politics",
												"I must study politics and war that my sons may have liberty to study mathematics and philosophy. - John Adams");
		DebateCategory dc2 = new DebateCategory("Religion",
												"When I admire the wonders of a sunset or the beauty of the moon, my soul expands in the worship of the creator. - Mahatma Gandhi");
		DebateCategory dc3 = new DebateCategory("Environmental",
												"Never doubt that a small group of thoughtful, committed citizens can change the world; indeed, it's the only thing that ever has. - Margaret Mead");
		DebateCategory dc4 = new DebateCategory("Music",
												"Where words fail, music speaks. - Hans Anderson");
		try {
			pm.deleteDebateCategory(dc1);
			pm.deleteDebateCategory(dc2);
			pm.deleteDebateCategory(dc3);
			pm.deleteDebateCategory(dc4);
		} catch (MyThoughtsException e) {
			System.out.println("FAILED");
			e.printStackTrace();
			DbAccessInterface.disconnect(con);
			return;
		}
		System.out.println("success");

		// INSERT DEBATE TOPICS

		System.out.print("Deleting Debate Topics: ");
		DebateTopic dt1 = new DebateTopic("Women should be allowed to Pastor a church.",
										  "",
										  0,
										  0,
										  0,
										  user2,
										  new Date(),
										  new ArrayList<DebateCategory>());
		dt1.addCategories(dc2);
		DebateTopic dt2 = new DebateTopic("The North Dakota Pipeline should not be built.",
										  "",
										  0,
										  0,
										  0,
										  user2,
										  new Date(),
										  new ArrayList<DebateCategory>());
		dt2.addCategories(dc1, dc3);
		DebateTopic dt3 = new DebateTopic("The border wall will be good for Mexico-United States relations.",
										  "",
										  0,
										  0,
										  0,
										  user3,
										  new Date(),
										  new ArrayList<DebateCategory>());
		dt3.addCategories(dc1);
		DebateTopic dt4 = new DebateTopic("The Beatles are the best rock band.",
										  "",
										  0,
										  0,
										  0,
										  user1,
										  new Date(),
										  new ArrayList<DebateCategory>());
		dt4.addCategories(dc4);
		try {
			pm.deleteDebateTopic(dt1);
			pm.deleteDebateTopic(dt2);
			pm.deleteDebateTopic(dt3);
			pm.deleteDebateTopic(dt4);
		} catch (MyThoughtsException e) {
			System.out.println("FAILED");
			e.printStackTrace();
			DbAccessInterface.disconnect(con);
			return;
		}
		System.out.println("success");

		// INSERT COMMENTS (not coded yet)

//		System.out.print("Deleting Comments: ");
//		Comment c1 = new Comment("Absurd!",
//								 "The reason Pastors are men is laid out clearly in the Bible.",
//								 user3,
//								 new Date(),
//								 null);
//		Comment c2 = new Comment("Humans have grown past this...",
//								 "Women should be allowed to pastor!",
//								 user2,
//								 new Date(),
//								 c1);
//		Comment c3 = new Comment("The Beatles Suck",
//								 "Give me 3 songs of their's that everyone loves, and I'll change my mind.",
//								 user1,
//								 new Date(),
//								 null);
//		Comment c4 = new Comment("Maybe the best oldies band, but not rock band",
//								 "Get a grip America.",
//								 user2,
//								 new Date(),
//								 null);
//		try {
//			pm.deleteComment(c1);
//			pm.deleteComment(c2);
//			pm.deleteComment(c3);
//			pm.deleteComment(c4);
//		} catch (MyThoughtsException e) {
//			System.out.println("FAILED");
//			e.printStackTrace();
//			DbAccessInterface.disconnect(con);
//			return;
//		}
//		System.out.println("success");

		System.out.println("Testing Completed!");
		return;

	}

}
