//package edu.uga.cs.test;
//
//import edu.uga.cs.persistence.*;
//
//import java.sql.Connection;
//import java.util.ArrayList;
//import java.util.Date;
//
//import edu.uga.cs.MyThoughtsException;
//import edu.uga.cs.object.*;
//
//public class DbSaveTests {
//
//	public static void main(String[] args) {
//		Connection con = DbAccessInterface.connect();
//		PersistenceManager pm = new PersistenceManager(con);
//
//		// INSERT administrator
//
//		System.out.print("Saving Administrator: ");
//		Administrator admin = new Administrator("Jerry",
//												"Jones",
//												"jones",
//												"password",
//												"jerry@cowboys.com");
//		try {
//			admin.setId(pm.savePerson(admin));
//		} catch (MyThoughtsException e) {
//			System.out.println("FAILED");
//			e.printStackTrace();
//			DbAccessInterface.disconnect(con);
//			return;
//		}
//		System.out.println("success");
//
//		// INSERT users
//
//		System.out.print("Saving Users: ");
//		User user1 = new User("Phillip",
//							  "Rodgers",
//							  "rodgers",
//							  "password",
//							  "rodgers@phillip.com",
//							  false,
//							  0);
//		User user2 = new User("Darth",
//							  "Vader",
//							  "vader",
//							  "password",
//							  "darth.vader@deathstar.com",
//							  true,
//							  82);
//		User user3 = new User("Simon",
//							  "Sayz",
//							  "sayz",
//							  "password",
//							  "simon@sayz.com",
//							  false,
//							  6);
//		try {
//			user1.setId(pm.savePerson(user1));
//			user2.setId(pm.savePerson(user2));
//			user3.setId(pm.savePerson(user3));
//		} catch (MyThoughtsException e) {
//			System.out.println("FAILED");
//			e.printStackTrace();
//			DbAccessInterface.disconnect(con);
//			return;
//		}
//		System.out.println("success");
//
//		// INSERT DEBATE CATEGORIES
//
//		System.out.print("Saving Debate Categories: ");
//		DebateCategory featured = new DebateCategory("Featured",
//				"Picked by the myThoughts team!",
//				"fa fa-star",
//				"green");
//		DebateCategory trending = new DebateCategory("Trending",
//				"The topics with the most votes in the past 24 hours.",
//				"fa fa-line-chart",
//				"red");
//		DebateCategory recent = new DebateCategory("Recent",
//				"The most recent debate topics.",
//				"fa fa-clock-o",
//				"blue");
//		DebateCategory dc1 = new DebateCategory("Politics",
//												"I must study politics and war that my sons may have liberty to study mathematics and philosophy. - John Adams",
//												"fa fa-gavel",
//												"red");
//		DebateCategory dc2 = new DebateCategory("Religion",
//												"When I admire the wonders of a sunset or the beauty of the moon, my soul expands in the worship of the creator. - Mahatma Gandhi",
//												"fa fa-book",
//												"blue");
//		DebateCategory dc3 = new DebateCategory("Environmental",
//												"Never doubt that a small group of thoughtful, committed citizens can change the world; indeed, it's the only thing that ever has. - Margaret Mead",
//												"fa fa-tree",
//												"green");
//		DebateCategory dc4 = new DebateCategory("Music",
//												"Where words fail, music speaks. - Hans Anderson",
//												"fa fa-music",
//												"blue");
//		try {
//			featured.setId(pm.saveDebateCategory(featured));
//			trending.setId(pm.saveDebateCategory(trending));
//			recent.setId(pm.saveDebateCategory(recent));
//			dc1.setId(pm.saveDebateCategory(dc1));
//			dc2.setId(pm.saveDebateCategory(dc2));
//			dc3.setId(pm.saveDebateCategory(dc3));
//			dc4.setId(pm.saveDebateCategory(dc4));
//		} catch (MyThoughtsException e) {
//			System.out.println("FAILED");
//			e.printStackTrace();
//			DbAccessInterface.disconnect(con);
//			return;
//		}
//		System.out.println("success");
//
//		// INSERT DEBATE TOPICS
//
//		System.out.print("Saving Debate Topics: ");
//		DebateTopic dt1 = new DebateTopic("Women should be allowed to Pastor a church.",
//										  "",
//										  0,
//										  0,
//										  0,
//										  user2,
//										  new Date(),
//										  new ArrayList<DebateCategory>());
//		dt1.addCategories(dc2);
//		DebateTopic dt2 = new DebateTopic("The North Dakota Pipeline should not be built.",
//										  "",
//										  0,
//										  0,
//										  0,
//										  user2,
//										  new Date(),
//										  new ArrayList<DebateCategory>());
//		dt2.addCategories(dc1, dc3);
//		DebateTopic dt3 = new DebateTopic("The border wall will be good for Mexico-United States relations.",
//										  "",
//										  0,
//										  0,
//										  0,
//										  user3,
//										  new Date(),
//										  new ArrayList<DebateCategory>());
//		dt3.addCategories(dc1);
//		DebateTopic dt4 = new DebateTopic("The Beatles are the best rock band.",
//										  "",
//										  0,
//										  0,
//										  0,
//										  user1,
//										  new Date(),
//										  new ArrayList<DebateCategory>());
//		dt4.addCategories(dc4);
//		try {
//			dt1.setId(pm.saveDebateTopic(dt1));
//			dt2.setId(pm.saveDebateTopic(dt2));
//			dt3.setId(pm.saveDebateTopic(dt3));
//			dt4.setId(pm.saveDebateTopic(dt4));
//		} catch (MyThoughtsException e) {
//			System.out.println("FAILED");
//			e.printStackTrace();
//			DbAccessInterface.disconnect(con);
//			return;
//		}
//		System.out.println("success");
//
//		// INSERT COMMENTS
//
//		System.out.print("Saving Comments: ");
//		Comment c1 = new Comment("Absurd!",
//				 				 "The reason Pastors are men is laid out clearly in the Bible.",
//				 				 user1,
//				 				 new Date(),
//				 				 null,
//				 				 dt1);
//		Comment c2 = new Comment("Humans have grown past this...",
//						 		 "Women should be allowed to pastor!",
//						 		 user2,
//						 		 new Date(),
//						 		 c1,
//						 		 dt1);
//		Comment c3 = new Comment("The Beatles Suck",
//						 		 "Give me 3 songs of their's that everyone loves, and I'll change my mind.",
//						 		 user3,
//						 		 new Date(),
//						 		 null,
//						 		 dt4);
//		Comment c4 = new Comment("Maybe the best oldies band, but not rock band",
//						 		 "Get a grip America.",
//						 		 user2,
//						 		 new Date(),
//						 		 null,
//						 		 dt4);
//		try {
//			c1.setId(pm.saveComment(c1));
//			c2.setId(pm.saveComment(c2));
//			c3.setId(pm.saveComment(c3));
//			c4.setId(pm.saveComment(c4));
//		} catch (MyThoughtsException e) {
//			System.out.println("FAILED");
//			e.printStackTrace();
//			DbAccessInterface.disconnect(con);
//			return;
//		}
//		System.out.println("success");
//
//		// INSERTing likes
//
//		System.out.print("Saving Likes: ");
//
//		try {
//			pm.castVote(user1, c2, true);
//			pm.castAgree(user1, c2, true);
//			pm.castAgree(user1, dt1, false);
//			pm.castVote(user1, dt2, false);
//		} catch (MyThoughtsException e) {
//			System.out.println("FAILED");
//			e.printStackTrace();
//			DbAccessInterface.disconnect(con);
//			return;
//		}
//		System.out.println("success");
//
//		System.out.println("Testing Completed!");
//		return;
//
//	}
//
//}
