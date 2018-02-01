package fr.eni.spectacle.ihm;

import fr.eni.spectacle.bll.BLLException;
import fr.eni.spectacle.dal.DALException;

import javax.swing.SwingUtilities;

public class SpectacleApp {
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable(){
			
			@Override
			public void run(){
				try {
					Controller.get().startApp();
				} catch (BLLException e) {
					e.printStackTrace();
				} catch (DALException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
