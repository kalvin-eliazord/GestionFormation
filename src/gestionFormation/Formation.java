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

public class Formation extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JFrame frameFormaConcer = new JFrame();;

	private JTable tableFormation = new JTable();
	private JTable tableConcerner = new JTable();

	private JScrollPane scrollPanFormation = new JScrollPane();
	private JScrollPane scrollPanConcerner = new JScrollPane();

	private JPanel panelFormation = new JPanel();
	private JPanel panelConcerner = new JPanel();

	private JTextField txtfieldObjectif = new JTextField();
	private JTextField txtfieldCout = new JTextField();
	private JTextField txtfieldStatus = new JTextField();
	private JTextField txtfieldNumFormation = new JTextField();

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

	private Session laSession;

	public Formation() {

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

		lblFormationNumForma.setBounds(10, 23, 130, 14);
		frameFormaConcer.getContentPane().add(lblFormationNumForma);

		lblObjectifForma.setBounds(137, 23, 99, 14);
		frameFormaConcer.getContentPane().add(lblObjectifForma);

		lblConcernerNumForma.setBounds(10, 335, 136, 14);
		frameFormaConcer.getContentPane().add(lblConcernerNumForma);

		txtfieldObjectif.setBounds(137, 36, 86, 20);
		frameFormaConcer.getContentPane().add(txtfieldObjectif);
		txtfieldObjectif.setColumns(10);

		btnSession.setBounds(576, 346, 260, 23);
		frameFormaConcer.getContentPane().add(btnSession);

		lblCoutForma.setBounds(233, 23, 86, 14);
		frameFormaConcer.getContentPane().add(lblCoutForma);
		txtfieldCout.setColumns(10);
		txtfieldCout.setBounds(233, 36, 86, 20);
		frameFormaConcer.getContentPane().add(txtfieldCout);

		lblNumForma.setBounds(148, 335, 96, 14);
		frameFormaConcer.getContentPane().add(lblNumForma);

		comboIdStatus.setBounds(149, 347, 117, 22);
		frameFormaConcer.getContentPane().add(comboIdStatus);

		comboConcerner_NumForma.setBounds(10, 35, 117, 22);
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

		comboFormation_NumForma.setBounds(10, 346, 128, 22);
		frameFormaConcer.getContentPane().add(comboFormation_NumForma);

		txtfieldNumFormation.setBounds(289, 349, 139, 20);
		frameFormaConcer.getContentPane().add(txtfieldNumFormation);
		txtfieldNumFormation.setColumns(10);

		txtfieldStatus.setColumns(10);
		txtfieldStatus.setBounds(444, 349, 113, 20);
		frameFormaConcer.getContentPane().add(txtfieldStatus);

		lblNewLabel.setBounds(289, 335, 139, 14);
		frameFormaConcer.getContentPane().add(lblNewLabel);

		lblModifierIdstatus.setBounds(444, 335, 117, 14);
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

			//passage au frame session
			laSession.getFrameSession().setVisible(true);
			frameFormaConcer.setVisible(false);

		} else if (event.getSource() == btnDeleteConcerner) {

			try {
				//requete qui va supprimer le champ dont l'idStatus et le numFormation correspond
				BDD.executeUpdate("DELETE FROM `concerner`"
						+ " WHERE `idStatus`='"+getStringComboIdStatus()+"'"
						+ " AND `numFormation`='"+getStringConcernerNumForma()+"'");	

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
						+ "SET `idStatus`='"+getStringComboIdStatus()+"', `numFormation`="+getStringConcernerNumForma()+" "
						+ "WHERE `numFormation`="+getStringConcernerNumForma()+" AND `numFormation`="+getStringConcernerNumForma());

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
						+ "VALUES ('"+getStringComboIdStatus()+"',"+getStringConcernerNumForma()+")");	

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
		return txtfieldCout.getText();
	}

	private String getTxtFieldObjectif() {
		return txtfieldObjectif.getText();
	}

	public String getStringConcernerNumForma() {
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
