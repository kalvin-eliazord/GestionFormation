package formationAdmin;

import javax.swing.JFrame;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class Acceuil {

	//frame
	private static JFrame  frameAcceuil = new JFrame();

	//bouttons
	private JButton btnLogout = new JButton("Déconnexion");
	private JButton btnAjout = new JButton("Ajout");

	//panel
	private final JPanel panelGestionForma = new JPanel();
	private final JButton btnModifiersupprimer = new JButton("Modifier/Supprimer");

	//constructeur
	public Acceuil() {

		//paramétrage de la fenetre d'Acceuil
		frameAcceuil.setTitle("Accueil");
		frameAcceuil.setBounds(100, 100, 436, 287);
		frameAcceuil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameAcceuil.getContentPane().setLayout(null);
		panelGestionForma.setBorder(new TitledBorder(null, "GESTION DES FORMATIONS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelGestionForma.setBounds(80, 76, 240, 104);

		frameAcceuil.getContentPane().add(panelGestionForma);
		panelGestionForma.setLayout(null);

		//paramétrage graphique du boutton ajout/modification
		btnAjout.setBounds(6, 16, 228, 30);
		panelGestionForma.add(btnAjout);

		//paramétrage graphique du boutton modifier/supprimer
		btnModifiersupprimer.setBounds(6, 57, 228, 30);
		panelGestionForma.add(btnModifiersupprimer);

		//paramétrage graphique du boutton Déconnexion
		btnLogout.setBounds(296, 11, 114, 23);
		frameAcceuil.getContentPane().add(btnLogout);


		//action listener du boutton modifier supprimer
		btnModifiersupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//passage à la frame intervenants
				frameAcceuil.setVisible(false);
				Intervenant.getInterFrame().setVisible(true);

				//requete qui va itialiser le tableau des intervenants
				BDD.executeSelect("SELECT * FROM `intervenant`");
				Intervenant.getTableInter().setModel(DbUtils.resultSetToTableModel(BDD.getRs()));
			}
		});

		//action du boutton ajout
		btnAjout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//passage à la frame intervenants
				frameAcceuil.setVisible(false);
				Intervenant.getInterFrame().setVisible(true);

				//requete qui va itialiser le tableau des intervenants
				BDD.executeSelect("SELECT * FROM `intervenant`");
				Intervenant.getTableInter().setModel(DbUtils.resultSetToTableModel(BDD.getRs()));
			}
		});

		//paramétrage de l'action du boutton Déconnexion
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//passage à la fenetre login
				Login.getFrameLogin().setVisible(true);
				frameAcceuil.setVisible(false);
			}
		});

	}

	//getter
	public static JFrame getFrameAcceuil() {

		return frameAcceuil;
	}
}
