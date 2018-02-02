package fr.eni.spectacle.dal;


import fr.eni.spectacle.bo.Client;
import fr.eni.spectacle.bo.Reservation;
import fr.eni.spectacle.bo.Spectacle;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public class AppliTestDAL {

	public static void main(String[] args) throws DALException {

		//Déclaration et instanciation de la DAO
		Dao<Spectacle> stectacleDAO = DAOFactory.getSpectacleDAO();

		//Instanciation du jeu d'essai
		//Calendar today = new Calendar();
		Spectacle a1 = new Spectacle( "star","garou", "nantes", new Date(118,05,23), 150);
		Spectacle a2 = new Spectacle(  "test 1","johny", "paris", new Date(118,05,23), 150);
		Spectacle a3 = new Spectacle( "test 2","acdc", "lille", new Date(119,01,20), 200);

		Client c1 = new Client("DEZANDEe", "Charles", "charles.manu@sigma.fr", 
				"derriere l'eglise", "44800", "nantes");
		Client c2 = new Client("DEZANDEe", "Charles", "charles.manu@sigma.fr", 
				"derriere l'eglise", "44800", "nantes");
		Client c3 = new Client("DEZANDEe", "Charles", "charles.manu@sigma.fr", 
				"derriere l'eglise", "44800", "nantes");
		
		//Reservation r1 = new Reservation(idSpectacle, clientId, nombrePlaces, date_reservation);
		
		
		System.out.println("Ajout des articles... ");
		//TODO...
		try {
			stectacleDAO.insert(a1);
			System.out.println("Article ajouté  : " + a1.toString() );
			stectacleDAO.insert(a2);
			System.out.println("Article ajouté  : " + a2.toString() );
			stectacleDAO.insert(a3);
			System.out.println("Article ajouté  : " + a3.toString() );


			//Sélection de l'article par id
			Spectacle a = stectacleDAO.selectById(a2.getIdSpectacle());
			System.out.println("\nSélection de l'article par id  : " + a.toString() );

			//Sélection de tous les articles
			List<Spectacle> spectacles = stectacleDAO.selectAll();
			System.out.println("\nSélection de tous les spectacles  : "  );
			afficherArticles(spectacles);

			//Modification d'un article
			System.out.println("\nModification d'un article  : " );
			System.out.println("Article avant modification : "  + a1.toString());
			a1.setArtiste("changé");
			a1.setLieu("trou");
			stectacleDAO.update(a1);
			System.out.println("Article après modification  : " + a1.toString() );
			
			
			//Suppression d'un article
			System.out.println("\nSuppression de l'article  : " + a1.toString());
			//stectacleDAO.delete(a1.getIdSpectacle());
            spectacles = stectacleDAO.selectAll();
			System.out.println("Liste des articles après suppression : "  );
			afficherArticles(spectacles);
			System.out.println("---------------------------------------------------------------");

			
		} catch (DALException e) {
			e.printStackTrace();
		}

	}

	
	private static void afficherArticles(List<Spectacle> articles){
		StringBuffer sb = new StringBuffer();
		for(Spectacle art: articles){
			sb.append(art.toString());
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
