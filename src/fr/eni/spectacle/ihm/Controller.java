package fr.eni.spectacle.ihm;

import fr.eni.spectacle.bll.BLLException;
import fr.eni.spectacle.dal.DALException;

public class Controller {
	
	private FenetreAccueilSpectacle fenetreSpectacle;
	private static Controller instance;
	
	private Controller(){
		
	}

	public static synchronized Controller get(){
		if (instance == null){
			instance = new Controller();
		}
		return instance;
	}
	
	public void startApp() throws BLLException, DALException {
		fenetreSpectacle = new FenetreAccueilSpectacle();
	}
}
