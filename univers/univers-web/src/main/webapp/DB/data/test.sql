USE `univers`;

SET foreign_key_checks = 0;
DROP TABLE IF EXISTS `university`;
DROP TABLE IF EXISTS `student`;
SET foreign_key_checks = 1;

CREATE TABLE `university` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `university_city` varchar(255) DEFAULT NULL,
  `university_country` varchar(255) DEFAULT NULL,
  `university_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;


CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `age` int(11) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `university_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_a9nmvxoolj4das3ghh758un56` (`university_id`),
  CONSTRAINT `FK_a9nmvxoolj4das3ghh758un56` FOREIGN KEY (`university_id`) REFERENCES `university` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `university` WRITE;
INSERT INTO `university` VALUES (1,'seoul','korea','snu'),(2,'chonbuk','korea','cbk'),(3,'seoul','korea','snut'),(4,'seoul','korea','cau'),(5,'seoul','korea','hufs'),(6,'seoul','korea','kku'),(7,'seoul','korea','hanyang');
UNLOCK TABLES;

LOCK TABLES `student` WRITE;
INSERT INTO `student` VALUES 
(1,'22','daeung','kim','male',3),(2,'23','eunju','kim','female',1),(3,'24','eunju','lee','female',7),(4,'20','jongchang','lee','male',4),(5,'27','sunghwan','kim','male',4),(6,'27','sihwan','kim','male',5);
UNLOCK TABLES;
