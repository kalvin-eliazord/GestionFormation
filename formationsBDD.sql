-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 07 mai 2020 à 02:48
-- Version du serveur :  10.4.11-MariaDB
-- Version de PHP : 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `formations`
--

-- --------------------------------------------------------

--
-- Structure de la table `concerner`
--

CREATE TABLE `concerner` (
  `idStatus` int(20) NOT NULL,
  `numFormation` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `formation`
--

CREATE TABLE `formation` (
  `numFormation` int(20) NOT NULL,
  `objectif` varchar(20) NOT NULL,
  `couts` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `formation`
--

INSERT INTO `formation` (`numFormation`, `objectif`, `couts`) VALUES
(3, 'tuer', 50),
(4, 'a', 55),
(20, 'kl', 20),
(21, 'aa', 11);

-- --------------------------------------------------------

--
-- Structure de la table `intervenant`
--

CREATE TABLE `intervenant` (
  `idIntervenant` int(20) NOT NULL,
  `nom` varchar(20) NOT NULL,
  `prenom` varchar(20) NOT NULL,
  `titre` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `intervenant`
--

INSERT INTO `intervenant` (`idIntervenant`, `nom`, `prenom`, `titre`) VALUES
(2, 'a', 'a', 'b'),
(4, 'chocho', 'chocho', 'chocho'),
(20, 'ba', 'ba', 'ba'),
(25, 'root', 'Kalvin', 'test'),
(26, 'a', 'a', 'a'),
(27, 'aa', 'aa', 'aa');

-- --------------------------------------------------------

--
-- Structure de la table `lieu`
--

CREATE TABLE `lieu` (
  `idLieu` int(20) NOT NULL,
  `nomLieu` varchar(20) NOT NULL,
  `adresse` varchar(20) NOT NULL,
  `codePostal` int(5) NOT NULL,
  `ville` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `lieu`
--

INSERT INTO `lieu` (`idLieu`, `nomLieu`, `adresse`, `codePostal`, `ville`) VALUES
(1, 'papa', 'popo', 12345, 'papa'),
(2, 'belgique', 'lol', 12345, 'lol'),
(20, 'a', 'a', 12345, 'a'),
(24, 'popo', 'popo', 12345, 'popo'),
(25, 'papa', 'popo', 12345, 'popo');

-- --------------------------------------------------------

--
-- Structure de la table `session`
--

CREATE TABLE `session` (
  `numSession` int(11) NOT NULL,
  `dateLimiteInsc` date NOT NULL,
  `dateSession` date NOT NULL,
  `dateDeFin` date NOT NULL,
  `idIntervenant` int(11) NOT NULL,
  `idLieu` int(11) NOT NULL,
  `numFormation` int(11) NOT NULL,
  `nbPlaces` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `session`
--

INSERT INTO `session` (`numSession`, `dateLimiteInsc`, `dateSession`, `dateDeFin`, `idIntervenant`, `idLieu`, `numFormation`, `nbPlaces`) VALUES
(11, '2020-05-04', '2020-05-04', '2020-05-04', 20, 20, 20, 20),
(12, '2020-05-05', '2020-05-20', '2020-05-09', 25, 20, 4, 40),
(14, '2020-05-05', '2020-05-05', '2020-05-05', 2, 1, 3, 1);

-- --------------------------------------------------------

--
-- Structure de la table `sincrire`
--

CREATE TABLE `sincrire` (
  `idUtilisateur` int(20) NOT NULL,
  `numSession` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `status`
--

CREATE TABLE `status` (
  `idStatus` int(20) NOT NULL,
  `libelle` varchar(20) NOT NULL,
  `idUtilisateur` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `status`
--

INSERT INTO `status` (`idStatus`, `libelle`, `idUtilisateur`) VALUES
(1, 'admin', 1);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `idUtilisateur` int(20) NOT NULL,
  `nom` varchar(20) NOT NULL,
  `prenom` varchar(20) NOT NULL,
  `mail` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`idUtilisateur`, `nom`, `prenom`, `mail`, `password`) VALUES
(1, 'Eliazord', 'Kalvin', 'admin@admin.com', 'admin');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `concerner`
--
ALTER TABLE `concerner`
  ADD PRIMARY KEY (`idStatus`,`numFormation`),
  ADD KEY `numformation` (`numFormation`);

--
-- Index pour la table `formation`
--
ALTER TABLE `formation`
  ADD PRIMARY KEY (`numFormation`);

--
-- Index pour la table `intervenant`
--
ALTER TABLE `intervenant`
  ADD PRIMARY KEY (`idIntervenant`);

--
-- Index pour la table `lieu`
--
ALTER TABLE `lieu`
  ADD PRIMARY KEY (`idLieu`);

--
-- Index pour la table `session`
--
ALTER TABLE `session`
  ADD PRIMARY KEY (`numSession`),
  ADD KEY `idintervenant` (`idIntervenant`),
  ADD KEY `idlieu` (`idLieu`),
  ADD KEY `numformation` (`numFormation`);

--
-- Index pour la table `sincrire`
--
ALTER TABLE `sincrire`
  ADD PRIMARY KEY (`idUtilisateur`,`numSession`),
  ADD KEY `numsession` (`numSession`);

--
-- Index pour la table `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`idStatus`),
  ADD KEY `idutilisateur` (`idUtilisateur`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`idUtilisateur`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `formation`
--
ALTER TABLE `formation`
  MODIFY `numFormation` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT pour la table `intervenant`
--
ALTER TABLE `intervenant`
  MODIFY `idIntervenant` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT pour la table `lieu`
--
ALTER TABLE `lieu`
  MODIFY `idLieu` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT pour la table `session`
--
ALTER TABLE `session`
  MODIFY `numSession` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT pour la table `status`
--
ALTER TABLE `status`
  MODIFY `idStatus` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `idUtilisateur` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `session`
--
ALTER TABLE `session`
  ADD CONSTRAINT `session_ibfk_1` FOREIGN KEY (`idIntervenant`) REFERENCES `intervenant` (`idIntervenant`),
  ADD CONSTRAINT `session_ibfk_2` FOREIGN KEY (`idLieu`) REFERENCES `lieu` (`idLieu`),
  ADD CONSTRAINT `session_ibfk_3` FOREIGN KEY (`numFormation`) REFERENCES `formation` (`numFormation`);

--
-- Contraintes pour la table `sincrire`
--
ALTER TABLE `sincrire`
  ADD CONSTRAINT `sincrire_ibfk_1` FOREIGN KEY (`idUtilisateur`) REFERENCES `utilisateur` (`idUtilisateur`),
  ADD CONSTRAINT `sincrire_ibfk_2` FOREIGN KEY (`numSession`) REFERENCES `session` (`numSession`);

--
-- Contraintes pour la table `status`
--
ALTER TABLE `status`
  ADD CONSTRAINT `status_ibfk_1` FOREIGN KEY (`idUtilisateur`) REFERENCES `utilisateur` (`idUtilisateur`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
