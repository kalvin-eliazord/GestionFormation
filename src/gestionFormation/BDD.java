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

		chargerDriver("com.mysql.jdbc.Driver");

		connexionBdd("mysql://localhost/", "croslformations", "root", "");

		creerStatement();
	}

	public void chargerDriver(String pilote) {

		try {
			Class.forName(pilote);
			System.out.println("Driver trouvé!");
		}
		catch (ClassNotFoundException e) {

			Connexion.affichagePopUp("Driver non trouvé!");
			e.printStackTrace();
		}
	}

	public void connexionBdd(String localisationBdd, String bddName, String user, String password) {

		try {
			cnx = DriverManager.getConnection("jdbc:"+localisationBdd+bddName, user, password);
			System.out.println("Connexion à la BDD "+ bddName +" OK!!");
		}
		catch (SQLException e) {
			System.out.println("Problème Connexion BDD "+ bddName + "  !!");

			e.printStackTrace();
		}

	}

	public void creerStatement() {
		try {
			stmt = cnx.createStatement();
		} 
		catch (SQLException e) {
			Connexion.affichagePopUp("Problème création statement!!");
			e.printStackTrace();
		}
	}

	public static void executeSelect(String requete) {
		try {
			rs = stmt.executeQuery(requete);

		} catch (SQLException e) {

			String errorConnexion = "Unknown column 'status.idUtilisateur'";

			if (e.toString().contains(errorConnexion)){
				Connexion.affichagePopUp("Tu n'as pas le statut pour te connecter!");

			} else {

				Connexion.affichagePopUp("Probleme requete SELECT non executée !!");
				e.printStackTrace();

			}
		}
	}

	public static void executeUpdate(String requete) {
		try {
			stmt.executeUpdate(requete);
			Connexion.affichagePopUp("Mis à jour réussie!");

		} catch (SQLException e) {

			//exception liée à une supression d'un champ lié à une clef étrangère
			String errorDeleteForeignKey ="com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException";
			//exception d'une date qui n'est pas rentré dans le bon format
			String errorData ="com.mysql.jdbc.MysqlDataTruncation: Data truncation: Incorrect date value:";

			if (e.toString().contains(errorDeleteForeignKey)){
				Connexion.affichagePopUp("Supprimer la SESSION liée AVANT!");

			} else if(e.toString().contains(errorData)) {	
				Connexion.affichagePopUp("Le format de la DATE n'est pas respecté! (AAAA/MM/JJ)");

			} else {
				Connexion.affichagePopUp("Ajout/Modification NON effectuée!!");
			}
			
			e.printStackTrace();
		}
	}

	public static boolean recupererResultatsRequete() throws SQLException {

		int count = 0;
		while(rs.next()){
			count = count +1;
		}

		if (count==1){
			return true;
		}

		return false;
	}

	public static DefaultTableModel buildTable(ResultSet rs) throws SQLException {

		resMeta = rs.getMetaData();
		
		int columnCount = resMeta.getColumnCount();
		
		Vector<String> columnNames = new Vector<String>();

		for (int column = 1; column <= columnCount; column++) {
			columnNames.add(resMeta.getColumnName(column));
		}

		Vector<Vector<Object>> data = new Vector<Vector<Object>>();

		while (rs.next()) {
			
			Vector<Object> vector = new Vector<Object>();
			
			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
				vector.add(rs.getObject(columnIndex));
			}

			data.add(vector);
		}

		return new DefaultTableModel(data, columnNames);

	}	

	public static ResultSet getRs() {
		return rs;
	}

}