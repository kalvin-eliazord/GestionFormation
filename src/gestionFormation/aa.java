package gestionFormation;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JComboBox;

public class aa extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JFrame frameFormaConcer = new JFrame();;

	private JTable tableFormation = new JTable();
	private JTable tableConcerner = new JTable();

	private JScrollPane scrollPanFormation = new JScrollPane();
	private JScrollPane scrollPanConcerner = new JScrollPane();

	private JPanel panelFormation = new JPanel();
	private JPanel panelConcerner = new JPanel();

	private JTextField txtFieldObjectif = new JTextField();
	private JTextField txtFieldCout = new JTextField();
	private JTextField txtFieldIdStatus = new JTextField();
	private JTextField txtFieldNumFormation = new JTextField();

	private JButton btnUpdateFormation = new JButton("Modifier");
	private JButton btnInsertFormation = new JButton("Insérer");
	private JButton btnSession = new JButton("Retour Sessions");
	private JButton btnDeleteFormation = new JButton("Supprimer");
	private JButton btnInsertConcerner = new JButton("Insérer");
	private JButton btnUpdateConcerner = new JButton("Modifier");
	private JButton btnDeleteConcerner = new JButton("Supprimer");

	private JLabel lblFormationNumForma = new JLabel("Formation.numFormation");
	private JLabel lblConcernerNumForma = new JLabel("Concerner.numFormation");
	private JLabel lblObjectifForma = new JLabel("Formation.objectif");
	private JLabel lblCoutForma = new JLabel("Formation.cout");
	private JLabel lblNumForma = new JLabel("Concerner.idStatus");
	private JLabel lblModifierIdstatus = new JLabel("Pour modifier idStatus");
	private JLabel lblNewLabel = new JLabel("Pour modifier numFormation");

	private static JComboBox<String> comboIdStatus = new JComboBox<String>();
	private static JComboBox<String> comboFormation_NumForma = new JComboBox<String>();
	private JComboBox<String> comboConcerner_NumForma = new JComboBox<String>();

	public aa() {

		frameFormaConcer.setTitle("Formation / Concerner");
		frameFormaConcer.setBounds(100, 100, 893, 702);
		frameFormaConcer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameFormaConcer.getContentPane().setLayout(null);
		frameFormaConcer.getContentPane().setLayout(null);

		panelFormation.setBounds(10, 79, 826, 245);
		panelFormation.setForeground(Color.BLUE);
		panelFormation.setBorder(new TitledBorder(null, "FORMATIONS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frameFormaConcer.getContentPane().add(panelFormation);
		panelFormation.setLayout(null);

		scrollPanFormation.setBounds(6, 16, 645, 218);
		panelFormation.add(scrollPanFormation);
		scrollPanFormation.setViewportView(tableFormation);

		btnInsertFormation.setBounds(661, 68, 155, 23);
		panelFormation.add(btnInsertFormation);

		btnUpdateFormation.setBounds(661, 102, 155, 23);
		panelFormation.add(btnUpdateFormation);

		btnDeleteFormation.setBounds(661, 136, 155, 23);
		panelFormation.add(btnDeleteFormation);

		lblFormationNumForma.setBounds(10, 23, 157, 14);
		frameFormaConcer.getContentPane().add(lblFormationNumForma);

		lblObjectifForma.setBounds(174, 23, 139, 14);
		frameFormaConcer.getContentPane().add(lblObjectifForma);

		lblConcernerNumForma.setBounds(10, 335, 157, 14);
		frameFormaConcer.getContentPane().add(lblConcernerNumForma);

		txtFieldObjectif.setBounds(174, 36, 126, 20);
		frameFormaConcer.getContentPane().add(txtFieldObjectif);
		txtFieldObjectif.setColumns(10);

		btnSession.setBounds(607, 347, 260, 23);
		frameFormaConcer.getContentPane().add(btnSession);

		lblCoutForma.setBounds(342, 23, 110, 14);
		frameFormaConcer.getContentPane().add(lblCoutForma);
		txtFieldCout.setColumns(10);
		txtFieldCout.setBounds(342, 36, 110, 20);
		frameFormaConcer.getContentPane().add(txtFieldCout);

		lblNumForma.setBounds(181, 335, 119, 14);
		frameFormaConcer.getContentPane().add(lblNumForma);

		comboIdStatus.setBounds(183, 348, 117, 22);
		frameFormaConcer.getContentPane().add(comboIdStatus);

		comboConcerner_NumForma.setBounds(10, 35, 130, 22);
		frameFormaConcer.getContentPane().add(comboConcerner_NumForma);

		panelConcerner.setLayout(null);
		panelConcerner.setForeground(Color.BLUE);
		panelConcerner.setBorder(new TitledBorder(null, "CONCERNER", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelConcerner.setBounds(10, 380, 826, 245);
		frameFormaConcer.getContentPane().add(panelConcerner);

		scrollPanConcerner.setBounds(6, 16, 645, 218);
		panelConcerner.add(scrollPanConcerner);
		scrollPanConcerner.setViewportView(tableConcerner);

		btnInsertConcerner.setBounds(661, 68, 155, 23);
		panelConcerner.add(btnInsertConcerner);

		btnUpdateConcerner.setBounds(661, 102, 155, 23);
		panelConcerner.add(btnUpdateConcerner);

		btnDeleteConcerner.setBounds(661, 136, 155, 23);
		panelConcerner.add(btnDeleteConcerner);

		comboFormation_NumForma.setBounds(10, 347, 158, 22);
		frameFormaConcer.getContentPane().add(comboFormation_NumForma);

		txtFieldNumFormation.setBounds(310, 350, 139, 20);
		frameFormaConcer.getContentPane().add(txtFieldNumFormation);
		txtFieldNumFormation.setColumns(10);

		txtFieldIdStatus.setColumns(10);
		txtFieldIdStatus.setBounds(480, 350, 117, 20);
		frameFormaConcer.getContentPane().add(txtFieldIdStatus);

		lblNewLabel.setBounds(313, 335, 157, 14);
		frameFormaConcer.getContentPane().add(lblNewLabel);

		lblModifierIdstatus.setBounds(480, 335, 136, 14);
		frameFormaConcer.getContentPane().add(lblModifierIdstatus);

		btnDeleteFormation.addActionListener(this);
		btnUpdateFormation.addActionListener(this);
		btnInsertFormation.addActionListener(this);
		btnSession.addActionListener(this);
		btnDeleteConcerner.addActionListener(this);
		btnUpdateConcerner.addActionListener(this);
		btnInsertConcerner.addActionListener(this);
	}

	public void actionPerformed(ActionEvent event) {

		if(event.getSource() == btnDeleteFormation) {

			//requete qui supprime les informations dont le numFormation correspond
			BDD.executeUpdate("DELETE FROM `formation` WHERE `numFormation`="+getStringFormationNumForma());
			// mis à jour du tableau formation
			BDD.executeSelect("SELECT * FROM `formation`");

			try {
				getJTableFormation().setModel(BDD.buildTable(BDD.getRs()));

			} catch (SQLException e) {
				e.printStackTrace();
			}

			//mis à jour du jcombobox numFormation
			getComboFormation_NumForma().removeAllItems();
			try {
				BDD.executeSelect("SELECT `numFormation` FROM `formation`");
				while (BDD.getRs().next()) {  
					getComboFormation_NumForma().addItem(Integer.toString(BDD.getRs().getInt("numFormation")));  
				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} else if(event.getSource() == btnUpdateFormation) {

			//requete qui met à jour les informations dont le numFormation correspond
			BDD.executeUpdate("UPDATE `formation` "
					+ "SET `objectif`='"+getTxtFieldObjectif()+"', `couts`="+getTxtFieldCouts()+" "
					+ "WHERE `numFormation`="+getStringFormationNumForma());

			// mis à jour du tableau formation
			BDD.executeSelect("SELECT * FROM `formation`");

			try {
				getJTableFormation().setModel(BDD.buildTable(BDD.getRs()));

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else if (event.getSource() == btnInsertFormation) {

			//requete qui va insérer dans la table formation
			BDD.executeUpdate("INSERT INTO `formation`( `objectif`, `couts`) VALUES ('"+getTxtFieldObjectif()+"',"+getTxtFieldCouts()+")");	
			// mis à jour du tableau formation
			BDD.executeSelect("SELECT * FROM `formation`");

			try {
				getJTableFormation().setModel(BDD.buildTable(BDD.getRs()));

			} catch (SQLException e) {
				e.printStackTrace();
			}

			//mis à jour du jcombobox numFormation
			getComboFormation_NumForma().removeAllItems();
			try {
				BDD.executeSelect("SELECT `numFormation` FROM `formation`");
				while (BDD.getRs().next()) {  
					getComboFormation_NumForma().addItem(Integer.toString(BDD.getRs().getInt("numFormation")));  
				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} else if(event.getSource() == btnSession) {

			Session laSession = new Session();

			//passage au frame session
			laSession.getFrameSession().setVisible(true);
			frameFormaConcer.setVisible(false);

			//mis à jour du tableau session
			BDD.executeSelect("SELECT * FROM `session`");

			try {
				laSession.getJTableSess().setModel(BDD.buildTable(BDD.getRs()));

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			//mis à jour des Jcombobox
			laSession.getComboNumSess().removeAllItems();
			laSession.getComboIdInter().removeAllItems();
			laSession.getComboIdLieu().removeAllItems();
			laSession.getComboNumForma().removeAllItems();

			try {

				BDD.executeSelect("SELECT `numSession` FROM `session`");
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

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} else if (event.getSource() == btnDeleteConcerner) {

			try {
				//requete qui va supprimer le champ dont l'idStatus et le numFormation correspond
				BDD.executeUpdate("DELETE FROM `concerner`"
						+ " WHERE `idStatus`='"+getStringComboIdStatus()+"'"
						+ " AND `numFormation`='"+getStringComboNumFormaC()+"'");	

				//rafraichissement du tableau concerner
				BDD.executeSelect("SELECT * FROM concerner ");

				getJTableConcerner().setModel(BDD.buildTable(BDD.getRs()));

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} else if (event.getSource() == btnUpdateConcerner) {	

			try {
				//requete qui mettre à jour les informations dont le numFormation et l'idStatus correspond
				BDD.executeUpdate("UPDATE `concerner` "
						+ "SET `idStatus`='"+getTxtFieldIdStatus()+"', `numFormation`="+getTxtFieldNumFormation()+" "
						+ "WHERE `idStatus`="+getStringComboIdStatus()+" AND `numFormation`="+getStringComboNumFormaC());

				// mis à jour du tableau concerner
				BDD.executeSelect("SELECT * FROM concerner");
				getJTableConcerner().setModel(BDD.buildTable(BDD.getRs()));

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} else if (event.getSource() == btnInsertConcerner) {	

			try {
				//requete qui va insérer dans la table concerner
				BDD.executeUpdate("INSERT INTO `concerner`"
						+ "( `idStatus`, `numFormation`) "
						+ "VALUES ('"+getStringComboIdStatus()+"',"+getStringComboNumFormaC()+")");	

				// mis à jour du tableau formation
				BDD.executeSelect("SELECT concerner.idStatus, STATUS .libelle, concerner.numFormation, formation.objectif "
						+ "FROM `concerner`, formation, STATUS "
						+ "WHERE concerner.numFormation = formation.numFormation AND concerner.idStatus = STATUS .idStatus");

				getJTableConcerner().setModel(BDD.buildTable(BDD.getRs()));

			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	private String getStringFormationNumForma() {
		return comboFormation_NumForma.getSelectedItem().toString();
	}

	private String getTxtFieldCouts() {
		return txtFieldCout.getText();
	}

	private String getTxtFieldObjectif() {
		return txtFieldObjectif.getText();
	}
	
	public String getTxtFieldNumFormation() {
		return txtFieldNumFormation.getText();
	}
	
	public String getTxtFieldIdStatus() {
		return txtFieldIdStatus.getText();
	}
	
	public String getStringComboNumFormaC() {
		return comboConcerner_NumForma.getSelectedItem().toString();
	}

	public String getStringComboIdStatus() {
		return comboIdStatus.getSelectedItem().toString();
	}

	public JFrame getFrameFormaConcer() {
		return frameFormaConcer;
	}

	public JTable getJTableFormation() {
		return tableFormation;
	}

	public JTable getJTableConcerner() {
		return tableConcerner;
	}

	public JComboBox<String> getComboIdStatus() {
		return comboIdStatus;
	}

	public JComboBox<String> getComboFormation_NumForma() {
		return comboFormation_NumForma;
	}

	public JComboBox<String> getComboConcerner_NumForma() {
		return comboConcerner_NumForma;
	}
}
