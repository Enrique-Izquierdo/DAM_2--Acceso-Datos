-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 30-01-2022 a las 13:17:41
-- Versión del servidor: 10.4.22-MariaDB
-- Versión de PHP: 8.0.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `restcountries`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paisos`
--

CREATE TABLE `paisos` (
  `nomComu` varchar(100) NOT NULL,
  `nomOficial` varchar(100) NOT NULL,
  `capital` varchar(100) NOT NULL,
  `poblacio` varchar(100) NOT NULL,
  `regio` varchar(100) NOT NULL,
  `subregio` varchar(100) NOT NULL,
  `superficie` varchar(100) NOT NULL,
  `bandera` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `paisos`
--

INSERT INTO `paisos` (`nomComu`, `nomOficial`, `capital`, `poblacio`, `regio`, `subregio`, `superficie`, `bandera`) VALUES
('España', 'Reino de España', 'Madrid', '47351567', 'Europe', 'Southern Europe', '505992', 'https://flagcdn.com/w320/es.png'),
('Alemania', 'República Federal de Alemania', 'Berlin', '83240525', 'Europe', 'Western Europe', '357114', 'https://flagcdn.com/w320/de.png'),
('Etiopia', 'República Democrática Federal de Etiopía', 'Addis Ababa', '114963583', 'Africa', 'Eastern Africa', '1104300', 'https://flagcdn.com/w320/et.png'),
('Peru', 'República de Perú', 'Lima', '32971846', 'Americas', 'South America', '1285216', 'https://flagcdn.com/w320/pe.png'),
('Islandia', 'Islandia', 'Reykjavik', '366425', 'Europe', 'Northern Europe', '103000', 'https://flagcdn.com/w320/is.png'),
('Rumania', 'Rumania', 'Bucharest', '19286123', 'Europe', 'Southeast Europe', '238391', 'https://flagcdn.com/w320/ro.png'),
('Marruecos', 'Reino de Marruecos', 'Rabat', '36910558', 'Africa', 'Northern Africa', '446550', 'https://flagcdn.com/w320/ma.png'),
('Estonia', 'República de Estonia', 'Tallinn', '1331057', 'Europe', 'Northern Europe', '45227', 'https://flagcdn.com/w320/ee.png'),
('Bolivia', 'Estado Plurinacional de Bolivia', 'Sucre', '11673029', 'Americas', 'South America', '1098581', 'https://flagcdn.com/w320/bo.png'),
('Mexico', 'Estados Unidos Mexicanos', 'Mexico City', '128932753', 'Americas', 'North America', '1964375', 'https://flagcdn.com/w320/mx.png'),
('Cuba', 'República de Cuba', 'Havana', '11326616', 'Americas', 'Caribbean', '109884', 'https://flagcdn.com/w320/cu.png'),
('China', 'República de China en Taiwán', 'Taipei', '23503349', 'Asia', 'Eastern Asia', '36193', 'https://flagcdn.com/w320/tw.png'),
('Ucrania', 'Ucrania', 'Kyiv', '44134693', 'Europe', 'Eastern Europe', '603500', 'https://flagcdn.com/w320/ua.png');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
