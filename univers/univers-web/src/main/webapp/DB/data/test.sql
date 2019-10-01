USE `univers`;

LOCK TABLES `university` WRITE;
INSERT INTO `university` VALUES (1,'seoul','korea','snu'),(2,'chonbuk','korea','cbk'),(3,'seoul','korea','snut'),(4,'seoul','korea','cau'),(5,'seoul','korea','hufs'),(6,'seoul','korea','kku'),(7,'seoul','korea','hanyang');
UNLOCK TABLES;

LOCK TABLES `student` WRITE;
INSERT INTO `student` VALUES 
(1,'22','daeung','kim','male',3),(1,'23','eunju','kim','female',1),(3,'24','eunju','lee','female',7),(4,'20','jongchang','lee','male',4);
UNLOCK TABLES;
