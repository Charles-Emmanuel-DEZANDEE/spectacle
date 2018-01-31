package fr.eni.spectacle.ihm;

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
	
	public void startApp(){
		fenetreSpectacle = new FenetreAccueilSpectacle();
	}
}
