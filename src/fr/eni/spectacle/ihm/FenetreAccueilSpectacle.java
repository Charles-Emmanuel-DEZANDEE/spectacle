package fr.eni.spectacle.ihm;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.eni.spectacle.bo.Spectacle;

public class FenetreAccueilSpectacle extends JFrame{
	
	private JMenuBar toolbar;
	private JLabel labelListeSpectacle;
	private JLabel labelRechercher;
	private JTextField fieldRechercherArtiste;
	private JButton buttonRechercherArtiste;
	private JPanel panelSpectacle;
	private JLabel labelReservation;

	public FenetreAccueilSpectacle(){
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(800, 800);
		setResizable(false);
		setTitle("Réservation spectacle");
		this.toolbar = new JMenuBar();
		JButton accueil = new JButton("Accueil");
		this.toolbar.add(accueil);
		JButton reservations = new JButton("Réservations");
		this.toolbar.add(reservations);
		JButton clients = new JButton("Clients");
		this.toolbar.add(clients);
		this.setJMenuBar(this.toolbar);
		initListeSpectacle();
		setVisible(true);
	}
	
	private void initListeSpectacle(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		
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

		setContentPane(panel);
	}
	
	private void initReservation(Spectacle spectacle){
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		
		//Ligne 1
		gbc.gridwidth = 0;
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(this.getLabelReservation(), gbc);
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(new JLabel(spectacle.getArtiste() + ", " + spectacle.getTitre() + " " + spectacle.getLieu() + " / " + spectacle.getDate()), gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		panel.add(new JLabel(String.valueOf("Places disponibles :" + spectacle.getPlacesDisponibles())), gbc);
		
		setContentPane(panel);	
	}
	
	// initListeSpectacle
	
	public JLabel getLabelListeSpectacle(){
		if (this.labelListeSpectacle == null){
			this.labelListeSpectacle = new JLabel("Liste des spectacles");
			this.labelListeSpectacle.setFont(new Font("Serif", Font.PLAIN, 35));
		}
		return this.labelListeSpectacle;
	}
	
	public JLabel getLabelRechercher(){
		if (this.labelRechercher == null){
			this.labelRechercher = new JLabel("Recherche par Artiste : ");
			this.labelRechercher.setFont(new Font("Serif", Font.PLAIN, 20));
		}
		return this.labelRechercher;
	}
	
	public JTextField getFieldRechercherArtiste(){
		if (this.fieldRechercherArtiste == null){
			this.fieldRechercherArtiste = new JTextField(20);
		}
		return this.fieldRechercherArtiste;
	}
	
	public JButton getButtonRechercherArtiste(){
		if (this.buttonRechercherArtiste == null){
			this.buttonRechercherArtiste = new JButton("OK");
		}
		return this.buttonRechercherArtiste;
	}
	
	public Map<Spectacle, JPanel> getLabelSpectacle(){
		Map<Spectacle, JPanel> listeJLabelSpectacle = new HashMap<>();

		List<Spectacle> listeSpectacle = new ArrayList<Spectacle>();
		// todo recuprer la liste 
		for(Spectacle spectacle : listeSpectacle){
			listeJLabelSpectacle.put(spectacle, getPanelSpectacle(spectacle));
		}
		
		return listeJLabelSpectacle;
	}
	
	public JPanel getPanelSpectacle(Spectacle spectacle) {
		JButton btn = null;
		// pour l'instant 
		boolean dispoOuReserv = true;
		if (panelSpectacle == null) {
			panelSpectacle = new JPanel();
			panelSpectacle.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(5, 5, 5, 5);
			gbc.gridx = 0;
			gbc.gridy = 1;
			panelSpectacle.add(new JLabel(spectacle.getArtiste() + ", " + spectacle.getTitre() + " " + spectacle.getLieu() + " / " + spectacle.getDate()), gbc);
			gbc.gridx = 1;
			gbc.gridy = 1;
			
			//if(dispoOuReserv(spectacle) == true){
			if(dispoOuReserv == true){
				btn = new JButton("Réservations");
				btn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						initReservation(spectacle);
					}
				});
				panelSpectacle.add(btn, gbc);
			}else{
				btn = new JButton("Indisponible");
				btn.setEnabled(false);
				panelSpectacle.add(btn, gbc);
			}
		}
		return panelSpectacle;
	}
	
	// initReservation
	
	public JLabel getLabelReservation(){
		if (this.labelReservation == null){
			this.labelReservation = new JLabel("Réservation");
			this.labelReservation.setFont(new Font("Serif", Font.PLAIN, 35));
		}
		return this.labelReservation;
	}
	
}
