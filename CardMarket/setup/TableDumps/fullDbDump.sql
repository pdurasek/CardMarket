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
) ENGINE=InnoDB AUTO_INCREMENT=754 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card`
--

LOCK TABLES `card` WRITE;
/*!40000 ALTER TABLE `card` DISABLE KEYS */;
INSERT INTO `card` VALUES (1,'Lepa Karta',1,'Dost Dobra Karta','ash.png',4,1,1,1,1,1),(534,'Azdaja',1,'Dost Dobra Karta','ash.png',4,1,2,1,2,1),(544,'Leva Ko Desna',1,'Dost Dobra Karta','ash.png',4,1,1,1,3,1),(554,'Zmaj',1,'Dost Dobra Karta','ash.png',4,1,2,1,4,1),(564,'Ruka',1,'Dost Dobra Karta','ash.png',4,1,3,1,1,1),(574,'Lepa Mara',1,'Dost Dobra Karta','ash.png',4,1,4,1,2,1),(584,'Beba',1,'Dost Dobra Karta','ash.png',4,1,5,1,3,1),(594,'Bik',1,'Dost Dobra Karta','ash.png',4,1,2,1,4,1),(604,'Kurati Bik',1,'Dost Dobra Karta','ash.png',4,1,3,1,2,1),(614,'Heri Poter',1,'Dost Dobra Karta','ash.png',4,1,1,1,1,1),(624,'Brat Od Hulka',1,'Dost Dobra Karta','ash.png',4,1,4,1,1,1),(634,'Undergdoun',1,'Dost Dobra Karta','ash.png',4,1,5,1,2,1),(644,'Benzinska',1,'Dost Dobra Karta','ash.png',4,1,1,1,4,1),(654,'Pumpa',1,'Dost Dobra Karta','ash.png',4,1,1,1,5,1),(664,'Macka',1,'Dost Dobra Karta','ash.png',4,1,1,1,6,1),(674,'Pas s Nogom',1,'Dost Dobra Karta','ash.png',4,1,2,1,7,1),(684,'Ruzni Zmaj',1,'Dost Dobra Karta','ash.png',4,1,1,1,4,1),(694,'Lepi Zmaj',1,'Dost Dobra Karta','ash.png',4,1,3,1,2,1),(704,'Kek',1,'Dost Dobra Karta','ash.png',4,1,1,1,2,1),(714,'Bone',1,'Dost Dobra Karta','ash.png',4,1,1,1,4,1),(724,'Zdravko',1,'Dost Dobra Karta','ash.png',4,1,3,1,8,1),(734,'Suq Madiq',1,'Dost Dobra Karta','ash.png',4,1,1,1,5,1),(744,'Heri Poter',0,'Nelosa Karta','ghost.png',4,1,3,1,2,3);
/*!40000 ALTER TABLE `card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cardcondition`
--

DROP TABLE IF EXISTS `cardcondition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cardcondition` (
  `conditionId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(70) DEFAULT NULL,
  `abbr` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`conditionId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cardcondition`
--

LOCK TABLES `cardcondition` WRITE;
/*!40000 ALTER TABLE `cardcondition` DISABLE KEYS */;
INSERT INTO `cardcondition` VALUES (1,'Mint','M'),(2,'Near Mint','NM'),(3,'Excellent','E'),(4,'Good','G'),(5,'Light Played','LP'),(6,'Played','P'),(7,'Damaged','D');
/*!40000 ALTER TABLE `cardcondition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cardoffer`
--

DROP TABLE IF EXISTS `cardoffer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cardoffer` (
  `cardOfferID` int(11) NOT NULL AUTO_INCREMENT,
  `Card_cardID` int(11) NOT NULL,
  `User_userID` int(11) NOT NULL,
  `quantity` int(11) DEFAULT '1',
  `price` decimal(10,0) DEFAULT '1',
  `reserved` tinyint(1) DEFAULT '0',
  `reservedCardID` int(11) DEFAULT NULL,
  PRIMARY KEY (`cardOfferID`),
  KEY `fk_Card_has_User_User1_idx` (`User_userID`),
  KEY `fk_Card_has_User_Card1_idx` (`Card_cardID`),
  KEY `fk_Card_has_User_Cart1_idx` (`reservedCardID`),
  CONSTRAINT `fk_Card_has_User_Card1` FOREIGN KEY (`Card_cardID`) REFERENCES `card` (`cardID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Card_has_User_Cart1` FOREIGN KEY (`reservedCardID`) REFERENCES `reservedCard` (`reservedCardID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Card_has_User_User1` FOREIGN KEY (`User_userID`) REFERENCES `user` (`userID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cardoffer`
--

LOCK TABLES `cardoffer` WRITE;
/*!40000 ALTER TABLE `cardoffer` DISABLE KEYS */;
INSERT INTO `cardoffer` VALUES (3,1,3,2,10,0,NULL),(4,534,4,3,15,1,2),(14,614,3,2,1,0,NULL),(24,614,4,2,2,0,NULL),(34,744,4,3,5,0,NULL);
/*!40000 ALTER TABLE `cardoffer` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cardset`
--

LOCK TABLES `cardset` WRITE;
/*!40000 ALTER TABLE `cardset` DISABLE KEYS */;
INSERT INTO `cardset` VALUES (1,'Maximum Crisis','MACR'),(2,'Raging Tempest','RATE'),(3,'Invasion: Vengeance','INOV'),(4,'The Dark Illusion','TDIL'),(5,'Battle of Legend: Light\'s Revenge','BLLR'),(6,'Pendulum Evolution','PEVO'),(7,'Shining Victories','SHVI'),(8,'Breakers of Shadow','BOSH');
/*!40000 ALTER TABLE `cardset` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservedCard`
--

DROP TABLE IF EXISTS `reservedCard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservedCard` (
  `reservedCardID` int(11) NOT NULL AUTO_INCREMENT,
  `User_userID` int(11) NOT NULL,
  PRIMARY KEY (`reservedCardID`),
  KEY `fk_Cart_User1_idx` (`User_userID`),
  CONSTRAINT `fk_Cart_User1` FOREIGN KEY (`User_userID`) REFERENCES `user` (`userID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservedCard`
--

LOCK TABLES `reservedCard` WRITE;
/*!40000 ALTER TABLE `reservedCard` DISABLE KEYS */;
INSERT INTO `reservedCard` VALUES (2,3);
/*!40000 ALTER TABLE `reservedCard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `country` (
  `countryID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `abbr` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`countryID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,'Croatia','CRO'),(2,'United Kingdom','UK'),(3,'France','FR'),(4,'Italy','ITA'),(5,'Germany','GER'),(6,'Spain','SPA'),(7,'Netherlands','NL'),(8,'Austria','AU');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `credibility`
--

DROP TABLE IF EXISTS `credibility`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `credibility` (
  `credibilityID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(80) DEFAULT NULL,
  `description` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`credibilityID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credibility`
--

LOCK TABLES `credibility` WRITE;
/*!40000 ALTER TABLE `credibility` DISABLE KEYS */;
INSERT INTO `credibility` VALUES (1,'Outstanding','Outstanding seller'),(2,'Very Good','Very good  seller'),(3,'Good','Good  seller'),(4,'Average','Average  seller'),(5,'Bad','Bad  seller');
/*!40000 ALTER TABLE `credibility` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `language`
--

DROP TABLE IF EXISTS `language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `language` (
  `languageID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(80) DEFAULT NULL,
  `abbr` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`languageID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `language`
--

LOCK TABLES `language` WRITE;
/*!40000 ALTER TABLE `language` DISABLE KEYS */;
INSERT INTO `language` VALUES (1,'English','EN'),(2,'French','FR'),(3,'German','GER'),(4,'Spanish','SPA'),(5,'Italian','ITA'),(6,'Portuguese','POR');
/*!40000 ALTER TABLE `language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `orderID` int(11) NOT NULL AUTO_INCREMENT,
  `details` varchar(45) DEFAULT NULL,
  `userID` int(11) NOT NULL,
  `addressID` int(11) NOT NULL,
  `paymentMethodID` int(11) NOT NULL,
  PRIMARY KEY (`orderID`),
  KEY `fk_Order_User1_idx` (`userID`),
  KEY `fk_Order_Address1_idx` (`addressID`),
  KEY `fk_Order_PaymentMethod1_idx` (`paymentMethodID`),
  CONSTRAINT `fk_Order_Address1` FOREIGN KEY (`addressID`) REFERENCES `shippingaddress` (`addressID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Order_PaymentMethod1` FOREIGN KEY (`paymentMethodID`) REFERENCES `paymentmethod` (`paymentMethodID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Order_User1` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (2,'dasd',3,1,1);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paymentmethod`
--

DROP TABLE IF EXISTS `paymentmethod`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paymentmethod` (
  `paymentMethodID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(80) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`paymentMethodID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paymentmethod`
--

LOCK TABLES `paymentmethod` WRITE;
/*!40000 ALTER TABLE `paymentmethod` DISABLE KEYS */;
INSERT INTO `paymentmethod` VALUES (1,'Visa','Visa credit card'),(2,'MasterCard','MasterCard credit card'),(3,'American Express','American Express credit card'),(4,'PayPal','PayPal online payment service');
/*!40000 ALTER TABLE `paymentmethod` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rarity`
--

DROP TABLE IF EXISTS `rarity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rarity` (
  `rarityID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `abbr` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`rarityID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rarity`
--

LOCK TABLES `rarity` WRITE;
/*!40000 ALTER TABLE `rarity` DISABLE KEYS */;
INSERT INTO `rarity` VALUES (1,'Common','C'),(2,'Rare','R'),(3,'Super Rare','SR'),(4,'Ultra Rare','UR'),(5,'Ultimate Rare','UtR'),(6,'Secret Rare','ScR'),(7,'Gold Rare','GR');
/*!40000 ALTER TABLE `rarity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `set`
--

DROP TABLE IF EXISTS `set`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `set` (
  `setID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `abbr` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`setID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `set`
--

LOCK TABLES `set` WRITE;
/*!40000 ALTER TABLE `set` DISABLE KEYS */;
INSERT INTO `set` VALUES (1,'Maximum Crisis','MACR'),(2,'Raging Tempest','RATE'),(3,'Invasion: Vengeance','INOV'),(4,'The Dark Illusion','TDIL'),(5,'Battle of Legend: Light\'s Revenge','BLLR'),(6,'Pendulum Evolution','PEVO'),(7,'Shining Victories','SHVI'),(8,'Breakers of Shadow','BOSH');
/*!40000 ALTER TABLE `set` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shippingaddress`
--

DROP TABLE IF EXISTS `shippingaddress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shippingaddress` (
  `addressID` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(200) DEFAULT NULL,
  `zipcode` varchar(6) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `countryID` int(11) NOT NULL,
  PRIMARY KEY (`addressID`),
  KEY `fk_Address_Country1_idx` (`countryID`),
  CONSTRAINT `fk_Address_Country1` FOREIGN KEY (`countryID`) REFERENCES `country` (`countryID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shippingaddress`
--

LOCK TABLES `shippingaddress` WRITE;
/*!40000 ALTER TABLE `shippingaddress` DISABLE KEYS */;
INSERT INTO `shippingaddress` VALUES (1,'Levo','1010','Desno',1);
/*!40000 ALTER TABLE `shippingaddress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shippingaddresstemplate`
--

DROP TABLE IF EXISTS `shippingaddresstemplate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shippingaddresstemplate` (
  `addressID` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(200) DEFAULT NULL,
  `zipcode` varchar(6) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `countryID` int(11) NOT NULL,
  PRIMARY KEY (`addressID`),
  KEY `fk_Address_Country1_idx` (`countryID`),
  CONSTRAINT `fk_Address_Country10` FOREIGN KEY (`countryID`) REFERENCES `country` (`countryID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shippingaddresstemplate`
--

LOCK TABLES `shippingaddresstemplate` WRITE;
/*!40000 ALTER TABLE `shippingaddresstemplate` DISABLE KEYS */;
INSERT INTO `shippingaddresstemplate` VALUES (1,'Levo','1010','Desno',1);
/*!40000 ALTER TABLE `shippingaddresstemplate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `soldcard`
--

DROP TABLE IF EXISTS `soldcard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `soldcard` (
  `soldCardID` int(11) NOT NULL AUTO_INCREMENT,
  `quantity` int(11) DEFAULT '1',
  `price` decimal(10,0) DEFAULT '1',
  `Card_cardID` int(11) NOT NULL,
  `buyerID` int(11) NOT NULL,
  `sellerID` int(11) NOT NULL,
  `orderID` int(11) NOT NULL,
  PRIMARY KEY (`soldCardID`),
  KEY `fk_Card_has_User_Card1_idx` (`Card_cardID`),
  KEY `fk_SoldCard_User1_idx` (`buyerID`),
  KEY `fk_SoldCard_User2_idx` (`sellerID`),
  KEY `fk_SoldCard_Order1_idx` (`orderID`),
  CONSTRAINT `fk_Card_has_User_Card10` FOREIGN KEY (`Card_cardID`) REFERENCES `card` (`cardID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_SoldCard_Order1` FOREIGN KEY (`orderID`) REFERENCES `order` (`orderID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_SoldCard_User1` FOREIGN KEY (`buyerID`) REFERENCES `user` (`userID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_SoldCard_User2` FOREIGN KEY (`sellerID`) REFERENCES `user` (`userID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `soldcard`
--

LOCK TABLES `soldcard` WRITE;
/*!40000 ALTER TABLE `soldcard` DISABLE KEYS */;
INSERT INTO `soldcard` VALUES (1,3,10,1,3,4,2);
/*!40000 ALTER TABLE `soldcard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subtype`
--

DROP TABLE IF EXISTS `subtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subtype` (
  `subTypeID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`subTypeID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subtype`
--

LOCK TABLES `subtype` WRITE;
/*!40000 ALTER TABLE `subtype` DISABLE KEYS */;
INSERT INTO `subtype` VALUES (1,'Monster'),(2,'Spell'),(3,'Trap');
/*!40000 ALTER TABLE `subtype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type`
--

DROP TABLE IF EXISTS `type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `type` (
  `typeID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`typeID`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type`
--

LOCK TABLES `type` WRITE;
/*!40000 ALTER TABLE `type` DISABLE KEYS */;
INSERT INTO `type` VALUES (4,'Synchro'),(14,'Pendulum');
/*!40000 ALTER TABLE `type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `cardsBought` int(11) DEFAULT NULL,
  `cardsSold` int(11) DEFAULT NULL,
  `email` varchar(80) DEFAULT NULL,
  `addressID` int(11) NOT NULL,
  `credibilityID` int(11) NOT NULL,
  PRIMARY KEY (`userID`),
  KEY `fk_User_ShippingAddressTemplate1_idx` (`addressID`),
  KEY `fk_User_Credibility1_idx` (`credibilityID`),
  CONSTRAINT `fk_User_Credibility1` FOREIGN KEY (`credibilityID`) REFERENCES `credibility` (`credibilityID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_ShippingAddressTemplate1` FOREIGN KEY (`addressID`) REFERENCES `shippingaddresstemplate` (`addressID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (3,'PaxKing','paco',5,10,NULL,1,1),(4,'Memfisto','memfi',10,5,NULL,1,2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
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

-- Dump completed on 2017-11-23 21:25:52
