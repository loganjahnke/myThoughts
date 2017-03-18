#
# this SQL file creates the schema for the myThoughts database
#
# remove the existing tables
#
DROP TABLE IF EXISTS person;
DROP TABLE IF EXISTS debate_category;
DROP TABLE IF EXISTS debate_topic;
DROP TABLE IF EXISTS comment;
#
# Table definition for table 'person'
#
CREATE TABLE person (
    id              INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    firstname       VARCHAR(255) NOT NULL,
    lastname        VARCHAR(255) NOT NULL,
    username        VARCHAR(255) NOT NULL UNIQUE,
    password        VARCHAR(255) NOT NULL,
    email           VARCHAR(255) NOT NULL,
    isAdmin         BOOLEAN NOT NULL,
    isModerator     BOOLEAN,
    karma           INT
);

#
# Table definition for table 'debate_category'
#
CREATE TABLE debate_category (
    id              INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    name            VARCHAR(255) NOT NULL UNIQUE,
    description     VARCHAR(511)
);

#
# Table definition for table 'debate_topic'
#
CREATE TABLE debate_topic (
    id              INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    title           VARCHAR(255) NOT NULL,
    description     VARCHAR(1023),
    vote            INT NOT NULL,
    agrees          INT NOT NULL,
    disagrees       INT NOT NULL,
    user_id         INT UNSIGNED NOT NULL,

    FOREIGN KEY (user_id) REFERENCES user(id)
);

#
# Table definition for table 'comment'
#
CREATE TABLE comment (
    id    	        INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    subject         VARCHAR(255) NOT NULL,
    argument        VARCHAR(2047) NOT NULL,
    user_id         INT UNSIGNED NOT NULL,

    FOREIGN KEY (user_id) REFERENCES user(id)
);