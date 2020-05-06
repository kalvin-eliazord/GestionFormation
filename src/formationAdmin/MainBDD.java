package formationAdmin;

import java.sql.SQLException;

//connexion � la BDD
public class MainBDD {
	
	private BDD laBDD;

	public MainBDD(BDD laBDD) throws SQLException {

		this.laBDD = laBDD;

		// Charger le driver JBDC
		this.laBDD.chargerDriver("com.mysql.jdbc.Driver");

		// Connexion � la BDD
		this.laBDD.connexionBdd("mysql://localhost/", "formations", "root", "");

		// Creation d'ub statement
		this.laBDD.creerStatement();

	}

}
