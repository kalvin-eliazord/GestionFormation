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
	
	/**
	 * Cette classe permet de se connecter � l'application
	 * et d'acc�der � l'IHM Session.
	 */
	
	private JFrame frameLogin = new JFrame();

	private JLabel lblMail = new JLabel("Adresse Mail");
	private JLabel lblPassword = new JLabel("Mot de passe");;

	private JButton loginButton = new JButton("Connexion");

	private JTextField txtUsername = new JTextField();
	private JTextField txtPassword = new JPasswordField();

	private Session laSession = new Session();
	
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

		frameLogin.setTitle("Interface d'authentification");
		frameLogin.setVisible(true);

		loginButton.addActionListener(this);
	}

	public void actionPerformed(ActionEvent event) {

		if( event.getSource() == loginButton) {

			// Requ�te qui va filtrer dans la BDD un mail, un password et un libelle 
			BDD.executeSelect("SELECT * FROM  `utilisateur` "
					+ "WHERE `mail`='"+getTxtMail()+"' "
					+ "AND `password`='"+getTxtPassword()+"' "
					+ "AND idStatus='5'");

			// si la requ�te renvoie vraie alors la connexion se fait
			try {
				if(BDD.recupererResultatsRequete() == true) {

					//rend visible l'ihm session
					getFrameConnexion().dispose();
					laSession.getFrameSession().setVisible(true);

					//mis � jour du tableau session
					BDD.executeSelect("SELECT * FROM `session`");
					laSession.getJTableSession().setModel(BDD.buildTable(BDD.getRs()));

					//mis � jour des Jcombobox
					laSession.getComboNumSess().removeAllItems();
					laSession.getComboIdInter().removeAllItems();
					laSession.getComboIdLieu().removeAllItems();
					laSession.getComboNumForma().removeAllItems();

					BDD.executeSelect("SELECT `numSession` FROM `session` ORDER BY `numSession`");
					while (BDD.getRs().next()) {  
						laSession.getComboNumSess().addItem(Integer.toString(BDD.getRs().getInt("numSession")));  
					}

					BDD.executeSelect("SELECT `idIntervenant` FROM `intervenant`");
					while (BDD.getRs().next()) {  
						laSession.getComboIdInter().addItem(Integer.toString(BDD.getRs().getInt("idIntervenant")));  
					}

					BDD.executeSelect("SELECT `idLieu` FROM `lieu`");
					while (BDD.getRs().next()) {  
						laSession.getComboIdLieu().addItem(Integer.toString(BDD.getRs().getInt("idLieu")));  
					}

					BDD.executeSelect("SELECT `numFormation` FROM `formation`");
					while (BDD.getRs().next()) {  
						laSession.getComboNumForma().addItem(Integer.toString(BDD.getRs().getInt("numFormation")));  
					}
				}

			}catch (SQLException e1) {
				JOptionPane.showMessageDialog(frameLogin ,"MAIL ou MDP incorrect!");
				e1.printStackTrace();
			}
		}
	}

	public String getTxtPassword() {
		return txtPassword.getText();
	}

	public String getTxtMail() {
		return txtUsername.getText();
	}

	public JFrame getFrameConnexion() {
		return frameLogin;
	}

}
