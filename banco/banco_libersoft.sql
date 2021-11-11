CREATE DATABASE  IF NOT EXISTS `libersoft` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `libersoft`;
-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: libersoft
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `administrador`
--

DROP TABLE IF EXISTS `administrador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrador` (
  `id_administrador` int NOT NULL AUTO_INCREMENT,
  `usuario` varchar(30) NOT NULL,
  `senha` varchar(30) NOT NULL,
  PRIMARY KEY (`id_administrador`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrador`
--

LOCK TABLES `administrador` WRITE;
/*!40000 ALTER TABLE `administrador` DISABLE KEYS */;
INSERT INTO `administrador` VALUES (3,'Helen Thomas','IVF58GLW5UI'),(9,'Jarrod Haley','KTN05IYT1DZ'),(11,'Abraham Casey','BHI57JOX6JO'),(15,'Julie Hines','AYU54GLD4AK'),(44,'Chandler Hicks','BUD71GNK6SS');
/*!40000 ALTER TABLE `administrador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aluno`
--

DROP TABLE IF EXISTS `aluno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aluno` (
  `id_aluno` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(70) NOT NULL,
  `email` varchar(100) NOT NULL,
  `senha` varchar(30) NOT NULL,
  `telefone` varchar(11) NOT NULL,
  `cpf` varchar(11) NOT NULL,
  `endereco` varchar(100) DEFAULT NULL,
  `fk_bibliotecario` int DEFAULT NULL,
  PRIMARY KEY (`id_aluno`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `cpf` (`cpf`),
  KEY `fk_bibliotecario` (`fk_bibliotecario`),
  CONSTRAINT `aluno_ibfk_1` FOREIGN KEY (`fk_bibliotecario`) REFERENCES `bibliotecario` (`id_bibliotecario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aluno`
--

LOCK TABLES `aluno` WRITE;
/*!40000 ALTER TABLE `aluno` DISABLE KEYS */;
/*!40000 ALTER TABLE `aluno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bibliotecario`
--

DROP TABLE IF EXISTS `bibliotecario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bibliotecario` (
  `id_bibliotecario` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(70) NOT NULL,
  `email` varchar(100) NOT NULL,
  `senha` varchar(30) NOT NULL,
  `telefone` varchar(11) NOT NULL,
  `cpf` varchar(11) NOT NULL,
  `endereco` varchar(100) DEFAULT NULL,
  `fk_administrador` int DEFAULT NULL,
  PRIMARY KEY (`id_bibliotecario`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `cpf` (`cpf`),
  KEY `fk_administrador` (`fk_administrador`),
  CONSTRAINT `bibliotecario_ibfk_1` FOREIGN KEY (`fk_administrador`) REFERENCES `administrador` (`id_administrador`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bibliotecario`
--

LOCK TABLES `bibliotecario` WRITE;
/*!40000 ALTER TABLE `bibliotecario` DISABLE KEYS */;
/*!40000 ALTER TABLE `bibliotecario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emprestimo`
--

DROP TABLE IF EXISTS `emprestimo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `emprestimo` (
  `id_emprestimo` int NOT NULL AUTO_INCREMENT,
  `data_emprestimo` date DEFAULT NULL,
  `renovacoes` tinyint DEFAULT NULL,
  `data_devolucao` date DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `fk_aluno` int DEFAULT NULL,
  `fk_livro` int DEFAULT NULL,
  `data_renovacao` date DEFAULT NULL,
  `situacao` varchar(20) NOT NULL,
  `id_aluno` int DEFAULT NULL,
  `id_bibliotecario` int DEFAULT NULL,
  `id_livro` int DEFAULT NULL,
  PRIMARY KEY (`id_emprestimo`),
  KEY `fk_aluno` (`fk_aluno`),
  KEY `fk_livro` (`fk_livro`),
  KEY `FK4bfgr7r9ni87xg6optg64tf8c` (`id_aluno`),
  KEY `FKt179vmnlh6eq8wfrj0ytutayt` (`id_bibliotecario`),
  KEY `FK9o80s7i3wn6ks727ytgmudti4` (`id_livro`),
  CONSTRAINT `emprestimo_ibfk_1` FOREIGN KEY (`fk_aluno`) REFERENCES `aluno` (`id_aluno`),
  CONSTRAINT `emprestimo_ibfk_2` FOREIGN KEY (`fk_livro`) REFERENCES `livro` (`id_livro`),
  CONSTRAINT `FK4bfgr7r9ni87xg6optg64tf8c` FOREIGN KEY (`id_aluno`) REFERENCES `aluno` (`id_aluno`),
  CONSTRAINT `FK9o80s7i3wn6ks727ytgmudti4` FOREIGN KEY (`id_livro`) REFERENCES `livro` (`id_livro`),
  CONSTRAINT `FKt179vmnlh6eq8wfrj0ytutayt` FOREIGN KEY (`id_bibliotecario`) REFERENCES `bibliotecario` (`id_bibliotecario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emprestimo`
--

LOCK TABLES `emprestimo` WRITE;
/*!40000 ALTER TABLE `emprestimo` DISABLE KEYS */;
/*!40000 ALTER TABLE `emprestimo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `livro`
--

DROP TABLE IF EXISTS `livro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `livro` (
  `id_livro` int NOT NULL AUTO_INCREMENT,
  `categoria` varchar(50) NOT NULL,
  `titulo` varchar(50) NOT NULL,
  `volume` smallint DEFAULT NULL,
  `autor` varchar(60) NOT NULL,
  `quantidade` smallint NOT NULL,
  `resumo` varchar(500) NOT NULL,
  `imagem` varchar(100) NOT NULL,
  `fk_bibliotecario` int DEFAULT NULL,
  PRIMARY KEY (`id_livro`),
  KEY `fk_bibliotecario` (`fk_bibliotecario`),
  CONSTRAINT `livro_ibfk_1` FOREIGN KEY (`fk_bibliotecario`) REFERENCES `bibliotecario` (`id_bibliotecario`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `livro`
--

LOCK TABLES `livro` WRITE;
/*!40000 ALTER TABLE `livro` DISABLE KEYS */;
INSERT INTO `livro` VALUES (1,'Doidera','Código Limpo',1,'Schopenhauer',123,'Um resumo bem grande Um resumo bem grande Um resumo bem grande Um resumo bem grande Um resumo bem grande Um resumo bem grande Um resumo bem grande Um resumo bem grande Um resumo bem grande ','/img/livro/codigo_limpo.jpg',NULL),(2,'Djow','Loucuras do Bananal',777,'Raul Crespiano',1,'Resumao grandaoooummmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm','/img/livro/padrao.jpg',NULL),(3,'Programação','Sistemas Operacionais Modernos',2,'Andrew S. Tenembaum',500,'Puta livro bom mermao Puta livro bom mermao Puta livro bom mermao Puta livro bom mermao Puta livro bom mermao Puta livro bom mermao Puta livro bom mermao ','/img/livro/sistemas_operacionais_modernos.jpg',NULL);
/*!40000 ALTER TABLE `livro` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-10  2:27:46
