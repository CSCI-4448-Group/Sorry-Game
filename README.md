# Sorry-Game

* Group Members: Sidhant Puntambekar, Brian Noble, Isaac Pyle

Simulation of the Sorry! board game

Java JDK Version: 18.0.0

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