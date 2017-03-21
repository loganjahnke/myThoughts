package edu.uga.cs.test;

import edu.uga.cs.persistence.*;

import java.sql.Connection;

import edu.uga.cs.MyThoughtsException;
import edu.uga.cs.object.*;

public class DatabaseUnitTests {

	public static void main() {
		Connection con = DbAccessInterface.connect();
		PersistenceManager pm = new PersistenceManager(con);

		// INSERT administrator

		System.out.print("Saving Administrator: ");
		Administrator admin = new Administrator("Jerry",
												"Jones",
												"jones",
												"password",
												"jerry@cowboys.com");
		try {
			pm.savePerson(admin);
		} catch (MyThoughtsException e) {
			System.out.println("FAILED");
			e.printStackTrace();
			DbAccessInterface.disconnect(con);
			return;
		}
		System.out.println("success");

		// INSERT users

		System.out.print("Saving Users: ");
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
			pm.savePerson(user1);
			pm.savePerson(user2);
			pm.savePerson(user3);
		} catch (MyThoughtsException e) {
			System.out.println("FAILED");
			e.printStackTrace();
			DbAccessInterface.disconnect(con);
			return;
		}
		System.out.println("success");
	}

}
