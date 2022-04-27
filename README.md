# Sorry-Game

* Group Members: Sidhant Puntambekar, Brian Noble, Isaac Pyle

Simulation of the Sorry! board game

Java JDK Version: 18.0.0

A quick highlight of some of the patterns:
* Builder pattern in BoardBuilder.java
* Command pattern in Command.java
* MVC in the whole project, using controller classes, view classes, and model classes named accordingly
* Observer / Singleton in their class files and in Tracker.java / Logger.java / Observer.java / Subject.java
* ObjectPool pattern in the PlayerPool.java class

There are many other OO design patterns throughout, but these are easy to glance at and understand.

To create the database table, you can run the following SQL Create statement:

```
CREATE TABLE `sorry_table` (
`id` int NOT NULL AUTO_INCREMENT,
`gameid` int DEFAULT NULL,
`name` varchar(45) DEFAULT NULL,
`moved` int DEFAULT NULL,
`sorries` int DEFAULT NULL,
`started` int DEFAULT NULL,
`home` int DEFAULT NULL,
PRIMARY KEY (`id`)
)
```

Note that the database schema needs to be called "sorryDB".

In the config.properties file located in src/main/resources, update the username and password with your database credentials.

If you would like to see / test the functionality of the program without connecting to the database, change the boolean value *track_and_use_db* in GameController to false.  

For project 5 initial design document, project 6 update document, and project 7 final report document, please consult 
the relevant PDFs in this repository. A link to our video demonstration is located in the project 7 final report 
document. If the database is not working properly on your end, please see the video for the database in use in regard to the leaderboard page.

Dependencies should be included in the pom.xml, and include:
* JavaFX
* MySQL
* JUnit