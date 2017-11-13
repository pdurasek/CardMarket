CREATE DATABASE  IF NOT EXISTS `card_market` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `card_market`;
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
-- Table structure for table `card`
--

DROP TABLE IF EXISTS `card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `card` (
  `cardID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(150) DEFAULT NULL,
  `firstEd` tinyint(1) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `imageUrl` varchar(45) DEFAULT NULL,
  `typeID` int(11) NOT NULL,
  `subTypeID` int(11) NOT NULL,
  `rarityID` int(11) NOT NULL,
  `languageID` int(11) NOT NULL,
  `setID` int(11) NOT NULL,
  `conditionId` int(11) NOT NULL,
  PRIMARY KEY (`cardID`),
  KEY `fk_Card_Type1_idx` (`typeID`),
  KEY `fk_Card_SubType1_idx` (`subTypeID`),
  KEY `fk_Card_Rarity1_idx` (`rarityID`),
  KEY `fk_Card_Language1_idx` (`languageID`),
  KEY `fk_Card_Set1_idx` (`setID`),
  KEY `fk_Card_Condition1_idx` (`conditionId`),
  CONSTRAINT `fk_Card_Condition1` FOREIGN KEY (`conditionId`) REFERENCES `cardcondition` (`conditionId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Card_Language1` FOREIGN KEY (`languageID`) REFERENCES `language` (`languageID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Card_Rarity1` FOREIGN KEY (`rarityID`) REFERENCES `rarity` (`rarityID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Card_Set1` FOREIGN KEY (`setID`) REFERENCES `cardset` (`setID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Card_SubType1` FOREIGN KEY (`subTypeID`) REFERENCES `subtype` (`subTypeID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Card_Type1` FOREIGN KEY (`typeID`) REFERENCES `type` (`typeID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=735 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card`
--

LOCK TABLES `card` WRITE;
/*!40000 ALTER TABLE `card` DISABLE KEYS */;
INSERT INTO `card` VALUES (1,'Lepa Karta',1,'Dost Dobra Karta','ash.png',1,1,1,1,1,1),(534,'Lepa Karta',1,'Dost Dobra Karta','ash.png',1,1,1,1,1,1),(544,'Lepa Karta',1,'Dost Dobra Karta','ash.png',1,1,1,1,1,1),(554,'Lepa Karta',1,'Dost Dobra Karta','ash.png',1,1,1,1,1,1),(564,'Lepa Karta',1,'Dost Dobra Karta','ash.png',1,1,1,1,1,1),(574,'Lepa Karta',1,'Dost Dobra Karta','ash.png',1,1,1,1,1,1),(584,'Lepa Karta',1,'Dost Dobra Karta','ash.png',1,1,1,1,1,1),(594,'Lepa Karta',1,'Dost Dobra Karta','ash.png',1,1,1,1,1,1),(604,'Lepa Karta',1,'Dost Dobra Karta','ash.png',1,1,1,1,1,1),(614,'Lepa Karta',1,'Dost Dobra Karta','ash.png',1,1,1,1,1,1),(624,'Lepa Karta',1,'Dost Dobra Karta','ash.png',1,1,1,1,1,1),(634,'Lepa Karta',1,'Dost Dobra Karta','ash.png',1,1,1,1,1,1),(644,'Lepa Karta',1,'Dost Dobra Karta','ash.png',1,1,1,1,1,1),(654,'Lepa Karta',1,'Dost Dobra Karta','ash.png',1,1,1,1,1,1),(664,'Lepa Karta',1,'Dost Dobra Karta','ash.png',1,1,1,1,1,1),(674,'Lepa Karta',1,'Dost Dobra Karta','ash.png',1,1,1,1,1,1),(684,'Lepa Karta',1,'Dost Dobra Karta','ash.png',1,1,1,1,1,1),(694,'Lepa Karta',1,'Dost Dobra Karta','ash.png',1,1,1,1,1,1),(704,'Lepa Karta',1,'Dost Dobra Karta','ash.png',1,1,1,1,1,1),(714,'Lepa Karta',1,'Dost Dobra Karta','ash.png',1,1,1,1,1,1),(724,'Lepa Karta',1,'Dost Dobra Karta','ash.png',1,1,1,1,1,1),(734,'Lepa Karta',1,'Dost Dobra Karta','ash.png',1,1,1,1,1,1);
/*!40000 ALTER TABLE `card` ENABLE KEYS */;
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

-- Dump completed on 2017-11-13 22:16:01
