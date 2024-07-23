CREATE DATABASE  IF NOT EXISTS `social_network` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `social_network`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: social_network
-- ------------------------------------------------------
-- Server version	8.4.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `attachment`
--

DROP TABLE IF EXISTS `attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attachment` (
  `post` int DEFAULT NULL,
  `media` int DEFAULT NULL,
  KEY `attachment_post_id_fk` (`post`),
  KEY `attachment_media_id_fk` (`media`),
  CONSTRAINT `attachment_media_id_fk` FOREIGN KEY (`media`) REFERENCES `media` (`id`) ON DELETE CASCADE,
  CONSTRAINT `attachment_post_id_fk` FOREIGN KEY (`post`) REFERENCES `post` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attachment`
--

LOCK TABLES `attachment` WRITE;
/*!40000 ALTER TABLE `attachment` DISABLE KEYS */;
INSERT INTO `attachment` VALUES (9,3),(NULL,4),(NULL,5);
/*!40000 ALTER TABLE `attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `community`
--

DROP TABLE IF EXISTS `community`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `community` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `creator` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `community_user_id_fk` (`creator`),
  CONSTRAINT `community_user_id_fk` FOREIGN KEY (`creator`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `community`
--

LOCK TABLES `community` WRITE;
/*!40000 ALTER TABLE `community` DISABLE KEYS */;
INSERT INTO `community` VALUES (1,'Développeurs Java',1),(2,'Développeurs C#',4),(3,'Développeurs Web',1),(4,'Les footballeurs pro',11);
/*!40000 ALTER TABLE `community` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `country` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=250 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,'Andorra'),(2,'United Arab Emirates'),(3,'Afghanistan'),(4,'Antigua & Barbuda'),(5,'Anguilla'),(6,'Albania'),(7,'Armenia'),(8,'Angola'),(9,'Antarctica'),(10,'Argentina'),(11,'American Samoa'),(12,'Austria'),(13,'Australia'),(14,'Aruba'),(15,'Åland Islands'),(16,'Azerbaijan'),(17,'Bosnia & Herzegovina'),(18,'Barbados'),(19,'Bangladesh'),(20,'Belgium'),(21,'Burkina Faso'),(22,'Bulgaria'),(23,'Bahrain'),(24,'Burundi'),(25,'Benin'),(26,'St Barthélemy'),(27,'Bermuda'),(28,'Brunei'),(29,'Bolivia'),(30,'Caribbean Netherlands'),(31,'Brazil'),(32,'Bahamas'),(33,'Bhutan'),(34,'Bouvet Island'),(35,'Botswana'),(36,'Belarus'),(37,'Belize'),(38,'Canada'),(39,'Cocos (Keeling) Islands'),(40,'Congo - Kinshasa'),(41,'Central African Republic'),(42,'Congo - Brazzaville'),(43,'Switzerland'),(44,'Côte d’Ivoire'),(45,'Cook Islands'),(46,'Chile'),(47,'Cameroon'),(48,'China'),(49,'Colombia'),(50,'Costa Rica'),(51,'Cuba'),(52,'Cape Verde'),(53,'Curaçao'),(54,'Christmas Island'),(55,'Cyprus'),(56,'Czechia'),(57,'Germany'),(58,'Djibouti'),(59,'Denmark'),(60,'Dominica'),(61,'Dominican Republic'),(62,'Algeria'),(63,'Ecuador'),(64,'Estonia'),(65,'Egypt'),(66,'Western Sahara'),(67,'Eritrea'),(68,'Spain'),(69,'Ethiopia'),(70,'Finland'),(71,'Fiji'),(72,'Falkland Islands'),(73,'Micronesia'),(74,'Faroe Islands'),(75,'France'),(76,'Gabon'),(77,'United Kingdom'),(78,'Grenada'),(79,'Georgia'),(80,'French Guiana'),(81,'Guernsey'),(82,'Ghana'),(83,'Gibraltar'),(84,'Greenland'),(85,'Gambia'),(86,'Guinea'),(87,'Guadeloupe'),(88,'Equatorial Guinea'),(89,'Greece'),(90,'South Georgia & South Sandwich Islands'),(91,'Guatemala'),(92,'Guam'),(93,'Guinea-Bissau'),(94,'Guyana'),(95,'Hong Kong SAR China'),(96,'Heard & McDonald Islands'),(97,'Honduras'),(98,'Croatia'),(99,'Haiti'),(100,'Hungary'),(101,'Indonesia'),(102,'Ireland'),(103,'Israel'),(104,'Isle of Man'),(105,'India'),(106,'British Indian Ocean Territory'),(107,'Iraq'),(108,'Iran'),(109,'Iceland'),(110,'Italy'),(111,'Jersey'),(112,'Jamaica'),(113,'Jordan'),(114,'Japan'),(115,'Kenya'),(116,'Kyrgyzstan'),(117,'Cambodia'),(118,'Kiribati'),(119,'Comoros'),(120,'St Kitts & Nevis'),(121,'North Korea'),(122,'South Korea'),(123,'Kuwait'),(124,'Cayman Islands'),(125,'Kazakhstan'),(126,'Laos'),(127,'Lebanon'),(128,'St Lucia'),(129,'Liechtenstein'),(130,'Sri Lanka'),(131,'Liberia'),(132,'Lesotho'),(133,'Lithuania'),(134,'Luxembourg'),(135,'Latvia'),(136,'Libya'),(137,'Morocco'),(138,'Monaco'),(139,'Moldova'),(140,'Montenegro'),(141,'St Martin'),(142,'Madagascar'),(143,'Marshall Islands'),(144,'North Macedonia'),(145,'Mali'),(146,'Myanmar (Burma)'),(147,'Mongolia'),(148,'Macao SAR China'),(149,'Northern Mariana Islands'),(150,'Martinique'),(151,'Mauritania'),(152,'Montserrat'),(153,'Malta'),(154,'Mauritius'),(155,'Maldives'),(156,'Malawi'),(157,'Mexico'),(158,'Malaysia'),(159,'Mozambique'),(160,'Namibia'),(161,'New Caledonia'),(162,'Niger'),(163,'Norfolk Island'),(164,'Nigeria'),(165,'Nicaragua'),(166,'Netherlands'),(167,'Norway'),(168,'Nepal'),(169,'Nauru'),(170,'Niue'),(171,'New Zealand'),(172,'Oman'),(173,'Panama'),(174,'Peru'),(175,'French Polynesia'),(176,'Papua New Guinea'),(177,'Philippines'),(178,'Pakistan'),(179,'Poland'),(180,'St Pierre & Miquelon'),(181,'Pitcairn Islands'),(182,'Puerto Rico'),(183,'Palestinian Territories'),(184,'Portugal'),(185,'Palau'),(186,'Paraguay'),(187,'Qatar'),(188,'Réunion'),(189,'Romania'),(190,'Serbia'),(191,'Russia'),(192,'Rwanda'),(193,'Saudi Arabia'),(194,'Solomon Islands'),(195,'Seychelles'),(196,'Sudan'),(197,'Sweden'),(198,'Singapore'),(199,'St Helena'),(200,'Slovenia'),(201,'Svalbard & Jan Mayen'),(202,'Slovakia'),(203,'Sierra Leone'),(204,'San Marino'),(205,'Senegal'),(206,'Somalia'),(207,'Suriname'),(208,'South Sudan'),(209,'São Tomé & Príncipe'),(210,'El Salvador'),(211,'Sint Maarten'),(212,'Syria'),(213,'Eswatini'),(214,'Turks & Caicos Islands'),(215,'Chad'),(216,'French Southern Territories'),(217,'Togo'),(218,'Thailand'),(219,'Tajikistan'),(220,'Tokelau'),(221,'Timor-Leste'),(222,'Turkmenistan'),(223,'Tunisia'),(224,'Tonga'),(225,'Turkey'),(226,'Trinidad & Tobago'),(227,'Tuvalu'),(228,'Taiwan'),(229,'Tanzania'),(230,'Ukraine'),(231,'Uganda'),(232,'US Outlying Islands'),(233,'United States'),(234,'Uruguay'),(235,'Uzbekistan'),(236,'Vatican City'),(237,'St Vincent & the Grenadines'),(238,'Venezuela'),(239,'British Virgin Islands'),(240,'US Virgin Islands'),(241,'Vietnam'),(242,'Vanuatu'),(243,'Wallis & Futuna'),(244,'Samoa'),(245,'Yemen'),(246,'Mayotte'),(247,'South Africa'),(248,'Zambia'),(249,'Zimbabwe');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `direct_message`
--

DROP TABLE IF EXISTS `direct_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `direct_message` (
  `id` int NOT NULL AUTO_INCREMENT,
  `text` varchar(255) NOT NULL,
  `created_at` date NOT NULL,
  `sender` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `direct_message_user_id_fk` (`sender`),
  CONSTRAINT `direct_message_user_id_fk` FOREIGN KEY (`sender`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `direct_message`
--

LOCK TABLES `direct_message` WRITE;
/*!40000 ALTER TABLE `direct_message` DISABLE KEYS */;
INSERT INTO `direct_message` VALUES (3,'Voici mon premier message','2024-05-24',1),(4,'Salut toi comment va regarde la video','2024-05-24',1),(5,'Ici se termine mes messages','2024-05-24',1),(6,'Ca va bien et toi','2024-05-24',4);
/*!40000 ALTER TABLE `direct_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `like`
--

DROP TABLE IF EXISTS `like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `like` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `post_liked` int NOT NULL,
  `liked_by` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `like_user_id_fk` (`liked_by`),
  KEY `like_post_id_fk` (`post_liked`),
  CONSTRAINT `like_post_id_fk` FOREIGN KEY (`post_liked`) REFERENCES `post` (`id`) ON DELETE CASCADE,
  CONSTRAINT `like_user_id_fk` FOREIGN KEY (`liked_by`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `like`
--

LOCK TABLES `like` WRITE;
/*!40000 ALTER TABLE `like` DISABLE KEYS */;
INSERT INTO `like` VALUES (7,'2024-05-25',12,1),(8,'2024-05-23',12,4),(9,'2024-05-12',12,6),(10,'2024-02-25',12,8),(11,'2024-05-25',12,7),(12,'2024-05-25',12,10),(13,'2024-05-25',9,1),(14,'2024-05-25',9,10),(15,'2024-05-01',9,7),(16,'2024-04-17',10,7),(17,'2024-05-01',11,1),(18,'2024-05-03',11,4),(19,'2024-05-06',11,11),(20,'2024-05-17',12,11);
/*!40000 ALTER TABLE `like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `locality`
--

DROP TABLE IF EXISTS `locality`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `locality` (
  `code` int NOT NULL AUTO_INCREMENT,
  `name` varchar(105) NOT NULL,
  `city` varchar(60) NOT NULL,
  `zip_code` int NOT NULL,
  `localisation` int NOT NULL,
  PRIMARY KEY (`code`),
  KEY `locality_country_id_fk` (`localisation`),
  CONSTRAINT `locality_country_id_fk` FOREIGN KEY (`localisation`) REFERENCES `country` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locality`
--

LOCK TABLES `locality` WRITE;
/*!40000 ALTER TABLE `locality` DISABLE KEYS */;
INSERT INTO `locality` VALUES (1,'Namur','Jambes',5100,20),(2,'Namur','Gembloux',5030,20),(3,'Namur','Gembloux',5031,20),(4,'Namur','Gembloux',5032,20),(5,'Île-de-France','Paris',75000,75),(6,'Namur','Namur',5000,20),(7,'Île-de-France','Paris',75001,75);
/*!40000 ALTER TABLE `locality` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `media`
--

DROP TABLE IF EXISTS `media`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `media` (
  `id` int NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL,
  `attachment` int DEFAULT NULL,
  `format` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `media_media_type_id_fk` (`format`),
  KEY `media_direct_message_id_fk` (`attachment`),
  CONSTRAINT `media_direct_message_id_fk` FOREIGN KEY (`attachment`) REFERENCES `direct_message` (`id`) ON DELETE CASCADE,
  CONSTRAINT `media_media_type_id_fk` FOREIGN KEY (`format`) REFERENCES `media_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `media`
--

LOCK TABLES `media` WRITE;
/*!40000 ALTER TABLE `media` DISABLE KEYS */;
INSERT INTO `media` VALUES (3,'www.image.com',3,1),(4,'www.lavideo.com',4,2),(5,'ladeuxiemevideo.com',6,2);
/*!40000 ALTER TABLE `media` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `media_type`
--

DROP TABLE IF EXISTS `media_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `media_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type_name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `media_type`
--

LOCK TABLES `media_type` WRITE;
/*!40000 ALTER TABLE `media_type` DISABLE KEYS */;
INSERT INTO `media_type` VALUES (1,'image'),(2,'video');
/*!40000 ALTER TABLE `media_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `user` int NOT NULL,
  `community` int DEFAULT NULL,
  KEY `member_user_id_fk` (`user`),
  KEY `member_community_id_fk` (`community`),
  CONSTRAINT `member_community_id_fk` FOREIGN KEY (`community`) REFERENCES `community` (`id`) ON DELETE CASCADE,
  CONSTRAINT `member_user_id_fk` FOREIGN KEY (`user`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,1),(4,1),(6,1),(7,1),(10,1),(1,2),(11,2),(9,2),(4,2),(1,3),(8,3),(1,4),(4,4),(6,4),(10,4),(11,4);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `id` int NOT NULL AUTO_INCREMENT,
  `text` varchar(255) NOT NULL,
  `created_at` date NOT NULL,
  `posted_by` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `post_user_id_fk` (`posted_by`),
  CONSTRAINT `post_user_id_fk` FOREIGN KEY (`posted_by`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (9,'Je suis un super post avec une image','2024-05-23',1),(10,'Post numéro 2','2024-05-24',7),(11,'Troisième post','2024-05-24',4),(12,'Tuto java : print une ligne. Vous devez utiliser System.out.println','2024-05-24',1);
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posted_in`
--

DROP TABLE IF EXISTS `posted_in`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `posted_in` (
  `post` int DEFAULT NULL,
  `community` int DEFAULT NULL,
  KEY `posted_in_post_id_fk` (`post`),
  KEY `posted_in_community_id_fk` (`community`),
  CONSTRAINT `posted_in_community_id_fk` FOREIGN KEY (`community`) REFERENCES `community` (`id`) ON DELETE CASCADE,
  CONSTRAINT `posted_in_post_id_fk` FOREIGN KEY (`post`) REFERENCES `post` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posted_in`
--

LOCK TABLES `posted_in` WRITE;
/*!40000 ALTER TABLE `posted_in` DISABLE KEYS */;
INSERT INTO `posted_in` VALUES (12,1);
/*!40000 ALTER TABLE `posted_in` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receiver`
--

DROP TABLE IF EXISTS `receiver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `receiver` (
  `direct_message` int DEFAULT NULL,
  `user` int NOT NULL,
  KEY `receiver_user_id_fk` (`user`),
  KEY `receiver_direct_message_id_fk` (`direct_message`),
  CONSTRAINT `receiver_direct_message_id_fk` FOREIGN KEY (`direct_message`) REFERENCES `direct_message` (`id`) ON DELETE CASCADE,
  CONSTRAINT `receiver_user_id_fk` FOREIGN KEY (`user`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receiver`
--

LOCK TABLES `receiver` WRITE;
/*!40000 ALTER TABLE `receiver` DISABLE KEYS */;
INSERT INTO `receiver` VALUES (3,4),(4,4),(5,4),(6,1);
/*!40000 ALTER TABLE `receiver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(100) NOT NULL,
  `date_of_birth` date NOT NULL,
  `gender` char(1) NOT NULL,
  `created_at` date NOT NULL,
  `street_and_number` varchar(255) NOT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `biography` varchar(255) DEFAULT NULL,
  `is_admin` bit(1) NOT NULL,
  `home` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `username` (`username`),
  KEY `user_locality_code_fk` (`home`),
  CONSTRAINT `user_locality_code_fk` FOREIGN KEY (`home`) REFERENCES `locality` (`code`),
  CONSTRAINT `chk_email` CHECK ((`email` like _utf8mb4'%_@__%.__%')),
  CONSTRAINT `chk_gender` CHECK ((`gender` in (_utf8mb4'm',_utf8mb4'w',_utf8mb4'o'))),
  CONSTRAINT `chk_pasword` CHECK ((length(`password`) between 6 and 100)),
  CONSTRAINT `chk_phone_number_length` CHECK (((`phone_number` is null) or (length(`phone_number`) between 1 and 20))),
  CONSTRAINT `chk_username` CHECK (((length(`username`) > 0) and (length(`username`) <= 20)))
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'test@test.com','Le Testeur','Dys1jR6nkuvYSI2Iq2DyUg==:E6VGiG+taGB9hNWAYj8tIoK3GMN48ds1z/8MjPHWUoU=','2002-05-11','m','2024-05-28','Avenue du pont',NULL,'Je suis un super testeur',_binary '',1),(4,'letesteur2@test.com','second testeur','5PTInM0Gayf0/5UGC8XgSg==:/cZ+u7KxhGj/C0iMDbBPIEBQ5X78enmZOfgG7zKFJeM=','1995-05-11','w','2024-05-28','Rue des pions 67','123456',NULL,_binary '',7),(6,'super@test.com','Le Super','6oZo0g7sylrJ6VOfeK1HUw==:qfQj5WTcno0LXxyszFfJD1IH5zP4k/UFFAzepcpI7x0=','1989-11-06','o','2024-05-28','Rue de la gare 23',NULL,NULL,_binary '\0',4),(7,'alexandre@gmail.com','Alexandre','lVGkyjuhBCf6P8No5Y82WQ==:mslFG++GgoyDiy3nAaTXqX1H1tu2f5ND3lFM7GVqXJg=','2014-01-23','m','2024-05-28','Avenue des écrevisses','4323455',NULL,_binary '',5),(8,'levieux@gmail.com','le vieux','f4vRvAt4pHzp4xxvvSZHoA==:oKJMJFmwSd+etPKFwh/T08zaEzsozGC3JX/+2iC7mXs=','1954-08-03','m','2024-05-28','Rue des vieux',NULL,NULL,_binary '\0',3),(9,'abcdef@gmail.com','superheureux','nX91FodTBjQcvJUKV9L+9w==:OgJ/jIwEo0sG1nelX5LPhEDHJAXGd6U/mhfBDzh2SvE=','1972-02-23','m','2024-05-28','Avenue des avenues',NULL,NULL,_binary '\0',6),(10,'venue@tapis.com','lavenue','v7dbBEWlCWMyKWydY3cjzQ==:V9PekQ+6F4F5iYTwgmVsKxYHlQQZ6oAYjBfxIfHUabk=','2010-01-02','w','2024-05-28','Rue des rues',NULL,NULL,_binary '',2),(11,'spin@flip.conm','spinandflip','o8oZJ4sRZTXkjr/iol2yqg==:gLnVeyAkyvFGgpA3rWMPk6hxZ5jV/tve/PbJIG1ozuk=','2012-06-05','m','2024-05-28','Rue des flips','8765432','Super les super flips',_binary '\0',5);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-28 21:28:03
