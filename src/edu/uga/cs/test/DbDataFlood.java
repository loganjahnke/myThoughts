package edu.uga.cs.test;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import edu.uga.cs.MyThoughtsException;
import edu.uga.cs.object.DebateTopic;
import edu.uga.cs.object.User;
import edu.uga.cs.persistence.DbAccessInterface;
import edu.uga.cs.persistence.PersistenceManager;

public class DbDataFlood {

	public static void main(String[] args) {

		String firstnames[] = { "Stepanie", "Ranee", "Cari", "Marquetta", "Todd", "Rosalyn", "Zackary", "Latricia", "Micheal", "Roman", "Nicki", "Cherie", "Eric", "Theodore", "Cathern", "Jasper", "Ida", "Genie", "Reba", "Yolande", "Kieth", "Lilian", "Murray", "Louann", "Kenny", "Renay", "Johanne", "Veronica", "Venus", "Keren", "Grant", "Trey", "Trent", "Mercedez", "Rolande", "Larry", "Lashaun", "Hazel", "Coy", "Chandra", "Janiece", "Karry", "Antonette", "Harmony", "Nora", "Judi", "Regena", "Louis", "Genevive", "Susy", "Melinda", "Marcie", "Shin", "Filomena", "Samuel", "Lakeesha", "Sage", "Peggie", "Monroe", "Celine", "Nathaniel", "Dean", "Delora", "Shaunta", "Oda", "Stuart", "Latia", "Jinny", "Tanika", "Joetta", "Angila", "Jacki", "Desiree", "Carmella", "Marketta", "Branden", "Thea", "Renetta", "Pamula", "Judie", "Francesca", "Darren", "Eulah", "Vinita", "Darline", "Andres", "Mattie", "Rebeca", "Marry", "Jarvis", "Twanna", "Janetta", "Bella", "China", "Mariano", "Johnnie", "Edgardo", "Keely", "Brook", "Candra" };
		String lastnames[] = { "Fox", "Valencia", "Coffey", "Keith", "Daniels", "Hall", "Leonard", "Mahoney", "Oneill", "Rios", "Flynn", "Bird", "Case", "Gray", "Anderson", "Kerr", "Allison", "Lester", "Mcmillan", "Fitzgerald", "Gonzales", "Lee", "Baxter", "Thornton", "Newton", "Fuentes", "Hayes", "Nichols", "Brennan", "Meadows", "Yates", "Brooks", "Petersen", "Weaver", "Schmitt", "Preston", "Rubio", "Sampson", "Gordon", "Vance", "Mason", "Cole", "Hopkins", "Singh", "Fowler", "Espinoza", "Garrett", "Mcfarland", "Olsen", "Moody", "Simpson", "Yu", "Patterson", "Palmer", "Delgado", "Petty", "Dickerson", "Tanner", "Schwartz", "Coleman", "Dean", "Hooper", "Gamble", "Hardy", "Calhoun", "Boyd", "Olson", "Parker", "Keller", "Ingram", "Casey", "Lynn", "Zamora", "Rasmussen", "Myers", "Heath", "Cruz", "Carey", "Mcpherson", "Parrish", "Lloyd", "Schroeder", "Braun", "Summers", "Grant", "Gross", "Arias", "Obrien", "Macias", "Greer", "Anthony", "Stephens", "Lam", "Bright", "Hendrix", "Marquez", "Johnson", "Mooney", "Ochoa", "Phelps" };

		PersistenceManager pm = new PersistenceManager(DbAccessInterface.connect());
		
		System.out.println("Saving users...");
		
		try {
			ArrayList<DebateTopic> debateTopics = pm.restoreAllDebateTopics();
			
			// Create 100 users and have them like or dislike topics
			for (int i = 0; i < 100; i++) {
				User user = new User();
				user.setFirstname(firstnames[ThreadLocalRandom.current().nextInt(0, 100)]);
				user.setLastname(lastnames[ThreadLocalRandom.current().nextInt(0, 100)]);
				user.setEmail(user.getFirstname().toLowerCase() + user.getLastname().toLowerCase() + "@gmail.com");
				user.setUsername(user.getFirstname().toLowerCase() + "_" + user.getLastname().toLowerCase());
				user.setPassword("password");
				user.setModerator(false);
				user.setKarma(ThreadLocalRandom.current().nextInt(0, 700));
				
				if (pm.restorePerson(user) != null)
					continue;
				
				pm.savePerson(user);
				
				// Randomly upvote/downvote with topics
				for (int vote = 0; vote < debateTopics.size(); vote++) {
					int ran = ThreadLocalRandom.current().nextInt(0, 3);
					if (ran == 0) {
						pm.castVote(user, debateTopics.get(vote), true);
					} else if (ran == 1) {
						pm.castVote(user, debateTopics.get(vote), false);
					}
				}
				
				// Randomly agree/disagree with topics
				for (int agree = 0; agree < debateTopics.size(); agree++) {
					int ran = ThreadLocalRandom.current().nextInt(0, 3);
					if (ran == 0) {
						pm.castAgree(user, debateTopics.get(agree), true);
					} else if (ran == 1) {
						pm.castAgree(user, debateTopics.get(agree), false);
					}
				}
				
				System.out.println("\t- SAVED: " + user.getUsername());
			}
		} catch (MyThoughtsException e) {
			e.printStackTrace();
		}
		
		System.out.println("Finished saving.");

	}

}
