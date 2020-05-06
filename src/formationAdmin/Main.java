package formationAdmin;

import java.sql.SQLException;

public class Main {
	
	public static void main(String[] args) throws SQLException {
		
		Login LaConnexionVue = new Login();
		
		Acceuil lIndexVue = new Acceuil();
		
		Formation laFormationVue = new Formation();
		
		Lieu leLieu = new Lieu();
		
		Sessions laSessionLieuVue = new Sessions();
		
		Intervenant lIntervenantsVue = new Intervenant();
		
		BDD laBDD = new BDD();
		
		MainBDD LeControleurFormation = new MainBDD(laBDD);

	}

}
