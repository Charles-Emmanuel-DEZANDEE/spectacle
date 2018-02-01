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

	
	private Controller() throws DALException, BLLException{
		fenetreSpectacle = new FenetreAccueilSpectacle();
	}

	public static synchronized Controller getInstance() throws DALException, BLLException{
		if (instance == null){
			instance = new Controller();
		}
		return instance;
	}
	
	public void startApp() throws BLLException, DALException {
		fenetreSpectacle.initListeSpectacle();
	}
	
	public void nouvelleReservation(Spectacle spectacle){
		fenetreSpectacle.initReservation(spectacle);
		fenetreSpectacle.revalidate();
		fenetreSpectacle.repaint();
	}
	
	public void rechercherArtiste(){
		
		fenetreSpectacle.getFieldRechercherArtiste();

	}
}
