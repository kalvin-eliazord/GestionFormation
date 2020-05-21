-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 21 mai 2020 à 15:38
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
-- Base de données : `croslformations`
--

-- --------------------------------------------------------

--
-- Structure de la table `concerner`
--

CREATE TABLE `concerner` (
  `idStatus` int(11) NOT NULL,
  `numFormation` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `concerner`
--

INSERT INTO `concerner` (`idStatus`, `numFormation`) VALUES
(1, 3),
(1, 8),
(2, 4),
(2, 6),
(2, 8),
(3, 5),
(3, 7),
(3, 8),
(4, 1),
(4, 2);

-- --------------------------------------------------------

--
-- Structure de la table `formation`
--

CREATE TABLE `formation` (
  `numFormation` int(11) NOT NULL,
  `objectif` varchar(100) NOT NULL,
  `couts` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `formation`
--

INSERT INTO `formation` (`numFormation`, `objectif`, `couts`) VALUES
(1, 'Traumatologie sportive', 60),
(2, 'Alimentation du sportif', 45),
(3, 'Conduites addictives', 60),
(4, 'gestion', 50),
(5, 'informatique', 55),
(6, 'developpement durable', 30),
(7, 'Secourisme', 65),
(8, 'communication', 45),
(13, 'sale', 10);

-- --------------------------------------------------------

--
-- Structure de la table `inscription`
--

CREATE TABLE `inscription` (
  `numSession` int(11) NOT NULL,
  `idUtilisateur` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `inscription`
--

INSERT INTO `inscription` (`numSession`, `idUtilisateur`) VALUES
(3, 18),
(4, 18),
(6, 15),
(8, 15),
(12, 15),
(13, 15);

-- --------------------------------------------------------

--
-- Structure de la table `intervenant`
--

CREATE TABLE `intervenant` (
  `idIntervenant` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `titre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `intervenant`
--

INSERT INTO `intervenant` (`idIntervenant`, `nom`, `prenom`, `titre`) VALUES
(2, 'pierrick', 'jose', 'developpeur'),
(3, 'gameiro', 'jose', 'gestion'),
(4, 'belle', 'anna', 'secourisme');

-- --------------------------------------------------------

--
-- Structure de la table `lieu`
--

CREATE TABLE `lieu` (
  `idLieu` int(11) NOT NULL,
  `nomLieu` varchar(50) NOT NULL,
  `adresse` varchar(200) NOT NULL,
  `codePostal` varchar(5) NOT NULL,
  `ville` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `lieu`
--

INSERT INTO `lieu` (`idLieu`, `nomLieu`, `adresse`, `codePostal`, `ville`) VALUES
(1, 'Salle informatique mrsl', '10 rue de la paix', '57000', 'Metz'),
(2, 'Salle polyvalente mp', '1 rue du 8 mai 1945', '57000', 'Metz'),
(3, 'Salle 1', '10 rue de la paix', '57000', 'Metz'),
(4, 'Salle 2', '10 rue de la paix', '57000', 'Metz'),
(5, 'Salle 3', '10 rue de la paix', '57000', 'Metz'),
(6, 'Salle informatique ncy', '47 rue de strasbourg', '54000', 'Nancy'),
(7, 'Salle polyvalente ncy1', '29 rue charles de gaulle', '54000', 'Nancy'),
(8, 'Salle 1 ncy', '47 rue de strasbourg', '54000', 'Nancy'),
(9, 'Salle 2 ncy', '47 rue de strasbourg', '54000', 'Nancy'),
(10, 'Salle 3 ncy', '47 rue de strasbourg', '54000', 'Nancy');

-- --------------------------------------------------------

--
-- Structure de la table `session`
--

CREATE TABLE `session` (
  `numSession` int(11) NOT NULL,
  `dateLimiteInscription` date NOT NULL,
  `dateSession` date NOT NULL,
  `dateFinSession` date NOT NULL,
  `idIntervenant` int(11) NOT NULL,
  `idLieu` int(11) NOT NULL,
  `numFormation` int(11) NOT NULL,
  `nbPlaces` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `session`
--

INSERT INTO `session` (`numSession`, `dateLimiteInscription`, `dateSession`, `dateFinSession`, `idIntervenant`, `idLieu`, `numFormation`, `nbPlaces`) VALUES
(3, '2020-05-16', '2020-05-17', '2020-05-19', 2, 2, 1, 10),
(4, '2020-03-23', '2020-03-24', '2020-03-25', 3, 4, 2, 8),
(5, '2020-04-23', '2020-04-24', '2020-04-25', 3, 7, 2, 8),
(6, '2020-05-23', '2020-05-24', '2020-05-25', 3, 4, 2, 8),
(7, '2020-06-23', '2020-06-24', '2020-06-25', 3, 3, 2, 8),
(8, '2020-07-23', '2020-07-24', '2020-07-25', 4, 6, 3, 15),
(10, '2020-05-23', '2020-05-24', '2020-05-25', 4, 6, 3, 15),
(11, '2020-06-23', '2020-06-24', '2020-06-25', 4, 7, 3, 15),
(12, '2020-04-20', '2020-04-21', '2020-04-22', 3, 2, 4, 10),
(13, '2020-05-20', '2020-05-21', '2020-05-22', 3, 7, 4, 10),
(15, '2020-08-13', '2020-08-14', '2020-08-15', 2, 3, 5, 12),
(18, '2020-12-11', '2020-12-12', '2020-12-13', 2, 8, 6, 13),
(19, '2020-04-02', '2020-04-03', '2020-03-04', 3, 1, 7, 10),
(20, '2020-05-02', '2020-05-03', '2020-05-04', 4, 2, 7, 10);

-- --------------------------------------------------------

--
-- Structure de la table `status`
--

CREATE TABLE `status` (
  `idStatus` int(11) NOT NULL,
  `libelle` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `status`
--

INSERT INTO `status` (`idStatus`, `libelle`) VALUES
(1, 'Bénévole'),
(2, 'Dirigeant'),
(3, 'Salarié'),
(4, 'Mouvement sportif'),
(5, 'admin');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `idUtilisateur` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(200) NOT NULL,
  `mail` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  `idStatus` int(11) NOT NULL,
  `telephone` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`idUtilisateur`, `nom`, `prenom`, `mail`, `password`, `idStatus`, `telephone`) VALUES
(1, 'Dujardin', 'jean', 'jdujardin@gmail.com', 'jdujardin', 1, 1234567891),
(2, 'Sy', 'omar', 'osy@yahoo.fr', 'osy', 2, 1234567891),
(3, 'Gauthier', 'jean paul', 'jpgauthier@live.fr', 'jpgauthier', 3, 1234567891),
(4, 'lothbrock', 'ragnar', 'rlothbrock@gmail.com', 'rlothbrock', 4, 1234567891),
(5, 'Smith', 'will', 'wsmith@yahoo.fr', 'wsmith', 1, 1234567891),
(6, 'Portman', 'nathalie', 'nportman@live.fr', 'nportman', 2, 1234567891),
(7, 'Gadot', 'gal', 'ggadot@gmail.com', 'ggadot', 3, 1234567891),
(8, 'Clark', 'emilia', 'eclark@yahoo.fr', 'eclark', 4, 1234567891),
(9, 'Johansson', 'scarlett', 'sjohansson@live.fr', 'sjohansson', 1, 1234567891),
(10, 'Hathaway', 'anne', 'ahathaway@gmail.com', 'ahathaway', 2, 1234567891),
(15, 'gourlay', 'antoine', 'gourlay@hotmail.fr', 'toto', 3, 1234567891),
(18, 'admin', 'admin', 'admin@admin.com', 'admin', 5, 123456789);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `concerner`
--
ALTER TABLE `concerner`
  ADD PRIMARY KEY (`idStatus`,`numFormation`) USING BTREE,
  ADD KEY `formation_numFormation_fk` (`numFormation`);

--
-- Index pour la table `formation`
--
ALTER TABLE `formation`
  ADD PRIMARY KEY (`numFormation`);

--
-- Index pour la table `inscription`
--
ALTER TABLE `inscription`
  ADD PRIMARY KEY (`numSession`,`idUtilisateur`),
  ADD KEY `id_utilisateur` (`idUtilisateur`);

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
  ADD KEY `numformation` (`numFormation`),
  ADD KEY `id_intervenant` (`idIntervenant`),
  ADD KEY `id_lieu` (`idLieu`);

--
-- Index pour la table `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`idStatus`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`idUtilisateur`),
  ADD KEY `id_public` (`idStatus`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `formation`
--
ALTER TABLE `formation`
  MODIFY `numFormation` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT pour la table `intervenant`
--
ALTER TABLE `intervenant`
  MODIFY `idIntervenant` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `lieu`
--
ALTER TABLE `lieu`
  MODIFY `idLieu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `session`
--
ALTER TABLE `session`
  MODIFY `numSession` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT pour la table `status`
--
ALTER TABLE `status`
  MODIFY `idStatus` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `idUtilisateur` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `concerner`
--
ALTER TABLE `concerner`
  ADD CONSTRAINT `formation_numFormation_fk` FOREIGN KEY (`numFormation`) REFERENCES `formation` (`numFormation`),
  ADD CONSTRAINT `status_idStatus_fk` FOREIGN KEY (`idStatus`) REFERENCES `status` (`idStatus`);

--
-- Contraintes pour la table `inscription`
--
ALTER TABLE `inscription`
  ADD CONSTRAINT `id_utilisateur` FOREIGN KEY (`idUtilisateur`) REFERENCES `utilisateur` (`idUtilisateur`),
  ADD CONSTRAINT `numsession` FOREIGN KEY (`numSession`) REFERENCES `session` (`numSession`);

--
-- Contraintes pour la table `session`
--
ALTER TABLE `session`
  ADD CONSTRAINT `session_ibfk_1` FOREIGN KEY (`numFormation`) REFERENCES `formation` (`numFormation`),
  ADD CONSTRAINT `session_ibfk_2` FOREIGN KEY (`idIntervenant`) REFERENCES `intervenant` (`idIntervenant`),
  ADD CONSTRAINT `session_ibfk_3` FOREIGN KEY (`idLieu`) REFERENCES `lieu` (`idLieu`);

--
-- Contraintes pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD CONSTRAINT `utilisateur_ibfk_1` FOREIGN KEY (`idStatus`) REFERENCES `status` (`idStatus`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
