package fr.eni.spectacle.ihm;

import javax.swing.SwingUtilities;

public class SpectacleApp {
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable(){
			
			@Override
			public void run(){
				Controller.get().startApp();
			}
		});
	}
}
