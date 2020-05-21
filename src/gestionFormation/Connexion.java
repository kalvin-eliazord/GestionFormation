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

public class Connexion implements ActionListener {

	private static JFrame frameLogin = new JFrame();

	private JLabel lblMail = new JLabel("Adresse Mail");
	private JLabel lblPassword = new JLabel("Mot de passe");;

	private JButton loginButton = new JButton("Connexion");

	private JTextField txtUsername = new JTextField();
	private JTextField txtPassword = new JPasswordField();

	public Connexion() {

		txtPassword.setBounds(143, 139, 123, 20);
		txtPassword.setColumns(10);
		frameLogin.getContentPane().add(txtPassword);

		frameLogin.setBounds(100, 100, 450, 300);
		frameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameLogin.getContentPane().setLayout(null);

		lblMail.setBounds(143, 37, 134, 17);
		frameLogin.getContentPane().add(lblMail);

		lblPassword.setBounds(143, 114, 77, 14);
		frameLogin.getContentPane().add(lblPassword);

		txtUsername.setBounds(143, 64, 123, 18);
		frameLogin.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);

		frameLogin.getContentPane().add(loginButton);
		loginButton.setBounds(143, 191, 123, 34);

		frameLogin.setTitle("Interface de Connexion");
		frameLogin.setVisible(true);

		loginButton.addActionListener(this);
	}

	public void actionPerformed(ActionEvent event) {

		if( event.getSource() == loginButton) {

			// Requête qui va filtrer dans la BDD un mail, un password et un libelle 
			BDD.executeSelect("SELECT * FROM  `utilisateur`, `status` "
					+ "WHERE `mail`='"+getTxtMail()+"' "
					+ "AND `password`='"+getTxtPassword()+"' "
					+ "AND utilisateur.idStatus = status.idStatus "
					+ "AND `libelle`= 'admin'");

			// si la requête renvoie vraie alors la connexion se fait
			try {
				if(BDD.recupererResultatsRequete() == true) {

					//affichage de l'acceuil
					Connexion.getFrameLogin().setVisible(false);
					Accueil.getFrameAcceuil().setVisible(true);

				} else {

					Connexion.affichagePopUp("MAIL ou MDP incorrect!");
				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public static void affichagePopUp(String message){
		JOptionPane.showMessageDialog(frameLogin, message);
	}

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
