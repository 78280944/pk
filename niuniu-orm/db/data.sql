-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 10.10.0.115    Database: lottery
-- ------------------------------------------------------
-- Server version	5.7.12-0ubuntu1

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
-- Dumping data for table `account_detail`
--

LOCK TABLES `account_detail` WRITE;
/*!40000 ALTER TABLE `account_detail` DISABLE KEYS */;
INSERT INTO `account_detail` VALUES (1,1,'system',0.00,0.0000,0.0000,'1','','0','0',0.00,NULL,NULL),(2,1000,'admin',1000000000.00,0.0070,0.8000,'1','system','0','0',0.00,NULL,NULL),(3,1001,'agency1',1000000.00,0.0070,0.8000,'1','admin','1','1',0.00,NULL,NULL),(4,1002,'agency2',500000.00,0.0060,0.6500,'1','agency1','2','1',0.00,NULL,NULL),(5,1003,'agency3',100000.00,0.0050,0.5000,'1','agency2','3','1',0.00,NULL,NULL),(6,1001,'play1',100000.00,0.0050,0.0000,'1','agency3','3','3',0.00,NULL,NULL),(7,1002,'play2',500000.00,0.0060,0.0000,'1','agency2','3','3',0.00,NULL,NULL);
/*!40000 ALTER TABLE `account_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `account_info`
--

LOCK TABLES `account_info` WRITE;
/*!40000 ALTER TABLE `account_info` DISABLE KEYS */;
INSERT INTO `account_info` VALUES (1000,'ceshi1','ceshi1','e10adc3949ba59abbe56e057f20f883e',0.00,0.0000,'',NULL,'','2016-11-25 15:15:20','1','wyp','1'),(1001,'play1','play1','e10adc3949ba59abbe56e057f20f883e',1000000.00,0.0050,'',NULL,'',NULL,'1','agency3','3'),(1002,'play2','play2','e10adc3949ba59abbe56e057f20f883e',1000000.00,0.0060,'',NULL,'',NULL,'1','agency2','2');
/*!40000 ALTER TABLE `account_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `lottery_item`
--

LOCK TABLES `lottery_item` WRITE;
/*!40000 ALTER TABLE `lottery_item` DISABLE KEYS */;
INSERT INTO `lottery_item` VALUES (1,'01','J12','1,2','01','角',1.9500,1.0000,0.0500),(2,'01','J23','2,3','01','角',1.9500,1.0000,0.0500),(3,'01','J41','4,1','01','角',1.9500,1.0000,0.0500),(4,'01','J34','3,4','01','角',1.9500,1.0000,0.0500),(5,'01','L12','1,2','01','连',2.8000,2.0000,0.1000),(6,'01','L13','1,3','01','连',2.8000,2.0000,0.1000),(7,'01','L14','1,4','01','连',2.8000,2.0000,0.1000),(8,'01','L21','2,1','01','连',2.8000,2.0000,0.1000),(9,'01','L24','2,4','01','连',2.8000,2.0000,0.1000),(10,'01','L23','2,3','01','连',2.8000,2.0000,0.1000),(11,'01','L32','3,2','01','连',2.8000,2.0000,0.1000),(12,'01','L31','3,1','01','连',2.8000,2.0000,0.1000),(13,'01','L34','3,4','01','连',2.8000,2.0000,0.1000),(14,'01','L41','4,1','01','连',2.8000,2.0000,0.1000),(15,'01','L42','4,2','01','连',2.8000,2.0000,0.1000),(16,'01','L43','43','01','连',2.8000,2.0000,0.1000),(17,'01','F1','1','01','番',3.7000,3.0000,0.1000),(18,'01','F2','2','01','番',3.7000,3.0000,0.1000),(19,'01','F3','3','01','番',3.7000,3.0000,0.1000),(20,'01','F4','4','01','番',3.7000,3.0000,0.1000),(21,'01','Z1','1','01','正',1.9500,1.0000,0.0500),(22,'01','Z2','2','01','正',1.9500,1.0000,0.0500),(23,'01','Z3','3','01','正',1.9500,1.0000,0.0500),(24,'01','Z4','4','01','正',1.9500,1.0000,0.0500),(25,'01','SZ234','2,3,4','01','三中',1.3000,0.3333,0.0500),(26,'01','SZ134','1,3,4','01','三中',1.3000,0.3333,0.0500),(27,'01','SZ124','1,2,4','01','三中',1.3000,0.3333,0.0500),(28,'01','SZ123','1,2,3','01','三中',1.3000,0.3333,0.0500),(29,'01','T1','1','02','特码',16.3000,17.0000,0.1000),(30,'01','T2','2','02','特码',16.3000,17.0000,0.1000),(31,'01','T3','3','02','特码',16.3000,17.0000,0.1000),(32,'01','T4','4','02','特码',16.3000,17.0000,0.1000),(33,'01','T5','5','02','特码',16.3000,17.0000,0.1000),(34,'01','T6','6','02','特码',16.3000,17.0000,0.1000),(35,'01','T7','7','02','特码',16.3000,17.0000,0.1000),(36,'01','T8','8','02','特码',16.3000,17.0000,0.1000),(37,'01','T9','9','02','特码',16.3000,17.0000,0.1000),(38,'01','T10','10','02','特码',16.3000,17.0000,0.1000),(39,'01','T11','11','02','特码',16.3000,17.0000,0.1000),(40,'01','T12','12','02','特码',16.3000,17.0000,0.1000),(41,'01','T13','13','02','特码',16.3000,17.0000,0.1000),(42,'01','T14','14','02','特码',16.3000,17.0000,0.1000),(43,'01','T15','15','02','特码',16.3000,17.0000,0.1000),(44,'01','T16','16','02','特码',16.3000,17.0000,0.1000),(45,'01','T17','17','02','特码',16.3000,17.0000,0.1000),(46,'01','T18','18','02','特码',16.3000,17.0000,0.1000),(47,'01','T19','19','02','特码',16.3000,17.0000,0.1000),(48,'01','T20','20','02','特码',16.3000,17.0000,0.1000),(49,'01','T21','21','02','特码',16.3000,17.0000,0.1000),(50,'01','RED','1,2,3,4,5,6,7','02','色',2.8000,2.0000,0.1000),(51,'01','BLUE','8,9,10,11,12,13,14','02','色',2.8000,2.0000,0.1000),(52,'01','GREEN','15,16,17,18,19,20,21','02','色',2.8000,2.0000,0.1000),(53,'01','BIG','11,12,13,14,15,16,17,18,19,20','02','大小',1.9500,1.0000,0.0500),(54,'01','SMALL','1,2,3,4,5,6,7,8,9,10','02','大小',1.9500,1.0000,0.0500),(55,'01','SINGLE','1,3,5,7,9,11,13,15,17,19','02','单双',1.9500,1.0000,0.0500),(56,'01','DOUBLE','2,4,6,8,10,12,14,16,18,20','02','单双',1.9500,1.0000,0.0500);
/*!40000 ALTER TABLE `lottery_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `lottery_order`
--

LOCK TABLES `lottery_order` WRITE;
/*!40000 ALTER TABLE `lottery_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `lottery_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `lottery_order_detail`
--

LOCK TABLES `lottery_order_detail` WRITE;
/*!40000 ALTER TABLE `lottery_order_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `lottery_order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `lottery_round`
--

LOCK TABLES `lottery_round` WRITE;
/*!40000 ALTER TABLE `lottery_round` DISABLE KEYS */;
/*!40000 ALTER TABLE `lottery_round` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `lottery_round_item`
--

LOCK TABLES `lottery_round_item` WRITE;
/*!40000 ALTER TABLE `lottery_round_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `lottery_round_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `notice_info`
--

LOCK TABLES `notice_info` WRITE;
/*!40000 ALTER TABLE `notice_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `notice_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `offaccount_info`
--

LOCK TABLES `offaccount_info` WRITE;
/*!40000 ALTER TABLE `offaccount_info` DISABLE KEYS */;
INSERT INTO `offaccount_info` VALUES (1,'system','system','e10adc3949ba59abbe56e057f20f883e',1000000000.00,0.0000,0.0000,'Y1,Y2,Y3,Y4,Y5','Y1,Y2,Y3,Y4,Y5','',NULL,'',NULL,'1','','0','0',NULL,NULL),(1000,'admin','admin','e10adc3949ba59abbe56e057f20f883e',1000000000.00,1.0000,1.0000,NULL,NULL,'',NULL,NULL,'2016-11-25 18:04:46','1','admin','0','0','',''),(1001,'agency1','agency1','e10adc3949ba59abbe56e057f20f883e',1000000.00,0.0070,0.8000,'Y1,Y2,Y3,Y4,Y5','Y1,Y2,Y3,Y4,Y5','','2016-12-17 11:09:10','',NULL,'1','admin','1','1',NULL,NULL),(1002,'agency2','agency2','e10adc3949ba59abbe56e057f20f883e',1000000.00,0.0060,0.8000,'Y1,Y2,Y3,Y4,Y5','Y1,Y2,Y3,Y4,Y5','','2016-12-17 11:09:10','',NULL,'1','agency1','2','1',NULL,NULL),(1003,'agency3','agency3','e10adc3949ba59abbe56e057f20f883e',1000000.00,0.0050,0.8000,'Y1,Y2,Y3,Y4,Y5','Y1,Y2,Y3,Y4,Y5','','2016-12-17 11:09:10','',NULL,'1','agency2','2','1',NULL,NULL),(1004,'sub1','sub1','e10adc3949ba59abbe56e057f20f883e',0.00,0.0000,0.0000,'Y1,Y2,Y3,Y4,Y5','','',NULL,'',NULL,'1','agency3','2','2',NULL,NULL),(1015,'wyp','wyp','e10adc3949ba59abbe56e057f20f883e',0.00,0.0000,0.0000,'Y1,Y2,Y3,Y4,Y5','Y1,Y2,Y3,Y4,Y5','','2016-11-29 11:09:10','',NULL,'1','admin','1','1','','');
/*!40000 ALTER TABLE `offaccount_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `trade_info`
--

LOCK TABLES `trade_info` WRITE;
/*!40000 ALTER TABLE `trade_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `trade_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-07 14:45:57
