package gestionFormation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class BDD {
	
	/**
	 * Cette classe permet d'afficher des messages d'erreurs 
	 * et de se connecter � la BDD.
	 */
	
	private static Connection cnx;
	private static Statement stmt;
	private static ResultSet rs;
	private static ResultSetMetaData resMeta;

	public BDD() {

		chargerDriver("com.mysql.jdbc.Driver");
		connexionBdd("mysql://localhost/", "croslformations", "root", "");
		creerStatement();
	}

	public void chargerDriver(String pilote) {

		try {
			Class.forName(pilote);
			System.out.println("Driver trouv�!");
		}
		catch (ClassNotFoundException e) {

			JOptionPane.showMessageDialog(null, "Driver non trouv�!");
			e.printStackTrace();
		}
	}

	public void connexionBdd(String localisationBdd, String bddName, String user, String password) {

		try {
			cnx = DriverManager.getConnection("jdbc:"+localisationBdd+bddName, user, password);
			System.out.println("Connexion � la BDD "+ bddName +" OK!!");
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Probl�me Connexion BDD "+ bddName + "  !!");

			e.printStackTrace();
		}

	}

	public void creerStatement() {
		try {
			stmt = cnx.createStatement();
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Probl�me cr�ation statement!!");
			e.printStackTrace();
		}
	}

	public static void executeSelect(String requete) {
		try {
			rs = stmt.executeQuery(requete);

		} catch (SQLException e) {

			String errorConnexion = "Unknown column 'status.idUtilisateur'";

			if (e.toString().contains(errorConnexion)){
				JOptionPane.showMessageDialog(null, "Tu n'as pas le statut pour te connecter!");

			} else {

				JOptionPane.showMessageDialog(null, "Probleme requete SELECT non execut�e !!");
				e.printStackTrace();

			}
		}
	}

	public static void executeUpdate(String requete) {
		try {
			stmt.executeUpdate(requete);
			JOptionPane.showMessageDialog(null,"Mis � jour r�ussie!");

		} catch (SQLException e) {

			//exception li�e � une supression d'un champ li� � une clef �trang�re
			String errorDeleteForeignKey ="Cannot delete or update a parent row: a foreign key constraint fails";

			//exception d'une date qui n'est pas rentr� dans le bon format
			String errorData ="MysqlDataTruncation: Data truncation: Incorrect date value:";

			//exception li�e � une insertion de champs identiques 
			String errorDuplication = "Duplicate entry ";

			if (e.toString().contains(errorDeleteForeignKey)){
				JOptionPane.showMessageDialog(null,"Supprimer la SESSION li�e AVANT!");

			} else if(e.toString().contains(errorData)) {	
				JOptionPane.showMessageDialog(null,"Le format de la DATE n'est pas respect�! (AAAA/MM/JJ)");

			} else if(e.toString().contains(errorDuplication)) {	
				JOptionPane.showMessageDialog(null,"Erreur! Le champs existe d�j�!");

			} else {
				JOptionPane.showMessageDialog(null ,"Ajout/Modification NON effectu�e!!");
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