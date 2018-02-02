package fr.eni.spectacle.ihm;

import java.util.List;

import fr.eni.spectacle.bll.BLLException;
import fr.eni.spectacle.bll.ClientManager;
import fr.eni.spectacle.bll.ReservationManager;
import fr.eni.spectacle.bll.SpectacleManager;
import fr.eni.spectacle.bo.Client;
import fr.eni.spectacle.bo.Reservation;
import fr.eni.spectacle.bo.Spectacle;
import fr.eni.spectacle.dal.DALException;
import fr.eni.spectacle.ihm.*;

public class Controller {
	
	private FenetreAccueilSpectacle fenetreSpectacle;
	private static Controller instance;
	private boolean nouveauClient;

	
	private Controller() throws DALException, BLLException{
		fenetreSpectacle = new FenetreAccueilSpectacle();
		this.nouveauClient = true;
	}

	public static synchronized Controller getInstance() throws DALException, BLLException{
		if (instance == null){
			instance = new Controller();
		}
		return instance;
	}
	
	public void startApp() throws BLLException, DALException {
		fenetreSpectacle.initListeSpectacle();
		fenetreSpectacle.revalidate();
		fenetreSpectacle.repaint();
	}
	
	public void listeReservations() throws BLLException, DALException{
		fenetreSpectacle.initListeReservations();
		fenetreSpectacle.revalidate();
		fenetreSpectacle.repaint();
	}
	
   public void listeClients() throws BLLException, DALException{
		fenetreSpectacle.initListeClients();
		fenetreSpectacle.revalidate();
		fenetreSpectacle.repaint();
    }
	
	public void nouvelleReservation(Spectacle spectacle){
		fenetreSpectacle.initReservation(spectacle);
		fenetreSpectacle.revalidate();
		fenetreSpectacle.repaint();
	}
	
	public void rechercherArtiste() throws BLLException, DALException{
		fenetreSpectacle.ListeSpectacleByArtiste(fenetreSpectacle.getFieldRechercherArtiste().getText());
		fenetreSpectacle.revalidate();
		fenetreSpectacle.repaint();
	}

	public void enregistrerReservation(int idSpectacle) throws BLLException, DALException {
	    //traitement
        if (this.nouveauClient){
            //création d'un client
			Client newClient = new Client(fenetreSpectacle.getFieldNom().getText(),fenetreSpectacle.getFielPrenom().getText(), fenetreSpectacle.getFieldEmail().getText(),fenetreSpectacle.getFieldAdresse().getText(),fenetreSpectacle.getFieldCP().getText(),fenetreSpectacle.getFieldVille().getText());
			ClientManager.getInstance().addClient(newClient);
        }

        // recupérer les champs avec get
        //on créé une reservation
Reservation newReservation = new Reservation(int idSpectacle,int clientId,
        int nombrePlaces);


        this.nouveauClient= true;

    }

    public void utiliserClientExistant(Client client){
	    this.nouveauClient= false;
	    //remplissage des champs de la reservation

    }
}
