CREATE DATABASE  IF NOT EXISTS `joblink` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `joblink`;
-- MySQL dump 10.13  Distrib 8.0.20, for Linux (x86_64)
--
-- Host: localhost    Database: joblink
-- ------------------------------------------------------
-- Server version	8.0.20-0ubuntu0.20.04.1

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
-- Table structure for table `Azienda`
--

DROP TABLE IF EXISTS `Azienda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Azienda` (
  `idAzienda` int NOT NULL AUTO_INCREMENT,
  `denominazione` varchar(45) NOT NULL,
  `sede` varchar(45) DEFAULT NULL,
  `numeroDipendenti` int DEFAULT NULL,
  `settore` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idAzienda`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Azienda`
--

LOCK TABLES `Azienda` WRITE;
/*!40000 ALTER TABLE `Azienda` DISABLE KEYS */;
/*!40000 ALTER TABLE `Azienda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Esperienza`
--

DROP TABLE IF EXISTS `Esperienza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Esperienza` (
  `idEsperienza` int NOT NULL AUTO_INCREMENT,
  `titolo` varchar(100) NOT NULL,
  `azienda` varchar(45) NOT NULL,
  `dataInizio` datetime NOT NULL,
  `dataFine` datetime NOT NULL,
  `descrizione` text NOT NULL,
  `localita` varchar(45) NOT NULL,
  `idPersona` int NOT NULL,
  PRIMARY KEY (`idEsperienza`),
  KEY `idP_idx` (`idPersona`),
  CONSTRAINT `idP` FOREIGN KEY (`idPersona`) REFERENCES `Persona` (`idPersona`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Esperienza`
--

LOCK TABLES `Esperienza` WRITE;
/*!40000 ALTER TABLE `Esperienza` DISABLE KEYS */;
/*!40000 ALTER TABLE `Esperienza` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Formazione`
--

DROP TABLE IF EXISTS `Formazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Formazione` (
  `idFormazione` int NOT NULL AUTO_INCREMENT,
  `titolo` varchar(100) NOT NULL,
  `istituto` varchar(100) NOT NULL,
  `dataInizio` datetime DEFAULT NULL,
  `dataFine` datetime DEFAULT NULL,
  `voto` int DEFAULT NULL,
  `idPersona` int NOT NULL,
  PRIMARY KEY (`idFormazione`,`titolo`,`istituto`),
  KEY `idPerso_idx` (`idPersona`),
  CONSTRAINT `idPerso` FOREIGN KEY (`idPersona`) REFERENCES `Persona` (`idPersona`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Formazione`
--

LOCK TABLES `Formazione` WRITE;
/*!40000 ALTER TABLE `Formazione` DISABLE KEYS */;
/*!40000 ALTER TABLE `Formazione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Messaggio`
--

DROP TABLE IF EXISTS `Messaggio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Messaggio` (
  `idMessaggio` int NOT NULL AUTO_INCREMENT,
  `oggetto` varchar(100) NOT NULL,
  `testo` text NOT NULL,
  `idPersona` int NOT NULL,
  `idAzienda` int NOT NULL,
  PRIMARY KEY (`idMessaggio`),
  KEY `idPersona_idx` (`idPersona`),
  KEY `idAzienda_idx` (`idAzienda`),
  CONSTRAINT `idAzienda` FOREIGN KEY (`idAzienda`) REFERENCES `Azienda` (`idAzienda`),
  CONSTRAINT `idPersona` FOREIGN KEY (`idPersona`) REFERENCES `Persona` (`idPersona`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Messaggio`
--

LOCK TABLES `Messaggio` WRITE;
/*!40000 ALTER TABLE `Messaggio` DISABLE KEYS */;
/*!40000 ALTER TABLE `Messaggio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Offerta`
--

DROP TABLE IF EXISTS `Offerta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Offerta` (
  `idOfferta` int NOT NULL AUTO_INCREMENT,
  `dataCreazione` datetime NOT NULL,
  `titoloOfferta` varchar(100) NOT NULL,
  `testoOfferta` text NOT NULL,
  `localita` varchar(45) DEFAULT NULL,
  `idAzienda` int NOT NULL,
  PRIMARY KEY (`idOfferta`),
  KEY `idAzienda_idx` (`idAzienda`),
  CONSTRAINT `idAziend` FOREIGN KEY (`idAzienda`) REFERENCES `Azienda` (`idAzienda`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Offerta`
--

LOCK TABLES `Offerta` WRITE;
/*!40000 ALTER TABLE `Offerta` DISABLE KEYS */;
/*!40000 ALTER TABLE `Offerta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Persona`
--

DROP TABLE IF EXISTS `Persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Persona` (
  `idPersona` int NOT NULL AUTO_INCREMENT,
  `cognome` varchar(45) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `dataDiNascita` datetime NOT NULL,
  `genere` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idPersona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Persona`
--

LOCK TABLES `Persona` WRITE;
/*!40000 ALTER TABLE `Persona` DISABLE KEYS */;
/*!40000 ALTER TABLE `Persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Possiede`
--

DROP TABLE IF EXISTS `Possiede`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Possiede` (
  `idPossiede` int NOT NULL AUTO_INCREMENT,
  `idPersona` int NOT NULL,
  `idSkill` int NOT NULL,
  PRIMARY KEY (`idPossiede`),
  KEY `idPerson_idx` (`idPersona`),
  KEY `idSkil_idx` (`idSkill`),
  CONSTRAINT `idPer` FOREIGN KEY (`idPersona`) REFERENCES `Persona` (`idPersona`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idSkil` FOREIGN KEY (`idSkill`) REFERENCES `Skill` (`idSkill`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Possiede`
--

LOCK TABLES `Possiede` WRITE;
/*!40000 ALTER TABLE `Possiede` DISABLE KEYS */;
/*!40000 ALTER TABLE `Possiede` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Richiesta`
--

DROP TABLE IF EXISTS `Richiesta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Richiesta` (
  `idRichiesta` int NOT NULL AUTO_INCREMENT,
  `idOfferta` int NOT NULL,
  `idSkill` int NOT NULL,
  PRIMARY KEY (`idRichiesta`),
  KEY `idOffert_idx` (`idOfferta`),
  KEY `idSkill_idx` (`idSkill`),
  CONSTRAINT `idOffert` FOREIGN KEY (`idOfferta`) REFERENCES `Offerta` (`idOfferta`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idSkill` FOREIGN KEY (`idSkill`) REFERENCES `Skill` (`idSkill`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Richiesta`
--

LOCK TABLES `Richiesta` WRITE;
/*!40000 ALTER TABLE `Richiesta` DISABLE KEYS */;
/*!40000 ALTER TABLE `Richiesta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Risposta`
--

DROP TABLE IF EXISTS `Risposta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Risposta` (
  `idRisposta` int NOT NULL AUTO_INCREMENT,
  `idPersona` int NOT NULL,
  `idOfferta` int NOT NULL,
  PRIMARY KEY (`idRisposta`),
  KEY `idPerson_idx` (`idPersona`),
  KEY `idOfferta_idx` (`idOfferta`),
  CONSTRAINT `idOfferta` FOREIGN KEY (`idOfferta`) REFERENCES `Offerta` (`idOfferta`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idPerson` FOREIGN KEY (`idPersona`) REFERENCES `Persona` (`idPersona`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Risposta`
--

LOCK TABLES `Risposta` WRITE;
/*!40000 ALTER TABLE `Risposta` DISABLE KEYS */;
/*!40000 ALTER TABLE `Risposta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Skill`
--

DROP TABLE IF EXISTS `Skill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Skill` (
  `idSkill` int NOT NULL AUTO_INCREMENT,
  `skill` varchar(45) NOT NULL,
  `livello` varchar(45) NOT NULL,
  PRIMARY KEY (`idSkill`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Skill`
--

LOCK TABLES `Skill` WRITE;
/*!40000 ALTER TABLE `Skill` DISABLE KEYS */;
/*!40000 ALTER TABLE `Skill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Utente`
--

DROP TABLE IF EXISTS `Utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Utente` (
  `idUtente` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idUtente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Utente`
--

LOCK TABLES `Utente` WRITE;
/*!40000 ALTER TABLE `Utente` DISABLE KEYS */;
/*!40000 ALTER TABLE `Utente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'joblink'
--

--
-- Dumping routines for database 'joblink'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-13 21:45:39
