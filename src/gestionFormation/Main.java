package gestionFormation;

public class Main {

	public static void main(String[] args) {

		Connexion laConnexion = new Connexion();

		Accueil lAcceuil = new Accueil();

		Formation laFormation = new Formation();
		
		Concerner leConcerner = new Concerner();

		Lieu leLieu = new Lieu();

		Session laSession = new Session();

		Intervenant lIntervenant = new Intervenant();

		BDD laBDD = new BDD();
	}
	
}
