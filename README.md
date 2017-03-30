# myThoughts
myThoughts is a web application that allows users to discuss their opinions on various topics in a forum-like setting. Posts and threads will be organized in a way that promotes a productive exchange of ideas.

# Developer Setup Instructions
1. Create table in mysql named myThoughts
    - In command line: `mysql`
    - `CREATE TABLE myThoughts;`
2. Install database by using mysql
    - `mysql -u [username] -p myThoughts < sql/debate.sql`
3. Install administrators
    - `mysql -u [username] -p myThoughts < sql/debate_data.sql`
4. Change `DB_CONNECTION_USERNAME` and `DB_CONNECTION_PASSWORD` inside `DbAccessConfig.java` to your needs
4. Run DbSaveTests in edu.uga.cs.test to populate database

# Class Diagram
![diagram](https://raw.githubusercontent.com/loganjahnke/myThoughts/master/docs/Debate%20Class%20Diagram.png?token=AI62QBenWj8QYYDrjW0Ry6ANyUco3gEoks5Y5VyNwA%3D%3D)

# Mockups
![homepage](https://raw.githubusercontent.com/loganjahnke/myThoughts/master/mockups/myThoughtsMockup-01.png?token=AI62QDWMAJSXujtTl9oZcPNj5tea08Geks5Y5UgJwA%3D%3D)

![topic-neutral](https://raw.githubusercontent.com/loganjahnke/myThoughts/master/mockups/post-neutral.png?token=AI62QIHVphotWZfiV5rFF6kGe7W9PF9Iks5Y5kx5wA%3D%3D)

![topic-agree](https://raw.githubusercontent.com/loganjahnke/myThoughts/master/mockups/post-agree.png?token=AI62QFo-Z6BBpUF0SQ51m_8MjnzIrD7Pks5Y5kx-wA%3D%3D)