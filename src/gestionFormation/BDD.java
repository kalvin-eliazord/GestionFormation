package gestionFormation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BDD {

	private Connection cnx;
	private static Statement stmt;
	private static ResultSet rs;

	//constructeur
	public BDD() {

		// Charger le driver JBDC
		chargerDriver("com.mysql.jdbc.Driver");

		// Connexion à la BDD
		connexionBdd("mysql://localhost/", "formations", "root", "");

		// Creation d'ub statement
		creerStatement();
	}

	//Recherche et chargement du driver approprié à la BDD
	public void chargerDriver(String pilote) {

		// Chargement du Driver (pilote)
		try {
			Class.forName(pilote);
			System.out.println("Driver trouvé!");
		}
		catch (ClassNotFoundException e) {

			Connexion.affichagePopUp("Driver non trouvé!");
			e.printStackTrace();
		}
	}

	// Etablissement de la connexion à la base de données
	public void connexionBdd(String localisationBdd, String bddName, String user, String password) {

		try {
			cnx = DriverManager.getConnection("jdbc:"+localisationBdd+bddName, user, password);
			System.out.println("Connexion à la BDD "+ bddName +" OK!!");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			Connexion.affichagePopUp("Connexion à la BDD "+ bddName + " échoué!");

			e.printStackTrace();
		}

	}

	// Creation d'un objet Statement
	public void creerStatement() {
		try {
			stmt = cnx.createStatement();
		} 
		catch (SQLException e) {
			Connexion.affichagePopUp("Problème création statement!!");
			e.printStackTrace();
		}
	}

	//permet de faire une requete SELECT
	public static void executeSelect(String requete) {
		try {
			rs = stmt.executeQuery(requete);

		} catch (SQLException e) {

			Connexion.affichagePopUp("Erreur! Requête SELECT non executée !!");
			e.printStackTrace();
		}
	}

	//permet de faire une requête INSERT, UPDATE et DELETE
	public static void executeUpdate(String requete) {
		try {
			stmt.executeUpdate(requete);
			Connexion.affichagePopUp("Mis à jour réussie!");

		} catch (SQLException e) {

			//exceptions liées à une supression d'un champ lié à une clef étrangère
			String errorDeleteForeignKey ="com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException";

			if (e.toString().contains(errorDeleteForeignKey)){
				Connexion.affichagePopUp("Supprimer la SESSION liée AVANT!");

			} else {
				Connexion.affichagePopUp("Erreur! Mis à jour non effectuée!");

			}
			e.printStackTrace();
		}
	}

	//permet de retourner vrai si une table est renvoyée après une requête
	public static boolean recupererResultatsRequete() throws SQLException {

		// Traitement de requête
		int count = 0;
		while(rs.next()){
			count = count +1;
		}
		if (count==1){
			return true;
		}
		
		return false;
	}

	// getter Rs
	public static ResultSet getRs() {
		return rs;
	}

}