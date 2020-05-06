package formationAdmin;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Sessions extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//frame
	private static JFrame sessionsFrame = new JFrame();;

	//tableaux et scroll
	private static JTable tableSessions = new JTable();
	private JScrollPane scrollPanSess = new JScrollPane();
	private JPanel panel_sessions = new JPanel();
	private JScrollBar scrollBarSess = new JScrollBar();

	//textfields
	private JTextField txtNumSession = new JTextField();
	private JTextField txtDateDeLimite = new JTextField();
	private JTextField txtDateDeFin  = new JTextField();
	private JTextField txtIdIntervenant = new JTextField();
	private JTextField txtIdLieu = new JTextField();
	private JTextField txtDateSess = new JTextField();
	private JTextField txtNumForma = new JTextField();

	//bouttons
	private JButton btnUpdateSess = new JButton("Modifier");
	private JButton btnInsertSess = new JButton("Insérer");
	private JButton btnAcceuil = new JButton("Retourner à l'acceuil");
	private JButton btnDeleteSess = new JButton("Supprimer");
	private JButton btnPrecedent = new JButton("Précédent");
	private JButton btnFormations = new JButton("Formations");

	//labels
	private JLabel lblNumSession = new JLabel("numSession");
	private JLabel lblDateFin = new JLabel("dateDeFin");
	private JLabel lblTitre = new JLabel("idIntervenant");
	private JLabel lblNom = new JLabel("dateLimitInsc");
	private JLabel lblIdLieuSess = new JLabel("idLieu");
	private JLabel lblDateSess = new JLabel("dateSession");
	private JLabel lblNumForma = new JLabel("numFormation");

	//constructeur
	public Sessions() {

		//paramétrage graphique du frame
		sessionsFrame.setTitle("Sessions");
		sessionsFrame.setBounds(100, 100, 759, 425);
		sessionsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sessionsFrame.getContentPane().setLayout(null);
		sessionsFrame.getContentPane().setLayout(null);

		//paramétrage graphique du panel
		panel_sessions.setBounds(20, 78, 713, 245);
		panel_sessions.setForeground(Color.BLUE);
		panel_sessions.setBorder(new TitledBorder(null, "SESSION DISPONIBLES", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sessionsFrame.getContentPane().add(panel_sessions);
		panel_sessions.setLayout(null);

		//paramétrage graphique du scrollpane
		scrollPanSess.setBounds(6, 16, 574, 218);
		panel_sessions.add(scrollPanSess);
		scrollPanSess.setViewportView(tableSessions);
		scrollPanSess.setRowHeaderView(scrollBarSess);
		
		//paramétrage graphique du boutton insérer
		btnInsertSess.setBounds(590, 68, 113, 23);
		panel_sessions.add(btnInsertSess);
		
		//paramétrage graphique du boutton modifier
		btnUpdateSess.setBounds(590, 102, 113, 23);
		panel_sessions.add(btnUpdateSess);
		
		//paramétrage graphique du boutton supprimer
		btnDeleteSess.setBounds(590, 137, 113, 23);
		panel_sessions.add(btnDeleteSess);

		//paramétrage graphique du label numSession
		lblNumSession.setBounds(20, 23, 86, 14);
		sessionsFrame.getContentPane().add(lblNumSession);
		
		//paramétrage graphique du label nom
		lblNom.setBounds(116, 23, 86, 14);
		sessionsFrame.getContentPane().add(lblNom);
		
		//paramétrage graphique du label dateFin
		lblDateFin.setBounds(311, 23, 65, 14);
		sessionsFrame.getContentPane().add(lblDateFin);
		
		//paramétrage graphique du label titre
		lblTitre.setBounds(407, 23, 89, 14);
		sessionsFrame.getContentPane().add(lblTitre);
		
		//paramétrage graphique du textfield numSession
		txtNumSession.setBounds(20, 36, 86, 20);
		sessionsFrame.getContentPane().add(txtNumSession);
		txtNumSession.setColumns(10);
		
		//paramétrage graphique du textfield dateDeLimite
		txtDateDeLimite.setBounds(116, 36, 86, 20);
		txtDateDeLimite.setText("AAAA-MM-JJ");
		txtDateDeLimite.setToolTipText("");
		sessionsFrame.getContentPane().add(txtDateDeLimite);
		txtDateDeLimite.setColumns(10);
		
		//paramétrage graphique du textfield dateDeFin
		txtDateDeFin.setBounds(311, 36, 86, 20);
		txtDateDeFin.setText("AAAA-MM-JJ");
		sessionsFrame.getContentPane().add(txtDateDeFin);
		txtDateDeFin.setColumns(10);
		
		//paramétrage graphique du textfield idIntervenant
		txtIdIntervenant.setBounds(407, 35, 86, 20);
		sessionsFrame.getContentPane().add(txtIdIntervenant);
		txtIdIntervenant.setColumns(10);
		
		//paramétrage graphique du boutton acceuil
		btnAcceuil.setBounds(424, 346, 260, 23);
		sessionsFrame.getContentPane().add(btnAcceuil);
		
		//paramétrage graphique du label idLieu
		lblIdLieuSess.setBounds(502, 23, 65, 14);
		sessionsFrame.getContentPane().add(lblIdLieuSess);
		
		//paramétrage graphique du label dateSession
		lblDateSess.setBounds(212, 23, 89, 14);
		sessionsFrame.getContentPane().add(lblDateSess);
		
		//paramétrage graphique du textfield DateSession
		txtDateSess.setText("AAAA-MM-JJ");
		txtDateSess.setColumns(10);
		txtDateSess.setBounds(212, 36, 86, 20);
		sessionsFrame.getContentPane().add(txtDateSess);
		
		//paramétrage graphique du txtfield idLieu
		txtIdLieu.setColumns(10);
		txtIdLieu.setBounds(502, 36, 86, 20);
		sessionsFrame.getContentPane().add(txtIdLieu);
		
		//paramétrage graphique du label numFormation
		lblNumForma.setBounds(598, 23, 105, 14);
		sessionsFrame.getContentPane().add(lblNumForma);
		
		//paramétrage graphique du txtfield numFormation
		txtNumForma.setColumns(10);
		txtNumForma.setBounds(598, 36, 86, 20);
		sessionsFrame.getContentPane().add(txtNumForma);

		//paramétrage graphique du boutton précédent
		btnPrecedent.setBounds(20, 346, 164, 23);
		sessionsFrame.getContentPane().add(btnPrecedent);
		btnFormations.setBounds(190, 346, 160, 23);

		//ajout des collonnes au tableau sessions
		tableSessions.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"numSession", "dateLimiteInsc", "dateSession", "dateDeFin", "idIntervenant", "idLieu","numFormation", "nbPlaces"
				}
				));
		
		//ajout d'un actionListener sur le boutton retourner à l'acceuil
		btnAcceuil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//passage au frame acceuil
				Acceuil.getFrameAcceuil().setVisible(true);
				sessionsFrame.setVisible(false);		
			}
		});
		
		//ajout d'un actionListener sur le boutton Delete
		btnDeleteSess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//requete qui supprime les champs dont la numSession correspond
				BDD.executeUpdate("DELETE FROM `session` WHERE `numSession`="+getNumSession());
				BDD.executeSelect("SELECT * FROM `session`");
				tableSessions.setModel(DbUtils.resultSetToTableModel(BDD.getRs()));	
			}
		});

		//ajout d'un actionListener sur le boutton Update
		btnUpdateSess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//requete qui met à jour les informations
				BDD.executeUpdate("UPDATE `session` SET `numSession`='"+getNumSession()+"',`dateLimiteinsc`='"+getTxtDateLimit()+"', `dateSession`='"+getTxtDateSess()+"', `dateDeFin`='"+getDateDeFin()+"', `idIntervenant`='"+getIdIntervenant()+"',`idLieu`='"+getTxtIdLieu()+"', `numFormation`='"+Formation.getTxtNumFormation()+"' WHERE `numSession`='"+getNumSession()+"'");
				// mis à jour du tableau 
				BDD.executeSelect("SELECT * FROM `session`");
				tableSessions.setModel(DbUtils.resultSetToTableModel(BDD.getRs()));	
			}
		});

		//ajout d'un actionListener sur le boutton insert
		btnInsertSess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//insertion des champs dans la table sessions
				BDD.executeUpdate("INSERT INTO `session`( `dateLimiteInsc`, `dateSession`, `dateDeFin`, `idIntervenant`, `idLieu`, `numFormation`) VALUES ('"+getTxtDateLimit()+"','"+getTxtDateSess()+"','"+getDateDeFin()+"','"+getIdIntervenant()+"','"+getTxtIdLieu()+"','"+Formation.getTxtNumFormation()+"');");
				// mis à jour des tableaux
				BDD.executeSelect("SELECT * FROM `session`");
				tableSessions.setModel(DbUtils.resultSetToTableModel(BDD.getRs()));
			}
		});

		//action du boutton précédent
		btnPrecedent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//passage au frame lieu
				Lieu.getFrameLieu().setVisible(true);
				sessionsFrame.setVisible(false);
			}
		});

	}

	//getters
	public String getNumSession() {

		return txtNumSession.getText();
	}

	public String getTxtDateLimit() {

		return txtDateDeLimite.getText();
	}

	public String getTxtDateSess() {

		return txtDateSess.getText();
	}

	public String getDateDeFin() {

		return txtDateDeFin.getText();
	}

	public String getTxtIdLieu() {

		return txtIdLieu.getText();
	}

	public String getIdIntervenant() {

		return txtIdIntervenant.getText();
	}

	public static JFrame getSessionsFrame() {

		return sessionsFrame;
	}

	public static JTable getJTableSess() {

		return tableSessions;
	}

}
