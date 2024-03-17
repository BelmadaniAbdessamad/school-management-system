-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 17, 2024 at 04:16 AM
-- Server version: 10.4.25-MariaDB
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `annuaire_ens`
--
CREATE DATABASE IF NOT EXISTS `annuaire_ens` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `annuaire_ens`;

-- --------------------------------------------------------

--
-- Table structure for table `departements`
--

CREATE TABLE `departements` (
  `id` int(11) NOT NULL,
  `departement` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `departements`
--

INSERT INTO `departements` (`id`, `departement`) VALUES(1, 'math');
INSERT INTO `departements` (`id`, `departement`) VALUES(2, 'physique');
INSERT INTO `departements` (`id`, `departement`) VALUES(3, 'informatique');
INSERT INTO `departements` (`id`, `departement`) VALUES(4, 'langues');

-- --------------------------------------------------------

--
-- Table structure for table `etudiants`
--

CREATE TABLE `etudiants` (
  `id` int(11) NOT NULL,
  `cne` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `filiere` int(11) NOT NULL,
  `tel` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `etudiants`
--

INSERT INTO `etudiants` (`id`, `cne`, `nom`, `prenom`, `filiere`, `tel`) VALUES(1, 123, 'Belmadani', 'Abdessamad', 5, '066666666');
INSERT INTO `etudiants` (`id`, `cne`, `nom`, `prenom`, `filiere`, `tel`) VALUES(2, 321, 'Ali', 'Morad', 5, '0645123857');
INSERT INTO `etudiants` (`id`, `cne`, `nom`, `prenom`, `filiere`, `tel`) VALUES(3, 124, 'Hamimi', 'Mohammed', 1, '0214568');
INSERT INTO `etudiants` (`id`, `cne`, `nom`, `prenom`, `filiere`, `tel`) VALUES(4, 125, 'Alaoui', 'Imad', 1, '05478666');
INSERT INTO `etudiants` (`id`, `cne`, `nom`, `prenom`, `filiere`, `tel`) VALUES(5, 541, 'Mrani', 'Fatim Ezzahra', 2, '0668547566');
INSERT INTO `etudiants` (`id`, `cne`, `nom`, `prenom`, `filiere`, `tel`) VALUES(6, 111, 'Fisal', 'Adellah', 2, '066666666');
INSERT INTO `etudiants` (`id`, `cne`, `nom`, `prenom`, `filiere`, `tel`) VALUES(7, 652, 'Draoui', 'Mariam', 3, '066666666');
INSERT INTO `etudiants` (`id`, `cne`, `nom`, `prenom`, `filiere`, `tel`) VALUES(8, 841, 'Zerhouni', 'Karim', 3, '066666666');
INSERT INTO `etudiants` (`id`, `cne`, `nom`, `prenom`, `filiere`, `tel`) VALUES(9, 984, 'Wadie', 'Abdessamad', 4, '066666666');
INSERT INTO `etudiants` (`id`, `cne`, `nom`, `prenom`, `filiere`, `tel`) VALUES(10, 931, 'Mohammadi', 'Reda', 4, '066666666');
INSERT INTO `etudiants` (`id`, `cne`, `nom`, `prenom`, `filiere`, `tel`) VALUES(11, 222, 'Tahiri', 'Amine', 5, '066666666');
INSERT INTO `etudiants` (`id`, `cne`, `nom`, `prenom`, `filiere`, `tel`) VALUES(12, 325, 'Rabie', 'Zakaria', 6, '066666666');
INSERT INTO `etudiants` (`id`, `cne`, `nom`, `prenom`, `filiere`, `tel`) VALUES(13, 953, 'Diaye', 'Ikhlas', 6, '066666666');
INSERT INTO `etudiants` (`id`, `cne`, `nom`, `prenom`, `filiere`, `tel`) VALUES(14, 827, 'Gondogan', 'Ali', 7, '066666666');
INSERT INTO `etudiants` (`id`, `cne`, `nom`, `prenom`, `filiere`, `tel`) VALUES(15, 173, 'De Brune', 'kevin', 7, '066666666');
INSERT INTO `etudiants` (`id`, `cne`, `nom`, `prenom`, `filiere`, `tel`) VALUES(16, 843, 'Alphonso', 'Davies', 8, '066666666');

-- --------------------------------------------------------

--
-- Table structure for table `filieres`
--

CREATE TABLE `filieres` (
  `id` int(11) NOT NULL,
  `filiere` varchar(255) NOT NULL,
  `departement` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `filieres`
--

INSERT INTO `filieres` (`id`, `filiere`, `departement`) VALUES(1, 'Math Appliqué', 1);
INSERT INTO `filieres` (`id`, `filiere`, `departement`) VALUES(2, 'la logique mathématique', 1);
INSERT INTO `filieres` (`id`, `filiere`, `departement`) VALUES(3, 'Physique appliquée', 2);
INSERT INTO `filieres` (`id`, `filiere`, `departement`) VALUES(4, 'Sciences et génie des matériaux', 2);
INSERT INTO `filieres` (`id`, `filiere`, `departement`) VALUES(5, 'Dev Web', 3);
INSERT INTO `filieres` (`id`, `filiere`, `departement`) VALUES(6, 'Dev Mobile', 3);
INSERT INTO `filieres` (`id`, `filiere`, `departement`) VALUES(7, 'Arabe', 4);
INSERT INTO `filieres` (`id`, `filiere`, `departement`) VALUES(8, 'English', 4);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `departements`
--
ALTER TABLE `departements`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `etudiants`
--
ALTER TABLE `etudiants`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_filiere_filiere` (`filiere`);

--
-- Indexes for table `filieres`
--
ALTER TABLE `filieres`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_filiere_departement` (`departement`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `departements`
--
ALTER TABLE `departements`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `etudiants`
--
ALTER TABLE `etudiants`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `filieres`
--
ALTER TABLE `filieres`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `etudiants`
--
ALTER TABLE `etudiants`
  ADD CONSTRAINT `fk_filiere_filiere` FOREIGN KEY (`filiere`) REFERENCES `filieres` (`id`);

--
-- Constraints for table `filieres`
--
ALTER TABLE `filieres`
  ADD CONSTRAINT `fk_filiere_departement` FOREIGN KEY (`departement`) REFERENCES `departements` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
