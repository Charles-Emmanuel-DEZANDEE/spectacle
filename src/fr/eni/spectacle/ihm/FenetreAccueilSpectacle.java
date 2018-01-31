package fr.eni.spectacle.ihm;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FenetreAccueilSpectacle extends JFrame{
	
	private JMenuBar toolbar;
	private JLabel labelListeSpectacle;
	private JLabel labelRechercher;
	private JTextField fieldRechercherArtiste;
	private JButton buttonRechercherArtiste;

	public FenetreAccueilSpectacle(){
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(800, 800);
		setResizable(false);
		setTitle("R�servation spectacle");
		this.toolbar = new JMenuBar();
		JButton accueil = new JButton("Accueil");
		this.toolbar.add(accueil);
		JButton reservations = new JButton("R�servations");
		this.toolbar.add(reservations);
		JButton clients = new JButton("Clients");
		this.toolbar.add(clients);
		this.setJMenuBar(this.toolbar);
		initIhm();
		setVisible(true);
	}
	
	private void initIhm(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);

		//Ligne 1
		gbc.gridwidth = 0;
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(this.getLabelListeSpectacle(), gbc);
		//Ligne 2
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(this.getLabelRechercher(), gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		panel.add(this.getFieldRechercherArtiste(), gbc);
		gbc.gridx = 2;
		gbc.gridy = 1;
		panel.add(this.getButtonRechercherArtiste(), gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		panel.add(panel2, gbc);
		panel.add(this.getButtonRechercherArtiste(), gbc);
		
		setContentPane(panel);
	}
	
	public JLabel getLabelListeSpectacle()
	{
		if (this.labelListeSpectacle == null) {
			this.labelListeSpectacle = new JLabel("Liste des spectacles");
			this.labelListeSpectacle.setFont(new Font("Serif", Font.PLAIN, 35));
		}
		return this.labelListeSpectacle;
	}
	
	public JLabel getLabelRechercher(){
		if (this.labelRechercher == null) {
			this.labelRechercher = new JLabel("Recherche par Artiste : ");
			this.labelRechercher.setFont(new Font("Serif", Font.PLAIN, 20));
		}
		return this.labelRechercher;
	}
	
	public JTextField getFieldRechercherArtiste()
	{
		if (this.fieldRechercherArtiste == null) {
			this.fieldRechercherArtiste = new JTextField(20);
		}
		return this.fieldRechercherArtiste;
	}
	
	public JButton getButtonRechercherArtiste()
	{
		if (this.buttonRechercherArtiste == null) {
			this.buttonRechercherArtiste = new JButton("OK");
		}
		return this.buttonRechercherArtiste;
	}
	
//	public JLabel getLabelSpectacle(){
//
//		List<String> listeSpectacle = new ArrayList<String>();
//		listeSpectacle.add("spectacle numero 1");
//		listeSpectacle.add("spectacle numero 2");
//		listeSpectacle.add("Spectacle numero 3");
//	}
	
}
