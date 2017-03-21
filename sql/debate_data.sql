# Insert Administrators

INSERT INTO person (firstname, lastname, username, password, email, isAdmin)
VALUES ("Logan", "Jahnke", "jahnke", "ljahnke", "jahnke@uga.edu", true);

INSERT INTO person (firstname, lastname, username, password, email, isAdmin)
VALUES ("Lucy", "Bradley", "bradley", "lbradley", "lbradley@uga.edu", true);

INSERT INTO person (firstname, lastname, username, password, email, isAdmin)
VALUES ("Shaban", "Samani", "samani", "ssamani", "samani24@uga.edu", true);

INSERT INTO person (firstname, lastname, username, password, email, isAdmin)
VALUES ("April", "Abbott", "abbott", "aabbott", "abbott5@uga.edu", true);

# Insert Users

INSERT INTO person (firstname, lastname, username, password, email, isAdmin, isModerator, karma)
VALUES ("Brandon", "Rockwell", "rockwell", "brockwell", "brandon.rockwell42@uga.edu", false, false, 0);

INSERT INTO person (firstname, lastname, username, password, email, isAdmin, isModerator, karma)
VALUES ("Lara", "Kunnapas", "kunnapas", "lkunnapas", "larak@uga.edu", false, false, 0);

INSERT INTO person (firstname, lastname, username, password, email, isAdmin, isModerator, karma)
VALUES ("Ryan", "Patrick", "patrick", "rpatrick", "delepatrick@gmail.com", false, false, 0);

# Insert Debate Categories

INSERT INTO debate_category (name, description)
VALUES ("Politics", "I must study politics and war that my sons may have liberty to study mathematics and philosophy. - John Adams");

INSERT INTO debate_category (name, description)
VALUES ("Religion", "When I admire the wonders of a sunset or the beauty of the moon, my soul expands in the worship of the creator. - Mahatma Gandhi");

INSERT INTO debate_category (name, description)
VALUES ("Environmental", "Never doubt that a small group of thoughtful, committed citizens can change the world; indeed, it's the only thing that ever has. - Margaret Mead");

INSERT INTO debate_category (name, description)
VALUES ("Music", "Where words fail, music speaks. - Hans Anderson");

# Insert Debate Topics

INSERT INTO debate_topic (title, description, vote, agrees, disagrees, user_id)
VALUES ("Women should be allowed to Pastor a church.", "", 0, 0, 0, 7);

INSERT INTO debate_topic (title, description, vote, agrees, disagrees, user_id)
VALUES ("The North Dakota Pipeline should not be built.", "", 0, 0, 0, 6);

INSERT INTO debate_topic (title, description, vote, agrees, disagrees, user_id)
VALUES ("The border wall will be good for Mexico-United States relations.", "", 0, 0, 0, 5);

INSERT INTO debate_topic (title, description, vote, agrees, disagrees, user_id)
VALUES ("The Beatles are the best rock band.", "", 0, 0, 0, 7);

# Insert Category - Topic Relations

INSERT INTO topic_category (topic_id, category_id)
VALUES (1, 2);

INSERT INTO topic_category (topic_id, category_id)
VALUES (2, 1);

INSERT INTO topic_category (topic_id, category_id)
VALUES (2, 3);

INSERT INTO topic_category (topic_id, category_id)
VALUES (3, 1);

INSERT INTO topic_category (topic_id, category_id)
VALUES (4, 4);

# Insert Comments

INSERT INTO comment (subject, argument, user_id, topic_id, parent_id)
VALUES ("Absurd!", "The reason Pastors are men is laid out clearly in the Bible.", 5, 1, NULL);

INSERT INTO comment (subject, argument, user_id, topic_id, parent_id)
VALUES ("However, humans have grown past this,", "Women should be allowed to pastor!", 7, 1, 1);

INSERT INTO comment (subject, argument, user_id, topic_id, parent_id)
VALUES ("The Beatles Suck", "Give me 3 songs of their's that everyone loves, and I'll change my mind.", 6, 4, NULL);

INSERT INTO comment (subject, argument, user_id, topic_id, parent_id)
VALUES ("Maybe the best oldies band, but not rock band", "Get a grip America.", 6, 4, 3);