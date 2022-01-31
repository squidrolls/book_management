-- MySQL dump 10.13  Distrib 8.0.21, for macos10.15 (x86_64)
--
-- Host: localhost    Database: booksystem1
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `book_admin`
--

DROP TABLE IF EXISTS `book_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_admin` (
  `admin_id` int NOT NULL,
  `admin_password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `admin_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_admin`
--

LOCK TABLES `book_admin` WRITE;
/*!40000 ALTER TABLE `book_admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `book_admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `buy_book`
--

DROP TABLE IF EXISTS `buy_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `buy_book` (
  `buy_id` int NOT NULL,
  `teacher_id` int NOT NULL,
  `open_id` char(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ISBN` char(17) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `book_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `author` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `press` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `publish_date` year NOT NULL,
  `admin_id` int DEFAULT NULL,
  `buy_status` int DEFAULT '0',
  PRIMARY KEY (`buy_id`),
  KEY `FK_Reference_10` (`open_id`),
  KEY `FK_Reference_12` (`admin_id`),
  KEY `FK_Reference_9` (`teacher_id`),
  CONSTRAINT `FK_Reference_10` FOREIGN KEY (`open_id`) REFERENCES `open_class` (`open_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_12` FOREIGN KEY (`admin_id`) REFERENCES `book_admin` (`admin_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buy_book`
--

LOCK TABLES `buy_book` WRITE;
/*!40000 ALTER TABLE `buy_book` DISABLE KEYS */;
/*!40000 ALTER TABLE `buy_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `choose_class`
--

DROP TABLE IF EXISTS `choose_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `choose_class` (
  `student_id` int NOT NULL,
  `open_id` char(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`student_id`,`open_id`),
  KEY `FK_Reference_6` (`open_id`),
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`open_id`) REFERENCES `open_class` (`open_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `choose_class`
--

LOCK TABLES `choose_class` WRITE;
/*!40000 ALTER TABLE `choose_class` DISABLE KEYS */;
INSERT INTO `choose_class` VALUES (1000,'005M080401B060'),(1000,'005M081001C055'),(1000,'009M030500A002'),(1000,'011M050200A010'),(1000,'019P085239C011');
/*!40000 ALTER TABLE `choose_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `choose_textbook`
--

DROP TABLE IF EXISTS `choose_textbook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `choose_textbook` (
  `open_id` char(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ISBN` char(17) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`open_id`,`ISBN`),
  KEY `FK_Reference_8` (`ISBN`),
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`open_id`) REFERENCES `open_class` (`open_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`ISBN`) REFERENCES `textbook` (`ISBN`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `choose_textbook`
--

LOCK TABLES `choose_textbook` WRITE;
/*!40000 ALTER TABLE `choose_textbook` DISABLE KEYS */;
/*!40000 ALTER TABLE `choose_textbook` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `class`
--

DROP TABLE IF EXISTS `class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class` (
  `course_id` int NOT NULL,
  `course_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `course_introduction` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class`
--

LOCK TABLES `class` WRITE;
/*!40000 ALTER TABLE `class` DISABLE KEYS */;
INSERT INTO `class` VALUES (1,'程序设计技术','\"修改简介\"'),(2,'电子与通信工程专业英语',NULL),(3,'现代信号处理',NULL),(4,'高级嵌入式系统',NULL),(5,'控制工程专业英语',NULL),(6,'网络控制及现场总线',NULL),(7,'微机技术','在计算机技术发展的早期，软件开发主要就是程序设计。但随着技术的发展，软件系统越来越复杂，逐渐分化出许多专用的软件系统，如操作系统、数据库系统、应用服务器，而且这些专用的软件系统愈来愈成为普遍的系统环境的一部分。'),(8,'无线通信原理与技术',NULL),(9,'高级数字图像处理',NULL),(10,'数字信号处理系统设计方法与实践',NULL),(11,'高级数字通信',NULL),(12,'模式识别导论',NULL),(13,'信息安全',NULL),(14,'物联网技术及其应用',NULL),(15,'语音信号处理',NULL),(16,'智能信息处理',NULL),(17,'SoC设计方法与实现',NULL),(18,'系统工程理论及方法',NULL),(19,'多变量系统理论',NULL),(20,'最优控制与状态估计',NULL),(21,'系统辨识',NULL),(22,'CIMS与CIPS技术及应用',NULL),(23,'智能化仪表',NULL),(24,'高级图形学',NULL),(25,'智能仪表原理与设计技术',NULL),(26,'现代传感技术','\"修改修改修改简介！\"'),(27,'误差理论','\"try1try\"'),(28,'仪表可靠性技术',NULL),(29,'无线传感器网络',NULL),(30,'数字图像处理',NULL),(31,'嵌入式系统及应用',NULL),(32,'专业英语阅读（控、仪）',NULL),(33,'先进控制技术',NULL),(34,'智能控制',NULL),(35,'网络控制及现场总线',NULL),(36,'智能信息处理',NULL),(37,'现代控制理论',NULL),(38,'现代变频调速技术',NULL),(39,'智能电网',NULL),(40,'工业过程监控技术',NULL),(41,'生产调度及其智能优化',NULL),(42,'现代电力电子技术',NULL),(43,'计算机工程数学',NULL),(44,'算法设计与分析',NULL),(45,'高级软件工程',NULL),(46,'现代数据库',NULL),(47,'计算机网络',NULL),(48,'信息安全技术',NULL),(49,'专业英语（计、软）(必修）',NULL),(50,'人工智能及其应用',NULL),(51,'计算机图形学',NULL),(52,'数据挖掘',NULL),(53,'云计算技术',NULL),(54,'最优化方法',NULL),(55,'计算方法',NULL),(56,'矩阵理论',NULL),(57,'随机过程',NULL),(58,'最优化方法',NULL),(59,'矩阵理论',NULL),(60,'中国特色社会主义理论与实践研究',NULL),(61,'政治理论',NULL),(62,'国际英语交流',NULL),(63,'高级英语阅读',NULL),(64,'学术英语写作',NULL),(65,'研究生基础英语',NULL),(66,'知识产权',NULL),(67,'文献检索与检索案例研究',NULL),(68,'市场经济与法律实务',NULL),(69,'管理学基础',NULL),(70,'国际金融与国际贸易',NULL);
/*!40000 ALTER TABLE `class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `elink`
--

DROP TABLE IF EXISTS `elink`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `elink` (
  `ISBN` char(17) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `elink` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`ISBN`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`ISBN`) REFERENCES `textbook` (`ISBN`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `elink`
--

LOCK TABLES `elink` WRITE;
/*!40000 ALTER TABLE `elink` DISABLE KEYS */;
INSERT INTO `elink` VALUES ('978-7-121-13824-9','http://www.apabi.com/hdlgdx/pub.mvc?pid=book.detail&metaid=m.20151206-XRT-902-0690&cult=CN'),('978-7-301-26032-6','http://www.apabi.com/hdlgdx/pub.mvc?pid=book.detail&metaid=m.20190902-BJDX-RXJC-0702&cult=CN'),('978-7-305-15192-7','http://www.apabi.com/hdlgdx/pub.mvc?pid=book.detail&metaid=m.20151123-XRT-902-0555&cult=CN'),('978-7-5117-2211-9','http://www.apabi.com/hdlgdx/pub.mvc?pid=book.detail&metaid=m.20150618-XRT-889-0277&cult=CN&username=%E6%9D%A5%E8%87%AA%20%E5%8D%8E%E4%B8%9C%E7%90%86%E5%B7%A5%E5%A4%A7%E5%AD%A6%20%E7%9A%84%E7%94%A8%E6%88%B7&ug=%E5%8D%8E%E4%B8%9C%E7%90%86%E5%B7%A5%E5%A4%A7%E5%AD%A6%E6%97%A0%E5%AF%86%E7%A0%81%E7%94%A8%E6%88%B7%E7%BB%84'),('978-7-5124-0283-6','http://www.apabi.com/hdlgdx/pub.mvc?pid=book.detail&metaid=m.20111212-YPT-889-0077&cult=CN'),('978-7-5612-2279-9','http://www.apabi.com/hdlgdx/pub.mvc?pid=book.detail&metaid=m.20100929-BPO-889-0590&cult=CN&username=%E6%9D%A5%E8%87%AA%20%E5%8D%8E%E4%B8%9C%E7%90%86%E5%B7%A5%E5%A4%A7%E5%AD%A6%20%E7%9A%84%E7%94%A8%E6%88%B7&ug=%E5%8D%8E%E4%B8%9C%E7%90%86%E5%B7%A5%E5%A4%A7%E5%AD%A6%E6%97%A0%E5%AF%86%E7%A0%81%E7%94%A8%E6%88%B7%E7%BB%84'),('978-7-5628-2237-0','http://www.apabi.com/hdlgdx/pub.mvc?pid=book.detail&metaid=m.20081225-m008-w009-019&cult=CN'),('978-7-5647-1432-1','http://www.apabi.com/hdlgdx/pub.mvc?pid=book.detail&metaid=m.20140925-XRT-889-1231&cult=CN');
/*!40000 ALTER TABLE `elink` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `open_class`
--

DROP TABLE IF EXISTS `open_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `open_class` (
  `open_id` char(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `course_id` int NOT NULL,
  `teacher_id` int DEFAULT NULL,
  `start_year` year NOT NULL,
  `start_term` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `grade` int DEFAULT NULL,
  `major` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`open_id`),
  KEY `FK_Reference_2` (`teacher_id`),
  KEY `FK_Reference_5` (`course_id`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`course_id`) REFERENCES `class` (`course_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `open_class`
--

LOCK TABLES `open_class` WRITE;
/*!40000 ALTER TABLE `open_class` DISABLE KEYS */;
INSERT INTO `open_class` VALUES ('005F085208B013',1,12548,2021,'1',2,'计算机科学'),('005F085208C009',2,12548,2021,'2',1,'电子信息'),('005F085208C018',3,12550,2021,'2',1,'计算机科学'),('005F085208C019',4,12551,2021,'1',2,'计算机科学'),('005F085210C005',5,12552,2021,'1',3,'机械工程'),('005F085210C020',6,12553,2021,'2',3,'计算机科学'),('005M080401B060',7,12553,2021,'1',4,'电子信息'),('005M081001B062',8,12555,2021,'2',1,'计算机科学'),('005M081001B150',9,12556,2021,'1',1,'计算机科学'),('005M081001B164',10,12557,2021,'1',2,'计算机科学'),('005M081001C054',11,12557,2021,'2',2,'计算机科学'),('005M081001C055',12,12559,2021,'1',2,'计算机科学'),('005M081001C156',13,12560,2021,'1',2,'计算机科学'),('005M081001C179',14,12561,2021,'1',4,'计算机科学'),('005M081001C180',15,12562,2021,'2',3,'计算机科学'),('005M081001C183',16,12563,2021,'1',2,'计算机科学'),('005M081001C187',17,12564,2021,'2',2,'计算机科学'),('005M081100B105',18,12565,2021,'2',2,'计算机科学'),('005M081101B101',19,12566,2021,'1',3,'计算机科学'),('005M081101B102',20,12567,2021,'1',3,'计算机科学'),('005M081101B103',21,12568,2021,'2',1,'计算机科学'),('005M081101C014',22,12569,2021,'1',4,'计算机科学'),('005M081101C020',23,12569,2021,'1',2,'计算机科学'),('005M081201C146',24,12569,2021,'2',3,'计算机科学'),('005P085203B017',25,12569,2021,'2',1,'计算机科学'),('005P085203B018',26,12573,2021,'1',4,'计算机科学'),('005P085203C011',27,12573,2021,'1',2,'计算机科学'),('005P085203C060',28,12575,2021,'2',2,'计算机科学'),('005P085203C061',29,12576,2021,'2',1,'计算机科学'),('005P085208B036',30,12577,2021,'1',1,'计算机科学'),('005P085208C040',31,12577,2021,'2',2,'计算机科学'),('005P085210B001',32,12577,2021,'2',2,'计算机科学'),('005P085210B008',33,12577,2021,'2',2,'计算机科学'),('005P085210B052',34,12581,2021,'1',3,'计算机科学'),('005P085210B053',35,12582,2021,'2',3,'计算机科学'),('005P085210B059',36,12582,2021,'1',2,'计算机科学'),('005P085210B063',37,12582,2021,'1',4,'计算机科学'),('005P085210C009',38,12585,2021,'1',2,'计算机科学'),('005P085210C048',39,12586,2021,'2',2,'计算机科学'),('005P085210C054',40,12587,2021,'1',4,'计算机科学'),('005P085210C055',41,12587,2021,'1',2,'计算机科学'),('005P085210C056',42,12587,2021,'2',1,'计算机科学'),('005P085211B020',43,12587,2021,'2',3,'计算机科学'),('005P085211B021',44,12591,2021,'1',4,'计算机科学'),('005P085211B022',45,12591,2021,'2',3,'计算机科学'),('005P085211B023',46,12593,2021,'2',2,'计算机科学'),('005P085211B024',47,12594,2021,'2',2,'计算机科学'),('005P085211B025',48,12595,2021,'2',1,'计算机科学'),('005P085211C007',49,12596,2021,'2',1,'计算机科学'),('005P085211C026',50,12597,2021,'2',1,'计算机科学'),('005P085211C027',51,12597,2021,'2',2,'计算机科学'),('005P085211C029',52,12599,2021,'1',3,'计算机科学'),('005P085211C062',53,12600,2021,'1',2,'计算机科学'),('006M070100A002',54,12601,2021,'1',2,'计算机科学'),('006M070100A003',55,12602,2021,'1',4,'计算机科学'),('006M070100A006',56,12603,2021,'1',2,'计算机科学'),('006M070100A007',57,12604,2021,'1',4,'计算机科学'),('006P070100A001',58,12605,2021,'2',3,'计算机科学'),('006P070100A005',59,12606,2021,'2',2,'计算机科学'),('009M030500A002',60,12607,2021,'2',3,'社会科学'),('009P030500A001',61,12608,2021,'2',2,'社会科学'),('011M050200A009',62,12609,2021,'2',2,'英语'),('011M050200A010',63,12610,2021,'1',2,'英语'),('011M050200A011',64,12611,2021,'1',2,'英语'),('011P050200A001',65,12612,2021,'2',2,'英语'),('015F030101D001',66,12613,2021,'1',2,'社会科学'),('015P030101D001',66,12614,2021,'1',3,'社会科学'),('017F120500A028',67,12615,2021,'2',2,'计算机科学'),('017P120500A019',67,12615,2021,'2',3,'计算机科学'),('019P085239C011',68,12617,2021,'1',2,'金融管理'),('019P085239D001',69,12618,2021,'1',2,'金融管理'),('019P085239D002',70,12619,2021,'1',2,'金融管理');
/*!40000 ALTER TABLE `open_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `student_id` int NOT NULL,
  `student_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `student_password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `grade` int NOT NULL,
  `major` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1000,'云帆','123456',4,'计算机科学');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher` (
  `teacher_id` int NOT NULL,
  `teacher_password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `teacher_info` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES (12548,'123456','曹老师'),(12550,'123456','常老师'),(12551,'123456','陈老师1'),(12552,'123456','陈老师2'),(12553,'123456','陈老师3'),(12555,'123456','陈老师4'),(12556,'123456','程老师'),(12557,'123456','戴老师'),(12559,'123456','杜老师1'),(12560,'123456','杜老师2'),(12561,'123456','樊老师'),(12562,'123456','范老师'),(12563,'123456','方老师1'),(12564,'123456','方老师2'),(12565,'123456','傅老师'),(12566,'123456','谷老师'),(12567,'123456','顾老师1'),(12568,'123456','顾老师2'),(12569,'123456','管老师'),(12573,'123456','郭老师1'),(12575,'123456','郭老师2'),(12576,'123456','过老师'),(12577,'123456','何老师1'),(12581,'123456','何老师2'),(12582,'123456','华老师'),(12585,'123456','黄老师1'),(12586,'123456','黄老师2'),(12587,'123456','黄老师3'),(12591,'123456','霍老师'),(12593,'123456','江老师'),(12594,'123456','蒋老师1'),(12595,'123456','蒋老师2'),(12596,'123456','蒋老师3'),(12597,'123456','李老师1'),(12599,'123456','李老师2'),(12600,'123456','李老师3'),(12601,'123456','李老师4'),(12602,'123456','李老师5'),(12603,'123456','刘老师1'),(12604,'123456','刘老师2'),(12605,'123456','刘老师3'),(12606,'123456','刘老师4'),(12607,'123456','刘老师5'),(12608,'123456','刘老师6'),(12609,'123456','卢老师'),(12610,'123456','鲁老师'),(12611,'123456','罗老师1'),(12612,'123456','罗老师2'),(12613,'123456','毛老师'),(12614,'123456','米老师'),(12615,'123456','牛老师'),(12617,'123456','钱老师'),(12618,'123456','秦老师'),(12619,'123456','卿老师');
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `textbook`
--

DROP TABLE IF EXISTS `textbook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `textbook` (
  `ISBN` char(17) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `book_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `author` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `press` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `publish_date` year NOT NULL,
  `book_link` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`ISBN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `textbook`
--

LOCK TABLES `textbook` WRITE;
/*!40000 ALTER TABLE `textbook` DISABLE KEYS */;
INSERT INTO `textbook` VALUES ('7-5026-1651-9','智能化仪表原理与使用维修','韩启纲','中国计量出版社',2002,'https://lib.ecust.edu.cn/zh-hans/book/247107'),('7-5053-4050-6','CIMS中的系统集成和信息集成','白庆华, 何玉林','电子工业出版社',1997,'https://lib.ecust.edu.cn/zh-hans/book/137999'),('978-7-03-034897-5','先进控制技术','毛志忠,常玉清','科学出版社',2012,'https://lib.ecust.edu.cn/zh-hans/book/642713'),('978-7-03-042690-1','知识产权教程','闫文军,唐素琴','科学出版社',2015,'https://lib.ecust.edu.cn/zh-hans/book/743525'),('978-7-03-043462-3','最优化方法','杨庆之','科学出版社',2015,'https://lib.ecust.edu.cn/zh-hans/book/746918'),('978-7-03-046758-4','计算机网络','李军怀, 吕林涛, 张翔','科学出版社',2016,'https://lib.ecust.edu.cn/zh-hans/book/786841'),('978-7-04-034970-2','中国特色社会主义理论与实践研究','《中国特色社会主义理论与实践研究》编写组','高等教育出版社',2012,'https://lib.ecust.edu.cn/zh-hans/book/644891'),('978-7-04-041889-7','计算方法引论','徐萃薇, 孙绳武','高等教育出版社',2015,'https://lib.ecust.edu.cn/zh-hans/book/747519'),('978-7-04-044255-7','人工智能及其应用','王万良','高等教育出版社',2016,'https://lib.ecust.edu.cn/zh-hans/book/785308'),('978-7-04-044729-3','随机过程','苏中根','高等教育出版社',2016,'https://lib.ecust.edu.cn/zh-hans/book/782971'),('978-7-111-48118-8','智能电网','(美) Fereidoon P. Sioshansi等','机械工业出版社',2015,'https://lib.ecust.edu.cn/zh-hans/book/756831'),('978-7-111-48824-8','测控技术与仪器专业英语','张凤登','机械工业出版社',2015,'https://lib.ecust.edu.cn/zh-hans/book/755716'),('978-7-111-49570-3','无线传感器网络','(英) 杨双华','机械工业出版社',2015,'https://lib.ecust.edu.cn/zh-hans/book/756857'),('978-7-111-50384-2','计算机图形学及其实践教程 ','黄静','机械工业出版社',2015,'https://lib.ecust.edu.cn/zh-hans/book/761866'),('978-7-111-51340-7','数字信号处理及应用','(美) 纽伯尔德·理查德','机械工业出版社',2015,'https://lib.ecust.edu.cn/zh-hans/book/768604'),('978-7-111-51626-2','智能控制技术','韦巍','机械工业出版社',2016,'https://lib.ecust.edu.cn/zh-hans/book/777074'),('978-7-111-52049-8','现代控制理论','闫茂德, 高昂, 胡延苏','机械工业出版社',2016,'https://lib.ecust.edu.cn/zh-hans/book/783018'),('978-7-111-52167-9','物联网技术与应用','武奇生','机械工业出版社',2016,'https://lib.ecust.edu.cn/zh-hans/book/781451'),('978-7-113-17859-8','系统工程原理及应用','陈队永','中国铁道出版社',2014,'https://lib.ecust.edu.cn/zh-hans/book/725418'),('978-7-115-18921-9','企业生产调度的智能优化方法','柳毅','人民邮电出版社',2008,''),('978-7-118-10376-2','可靠性工程概论','蒋平','国防工业出版社',2015,'https://lib.ecust.edu.cn/zh-hans/book/777838'),('978-7-121-13824-9','SoC设计方法与实现 ','郭 炜','电子工业出版社',2011,'https://lib.ecust.edu.cn/zh-hans/book/614495'),('978-7-121-17659-3','无线通信原理与应用','(美)Theodore S. Rappaport','电子工业出版社',2012,'https://lib.ecust.edu.cn/zh-hans/book/647997'),('978-7-121-20197-4','数字图像处理','(美)Rafael C. Gonzalez,(美)Richard E. Wo','电子工业出版社',2014,'https://lib.ecust.edu.cn/zh-hans/book/702216'),('978-7-121-20677-1','矩阵理论与方法 ','吴昌悫,魏洪增','电子工业出版社',2013,'https://lib.ecust.edu.cn/zh-hans/book/687844'),('978-7-121-21429-5','计算机数学 ','游安军','电子工业出版社',2013,'https://lib.ecust.edu.cn/zh-hans/book/689199'),('978-7-121-23933-5','现场总线控制网络技术','雷霖','电子工业出版社',2015,'https://lib.ecust.edu.cn/zh-hans/book/743457'),('978-7-121-24614-2','计算机图形学','(美) Donald Hearn, M. Pauline Baker, War','电子工业出版社',2014,'https://lib.ecust.edu.cn/zh-hans/book/739025'),('978-7-121-25187-0','数字通信','(美) Bernard Sklar','电子工业出版社',2015,'https://lib.ecust.edu.cn/zh-hans/book/755760'),('978-7-121-26131-2','计算机专业英语教程 ','金志权, 张幸儿','电子工业出版社',2015,'https://lib.ecust.edu.cn/zh-hans/book/760851'),('978-7-300-21553-2','马克思主义政治理论研究','钟明华, 叶啟绩','中国人民大学出版社',2015,'https://lib.ecust.edu.cn/zh-hans/book/765711'),('978-7-301-23110-4','高级英语阅读强化训练教程','李方慧','北京大学出版社',2013,'https://lib.ecust.edu.cn/zh-hans/book/690844'),('978-7-301-26032-6','管理学理论与实务','任广新, 陈葆华','北京大学出版社',2016,'https://lib.ecust.edu.cn/zh-hans/book/781646'),('978-7-302-16577-4','微型计算机技术及应用','戴梅萼,史嘉权','清华大学出版社',2008,'https://lib.ecust.edu.cn/zh-hans/book/519573'),('978-7-302-22500-3','模式识别（第三版）','张学工','清华大学出版社',2016,'https://lib.ecust.edu.cn/zh-hans/book/579642'),('978-7-302-25720-2','高级软件工程 ','方木云,刘 辉','清华大学出版社',2011,'https://lib.ecust.edu.cn/zh-hans/book/618685'),('978-7-302-26361-6','工业过程控制','焦小澄,朱张青','清华大学出版社',2011,'https://lib.ecust.edu.cn/zh-hans/book/622186'),('978-7-302-28822-0','电子与通信工程专业英语 ','赵桂钦','清华大学出版社',2012,'https://lib.ecust.edu.cn/zh-hans/book/642753'),('978-7-302-30576-7','智能信息处理导论 ','孙红','清华大学出版社',2013,'https://lib.ecust.edu.cn/zh-hans/book/668917'),('978-7-302-31019-8','图像工程','章毓晋','清华大学出版社',2013,'https://lib.ecust.edu.cn/zh-hans/book/662675'),('978-7-302-32437-9','云计算技术架构与实践','李天目,韩进','清华大学出版社',2014,'https://lib.ecust.edu.cn/zh-hans/book/699110'),('978-7-302-34090-4','智能控制原理与应用   ','蔡自兴,余伶俐,肖晓明','清华大学出版社',2014,'https://lib.ecust.edu.cn/zh-hans/book/703895'),('978-7-302-34853-5','系统辨识理论及应用','萧德云','清华大学出版社',2014,'https://lib.ecust.edu.cn/zh-hans/book/725419'),('978-7-302-37804-4','国际贸易与国际金融','卜伟','清华大学出版社',2015,'https://lib.ecust.edu.cn/zh-hans/book/743564'),('978-7-302-38934-7','现代信号处理教程','胡广书','清华大学出版社',2015,'https://lib.ecust.edu.cn/zh-hans/book/748571'),('978-7-302-41581-7','数据挖掘原理与算法 ','毛国君, 段立娟','清华大学出版社',2016,'https://lib.ecust.edu.cn/zh-hans/book/780804'),('978-7-302-41703-3','信息安全原理','(美) Michael E. Whitman, (美) Herbert J.','清华大学出版社',2015,'https://lib.ecust.edu.cn/zh-hans/book/777079'),('978-7-302-42450-5','算法设计与分析 ','屈婉玲','清华大学出版社',2016,'https://lib.ecust.edu.cn/zh-hans/book/781827'),('978-7-305-15192-7','文献检索与利用实用教程','颜世伟, 柴晓娟 ','南京大学出版社',2015,'https://lib.ecust.edu.cn/zh-hans/book/766591'),('978-7-309-11381-5','通用学术英语写作教程 ','蔡基刚','复旦大学出版社',2015,'https://lib.ecust.edu.cn/zh-hans/book/769836'),('978-7-313-12890-4','研究生英语综合教程','崔校平','上海交通大学出版社',2015,'https://lib.ecust.edu.cn/zh-hans/book/761841'),('978-7-5117-2211-9','经济与法律','蒋爱群','中央编译出版社',2014,'https://lib.ecust.edu.cn/zh-hans/book/771945'),('978-7-5124-0283-6','现代传感技术','樊尚春,刘广玉,李 成','北京航空航天大学出版社',2011,'https://lib.ecust.edu.cn/zh-hans/book/596054'),('978-7-5124-1670-3','嵌入式系统开发与实践','郑亮, 郑士海','北京航空航天大学出版社',2015,'https://lib.ecust.edu.cn/zh-hans/book/752053'),('978-7-5603-4995-4','误差理论与数据处理','丁振良','哈尔滨工业大学出版社',2015,'https://lib.ecust.edu.cn/zh-hans/book/744964'),('978-7-5612-2279-9','控制工程科技英语','陈蓓','西北工业大学出版社',2007,'https://lib.ecust.edu.cn/zh-hans/book/495295'),('978-7-5628-2237-0','智能仪表原理与设计技术 ','凌志浩','华东理工大学出版社',2008,'https://lib.ecust.edu.cn/zh-hans/book/523746'),('978-7-5636-4888-7','最优控制理论与方法','邵克勇, 王婷婷, 宋金波','中国石油大学出版社',2015,'https://lib.ecust.edu.cn/zh-hans/book/773200'),('978-7-5647-1432-1','现代数据库技术','赵正文','电子科技大学出版社',2013,'https://lib.ecust.edu.cn/zh-hans/book/682258'),('978-7-5647-2977-6','信息安全技术 ','张剑','电子科技大学出版社',2015,'https://lib.ecust.edu.cn/zh-hans/book/761441');
/*!40000 ALTER TABLE `textbook` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `upload_append`
--

DROP TABLE IF EXISTS `upload_append`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `upload_append` (
  `open_id` char(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `appendix` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`open_id`,`appendix`),
  CONSTRAINT `FK_Reference_11` FOREIGN KEY (`open_id`) REFERENCES `open_class` (`open_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `upload_append`
--

LOCK TABLES `upload_append` WRITE;
/*!40000 ALTER TABLE `upload_append` DISABLE KEYS */;
INSERT INTO `upload_append` VALUES ('005P085203B018','附件1'),('005P085203B018','附件2');
/*!40000 ALTER TABLE `upload_append` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-02  1:19:43
