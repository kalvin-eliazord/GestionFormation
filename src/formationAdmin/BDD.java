package formationAdmin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BDD {
	
	private Connection cnx;
	private static Statement stmt;
	private static ResultSet rs;

	//Recherche et chargement du driver approprié à la BDD
	public void chargerDriver(String pilote) {

		// Chargement du Driver (pilote)
		try {
			Class.forName(pilote);
			System.out.println("Driver trouvé!!!");
		}
		catch (ClassNotFoundException e) {

			System.out.println("Driver non trouvé!!!");
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
			System.out.println("Problème Connexion BDD "+ bddName + "  !!");

			e.printStackTrace();
		}

	}

	// Creation d'un objet Statement
	public void creerStatement() {
		try {
			stmt = cnx.createStatement();
		} 
		catch (SQLException e) {
			Login.affichagePopUp("Problème création statement!!");
			e.printStackTrace();
		}
	}
	
	//permet de faire une requete SELECT
	public static void executeSelect(String requete) {
		try {
			rs = stmt.executeQuery(requete);

		} catch (SQLException e) {

			Login.affichagePopUp("Probleme requete SELECT non executée !!");
			e.printStackTrace();
		}
	}
	
	//permet de faire une requête INSERT, UPDATE et DELETE
	public static void executeUpdate(String requete) {
		try {
			stmt.executeUpdate(requete);
			Login.affichagePopUp("Requete UPDATE éxécutée !!");
		} catch (SQLException e) {

			Login.affichagePopUp("Problème requete UPDATE non executée !!");
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