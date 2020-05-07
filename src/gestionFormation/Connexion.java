package gestionFormation;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Connexion {

	// Frame
	private static JFrame frameLogin = new JFrame();

	//labels
	private JLabel lblMail = new JLabel("Adresse Mail");
	private JLabel lblPassword = new JLabel("Mot de passe");;

	//bouttons
	private JButton loginButton = new JButton("Connexion");

	//Jtextfields
	private JTextField txtUsername = new JTextField();
	private JTextField txtPassword = new JPasswordField();

	// Constructeur
	public Connexion() {

		//paramétrage graphique du jtextfield Password
		txtPassword.setBounds(143, 139, 123, 20);
		txtPassword.setColumns(10);
		frameLogin.getContentPane().add(txtPassword);

		//paramétrage graphique de la fenetre
		frameLogin.setBounds(100, 100, 450, 300);
		frameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameLogin.getContentPane().setLayout(null);

		//paramétrage graphique du label nom
		lblMail.setBounds(143, 37, 134, 17);
		frameLogin.getContentPane().add(lblMail);

		//paramétrage graphique du label mot de passe
		lblPassword.setBounds(143, 114, 77, 14);
		frameLogin.getContentPane().add(lblPassword);

		//paramétrage graphique du textfield nom d'utilisateur
		txtUsername.setBounds(143, 64, 123, 18);
		frameLogin.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);

		//paramétrage graphique du bouton connexion
		frameLogin.getContentPane().add(loginButton);
		loginButton.setBounds(143, 191, 123, 34);

		//ajout d'un titre du frame
		frameLogin.setTitle("Interface de Connexion");
		frameLogin.setVisible(true);

		//action listener du boutton connexion
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Requête qui va filtrer dans la BDD un mail, un password et un libelle 
				BDD.executeSelect("SELECT * FROM  `utilisateur`, `status` WHERE `mail`='"+getTxtMail()+"' AND `password`='"+getTxtPassword()+"' AND status.idUtilisateur = utilisateur.idUtilisateur AND `libelle`= 'admin'");
			
				// si la requête renvoie vraie alors la connexion se fait
				try {
					if(BDD.recupererResultatsRequete() == true) {

						//affichage de l'acceuil
						Connexion.getFrameLogin().setVisible(false);
						Acceuil.getFrameAcceuil().setVisible(true);

					} else {
						
						//sinon pop up qui indique une erreur
						Connexion.affichagePopUp("MAIL ou MDP incorrect!");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

	}

	// méthode qui permet d'afficher un pop up
	public static void affichagePopUp(String message){
		JOptionPane.showMessageDialog(frameLogin, message);
	}

	// les getters 
	public String getTxtPassword() {
		return txtPassword.getText();
	}

	public String getTxtMail() {
		return txtUsername.getText();
	}

	public static JFrame getFrameLogin() {
		return frameLogin;
	}

}
