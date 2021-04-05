-- MySQL dump 10.13  Distrib 8.0.22, for macos10.15 (x86_64)
--
-- Host: localhost    Database: Country_Quiz
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `country_continent`
--

DROP TABLE IF EXISTS `country_continent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `country_continent` (
  `country` varchar(40) NOT NULL,
  `continent` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`country`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country_continent`
--

LOCK TABLES `country_continent` WRITE;
/*!40000 ALTER TABLE `country_continent` DISABLE KEYS */;
INSERT INTO `country_continent` VALUES ('Albania','Europe'),('Algeria','Africa'),('Andorra','Europe'),('Angola','Africa'),('Antigua and Barbuda','North America'),('Argentina','South America'),('Armenia','Europe'),('Australia','Oceania'),('Austria','Europe'),('Azerbaijan','Europe'),('Bahrain','Asia'),('Bangladesh','Asia'),('Barbados','North America'),('Belarus','Europe'),('Belgium','Europe'),('Belize','North America'),('Benin','Africa'),('Bhutan','Asia'),('Bolivia','South America'),('Bosnia and Herzegovina','Europe'),('Botswana','Africa'),('Brazil','South America'),('Brunei','Asia'),('Bulgaria','Europe'),('Burkina Faso','Africa'),('Burundi','Africa'),('Cambodia','Asia'),('Cameroon','Africa'),('Canada','North America'),('Cape Verde','Africa'),('Central African Republic','Africa'),('Chad','Africa'),('Chile','South America'),('China','Asia'),('Colombia','South America'),('Comoros','Africa'),('Congo','Africa'),('Costa Rica','North America'),('Croatia','Europe'),('Cuba','North America'),('Cyprus','Europe'),('Czech Republic','Europe'),('Democratic Republic of the Congo','Africa'),('Denmark','Europe'),('Djibouti','Africa'),('Dominica','North America'),('Dominican Republic','North America'),('East Timor','Asia'),('Ecuador','South America'),('Egypt','Africa'),('El Salvador','North America'),('Equatorial Guinea','Africa'),('Eritrea','Africa'),('Estonia','Europe'),('Ethiopia','Africa'),('Fiji','Oceania'),('Finland','Europe'),('France','Europe'),('Gabon','Africa'),('Gambia','Africa'),('Georgia','Europe'),('Germany','Europe'),('Ghana','Africa'),('Greece','Europe'),('Grenada','North America'),('Guatemala','North America'),('Guinea','Africa'),('Guinea-Bissau','Africa'),('Guyana','South America'),('Haiti','North America'),('Honduras','North America'),('Hungary','Europe'),('Iceland','Europe'),('India','Asia'),('Indonesia','Asia'),('Iran','Asia'),('Iraq','Asia'),('Ireland','Europe'),('Israel','Asia'),('Italy','Europe'),('Ivory Coast','Africa'),('Jamaica','North America'),('Japan','Asia'),('Jordan','Asia'),('Kazakhstan','Asia'),('Kenya','Africa'),('Kiribati','Oceania'),('Kosovo','Europe'),('Kuwait','Asia'),('Kyrgyzstan','Asia'),('Laos','Asia'),('Latvia','Europe'),('Lebanon','Asia'),('Lesotho','Africa'),('Liberia','Africa'),('Libya','Africa'),('Liechtenstein','Europe'),('Lithuania','Europe'),('Luxembourg','Europe'),('Macedonia','Europe'),('Madagascar','Africa'),('Malawi','Africa'),('Malaysia','Asia'),('Maldives','Asia'),('Mali','Africa'),('Malta','Europe'),('Marshall Islands','Oceania'),('Mauritania','Africa'),('Mauritius','Africa'),('Mexico','North America'),('Micronesia','Oceania'),('Moldova','Europe'),('Monaco','Europe'),('Mongolia','Asia'),('Montenegro','Europe'),('Morocco','Africa'),('Mozambique','Africa'),('Myanmar','Asia'),('Namibia','Africa'),('Nauru','Oceania'),('Nepal','Asia'),('Netherlands','Europe'),('New Zealand','Oceania'),('Nicaragua','North America'),('Niger','Africa'),('Nigeria','Africa'),('North Korea','Asia'),('Norway','Europe'),('Oman','Asia'),('Pakistan','Asia'),('Palau','Oceania'),('Panama','North America'),('Papua New Guinea','Oceania'),('Paraguay','South America'),('Peru','South America'),('Philippines','Asia'),('Poland','Europe'),('Portugal','Europe'),('Qatar','Asia'),('Romania','Europe'),('Russia','Asia'),('Rwanda','Africa'),('Saint Kitts and Nevis','North America'),('Saint Lucia','North America'),('Saint Vincent and the Grenadines','North America'),('Samoa','Oceania'),('San Marino','Europe'),('Sao Tome and Principe','Africa'),('Saudi Arabia','Asia'),('Senegal','Africa'),('Serbia','Europe'),('Seychelles','Africa'),('Sierra Leone','Africa'),('Singapore','Asia'),('Slovakia','Europe'),('Slovenia','Europe'),('Solomon Islands','Oceania'),('Somalia','Africa'),('South Africa','Africa'),('South Korea','Asia'),('South Sudan','Africa'),('Spain','Europe'),('Sri Lanka','Asia'),('Sudan','Africa'),('Suriname','South America'),('Swaziland','Africa'),('Sweden','Europe'),('Switzerland','Europe'),('Syria','Asia'),('Taiwan','Asia'),('Tajikistan','Asia'),('Tanzania','Africa'),('Thailand','Asia'),('Togo','Africa'),('Tonga','Oceania'),('Trinidad and Tobago','North America'),('Tunisia','Africa'),('Turkey','Asia'),('Turkmenistan','Asia'),('Tuvalu','Oceania'),('Uganda','Africa'),('Ukraine','Europe'),('United Arab Emirates','Asia'),('United Kingdom','Europe'),('United States of America','North America'),('Uruguay','South America'),('Uzbekistan','Asia'),('Vanuatu','Oceania'),('Vatican City','Europe'),('Venezuela','South America'),('Vietnam','Asia'),('Yemen','Asia'),('Zambia','Africa'),('Zimbabwe','Africa');
/*!40000 ALTER TABLE `country_continent` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-01 22:22:22
