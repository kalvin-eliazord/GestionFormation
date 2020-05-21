package gestionFormation;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class Accueil implements ActionListener {

	private static JFrame  frameAccueil = new JFrame();

	private JButton btnLogout = new JButton("Déconnexion");
	private JButton btnAjoutModif = new JButton("Ajout/Modification");

	private JPanel panelGestionForma = new JPanel();

	public Accueil() {

		frameAccueil.setTitle("Accueil");
		frameAccueil.setBounds(100, 100, 436, 287);
		frameAccueil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameAccueil.getContentPane().setLayout(null);

		panelGestionForma.setBorder(new TitledBorder(null, "GESTIONNAIRE DE FORMATION", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelGestionForma.setBounds(84, 94, 240, 56);
		frameAccueil.getContentPane().add(panelGestionForma);
		panelGestionForma.setLayout(null);

		btnAjoutModif.setBounds(6, 16, 228, 30);
		panelGestionForma.add(btnAjoutModif);

		btnLogout.setBounds(296, 11, 114, 23);
		frameAccueil.getContentPane().add(btnLogout);

		btnAjoutModif.addActionListener(this);
		btnLogout.addActionListener(this); 
	}

	public void actionPerformed(ActionEvent event) {

		if(event.getSource() == btnLogout) {
			//passage à la fenetre login
			Connexion.getFrameLogin().setVisible(true);
			frameAccueil.setVisible(false);

		} else if(event.getSource() == btnAjoutModif) {

			//passage à la frame intervenants
			frameAccueil.setVisible(false);
			Intervenant.getInterFrame().setVisible(true);

			//requete qui va itialiser le tableau des intervenants
			BDD.executeSelect("SELECT * FROM `intervenant`");

			try {
				Intervenant.getTableInter().setModel(BDD.buildTable(BDD.getRs()));

			} catch (SQLException e2) {
				e2.printStackTrace();
			}

			//mis à jour du jcombobox
			Intervenant.getComboIdInter().removeAllItems();
			try {
				BDD.executeSelect("SELECT `idIntervenant` FROM `intervenant`");
				while (BDD.getRs().next()) {  
					Intervenant.getComboIdInter().addItem(Integer.toString(BDD.getRs().getInt("idIntervenant")));  
				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}
	}

	public static JFrame getFrameAcceuil() {
		return frameAccueil;
	}
}
