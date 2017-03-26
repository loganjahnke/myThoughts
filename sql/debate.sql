#
# this SQL file creates the schema for the myThoughts database
#
# remove the existing tables
#
DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS topic_category;
DROP TABLE IF EXISTS debate_topic;
DROP TABLE IF EXISTS person;
DROP TABLE IF EXISTS debate_category;
#
# Table definition for table 'person'
#
CREATE TABLE person (
    id              INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    firstname       VARCHAR(255) NOT NULL,
    lastname        VARCHAR(255) NOT NULL,
    username        VARCHAR(255) NOT NULL UNIQUE,
    password        VARCHAR(255) NOT NULL,
    email           VARCHAR(255) NOT NULL UNIQUE,
    created         DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
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
    created         DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    vote            INT NOT NULL,
    agrees          INT NOT NULL,
    disagrees       INT NOT NULL,
    user_id         INT UNSIGNED NOT NULL,

    FOREIGN KEY (user_id) REFERENCES person(id) ON DELETE CASCADE
);

#
# Table definition for many-to-many table 'topic_category'
#
CREATE TABLE topic_category (
    category_id     INT UNSIGNED NOT NULL,
    topic_id        INT UNSIGNED NOT NULL,

    PRIMARY KEY (category_id, topic_id),
    FOREIGN KEY (category_id) REFERENCES debate_category(id) ON DELETE CASCADE,
    FOREIGN KEY (topic_id) REFERENCES debate_topic(id) ON DELETE CASCADE
);

#
# Table definition for table 'comment'
#
CREATE TABLE comment (
    id    	        INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    subject         VARCHAR(255) NOT NULL,
    argument        VARCHAR(2047) NOT NULL,
    created         DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    user_id         INT UNSIGNED NOT NULL,
    topic_id        INT UNSIGNED NOT NULL,
    parent_id       INT UNSIGNED, # represents a reply if not null

    FOREIGN KEY (user_id) REFERENCES person(id) ON DELETE CASCADE,
    FOREIGN KEY (topic_id) REFERENCES debate_topic(id) ON DELETE CASCADE,
    FOREIGN KEY (parent_id) REFERENCES comment(id) ON DELETE CASCADE
);