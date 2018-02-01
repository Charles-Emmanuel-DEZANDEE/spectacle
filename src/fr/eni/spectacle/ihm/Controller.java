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
	private ClientManager cMger;
	private ReservationManager rMger;
	private SpectacleManager sMger;
	private List<Client> listeClients;
	private List<Reservation> listeReservations;
	private List<Spectacle> listeSpectacle;
	
	private Controller() throws DALException, BLLException{
		cMger = new ClientManager();
		rMger = new ReservationManager();
		sMger = new SpectacleManager();
		
		listeClients = cMger.getClients();
		listeReservations = rMger.getReservations();
		listeSpectacle = sMger.getSpectacles();
	}

	public static synchronized Controller get() throws DALException, BLLException{
		if (instance == null){
			instance = new Controller();
		}
		return instance;
	}
	
	public void startApp() throws BLLException, DALException {
		fenetreSpectacle = new FenetreAccueilSpectacle();
		fenetreSpectacle.initListeSpectacle();
	}
	
	
}
