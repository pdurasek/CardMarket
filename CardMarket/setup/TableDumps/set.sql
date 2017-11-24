-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: eu-cdbr-azure-west-b.cloudapp.net    Database: card_market
-- ------------------------------------------------------
-- Server version	5.5.56-log

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
-- Table structure for table `cardset`
--

DROP TABLE IF EXISTS `cardset`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cardset` (
  `setID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `abbr` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`setID`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cardset`
--

LOCK TABLES `cardset` WRITE;
/*!40000 ALTER TABLE `cardset` DISABLE KEYS */;
INSERT INTO `cardset` VALUES (1,'Maximum Crisis','MACR'),(2,'Raging Tempest','RATE'),(3,'Invasion: Vengeance','INOV'),(4,'The Dark Illusion','TDIL'),(5,'Battle of Legend: Light\'s Revenge','BLLR'),(6,'Pendulum Evolution','PEVO'),(7,'Shining Victories','SHVI'),(8,'Breakers of Shadow','BOSH'),(9,'Dimension of Chaos','DOCS'),(10,'Clash of Rebelions','CORE'),(11,'Crossed Souls','CROS'),(12,'Secrets if Eternity','SECE'),(13,'The New Challengers','NECH'),(14,'Duelist Alliance','DUEA');
/*!40000 ALTER TABLE `cardset` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'card_market'
--

--
-- Dumping routines for database 'card_market'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-24 22:37:47
