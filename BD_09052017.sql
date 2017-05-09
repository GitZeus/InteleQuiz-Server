-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: us-cdbr-iron-east-03.cleardb.net    Database: heroku_dbc8380a194f679
-- ------------------------------------------------------
-- Server version	5.5.45-log

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
-- Table structure for table `rel_professor_disciplina`
--

DROP TABLE IF EXISTS `rel_professor_disciplina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rel_professor_disciplina` (
  `DISCIPLINA_ID` int(11) NOT NULL,
  `PROFESSOR_MATRICULA` varchar(15) NOT NULL,
  PRIMARY KEY (`DISCIPLINA_ID`,`PROFESSOR_MATRICULA`),
  KEY `fk_TB_DISCIPLINA_has_TB_PROFESSOR_TB_PROFESSOR1_idx` (`PROFESSOR_MATRICULA`),
  KEY `fk_TB_DISCIPLINA_has_TB_PROFESSOR_TB_DISCIPLINA1_idx` (`DISCIPLINA_ID`),
  CONSTRAINT `FK_DISCIPLINA` FOREIGN KEY (`DISCIPLINA_ID`) REFERENCES `tb_disciplina` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_PROFESSOR` FOREIGN KEY (`PROFESSOR_MATRICULA`) REFERENCES `tb_professor` (`MATRICULA`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rel_professor_disciplina`
--

LOCK TABLES `rel_professor_disciplina` WRITE;
/*!40000 ALTER TABLE `rel_professor_disciplina` DISABLE KEYS */;
INSERT INTO `rel_professor_disciplina` VALUES (1,'MA123'),(2,'MA123');
/*!40000 ALTER TABLE `rel_professor_disciplina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rel_questao_quiz`
--

DROP TABLE IF EXISTS `rel_questao_quiz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rel_questao_quiz` (
  `QUESTAO_ID` int(11) NOT NULL,
  `QUIZ_ID` int(11) NOT NULL,
  PRIMARY KEY (`QUESTAO_ID`,`QUIZ_ID`),
  KEY `fk_TB_MOD_QUIZ_has_TB_MOD_QUESTAO_TB_MOD_QUESTAO1_idx` (`QUESTAO_ID`),
  KEY `fk_TB_MOD_QUIZ_has_TB_MOD_QUESTAO_TB_MOD_QUIZ1_idx` (`QUIZ_ID`),
  CONSTRAINT `fk_TB_MOD_QUIZ_has_TB_MOD_QUESTAO_TB_MOD_QUESTAO1` FOREIGN KEY (`QUESTAO_ID`) REFERENCES `tb_questao` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_TB_MOD_QUIZ_has_TB_MOD_QUESTAO_TB_MOD_QUIZ1` FOREIGN KEY (`QUIZ_ID`) REFERENCES `tb_quiz` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rel_questao_quiz`
--

LOCK TABLES `rel_questao_quiz` WRITE;
/*!40000 ALTER TABLE `rel_questao_quiz` DISABLE KEYS */;
INSERT INTO `rel_questao_quiz` VALUES (472,102),(472,112),(482,102),(482,112),(492,102),(492,112),(502,102),(502,112);
/*!40000 ALTER TABLE `rel_questao_quiz` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rel_questao_tema`
--

DROP TABLE IF EXISTS `rel_questao_tema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rel_questao_tema` (
  `QUESTAO_ID` int(11) NOT NULL,
  `TEMA_ID` int(11) NOT NULL,
  PRIMARY KEY (`QUESTAO_ID`,`TEMA_ID`),
  KEY `fk_TB_TEMA_has_TB_QUESTAO_TB_TEMA1_idx` (`TEMA_ID`),
  CONSTRAINT `FK_QUESTAO` FOREIGN KEY (`QUESTAO_ID`) REFERENCES `tb_questao` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_TEMA` FOREIGN KEY (`TEMA_ID`) REFERENCES `tb_tema` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rel_questao_tema`
--

LOCK TABLES `rel_questao_tema` WRITE;
/*!40000 ALTER TABLE `rel_questao_tema` DISABLE KEYS */;
INSERT INTO `rel_questao_tema` VALUES (472,312),(482,322),(502,322),(492,332),(502,342);
/*!40000 ALTER TABLE `rel_questao_tema` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rel_treino_resposta`
--

DROP TABLE IF EXISTS `rel_treino_resposta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rel_treino_resposta` (
  `RESPOSTA_ID` int(11) NOT NULL,
  `TREINO_ID` int(11) NOT NULL,
  PRIMARY KEY (`RESPOSTA_ID`,`TREINO_ID`),
  KEY `fk_TB_RESPOSTA_has_TB_TREINO_TB_TREINO1_idx` (`TREINO_ID`),
  CONSTRAINT `FK_RESPOSTA` FOREIGN KEY (`RESPOSTA_ID`) REFERENCES `tb_resposta` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_TREINO` FOREIGN KEY (`TREINO_ID`) REFERENCES `tb_treino` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rel_treino_resposta`
--

LOCK TABLES `rel_treino_resposta` WRITE;
/*!40000 ALTER TABLE `rel_treino_resposta` DISABLE KEYS */;
/*!40000 ALTER TABLE `rel_treino_resposta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rel_turma_aluno`
--

DROP TABLE IF EXISTS `rel_turma_aluno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rel_turma_aluno` (
  `TURMA_ID` int(11) NOT NULL,
  `ALUNO_RA` varchar(15) NOT NULL,
  PRIMARY KEY (`TURMA_ID`,`ALUNO_RA`),
  KEY `fk_TB_TURMA_has_TB_ALUNO_TB_ALUNO1_idx` (`ALUNO_RA`),
  KEY `fk_TB_TURMA_has_TB_ALUNO_TB_TURMA1_idx` (`TURMA_ID`),
  CONSTRAINT `fk_TB_TURMA_has_TB_ALUNO_TB_ALUNO1` FOREIGN KEY (`ALUNO_RA`) REFERENCES `tb_aluno` (`RA`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_TB_TURMA_has_TB_ALUNO_TB_TURMA1` FOREIGN KEY (`TURMA_ID`) REFERENCES `tb_turma` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rel_turma_aluno`
--

LOCK TABLES `rel_turma_aluno` WRITE;
/*!40000 ALTER TABLE `rel_turma_aluno` DISABLE KEYS */;
INSERT INTO `rel_turma_aluno` VALUES (2,'21550465'),(12,'21550465');
/*!40000 ALTER TABLE `rel_turma_aluno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_aluno`
--

DROP TABLE IF EXISTS `tb_aluno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_aluno` (
  `RA` varchar(15) NOT NULL,
  `NOME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`RA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_aluno`
--

LOCK TABLES `tb_aluno` WRITE;
/*!40000 ALTER TABLE `tb_aluno` DISABLE KEYS */;
INSERT INTO `tb_aluno` VALUES ('21550465','Elizeu Freitas');
/*!40000 ALTER TABLE `tb_aluno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_disciplina`
--

DROP TABLE IF EXISTS `tb_disciplina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_disciplina` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOME` varchar(100) NOT NULL,
  `SIGLA` varchar(10) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_disciplina`
--

LOCK TABLES `tb_disciplina` WRITE;
/*!40000 ALTER TABLE `tb_disciplina` DISABLE KEYS */;
INSERT INTO `tb_disciplina` VALUES (1,'Lógica de Programação','LP'),(2,'Engenharia de Software','ES');
/*!40000 ALTER TABLE `tb_disciplina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_professor`
--

DROP TABLE IF EXISTS `tb_professor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_professor` (
  `MATRICULA` varchar(15) NOT NULL,
  `NOME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`MATRICULA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_professor`
--

LOCK TABLES `tb_professor` WRITE;
/*!40000 ALTER TABLE `tb_professor` DISABLE KEYS */;
INSERT INTO `tb_professor` VALUES ('MA123','Roberto Cantanhede');
/*!40000 ALTER TABLE `tb_professor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_questao`
--

DROP TABLE IF EXISTS `tb_questao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_questao` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TIPO` int(1) DEFAULT NULL,
  `NIVEL` int(1) DEFAULT NULL,
  `TEXTO` varchar(255) DEFAULT NULL,
  `STATUS` int(1) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=512 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_questao`
--

LOCK TABLES `tb_questao` WRITE;
/*!40000 ALTER TABLE `tb_questao` DISABLE KEYS */;
INSERT INTO `tb_questao` VALUES (472,0,0,'Algoritmo é uma sequência de passos interrelacionados utilizados para resolver um problema ou atingir um objetivo.',0),(482,0,0,'Uma variável serve para armazenar um valor na memória volátil do computador.',0),(492,0,0,'Toda função deve retornar algum valor',0),(502,0,0,'Existem oito tipos primitivos em Java',0);
/*!40000 ALTER TABLE `tb_questao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_quiz`
--

DROP TABLE IF EXISTS `tb_quiz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_quiz` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PROFESSOR_MATRICULA` varchar(15) NOT NULL,
  `DISCIPLINA_ID` int(11) NOT NULL,
  `DESCRICAO` varchar(255) DEFAULT NULL,
  `STATUS` int(1) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_PROFESSOR_DISCIPLINA_idx` (`PROFESSOR_MATRICULA`,`DISCIPLINA_ID`),
  CONSTRAINT `FK_QUIZ_PROFESSOR_DISCIPLINA` FOREIGN KEY (`PROFESSOR_MATRICULA`, `DISCIPLINA_ID`) REFERENCES `rel_professor_disciplina` (`PROFESSOR_MATRICULA`, `DISCIPLINA_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_quiz`
--

LOCK TABLES `tb_quiz` WRITE;
/*!40000 ALTER TABLE `tb_quiz` DISABLE KEYS */;
INSERT INTO `tb_quiz` VALUES (102,'MA123',1,'Quiz para testar a capacidade lógica do aluno de acordo com o conteúdo da primeira semana de aula',0),(112,'MA123',1,'Quiz que testa a capacidade do aluno de lembrar dos tipos primitivos do Java',0);
/*!40000 ALTER TABLE `tb_quiz` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_resposta`
--

DROP TABLE IF EXISTS `tb_resposta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_resposta` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `QUESTAO_ID` int(11) NOT NULL,
  `TEXTO` varchar(255) DEFAULT NULL,
  `CERTA` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_TB_MOD_RESPOSTA_TB_MOD_QUESTAO1_idx` (`QUESTAO_ID`),
  CONSTRAINT `FK_RESPOSTA_QUESTAO` FOREIGN KEY (`QUESTAO_ID`) REFERENCES `tb_questao` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=922 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_resposta`
--

LOCK TABLES `tb_resposta` WRITE;
/*!40000 ALTER TABLE `tb_resposta` DISABLE KEYS */;
INSERT INTO `tb_resposta` VALUES (842,472,'Verdadeiro',1),(852,472,'Falso',0),(862,482,'Verdadeiro',1),(872,482,'Falso',0),(882,492,'Verdadeiro',0),(892,492,'Falso',1),(902,502,'Verdadeiro',1),(912,502,'Falso',0);
/*!40000 ALTER TABLE `tb_resposta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_tema`
--

DROP TABLE IF EXISTS `tb_tema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_tema` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PROFESSOR_MATRICULA` varchar(15) NOT NULL,
  `DISCIPLINA_ID` int(11) NOT NULL,
  `NOME` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_TB_TEMA_TB_DISCIPLINA_has_TB_PROFESSOR1_idx` (`DISCIPLINA_ID`,`PROFESSOR_MATRICULA`),
  CONSTRAINT `FK_TEMA_PROFESSOR_DISCIPLINA` FOREIGN KEY (`DISCIPLINA_ID`, `PROFESSOR_MATRICULA`) REFERENCES `rel_professor_disciplina` (`DISCIPLINA_ID`, `PROFESSOR_MATRICULA`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=362 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_tema`
--

LOCK TABLES `tb_tema` WRITE;
/*!40000 ALTER TABLE `tb_tema` DISABLE KEYS */;
INSERT INTO `tb_tema` VALUES (312,'MA123',1,'Algoritmo'),(322,'MA123',1,'Variáveis'),(332,'MA123',1,'Funções'),(342,'MA123',1,'Tipos de Dados'),(352,'MA123',1,'Programação');
/*!40000 ALTER TABLE `tb_tema` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_treino`
--

DROP TABLE IF EXISTS `tb_treino`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_treino` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ALUNO_RA` varchar(15) NOT NULL,
  `TURMA_QUIZ_ID` int(11) NOT NULL,
  `TS_INICIO` datetime DEFAULT NULL,
  `TS_FIM` datetime DEFAULT NULL,
  `PONTUACAO` int(11) DEFAULT NULL,
  `APROVEITAMENTO` decimal(11,2) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_TB_TREINO_TB_ALUNO1_idx` (`ALUNO_RA`),
  KEY `FK_TURMA_QUIZ_idx` (`TURMA_QUIZ_ID`),
  CONSTRAINT `FK_ALUNO` FOREIGN KEY (`ALUNO_RA`) REFERENCES `tb_aluno` (`RA`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_TURMA_QUIZ` FOREIGN KEY (`TURMA_QUIZ_ID`) REFERENCES `tb_turma_quiz` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_treino`
--

LOCK TABLES `tb_treino` WRITE;
/*!40000 ALTER TABLE `tb_treino` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_treino` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_turma`
--

DROP TABLE IF EXISTS `tb_turma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_turma` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PROFESSOR_MATRICULA` varchar(15) NOT NULL,
  `DISCIPLINA_ID` int(11) NOT NULL,
  `TURNO` int(1) DEFAULT NULL,
  `ANO` int(4) DEFAULT NULL,
  `SEMESTRE` int(1) DEFAULT NULL,
  `LETRA` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_PROFESSOR_DISCIPLINA_idx` (`PROFESSOR_MATRICULA`,`DISCIPLINA_ID`),
  CONSTRAINT `FK_TURMA_PROFESSOR_DISCIPLINA` FOREIGN KEY (`PROFESSOR_MATRICULA`, `DISCIPLINA_ID`) REFERENCES `rel_professor_disciplina` (`PROFESSOR_MATRICULA`, `DISCIPLINA_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_turma`
--

LOCK TABLES `tb_turma` WRITE;
/*!40000 ALTER TABLE `tb_turma` DISABLE KEYS */;
INSERT INTO `tb_turma` VALUES (2,'MA123',1,0,2017,1,'A'),(12,'MA123',2,0,2017,1,'A');
/*!40000 ALTER TABLE `tb_turma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_turma_quiz`
--

DROP TABLE IF EXISTS `tb_turma_quiz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_turma_quiz` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TURMA_ID` int(11) NOT NULL,
  `QUIZ_ID` int(11) NOT NULL,
  `TS_PUBLICACAO` datetime DEFAULT NULL,
  `TS_ENCERRAMENTO` datetime DEFAULT NULL,
  `STATUS` int(1) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_TB_QUIZ_has_TB_TURMA_TB_TURMA1_idx` (`TURMA_ID`),
  KEY `fk_TB_QUIZ_has_TB_TURMA_TB_QUIZ1_idx` (`QUIZ_ID`),
  CONSTRAINT `fk_TB_QUIZ_has_TB_TURMA_TB_QUIZ1` FOREIGN KEY (`QUIZ_ID`) REFERENCES `tb_quiz` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_TB_QUIZ_has_TB_TURMA_TB_TURMA1` FOREIGN KEY (`TURMA_ID`) REFERENCES `tb_turma` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_turma_quiz`
--

LOCK TABLES `tb_turma_quiz` WRITE;
/*!40000 ALTER TABLE `tb_turma_quiz` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_turma_quiz` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-09  0:04:23
