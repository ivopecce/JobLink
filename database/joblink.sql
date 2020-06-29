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
  `sitoweb` varchar(45) DEFAULT NULL,
  `idUtente` int NOT NULL,
  PRIMARY KEY (`idAzienda`),
  KEY `idUtente_idx` (`idUtente`),
  CONSTRAINT `idUtente` FOREIGN KEY (`idUtente`) REFERENCES `Utente` (`idUtente`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Azienda`
--

LOCK TABLES `Azienda` WRITE;
/*!40000 ALTER TABLE `Azienda` DISABLE KEYS */;
INSERT INTO `Azienda` VALUES (1,'Telespazio','Fucino',5000,'Telecomunicazioni','www.telespazio.com',1),(2,'LFoundry','Avezzano',1200,'Elettronico','www.lfoundry.com',3);
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
  `descrizione` text,
  `istituto` varchar(100) NOT NULL,
  `dataInizio` date DEFAULT NULL,
  `dataFine` date DEFAULT NULL,
  `voto` int DEFAULT NULL,
  `idPersona` int NOT NULL,
  PRIMARY KEY (`idFormazione`,`titolo`,`istituto`),
  KEY `idPerso_idx` (`idPersona`),
  CONSTRAINT `idPerso` FOREIGN KEY (`idPersona`) REFERENCES `Persona` (`idPersona`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Formazione`
--

LOCK TABLES `Formazione` WRITE;
/*!40000 ALTER TABLE `Formazione` DISABLE KEYS */;
INSERT INTO `Formazione` VALUES (2,'Diploma superiore','Diploma in Informatica','IIS Avezzano','2012-09-10','2017-07-01',100,1),(3,'Certificazione CCNA','Certificazione CCNA R&S','Elis','2020-06-01','2020-06-01',75,1);
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
  `dataCreazione` date NOT NULL,
  `titoloOfferta` varchar(100) NOT NULL,
  `testoOfferta` text NOT NULL,
  `localita` varchar(45) DEFAULT NULL,
  `stato` varchar(10) NOT NULL,
  `idAzienda` int NOT NULL,
  PRIMARY KEY (`idOfferta`),
  KEY `idAzienda_idx` (`idAzienda`),
  CONSTRAINT `idAziend` FOREIGN KEY (`idAzienda`) REFERENCES `Azienda` (`idAzienda`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Offerta`
--

LOCK TABLES `Offerta` WRITE;
/*!40000 ALTER TABLE `Offerta` DISABLE KEYS */;
INSERT INTO `Offerta` VALUES (1,'2020-05-01','Tecnico operatore','Si cerca tecnico operatore da inserire in turno. Richiesto diploma','Fucino','ATTIVA',1),(2,'2019-01-05','Specialista networking','Si cerca spcialista in netowrking. Richiesta certificazione CCNA.','Fucino','NON_ATTIVA',1),(4,'2020-06-17','Cercasi carrellista','Si ricerca carrelista da inserire in turno in produzione','Avezzano','ATTIVA',2);
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
  `dataDiNascita` date NOT NULL,
  `genere` varchar(45) DEFAULT NULL,
  `residenza` varchar(45) NOT NULL,
  `idUtente` int NOT NULL,
  PRIMARY KEY (`idPersona`),
  KEY `idUte_idx` (`idUtente`),
  CONSTRAINT `idUte` FOREIGN KEY (`idUtente`) REFERENCES `Utente` (`idUtente`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Persona`
--

LOCK TABLES `Persona` WRITE;
/*!40000 ALTER TABLE `Persona` DISABLE KEYS */;
INSERT INTO `Persona` VALUES (1,'Pecce','Ivo','1990-10-01','MASCHIO','Avezzano',2),(2,'Pecce','Ivo','1986-06-19','MASCHIO','Italia',4);
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
  `livelloPosseduto` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`idPossiede`),
  KEY `idPerson_idx` (`idPersona`),
  KEY `idSkil_idx` (`idSkill`),
  CONSTRAINT `idPer` FOREIGN KEY (`idPersona`) REFERENCES `Persona` (`idPersona`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idSkil` FOREIGN KEY (`idSkill`) REFERENCES `Skill` (`idSkill`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Possiede`
--

LOCK TABLES `Possiede` WRITE;
/*!40000 ALTER TABLE `Possiede` DISABLE KEYS */;
INSERT INTO `Possiede` VALUES (1,1,1,'AVANZATO'),(2,1,2,'MEDIO');
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
  `livelloRichiesto` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`idRichiesta`),
  KEY `idOffert_idx` (`idOfferta`),
  KEY `idSkill_idx` (`idSkill`),
  CONSTRAINT `idOffert` FOREIGN KEY (`idOfferta`) REFERENCES `Offerta` (`idOfferta`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idSkill` FOREIGN KEY (`idSkill`) REFERENCES `Skill` (`idSkill`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Richiesta`
--

LOCK TABLES `Richiesta` WRITE;
/*!40000 ALTER TABLE `Richiesta` DISABLE KEYS */;
INSERT INTO `Richiesta` VALUES (1,1,1,'MEDIO'),(2,2,1,'AVANZATO'),(3,2,2,'BASE'),(5,1,2,'BASE');
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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Risposta`
--

LOCK TABLES `Risposta` WRITE;
/*!40000 ALTER TABLE `Risposta` DISABLE KEYS */;
INSERT INTO `Risposta` VALUES (20,1,1);
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
  PRIMARY KEY (`idSkill`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Skill`
--

LOCK TABLES `Skill` WRITE;
/*!40000 ALTER TABLE `Skill` DISABLE KEYS */;
INSERT INTO `Skill` VALUES (1,'networking'),(2,'programmazione');
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
  `password` varchar(100) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `tipologia` varchar(7) NOT NULL,
  PRIMARY KEY (`idUtente`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Utente`
--

LOCK TABLES `Utente` WRITE;
/*!40000 ALTER TABLE `Utente` DISABLE KEYS */;
INSERT INTO `Utente` VALUES (1,'azienda','azienda','info@telespazio.com','+390863100000','azienda'),(2,'persona','persona','email@esempio.it','+390863000000','persona'),(3,'lfoundry','lfoundry','info@lfoundry.com','+390863222222','azienda'),(4,'ivopecce','ivopecce','test@email.it','+393333333333','persona');
/*!40000 ALTER TABLE `Utente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'joblink'
--

--
-- Dumping routines for database 'joblink'
--
/*!50003 DROP PROCEDURE IF EXISTS `create_richiesta` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `create_richiesta`(_idOfferta integer, _skill char(45), _livello char(8))
BEGIN
	DECLARE _idSkill integer;
    SET _idSkill = (SELECT idSkill FROM Skill WHERE skill = _skill);
    IF _idSkill IS NULL THEN BEGIN
		INSERT INTO Skill (skill) VALUES (_skill);
		SET _idSkill = last_insert_id();
	END;
    END IF;
    
    INSERT INTO Richiesta (idOfferta, idSkill, livelloRichiesto) VALUES (_idOfferta, _idSkill, _livello);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delete_azienda` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_azienda`(_idAzienda integer)
BEGIN
	DECLARE _idUtente integer;
    SET _idUtente = (SELECT idUtente FROM Azienda WHERE Azienda.idAzienda = _idAzienda);
    DELETE FROM Utente WHERE idUtente = _idUtente;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delete_persona` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_persona`(_idPersona integer)
BEGIN
	DECLARE _idUtente integer;
    SET _idUtente = (SELECT idUtente FROM Persona WHERE Persona.idPersona = _idPersona);
    DELETE FROM Utente WHERE idUtente = _idUtente;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `find_esperienza` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `find_esperienza`(_idUtente integer)
BEGIN
	SELECT idEsperienza, titolo, azienda, dataInizio, dataFine, descrizione, localita
    FROM Esperienza
    WHERE idPersona = _idUtente;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `find_formazione` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `find_formazione`(_IDPersona integer)
BEGIN
	SELECT idFormazione, titolo, descrizione, istituto, dataInizio, dataFine, voto
    FROM Formazione
    WHERE idPersona = _IDPersona;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `find_skill` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `find_skill`(_idUtente integer)
BEGIN
	SELECT Skill.idSkill, Skill.skill, Possiede.livelloPosseduto
    FROM Skill INNER JOIN Possiede ON Skill.idSkill = Possiede.idSkill
    WHERE Possiede.idPersona = _idUtente;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `get_candidati` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_candidati`(_idOfferta integer)
BEGIN
	SELECT DISTINCT Persona.idPersona, cognome, nome, dataDiNascita, genere, residenza, email, telefono
    FROM Persona INNER JOIN Risposta ON Persona.idPersona=Risposta.idPersona INNER JOIN Utente ON Persona.idUtente = Utente.idUtente
    WHERE Risposta.idOfferta=_idOfferta;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `get_candidatura` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_candidatura`(_idPersona integer, _idOfferta integer)
BEGIN
	select distinct idRisposta from Risposta where idPersona=_idPersona and _idOfferta=idOfferta limit 1;  
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `get_skill_richieste` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_skill_richieste`(_idOfferta integer)
BEGIN
	SELECT Richiesta.idRichiesta, Skill.idSkill, Skill.skill, Richiesta.livelloRichiesto
    FROM Richiesta INNER JOIN Skill ON Skill.idSkill=Richiesta.idSkill
    WHERE Richiesta.idOfferta = _idOfferta;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `login` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `login`(_username char(45), _password char(100))
BEGIN
	DECLARE _tipologia char(7);
    SET _tipologia = (SELECT tipologia FROM Utente WHERE username=_username and password=_password);
    IF _tipologia = "azienda" THEN
    BEGIN
		SELECT tipologia, Azienda.idAzienda, username, password, email, telefono, denominazione, sitoweb, sede, settore, numeroDipendenti 
        FROM (Utente INNER JOIN Azienda ON Utente.idUtente=Azienda.idUtente) WHERE username=_username and password=_password;
    END;
    END IF;
	IF _tipologia = "persona" THEN BEGIN
		SELECT tipologia, Persona.idPersona, username, password, email, telefono, cognome, nome, dataDiNascita, genere, residenza
        FROM (Utente INNER JOIN Persona ON Utente.idUtente=Persona.idUtente) WHERE username=_username and password=_password;
	END;
	END IF;
    IF _tipologia = null THEN BEGIN SIGNAL SQLSTATE '45000' SET message_text = "Username o password errati"; END; END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `offerte_attinenti` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `offerte_attinenti`(_idPersona integer)
BEGIN
	#SELECT DISTINCT Skill.skill from Skill INNER JOIN Possiede on Skill.idSkill=Possiede.idSkill WHERE Possiede.idPersona=_idPersona;
    
    SELECT DISTINCT Offerta.idOfferta, Offerta.dataCreazione, Offerta.titoloOfferta, Offerta.testoOfferta, Offerta.localita, Azienda.idAzienda, Azienda.denominazione
    FROM Offerta INNER JOIN Richiesta ON Offerta.idOfferta=Richiesta.idOfferta INNER JOIN Skill ON Skill.idSkill=Richiesta.idSkill INNER JOIN Possiede ON Skill.idSkill=Possiede.idSkill INNER JOIN Azienda ON Azienda.idAzienda=Offerta.idAzienda
    WHERE Possiede.idPersona=_idPersona AND Offerta.stato="ATTIVA";
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `persone_attinenti` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `persone_attinenti`(_idOfferta integer)
BEGIN
	SELECT DISTINCT Persona.idPersona, cognome, nome, dataDiNascita, genere, residenza, email, telefono
    FROM Persona INNER JOIN Possiede ON Persona.idPersona=Possiede.idPersona INNER JOIN Skill ON Skill.idSkill=Possiede.idSkill INNER JOIN Richiesta ON Richiesta.idSkill=Possiede.idSkill INNER JOIN Utente ON Persona.idUtente=Utente.idUtente
    WHERE Richiesta.idOfferta=_idOfferta;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `registerAzienda` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `registerAzienda`(_username char(45), _password char(100), _email char(45), _telefono char(45), _denominazione char(45), _sede char(45), _settore char(45), _sitoweb char(45), _dipendenti integer)
BEGIN
	DECLARE user_presente char(45);
    DECLARE ultimoId integer;
    SET user_presente=(select username from Utente where username=_username);
    IF(user_presente) THEN
	BEGIN
		SIGNAL SQLSTATE '45000' SET message_text = "Username gia' presente";
	END;
    ELSE
    BEGIN
		INSERT INTO Utente(username, password, email, telefono, tipologia) VALUES (_username, _password, _email, _telefono, "azienda");
        SET ultimoId = last_insert_id();
        INSERT INTO Azienda(denominazione, sede, numeroDipendenti, settore, sitoWeb, idUtente) VALUES (_denominazione, _sede, _dipendenti, _settore, _sitoWeb, ultimoId);
	END;
    END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `registerPersona` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `registerPersona`(_username char(45), _password char(100), _email char(45), _telefono char(45), _cognome char(45), _nome char(45), _dataDiNascita date, _genere char(45), _residenza char(45))
BEGIN
	DECLARE user_presente char(45);
    DECLARE ultimoId integer;
    SET user_presente=(select username from Utente where username=_username);
    IF(user_presente) THEN
	BEGIN
		SIGNAL SQLSTATE '45000' SET message_text = "Username gia' presente";
	END;
    ELSE
    BEGIN
		INSERT INTO Utente(username, password, email, telefono, tipologia) VALUES (_username, _password, _email, _telefono, "persona");
        SET ultimoId = last_insert_id();
        INSERT INTO Persona(cognome, nome, dataDiNascita, genere, residenza, idUtente) VALUES (_cognome, _nome, _dataDiNascita, _genere, _residenza, ultimoId);
	END;
    END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `set_candidatura` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `set_candidatura`(_idOfferta integer, _idPersona integer, _candidatura boolean)
BEGIN
	IF (_candidatura is true) then begin
		insert into Risposta(idPersona, idOfferta) values (_idPersona, _idOfferta);
	end;
    else begin
		delete from Risposta where idPersona=_idPersona and idOfferta=_idOfferta;
    end;
    end if;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `skill_richieste` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `skill_richieste`(_idOfferta integer)
BEGIN
	SELECT skillRichieste FROM (SELECT group_concat(skill separator ", ") AS skillRichieste FROM Skill INNER JOIN Richiesta ON Skill.idSkill=Richiesta.idSkill WHERE Richiesta.idOfferta=_idOfferta) Richieste;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_richiesta` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_richiesta`(_idRichiesta integer, _skill char(45), _livello char(8))
BEGIN
	DECLARE _idSkill integer;
    SET _idSkill = (SELECT idSkill FROM Skill WHERE skill = _skill);
    IF _idSkill IS NULL THEN BEGIN
		INSERT INTO Skill (skill) VALUES (_skill);
		SET _idSkill = last_insert_id();
	END;
    END IF;
    
    UPDATE Richiesta SET idSkill = _idSkill, livelloRichiesto = _livello WHERE idRichiesta = _idRichiesta;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-29 18:45:23
