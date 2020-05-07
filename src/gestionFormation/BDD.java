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

		// Connexion � la BDD
		connexionBdd("mysql://localhost/", "formations", "root", "");

		// Creation d'ub statement
		creerStatement();
	}

	//Recherche et chargement du driver appropri� � la BDD
	public void chargerDriver(String pilote) {

		// Chargement du Driver (pilote)
		try {
			Class.forName(pilote);
			System.out.println("Driver trouv�!");
		}
		catch (ClassNotFoundException e) {

			Connexion.affichagePopUp("Driver non trouv�!");
			e.printStackTrace();
		}
	}

	// Etablissement de la connexion � la base de donn�es
	public void connexionBdd(String localisationBdd, String bddName, String user, String password) {

		try {
			cnx = DriverManager.getConnection("jdbc:"+localisationBdd+bddName, user, password);
			System.out.println("Connexion � la BDD "+ bddName +" OK!!");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			Connexion.affichagePopUp("Connexion � la BDD "+ bddName + " �chou�!");

			e.printStackTrace();
		}

	}

	// Creation d'un objet Statement
	public void creerStatement() {
		try {
			stmt = cnx.createStatement();
		} 
		catch (SQLException e) {
			Connexion.affichagePopUp("Probl�me cr�ation statement!!");
			e.printStackTrace();
		}
	}

	//permet de faire une requete SELECT
	public static void executeSelect(String requete) {
		try {
			rs = stmt.executeQuery(requete);

		} catch (SQLException e) {

			Connexion.affichagePopUp("Erreur! Requ�te SELECT non execut�e !!");
			e.printStackTrace();
		}
	}

	//permet de faire une requ�te INSERT, UPDATE et DELETE
	public static void executeUpdate(String requete) {
		try {
			stmt.executeUpdate(requete);
			Connexion.affichagePopUp("Mis � jour r�ussie!");

		} catch (SQLException e) {

			//exceptions li�es � une supression d'un champ li� � une clef �trang�re
			String errorDeleteForeignKey ="com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException";

			if (e.toString().contains(errorDeleteForeignKey)){
				Connexion.affichagePopUp("Supprimer la SESSION li�e AVANT!");

			} else {
				Connexion.affichagePopUp("Erreur! Mis � jour non effectu�e!");

			}
			e.printStackTrace();
		}
	}

	//permet de retourner vrai si une table est renvoy�e apr�s une requ�te
	public static boolean recupererResultatsRequete() throws SQLException {

		// Traitement de requ�te
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