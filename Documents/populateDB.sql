-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: bankschema
-- ------------------------------------------------------
-- Server version	5.7.11-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accounts` (
  `idNumber` bigint(20) NOT NULL AUTO_INCREMENT,
  `creationDate` bigint(20) NOT NULL,
  `moneyAmount` int(11) NOT NULL,
  `username` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`idNumber`),
  KEY `FK809DBBE621B58322` (`username`),
  CONSTRAINT `FK809DBBE621B58322` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES (1,1459705780135,100,'mihai'),(2,1459705780135,200,'mihai'),(3,1459705780135,300,'admin'),(4,1459705780135,15,'admin'),(5,1459705780135,20,'bogdan'),(6,1459705780135,645,'bogdan'),(7,1459705780135,44,'administrator'),(8,1459705780135,234,'oana'),(9,1459705780135,654,'oana'),(10,1459705780135,77,'oana'),(11,1459705780135,876,'cristi'),(12,1459705780135,236,'andreea'),(13,1459705780135,654,'iulia');
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bills`
--

DROP TABLE IF EXISTS `bills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bills` (
  `idNumber` bigint(20) NOT NULL AUTO_INCREMENT,
  `ammountToPay` int(11) NOT NULL,
  `username` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`idNumber`),
  KEY `FK5965D4C21B58322` (`username`),
  CONSTRAINT `FK5965D4C21B58322` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bills`
--

LOCK TABLES `bills` WRITE;
/*!40000 ALTER TABLE `bills` DISABLE KEYS */;
INSERT INTO `bills` VALUES (1,20,'admin'),(2,17,'admin'),(3,39,'admin'),(4,44,'admin'),(5,26,'admin'),(6,4,'admin'),(7,0,'admin'),(8,29,'admin'),(9,0,'admin'),(10,26,'admin'),(11,21,'administrator'),(12,41,'administrator'),(13,49,'administrator'),(14,11,'administrator'),(15,42,'administrator'),(16,2,'administrator'),(17,28,'administrator'),(18,32,'administrator'),(19,33,'administrator'),(20,29,'administrator'),(21,29,'andreea'),(22,31,'andreea'),(23,23,'andreea'),(24,45,'andreea'),(25,17,'andreea'),(26,6,'andreea'),(27,38,'andreea'),(28,18,'andreea'),(29,29,'andreea'),(30,32,'andreea'),(31,4,'bogdan'),(32,33,'bogdan'),(33,38,'bogdan'),(34,24,'bogdan'),(35,15,'bogdan'),(36,22,'bogdan'),(37,12,'bogdan'),(38,13,'bogdan'),(39,8,'bogdan'),(40,15,'bogdan'),(41,4,'cristi'),(42,12,'cristi'),(43,23,'cristi'),(44,31,'cristi'),(45,40,'cristi'),(46,22,'cristi'),(47,39,'cristi'),(48,24,'cristi'),(49,4,'cristi'),(50,48,'cristi'),(51,15,'iulia'),(52,13,'iulia'),(53,46,'iulia'),(54,32,'iulia'),(55,19,'iulia'),(56,16,'iulia'),(57,7,'iulia'),(58,49,'iulia'),(59,44,'iulia'),(60,4,'iulia'),(61,13,'mihai'),(62,2,'mihai'),(63,37,'mihai'),(64,48,'mihai'),(65,45,'mihai'),(66,44,'mihai'),(67,38,'mihai'),(68,36,'mihai'),(69,23,'mihai'),(70,10,'mihai'),(71,2,'mircea'),(72,43,'mircea'),(73,11,'mircea'),(74,46,'mircea'),(75,37,'mircea'),(76,36,'mircea'),(77,9,'mircea'),(78,1,'mircea'),(79,11,'mircea'),(80,43,'mircea'),(81,49,'oana'),(82,7,'oana'),(83,3,'oana'),(84,44,'oana'),(85,0,'oana'),(86,48,'oana'),(87,4,'oana'),(88,23,'oana'),(89,46,'oana'),(90,41,'oana'),(91,5,'stefan'),(92,16,'stefan'),(93,30,'stefan'),(94,23,'stefan'),(95,15,'stefan'),(96,36,'stefan'),(97,8,'stefan'),(98,10,'stefan'),(99,47,'stefan'),(100,25,'stefan');
/*!40000 ALTER TABLE `bills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `moneytransfer`
--

DROP TABLE IF EXISTS `moneytransfer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `moneytransfer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fromAccountId` int(11) NOT NULL,
  `sum` int(11) NOT NULL,
  `toAccountId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `moneytransfer`
--

LOCK TABLES `moneytransfer` WRITE;
/*!40000 ALTER TABLE `moneytransfer` DISABLE KEYS */;
/*!40000 ALTER TABLE `moneytransfer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transactions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amount` int(11) NOT NULL,
  `date` bigint(20) NOT NULL,
  `from_username` varchar(15) DEFAULT NULL,
  `to_username` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK747989558A93C066` (`to_username`),
  KEY `FK747989552ED99C17` (`from_username`),
  CONSTRAINT `FK747989552ED99C17` FOREIGN KEY (`from_username`) REFERENCES `users` (`username`),
  CONSTRAINT `FK747989558A93C066` FOREIGN KEY (`to_username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
INSERT INTO `transactions` VALUES (1,543,1459705780845,'mihai','mihai'),(2,7,1459705781058,'mihai','admin'),(3,5,1459705781258,'bogdan','bogdan'),(4,4,1459705781391,'bogdan','mihai'),(5,5,1459705781503,'mihai','mihai'),(6,77,1459705781614,'mihai','mihai'),(7,33,1459705781726,'bogdan','mihai'),(8,66,1459705781959,'bogdan','admin'),(9,5,1459705782122,'bogdan','bogdan'),(10,77,1459705782269,'mihai','bogdan'),(11,8,1459705782460,'admin','mihai'),(12,23,1459705782640,'admin','mihai'),(13,42,1459705782859,'iulia','mihai'),(14,52,1459705783580,'iulia','oana'),(15,42,1459705783781,'andreea','oana'),(16,25,1459705784027,'administrator','oana'),(17,23,1459705784215,'administrator','admin'),(18,44,1459705784414,'oana','mihai'),(19,39,1459705784604,'admin','mihai'),(20,34,1459705784714,'oana','cristi'),(21,56,1459705785025,'oana','andreea');
/*!40000 ALTER TABLE `transactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `username` varchar(15) NOT NULL,
  `address` varchar(255) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `personalCode` bigint(20) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('admin','Ceahlau 14','ROLE_ADMIN','','Andreea Muresan','1dfd62bcd326e5dede9deaea24af74d453f4c58c49473dc25fed2d44615958661840df8df7977e73',19357621),('administrator','Pitestu 140','ROLE_ADMIN','','Flaviu Stoican','db4434ec54357635ef7d2fad373aff36fb5a41a9f57452cce699f841785e998d80285cc0f736c2fc',13458692),('andreea','Carmen 130','ROLE_USER','','Andreea Davidescu','e222ad283a2da7b51366b1d4f30eeb8ed306067a7f4e026b10f8ab216aaa12f16bb293f35ab2d881',1991234),('bogdan','Giulesti 10','ROLE_USER','','Bogdan Gliga','0d14efbab952316d005fea99594a7e2a92c593f637d0598ae6f842b120ebef6c0ea1c273dfc20842',19958692),('cristi','Mircea cel Batran 103','ROLE_USER','','Cristi Mincea','1672e252ed7e04a5badf49107b70d14be15d6058017e9c74acd970697253f6f571b2d5ee5854db71',1768692),('iulia','Calarasi 40','ROLE_USER','','Iulia Lazar','5b00472d011bd1b733ae790a93b897d326b2ef15bf25bbbf3e1e79f8e163eb41320bb01fac745b9d',19955552),('mihai','Dorobantilor 109','ROLE_USER','','Mihai Pop','69d2b2320e64b38a80277144a7723aeedf63ba9297ced261d8d75bbf2939912c3349e0b28895ff34',19475725),('mircea','Mihai Viteazu 12','ROLE_USER','','Mircea Nitu','d2c93b0aeb79ee2f131fac7d331363d0bef76f42e046ca1015d4ba51ca3c3efe890e84a64546ab14',16859392),('oana','Fanfara 2','ROLE_USER','','Oana Blaga','cb6e6d2774de8eb1b4c1b6b2771d26e79cd531a34677f88d31f4c46b13cdcedeb97613e6713229b9',15958543),('stefan','Dunarii 210','ROLE_USER','','Stefan Fodor','6df9a3825d0bbf12a060111be41ea201ae832702ec93eada37c79c95a6ceb42ff67854add26c79fc',12955552);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-03 20:50:20
