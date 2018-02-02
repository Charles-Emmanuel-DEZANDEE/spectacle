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

	public void enregistrerReservation(){
	    //traitement
        if (this.nouveauClient){
            //création d'un client
        }

        // recupérer les champs avec get
        //on créé une reservation



        this.nouveauClient= true;

    }

    public void utiliserClientExistant(Client client){
	    this.nouveauClient= false;
	    //remplissage des champs de la reservation

    }

    public void listeDesResvations(){

    }

    public void listeDesClients(){

    }


}
