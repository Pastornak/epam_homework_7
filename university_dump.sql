-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: university
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `group`
--

DROP TABLE IF EXISTS `group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group` (
  `group_id` int(11) NOT NULL,
  `group_name` varchar(45) NOT NULL,
  `year_of_studying` int(11) NOT NULL,
  `speciality_code` int(11) NOT NULL,
  PRIMARY KEY (`group_id`),
  KEY `FK_speciality_code_idx` (`speciality_code`),
  CONSTRAINT `FK_group_speciality_code` FOREIGN KEY (`speciality_code`) REFERENCES `speciality` (`speciality_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group`
--

LOCK TABLES `group` WRITE;
/*!40000 ALTER TABLE `group` DISABLE KEYS */;
INSERT INTO `group` VALUES (1,'AI-31',3,127),(2,'IR-31',3,125),(3,'IR-21',2,125),(4,'RB-32',3,126);
/*!40000 ALTER TABLE `group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mark_for_exam`
--

DROP TABLE IF EXISTS `mark_for_exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mark_for_exam` (
  `student_code` int(11) NOT NULL,
  `subject_code` int(11) NOT NULL,
  `date_of_examination` date NOT NULL,
  `mark` int(11) DEFAULT NULL,
  PRIMARY KEY (`student_code`,`subject_code`,`date_of_examination`),
  KEY `FK_subject_code_idx` (`subject_code`),
  CONSTRAINT `FK_student_code` FOREIGN KEY (`student_code`) REFERENCES `student` (`student_code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_subject_code` FOREIGN KEY (`subject_code`) REFERENCES `subject` (`subject_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mark_for_exam`
--

LOCK TABLES `mark_for_exam` WRITE;
/*!40000 ALTER TABLE `mark_for_exam` DISABLE KEYS */;
INSERT INTO `mark_for_exam` VALUES (2348,1238,'2017-12-22',88),(2348,1284,'2017-12-14',95),(2348,4181,'2017-12-18',88),(2348,4381,'2017-12-26',80),(4325,1214,'2017-12-24',81),(4325,1238,'2017-12-20',82),(4325,3285,'2017-12-12',100),(4325,3835,'2017-12-16',88),(5467,1214,'2018-06-12',72),(5467,3285,'2018-06-20',72),(5467,3835,'2018-06-24',81),(5467,4381,'2018-06-16',65);
/*!40000 ALTER TABLE `mark_for_exam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `speciality`
--

DROP TABLE IF EXISTS `speciality`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `speciality` (
  `speciality_code` int(11) NOT NULL,
  `speciality_name` varchar(45) NOT NULL,
  `speciality_description` mediumtext NOT NULL,
  PRIMARY KEY (`speciality_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `speciality`
--

LOCK TABLES `speciality` WRITE;
/*!40000 ALTER TABLE `speciality` DISABLE KEYS */;
INSERT INTO `speciality` VALUES (125,'Internet of Things','Hello IoT guys.'),(126,'Artificial Intelligence','Hello AI guys.'),(127,'Robotics','Hello Robotics guys.');
/*!40000 ALTER TABLE `speciality` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `student_code` int(11) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `patronymic` varchar(45) NOT NULL,
  `sex` varchar(45) NOT NULL,
  `date_of_birth` date NOT NULL,
  `address` varchar(45) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `passport_info` varchar(6) NOT NULL,
  `gradebook_number` varchar(45) NOT NULL,
  `entry_date` date DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL,
  `speciality_code` int(11) NOT NULL,
  `form_of_studying` varchar(45) NOT NULL,
  `name_of_mother` varchar(45) DEFAULT NULL,
  `name_of_father` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`student_code`),
  KEY `FK_speciality_code_idx` (`speciality_code`),
  KEY `FK_student_group_id_idx` (`group_id`),
  CONSTRAINT `FK_speciality_code` FOREIGN KEY (`speciality_code`) REFERENCES `speciality` (`speciality_code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_student_group_id` FOREIGN KEY (`group_id`) REFERENCES `group` (`group_id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (2348,'Freik','Oleh','Batkovich','male','1998-05-05','Kyiv, Marshal st. 38','380955572888','JA9345','BN9435','2017-01-09',1,127,'daytime','Freik Katerina Vasylivna','Freik Bogdan Borisovich'),(4325,'Pasternak','Yurii','Volodymirovich','male','1999-03-11','Lviv, Karatist st. Mazohist 23','555-35-35','AH4324','OK1337','2016-01-09',2,125,'daytime','NULL','Pasternak Volodymir Vasilovich'),(5467,'Martinyak','Irina','Bogdanivna','female','2000-07-30','Dnipro, Shevchenka 18','390688796543','BH7583','JB4358','2017-01-09',3,125,'daytime','Martinyak Anastasiya Volodymirivna','NULL'),(5489,'Svisch','Olena','Igorivna','female','1999-12-12','Lviv, Naukova st. 35','380991122334','UR6509','OK9454','2016-01-09',4,126,'part-time','NULL','NULL');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subject` (
  `subject_code` int(11) NOT NULL,
  `subject_name` varchar(45) NOT NULL,
  `subject_description` mediumtext NOT NULL,
  PRIMARY KEY (`subject_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (1214,'Mathematics','Learn how to count.'),(1238,'Physics','Learn how the world works.'),(1284,'Microcontrollers','Learn all about embedded.'),(3285,'Database (DB)','Very many useful skills with DB.'),(3835,'Web development','Very many front/back end developer.'),(4181,'Mobile software development','Learn how to android.'),(4381,'Manual testing of Software','Learn how to click buttons.');
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-20 14:38:29
