package fr.eni.spectacle.ihm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import fr.eni.spectacle.bo.*;
import fr.eni.spectacle.bll.*;
import fr.eni.spectacle.dal.DALException;

public class FenetreAccueilSpectacle extends JFrame {

	private JMenuBar toolbar;
	private JLabel labelListeSpectacle;
	private JLabel labelRechercher;
	private JTextField fieldRechercherArtiste;
	private JButton buttonRechercherArtiste;
	private JPanel panelSpectacle;
	private JPanel panelClient;
	private JPanel panelReservation;
	private JLabel labelReservation;
	private JTextField fieldNom;
	private JTextField fieldPrenom;
	private JTextField fieldEmail;
	private JTextField fieldAdresse;
	private JTextField fieldCP;
	private JTextField fieldVille;
	private JButton buttonValider;
	private JComboBox<String> cboPlaces;
	private JComboBox<String> cboClients;
	private JButton buttonValider2;
	private JButton buttonAccueil;
	private JButton buttonClients;
	private JButton buttonReservations;
	private JLabel labelListeReservations;
	private JLabel labelListeClients;
	private JButton buttonSupprimerClients;
	
	public FenetreAccueilSpectacle() throws BLLException, DALException {

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(600, 600);
		setResizable(false);
		setTitle("Réservation spectacle");
		this.toolbar = new JMenuBar();
		JButton accueil = this.getButtonAccueil();
		this.toolbar.add(accueil);
		JButton reservations = this.getButtonReservations();
		this.toolbar.add(reservations);
		JButton clients = this.getButtonClients();
		this.toolbar.add(clients);
		this.setJMenuBar(this.toolbar);
		setVisible(true);
	}

	public void initListeSpectacle() throws BLLException, DALException {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);

		// Ligne 1
		gbc.gridwidth = 0;
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(this.getLabelListeSpectacle(), gbc);
		// Ligne 2
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

		// boucle qui ajoute des panels
		int index = 2;
		for(Map.Entry<Spectacle, JPanel> entry : getListPanelSpectacle().entrySet()) {
			Spectacle cle = entry.getKey();
			JPanel valeur = entry.getValue();
			// traitements
			gbc.gridwidth = 3;
			gbc.gridx = 0;
			gbc.gridy = index;

			panel.add(valeur,gbc);
			index ++;
		}
		setContentPane(panel);
		//scoll barre
		Container c = getContentPane();
		JScrollPane scroll = new JScrollPane(c);
		setContentPane(scroll);	
		}

	public void ListeSpectacleByArtiste(String artiste) throws BLLException, DALException {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);

		// Ligne 1
		gbc.gridwidth = 0;
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(this.getLabelListeSpectacle(), gbc);
		// Ligne 2
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

		// boucle qui ajoute des panels
		int index = 2;
		for(Map.Entry<Spectacle, JPanel> entry : getListPanelSpectacleByArtiste(artiste).entrySet()) {
			Spectacle cle = entry.getKey();
			JPanel valeur = entry.getValue();
			// traitements
			gbc.gridwidth = 3;
			gbc.gridx = 0;
			gbc.gridy = index;

			panel.add(valeur,gbc);
			index ++;
		}
		setContentPane(panel);
		//scroll barre
		Container c = getContentPane();
		JScrollPane scroll = new JScrollPane( c );
		setContentPane( scroll );	
		}
	
	public void initListeReservations()throws BLLException, DALException{
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		
		//Ligne 1
		gbc.gridwidth = 0;
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(this.getLabelListeReservations(), gbc);
		
		// boucle qui ajoute des panels
		int index = 1;
		for(Map.Entry<Reservation, JPanel> entry : getListPanelReservation().entrySet()) {
			//Reservation cle = entry.getKey();
			JPanel valeur = entry.getValue();
			// traitements
			gbc.gridwidth = 3;
			gbc.gridx = 0;
			gbc.gridy = index;

			panel.add(valeur,gbc);
			index ++;
		}
		
		setContentPane(panel);
		//scroll barre
		Container c = getContentPane();
		JScrollPane scroll = new JScrollPane(c);
		setContentPane(scroll);
	}
	
	public void initListeClients()throws BLLException, DALException{
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		
		//Ligne 1
		gbc.gridwidth = 0;
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(this.getLabelListeClients(), gbc);
		
		//Boucle qui ajoute les panels
		int index = 1;
		for(Map.Entry<Client, JPanel> entry : getListPanelClient().entrySet()) {
			JPanel valeur = entry.getValue();
			gbc.gridwidth = 2;
			gbc.gridx = 0;
			gbc.gridy = index;
			panel.add(valeur, gbc);
			index ++;
		}
		
		Container c = getContentPane();
		JScrollPane scroll = new JScrollPane(c);
		setContentPane(scroll);
		setContentPane(panel);
	}


	public void initReservation(Spectacle spectacle) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridBagLayout());
		JPanel panel3 = new JPanel();
		panel3.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);

		// Ligne 1
		gbc.gridwidth = 0;
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(this.getLabelReservation(), gbc);
		gbc.gridwidth = 1;
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(new JLabel(spectacle.getArtiste() + ", " + spectacle.getTitre()), gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		panel.add(new JLabel(spectacle.getLieu() + " / " + spectacle.getDate()), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridheight = 2;
		panel.add(new JLabel(String.valueOf("Places disponibles : " + spectacle.getPlacesDisponibles())), gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;	
		panel.add(panel2, gbc);
		
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		panel2.add(new JLabel("Nouveau client"), gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel2.add(new JLabel("Nom : "), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		panel2.add(this.getFieldNom(), gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		panel2.add(new JLabel("Prénom : "), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		panel2.add(this.getFielPrenom(), gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		panel2.add(new JLabel("Email : "), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		panel2.add(this.getFieldEmail(), gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		panel2.add(new JLabel("Adresse : "), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 4;
		panel2.add(this.getFieldAdresse(), gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 5;
		panel2.add(new JLabel("Code postal : "), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 5;
		panel2.add(this.getFieldCP(), gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 6;
		panel2.add(new JLabel("Ville : "), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 6;
		panel2.add(this.getFieldVille(), gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 7;
		panel2.add(new JLabel("Places : "), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 7;
		panel2.add(getCboPlaces(), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 8;
		panel2.add(this.getButtonValider(), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		panel.add(panel3, gbc);
		
		gbc.gridwidth = 1;
		gbc.gridx = 1;
		gbc.gridy = 0;
		panel3.add(new JLabel("Client existant"), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		panel3.add(getCboClients(), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		panel3.add(this.getButtonValider2(), gbc);
		
		setContentPane(panel);
	}

	// initListeSpectacle

	public JLabel getLabelListeSpectacle() {
		if (this.labelListeSpectacle == null) {
			this.labelListeSpectacle = new JLabel("Liste des spectacles");
			this.labelListeSpectacle.setFont(new Font("Serif", Font.PLAIN, 35));
		}
		return this.labelListeSpectacle;
	}

	public JLabel getLabelRechercher() {
		if (this.labelRechercher == null) {
			this.labelRechercher = new JLabel("Recherche par Artiste : ");
			this.labelRechercher.setFont(new Font("Serif", Font.PLAIN, 20));
		}
		return this.labelRechercher;
	}

	public JTextField getFieldRechercherArtiste() {
		if (this.fieldRechercherArtiste == null) {
			this.fieldRechercherArtiste = new JTextField(20);
		}
		return this.fieldRechercherArtiste;
	}

	public JButton getButtonRechercherArtiste() {
		if (this.buttonRechercherArtiste == null) {
			this.buttonRechercherArtiste = new JButton("OK");
			this.buttonRechercherArtiste.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						Controller.getInstance().rechercherArtiste();
					} catch (DALException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (BLLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
		return this.buttonRechercherArtiste;
	}

	public Map<Spectacle, JPanel> getListPanelSpectacle() throws BLLException, DALException {
		Map<Spectacle, JPanel> listeJLabelSpectacle = new HashMap<>();
		//On récupère la liste :
		List<Spectacle> listeSpectacle = SpectacleManager.getInstance().getSpectacles();
		for (Spectacle spectacle : listeSpectacle) {
			listeJLabelSpectacle.put(spectacle, getPanelSpectacle(spectacle));
		}
		return listeJLabelSpectacle;
	}
	
	public Map<Spectacle, JPanel> getListPanelSpectacleByArtiste(String artiste) throws BLLException, DALException {
		Map<Spectacle, JPanel> listeJLabelSpectacle = new HashMap<>();

		//On récupère la liste :
		List<Spectacle> listeSpectacle = SpectacleManager.getInstance().getSpectacleByArtiste(artiste);
		for (Spectacle spectacle : listeSpectacle) {
			listeJLabelSpectacle.put(spectacle, getPanelSpectacle(spectacle));
		}
		return listeJLabelSpectacle;
	}
	
	public Map<Client, JPanel> getListPanelClient() throws BLLException, DALException {
		Map<Client, JPanel> listeJLabelClient = new HashMap<>();
		//On récupère la liste :
		List<Client> listeClient = ClientManager.getInstance().getClients();
		for (Client client : listeClient) {
			listeJLabelClient.put(client, getPanelClient(client));
		}
		return listeJLabelClient;
	}
	
	public JPanel getPanelClient(Client client) throws BLLException, DALException {
		
		JButton btnSupprimer = null;
		JButton btnReservation = null;
		
		panelClient = new JPanel();
		panelClient.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;	
		panelClient.add(new JLabel(client.getPrenom() + " " + client.getNom() + " / " + client.getEmail()), gbc);

		btnReservation = new JButton("Réservation");
		btnReservation.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Controller.getInstance().afficherReservation(client.getIdClient());
				} catch (DALException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (BLLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		gbc.gridx = 1;
		gbc.gridy = 0;
		panelClient.add(btnReservation, gbc);
		
		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Controller.getInstance().supprimerClient(client);
				} catch (DALException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (BLLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		gbc.gridx = 2;
		gbc.gridy = 0;
		panelClient.add(btnSupprimer, gbc);

		return panelClient;
	}
	
	public JPanel getPanelSpectacle(Spectacle spectacle) throws BLLException, DALException {
		JButton btn = null;
		// pour l'instant
		//boolean dispoOuReserv = true;
		panelSpectacle = new JPanel();
		panelSpectacle.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelSpectacle.add(new JLabel(spectacle.getArtiste() + ", " + spectacle.getTitre()), gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelSpectacle.add(new JLabel(spectacle.getLieu() + " / " + spectacle.getDate()), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridheight = 2;
		if(SpectacleManager.getInstance().spectacleDispo(spectacle) == true){
		//if (dispoOuReserv == true) {
			btn = new JButton("Réservations");
			btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					//renvoi vers la reservation
					try {
						Controller.getInstance().nouvelleReservation(spectacle);
					} catch (DALException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (BLLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			panelSpectacle.add(btn, gbc);
		} else {
			btn = new JButton("Indisponible");
			btn.setEnabled(false);
			panelSpectacle.add(btn, gbc);
		}
		return panelSpectacle;
	}
	
	public Map<Reservation, JPanel> getListPanelReservation() throws BLLException, DALException {
		Map<Reservation, JPanel> listeJLabelReservation = new HashMap<>();
		//on rÃ©cuprÃ©re la liste
		List<Reservation> listeReservations = ReservationManager.getInstance().getReservations();
		for (Reservation reservation : listeReservations) {
			listeJLabelReservation.put(reservation, getPanelReservation(reservation));
		}

		return listeJLabelReservation;
	}
	
	public JPanel getPanelReservation(Reservation reservation) throws BLLException, DALException {
		JButton btn = null;
		// pour l'instant
		//boolean dispoOuReserv = true;
		panelReservation = new JPanel();
		panelReservation.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelReservation.add(new JLabel(SpectacleManager.getInstance().getSpectacleById((reservation.getIdSpectacle())).getArtiste() + ", " + SpectacleManager.getInstance().getSpectacleById((reservation.getIdSpectacle())).getTitre()), gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelReservation.add(new JLabel(SpectacleManager.getInstance().getSpectacleById((reservation.getIdSpectacle())).getLieu() + " / " + SpectacleManager.getInstance().getSpectacleById((reservation.getIdSpectacle())).getDate()), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridheight = 2;
			btn = new JButton("Annuler");
			btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						Controller.getInstance().removeReservation(reservation);
						Controller.getInstance().listeReservations();
					} catch (DALException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (BLLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			panelReservation.add(btn, gbc);
		
		return panelReservation;
	}

	public JLabel getLabelReservation() {
		if (this.labelReservation == null) {
			this.labelReservation = new JLabel("Réservation");
			this.labelReservation.setFont(new Font("Serif", Font.PLAIN, 35));
		}
		return this.labelReservation;
	}
	
	public JLabel getLabelListeReservations() {
		if (this.labelListeReservations == null) {
			this.labelListeReservations = new JLabel("Réservations");
			this.labelListeReservations.setFont(new Font("Serif", Font.PLAIN, 35));
		}
		return this.labelListeReservations;
	}
	
	public JLabel getLabelListeClients() {
		if (this.labelListeClients == null) {
			this.labelListeClients = new JLabel("Clients");
			this.labelListeClients.setFont(new Font("Serif", Font.PLAIN, 35));
		}
		return this.labelListeClients;
	}

	public JTextField getFieldNom() {
		if (this.fieldNom == null) {
			this.fieldNom = new JTextField(20);
		}
		return this.fieldNom;
	}

	public JTextField getFielPrenom() {
		if (this.fieldPrenom == null) {
			this.fieldPrenom = new JTextField(20);
		}
		return this.fieldPrenom;
	}

	public JTextField getFieldEmail() {
		if (this.fieldEmail == null) {
			this.fieldEmail = new JTextField(20);
		}
		return this.fieldEmail;
	}

	public JTextField getFieldAdresse() {
		if (this.fieldAdresse == null) {
			this.fieldAdresse = new JTextField(20);
		}
		return this.fieldAdresse;
	}

	public JTextField getFieldCP() {
		if (this.fieldCP == null) {
			this.fieldCP = new JTextField(20);
		}
		return this.fieldCP;
	}

	public JTextField getFieldVille() {
		if (this.fieldVille == null) {
			this.fieldVille = new JTextField(20);
		}
		return this.fieldVille;
	}
	
	public JButton getButtonValider(){
		if (this.buttonValider == null) {
			this.buttonValider = new JButton("Valider");
		}
		return this.buttonValider;
	}
	
	public JComboBox<String> getCboPlaces() {
		if (cboPlaces == null) {
			String[] places = { "1", "2", "3", "4", "5" };
			cboPlaces = new JComboBox<String>(places);
		}
		return cboPlaces;
	}
	
	public JComboBox<String> getCboClients() {
		if (cboClients == null) {
			String[] listeclient = {"test", "test1", "test2"};
			cboClients = new JComboBox<String>(listeclient);
		}
		return cboClients;
	}
	
	public JButton getButtonValider2(){
		if (this.buttonValider2 == null) {
			this.buttonValider2 = new JButton("Valider");
			this.buttonValider2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
		}
		return this.buttonValider2;
	}
	
	public JButton getButtonAccueil(){
		if (this.buttonAccueil == null) {
			this.buttonAccueil = new JButton("Accueil");
			this.buttonAccueil.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					try {
						Controller.getInstance().startApp();
					} catch (BLLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (DALException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
		return this.buttonAccueil;
	}
	
	public JButton getButtonReservations(){
		if (this.buttonReservations == null) {
			this.buttonReservations = new JButton("Réservations");
			this.buttonReservations.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					try {
						Controller.getInstance().listeReservations();
					} catch (BLLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (DALException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
		return this.buttonReservations;
	}
	
	public JButton getButtonClients(){
		if (this.buttonClients == null) {
			this.buttonClients = new JButton("Clients");
			this.buttonClients.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					try {
						Controller.getInstance().listeClients();
					} catch (BLLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (DALException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
		return this.buttonClients;
	}
}
