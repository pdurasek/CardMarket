-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
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
-- Dumping data for table `card`
--

LOCK TABLES `card` WRITE;
/*!40000 ALTER TABLE `card` DISABLE KEYS */;
INSERT INTO `card` VALUES (1,'Lepa Karta',1,'Dost Dobra Karta','ash.png',4,1,1,1,1,1),(534,'Azdaja',1,'Dost Dobra Karta','ash.png',4,1,2,1,2,1),(544,'Leva Ko Desna',1,'Dost Dobra Karta','ash.png',4,1,1,1,3,1),(554,'Zmaj',1,'Dost Dobra Karta','ash.png',4,1,2,1,4,1),(564,'Ruka',1,'Dost Dobra Karta','ash.png',4,1,3,1,1,1),(574,'Lepa Mara',1,'Dost Dobra Karta','ash.png',4,1,4,1,2,1),(584,'Beba',1,'Dost Dobra Karta','ash.png',4,1,5,1,3,1),(594,'Bik',1,'Dost Dobra Karta','ash.png',4,1,2,1,4,1),(604,'Kurati Bik',1,'Dost Dobra Karta','ash.png',4,1,3,1,2,1),(614,'Heri Poter',1,'Dost Dobra Karta','ash.png',4,1,1,1,1,1),(624,'Brat Od Hulka',1,'Dost Dobra Karta','ash.png',4,1,4,1,1,1),(634,'Undergdoun',1,'Dost Dobra Karta','ash.png',4,1,5,1,2,1),(644,'Benzinska',1,'Dost Dobra Karta','ash.png',4,1,1,1,4,1),(654,'Pumpa',1,'Dost Dobra Karta','ash.png',4,1,1,1,5,1),(664,'Macka',1,'Dost Dobra Karta','ash.png',4,1,1,1,6,1),(674,'Pas s Nogom',1,'Dost Dobra Karta','ash.png',4,1,2,1,7,1),(684,'Ruzni Zmaj',1,'Dost Dobra Karta','ash.png',4,1,1,1,4,1),(694,'Lepi Zmaj',1,'Dost Dobra Karta','ash.png',4,1,3,1,2,1),(704,'Kek',1,'Dost Dobra Karta','ash.png',4,1,1,1,2,1),(714,'Bone',1,'Dost Dobra Karta','ash.png',4,1,1,1,4,1),(724,'Zdravko',1,'Dost Dobra Karta','ash.png',4,1,3,1,8,1),(734,'Suq Madiq',1,'Dost Dobra Karta','ash.png',4,1,1,1,5,1),(744,'Heri Poter',0,'Nelosa Karta','ghost.png',4,1,3,1,2,3);
/*!40000 ALTER TABLE `card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `cardcondition`
--

LOCK TABLES `cardcondition` WRITE;
/*!40000 ALTER TABLE `cardcondition` DISABLE KEYS */;
INSERT INTO `cardcondition` VALUES (1,'Mint','M'),(2,'Near Mint','NM'),(3,'Excellent','E'),(4,'Good','G'),(5,'Light Played','LP'),(6,'Played','P'),(7,'Damaged','D');
/*!40000 ALTER TABLE `cardcondition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `cardset`
--

LOCK TABLES `cardset` WRITE;
/*!40000 ALTER TABLE `cardset` DISABLE KEYS */;
INSERT INTO `cardset` VALUES (1,'Maximum Crisis','MACR'),(2,'Raging Tempest','RATE'),(3,'Invasion: Vengeance','INOV'),(4,'The Dark Illusion','TDIL'),(5,'Battle of Legend: Light\'s Revenge','BLLR'),(6,'Pendulum Evolution','PEVO'),(7,'Shining Victories','SHVI'),(8,'Breakers of Shadow','BOSH'),(9,'Dimension of Chaos','DOCS'),(10,'Clash of Rebelions','CORE'),(11,'Crossed Souls','CROS'),(12,'Secrets if Eternity','SECE'),(13,'The New Challengers','NECH'),(14,'Duelist Alliance','DUEA');
/*!40000 ALTER TABLE `cardset` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,'Croatia','CRO'),(2,'United Kingdom','UK'),(3,'France','FR'),(4,'Italy','ITA'),(5,'Germany','GER'),(6,'Spain','SPA'),(7,'Netherlands','NL'),(8,'Austria','AU');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `credibility`
--

LOCK TABLES `credibility` WRITE;
/*!40000 ALTER TABLE `credibility` DISABLE KEYS */;
INSERT INTO `credibility` VALUES (1,'Outstanding','Outstanding seller'),(2,'Very Good','Very good  seller'),(3,'Good','Good  seller'),(4,'Average','Average  seller'),(5,'Bad','Bad  seller');
/*!40000 ALTER TABLE `credibility` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `language`
--

LOCK TABLES `language` WRITE;
/*!40000 ALTER TABLE `language` DISABLE KEYS */;
INSERT INTO `language` VALUES (1,'English','EN'),(2,'French','FR'),(3,'German','GER'),(4,'Spanish','SPA'),(5,'Italian','ITA'),(6,'Portuguese','POR');
/*!40000 ALTER TABLE `language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (2,'dasd',3,1,1);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `paymentmethod`
--

LOCK TABLES `paymentmethod` WRITE;
/*!40000 ALTER TABLE `paymentmethod` DISABLE KEYS */;
INSERT INTO `paymentmethod` VALUES (1,'Visa','Visa credit card'),(2,'MasterCard','MasterCard credit card'),(3,'American Express','American Express credit card'),(4,'PayPal','PayPal online payment service');
/*!40000 ALTER TABLE `paymentmethod` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `rarity`
--

LOCK TABLES `rarity` WRITE;
/*!40000 ALTER TABLE `rarity` DISABLE KEYS */;
INSERT INTO `rarity` VALUES (1,'Common','C'),(2,'Rare','R'),(3,'Super Rare','SR'),(4,'Ultra Rare','UR'),(5,'Ultimate Rare','UtR'),(6,'Secret Rare','ScR'),(7,'Gold Rare','GR');
/*!40000 ALTER TABLE `rarity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `shippingaddress`
--

LOCK TABLES `shippingaddress` WRITE;
/*!40000 ALTER TABLE `shippingaddress` DISABLE KEYS */;
INSERT INTO `shippingaddress` VALUES (1,'Levo','1010','Desno',1);
/*!40000 ALTER TABLE `shippingaddress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `shippingaddresstemplate`
--

LOCK TABLES `shippingaddresstemplate` WRITE;
/*!40000 ALTER TABLE `shippingaddresstemplate` DISABLE KEYS */;
INSERT INTO `shippingaddresstemplate` VALUES (1,'Levo','1010','Desno',1);
/*!40000 ALTER TABLE `shippingaddresstemplate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `soldcard`
--

LOCK TABLES `soldcard` WRITE;
/*!40000 ALTER TABLE `soldcard` DISABLE KEYS */;
INSERT INTO `soldcard` VALUES (1,3,10,1,3,4,2);
/*!40000 ALTER TABLE `soldcard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `subtype`
--

LOCK TABLES `subtype` WRITE;
/*!40000 ALTER TABLE `subtype` DISABLE KEYS */;
INSERT INTO `subtype` VALUES (1,'Monster'),(2,'Spell'),(3,'Trap');
/*!40000 ALTER TABLE `subtype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `type`
--

LOCK TABLES `type` WRITE;
/*!40000 ALTER TABLE `type` DISABLE KEYS */;
INSERT INTO `type` VALUES (4,'Synchro'),(14,'Pendulum');
/*!40000 ALTER TABLE `type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (3,'PaxKing','paco',5,10,NULL,1,1),(4,'Memfisto','memfi',10,5,NULL,1,2),(14,'Dedo','6d2aa05bb3ee41964f1830cd70f9aef0b36a0138e5116f4b64d00a960ab11053',0,0,'dedo@dedo.karatana',NULL,NULL),(24,'Jusuf','6d2aa05bb3ee41964f1830cd70f9aef0b36a0138e5116f4b64d00a960ab11053',0,0,'sinoddede@dedo.com',NULL,NULL);
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

-- Dump completed on 2017-11-24 23:18:36
