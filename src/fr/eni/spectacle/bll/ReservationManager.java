package fr.eni.spectacle.bll;

import java.util.List;

import fr.eni.spectacle.bo.Client;
import fr.eni.spectacle.bo.Reservation;
import fr.eni.spectacle.bo.Spectacle;
import fr.eni.spectacle.dal.DALException;
import fr.eni.spectacle.dal.Dao;
import fr.eni.spectacle.dal.DAOFactory;

public class ReservationManager {
	private static Dao<Reservation> daoReservation;
	private static Dao<Spectacle> daoSpectacle;
	private static ReservationManager instance;
	
	public ReservationManager() throws DALException {
		//Instancierr le Data Access Object
		daoReservation =DAOFactory.getReservationDAO();
		daoSpectacle = DAOFactory.getSpectacleDAO();
	}

	
	public static ReservationManager getInstance() throws BLLException, DALException{
		if ( ReservationManager.instance == null){
			ReservationManager.instance = new ReservationManager();
		}
		return ReservationManager.instance;
	}
	
	public Reservation getSpectacleById(int idReservation) throws BLLException{
		Reservation reservation=null;
		try {
			reservation = daoReservation.selectById(idReservation);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur r?cup?ration de la reservation par Id", e);
		}
		
		return reservation;
	}
	
	public List<Reservation> getReservations() throws BLLException{
		List<Reservation> reservations=null;
		try {
			reservations = daoReservation.selectAll();
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur r?cup?ration reservations", e);
		}
		
		return reservations;
	}
	
	public void addReservation(Reservation newReservation, Client client, boolean nouveauClient) throws BLLException {
		if(newReservation.getIdSpectacle()!= 0){
			throw new BLLException("Reservation deja existant.");
		}
		try {
			//on valide la reservation et client
			ClientManager.getInstance().validerClient(client);
			this.validerReservation(newReservation);

			//on ajoute le client si c'est un nouveau
            if (nouveauClient) {
                ClientManager.getInstance().addClient(client);
            }
            //on le met à jour s'il y a eu des modifs
			else {
                ClientManager.getInstance().updateClient(client);
            }

        // on ajoute la reservation
			daoReservation.insert(newReservation);
			//mettre a jour le spectacle 
			Spectacle spectacle = daoSpectacle.selectById(newReservation.getIdSpectacle());
			spectacle.setPlacesDisponibles(spectacle.getPlacesDisponibles() - newReservation.getNombrePlaces());
			daoSpectacle.update(spectacle);
		} catch (DALException e) {
			throw new BLLException("Echec addReservation", e);
		}
	}
	
	public void updateReservation(Reservation reservation, Client client) throws BLLException{
		try {
			this.validerReservation(reservation);
			ClientManager.getInstance().validerClient(client);
			ClientManager.getInstance().updateClient(client);
			daoReservation.update(reservation);
			
		} catch (DALException e) {
			throw new BLLException("Echec updateSpectacle-spectacle:"+reservation, e);
		}
	}
	
	public void removeReservation(int  idReservation, int idClient) throws BLLException{
		try {
			daoReservation.delete(idReservation);

		} catch (DALException e) {
			throw new BLLException("Echec de la suppression de la reservation - ", e);
		}
		
	}

	public void validerReservation(Reservation reservation) throws BLLException
	{
		boolean valide = true;
		StringBuffer sb = new StringBuffer();

		if(reservation==null){
			throw new BLLException("Spectacle null");
		}
		//Les attributs de la reservation sont obligatoires
		if(reservation.getNombrePlaces() == 0){
			sb.append("Le nombre de places doit être supérieur à 0.\n");
			valide = false;
		}

		if(!valide){
			throw new BLLException(sb.toString());
		}

	}


}
