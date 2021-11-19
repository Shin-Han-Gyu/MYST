-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: main_db
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `group_join`
--

DROP TABLE IF EXISTS `group_join`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_join` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `is_accepted` bit(1) DEFAULT b'0',
  `team_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK80ucv0liqt6sehjx0dxl3exl8` (`team_id`),
  KEY `FK53x4uq1gpj1cvvlh952yqsyy3` (`user_id`),
  CONSTRAINT `FK53x4uq1gpj1cvvlh952yqsyy3` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK80ucv0liqt6sehjx0dxl3exl8` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_join`
--

LOCK TABLES `group_join` WRITE;
/*!40000 ALTER TABLE `group_join` DISABLE KEYS */;
INSERT INTO `group_join` VALUES (1,_binary '',2,1),(2,_binary '\0',1,3);
/*!40000 ALTER TABLE `group_join` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_member`
--

DROP TABLE IF EXISTS `group_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_member` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `position` varchar(255) DEFAULT NULL,
  `team_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK290qeh4jdb1tjouv73jkoxak7` (`team_id`),
  KEY `FKpy45r2qx8a2vqafo7rste49c` (`user_id`),
  CONSTRAINT `FK290qeh4jdb1tjouv73jkoxak7` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FKpy45r2qx8a2vqafo7rste49c` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_member`
--

LOCK TABLES `group_member` WRITE;
/*!40000 ALTER TABLE `group_member` DISABLE KEYS */;
INSERT INTO `group_member` VALUES (1,'Leader',1,1),(2,'Leader',2,2),(3,'Member',2,1),(4,'Leader',3,3);
/*!40000 ALTER TABLE `group_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_task`
--

DROP TABLE IF EXISTS `group_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_task` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `complete_option` varchar(255) DEFAULT NULL,
  `is_complete` bit(1) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `team_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlq8g7a1at3b7qyj2bia342v37` (`team_id`),
  KEY `FK16bob6pu0hjahdbjcyym1j198` (`user_id`),
  CONSTRAINT `FK16bob6pu0hjahdbjcyym1j198` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FKlq8g7a1at3b7qyj2bia342v37` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_task`
--

LOCK TABLES `group_task` WRITE;
/*!40000 ALTER TABLE `group_task` DISABLE KEYS */;
INSERT INTO `group_task` VALUES (5,'DONE_BY_ALL',_binary '\0','11월 4주차 모의 코딩테스트 ',1,NULL),(6,'DONE_BY_ALL',_binary '\0','수요일 CS스터디 준비!!',2,NULL);
/*!40000 ALTER TABLE `group_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personal_task`
--

DROP TABLE IF EXISTS `personal_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personal_task` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `is_complete` bit(1) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `group_task_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKekjuoth1lxx0livuwg27mukfe` (`group_task_id`),
  KEY `FKkpb0vw23af61cpctsrhnrvxyj` (`user_id`),
  CONSTRAINT `FKekjuoth1lxx0livuwg27mukfe` FOREIGN KEY (`group_task_id`) REFERENCES `group_task` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FKkpb0vw23af61cpctsrhnrvxyj` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personal_task`
--

LOCK TABLES `personal_task` WRITE;
/*!40000 ALTER TABLE `personal_task` DISABLE KEYS */;
INSERT INTO `personal_task` VALUES (1,_binary '\0','백준 골드 5문제 풀기',NULL,1),(2,_binary '','프로젝트 PPT 만들기',NULL,1),(3,_binary '\0','삼성 서류 지원!',NULL,1),(4,_binary '\0','팔굽혀펴기 50회 하기',NULL,1),(5,_binary '','백준 1323 풀기',NULL,2),(6,_binary '','체온, 혈압, 산소포화도 측정하기!',NULL,2),(8,_binary '\0','프로젝트 발표준비하기!',NULL,2),(9,_binary '\0','떡튀순 또또즉석떡볶이',NULL,3),(10,_binary '\0','패딩 드라이 맡기기',NULL,3),(14,_binary '\0',NULL,5,1),(15,_binary '\0',NULL,6,2),(16,_binary '\0',NULL,6,1);
/*!40000 ALTER TABLE `personal_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `team` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
INSERT INTO `team` VALUES (1,'SSAFY 5기 모의 코테 스터디입니다! 매주 화요일 19시 ~ 22시 모의 코테 실시','모의 코테 스터디'),(2,'CS Study입니다! 매주 과목별 주제3개 선정 후 정리하기!','CS STUDY'),(3,'세계일주 여행을 위해 필요한 물품, 미리 준비해야할 업무 등을 함께 정리해 봅시다.','세계일주 여행팟');
/*!40000 ALTER TABLE `team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team_color`
--

DROP TABLE IF EXISTS `team_color`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `team_color` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `color_code` varchar(255) DEFAULT NULL,
  `team_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4r8upj89w8lgk7t8fga37hw8f` (`team_id`),
  KEY `FKkqsbke6kbg87yjmmp9ppaxbyl` (`user_id`),
  CONSTRAINT `FK4r8upj89w8lgk7t8fga37hw8f` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FKkqsbke6kbg87yjmmp9ppaxbyl` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team_color`
--

LOCK TABLES `team_color` WRITE;
/*!40000 ALTER TABLE `team_color` DISABLE KEYS */;
INSERT INTO `team_color` VALUES (1,'#ff42d6',1,1),(2,'#4dff00',2,2),(3,'#85e228',2,1),(4,'#527dff',3,3);
/*!40000 ALTER TABLE `team_color` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'신한규','{bcrypt}$2a$10$S0doYqR0dyiT0dry5QrMIOmYxtrVt3DNYo459kftEKVSC.K.aphPa','hanq'),(2,'김호석','{bcrypt}$2a$10$yjbgQZl8UogzxUdi0L0VFujkFtdO0L.AF4UsVoBINCU.6O.pCcNIe','mong'),(3,'이희정','{bcrypt}$2a$10$r4iQVVQ9Wdpe6uxuCPmNAu9MUwWttI2J.eS5qhaNoKGrJTl597CY6','patabear');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `user_id` bigint NOT NULL,
  `roles` varchar(255) DEFAULT NULL,
  KEY `FK55itppkw3i07do3h7qoclqd4k` (`user_id`),
  CONSTRAINT `FK55itppkw3i07do3h7qoclqd4k` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-19  9:00:28
