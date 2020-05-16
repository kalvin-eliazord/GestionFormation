package gestionFormation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class BDD {

	private Connection cnx;
	private static Statement stmt;
	private static ResultSet rs;
	private static ResultSetMetaData resMeta;

	//constructeur
	public BDD() {

		// Charger le driver JBDC
		chargerDriver("com.mysql.jdbc.Driver");

		// Connexion � la BDD
		connexionBdd("mysql://localhost/", "croslformations", "root", "");

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
			System.out.println("Probl�me Connexion BDD "+ bddName + "  !!");

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


			String errorConnexion = "Unknown column 'status.idUtilisateur'";

			if (e.toString().contains(errorConnexion)){
				Connexion.affichagePopUp("Tu n'es pas ADMIN!!");

			} else {

				Connexion.affichagePopUp("Probleme requete SELECT non execut�e !!");
				e.printStackTrace();

			}
		}
	}

	//permet de faire une requ�te INSERT, UPDATE et DELETE
	public static void executeUpdate(String requete) {
		try {
			stmt.executeUpdate(requete);
			Connexion.affichagePopUp("Mis � jour r�ussie!");

		} catch (SQLException e) {

			//exception li�e � une supression d'un champ li� � une clef �trang�re
			String errorDeleteForeignKey ="com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException";
			//exception d'une date qui n'est pas rentr� dans le bon format
			String errorData ="com.mysql.jdbc.MysqlDataTruncation: Data truncation: Incorrect date value:";

			if (e.toString().contains(errorDeleteForeignKey)){
				Connexion.affichagePopUp("Supprimer la SESSION li�e AVANT!");

			} else if(e.toString().contains(errorData)) {	
				Connexion.affichagePopUp("Le format de la DATE n'est pas respect�! (AAAA/MM/JJ)");

			} else {
				Connexion.affichagePopUp("Ajout/Modification NON effectu�e!!");

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

	//initialisation des donn�es des jtable
	public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {

		resMeta = rs.getMetaData();

		// liste qui va contenir le nom des colonnes
		Vector<String> columnNames = new Vector<String>();

		// nombre total de colonne
		int columnCount = resMeta.getColumnCount();

		//ajout des noms de colonne � la liste 
		for (int column = 1; column <= columnCount; column++) {
			columnNames.add(resMeta.getColumnName(column));
		}

		// liste qui va contenir les champs des colonnes
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();

		//ajout des champs � la liste
		while (rs.next()) {
			Vector<Object> vector = new Vector<Object>();
			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
				vector.add(rs.getObject(columnIndex));
			}

			data.add(vector);
		}

		return new DefaultTableModel(data, columnNames);

	}	

	// getter Rs
	public static ResultSet getRs() {
		return rs;
	}

}