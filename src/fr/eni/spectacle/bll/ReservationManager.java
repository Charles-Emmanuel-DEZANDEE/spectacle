package fr.eni.spectacle.bll;

import java.util.List;

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
			throw new BLLException("Erreur récupération de la reservation par Id", e);
		}
		
		return reservation;
	}
	
	public List<Reservation> getReservations() throws BLLException{
		List<Reservation> reservations=null;
		try {
			reservations = daoReservation.selectAll();
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur récupération reservations", e);
		}
		
		return reservations;
	}
	
	public void addReservation(Reservation newReservation) throws BLLException {
		if(newReservation.getIdSpectacle()!= 0){
			throw new BLLException("Reservation deja existant.");
		}
		try {
			//TODO 
			//validerSpectacle(newReservation);
			daoReservation.insert(newReservation);
			//mettre a jour le spectacle 
			Spectacle spectacle = daoSpectacle.selectById(newReservation.getIdSpectacle());
			spectacle.setPlacesDisponibles(spectacle.getPlacesDisponibles() - newReservation.getNombrePlaces());
			daoSpectacle.update(spectacle);
		} catch (DALException e) {
			throw new BLLException("Echec addReservation", e);
		}
	}
	
	public void updateReservation(Reservation reservation) throws BLLException{
		try {
			//todo
			//validerSpectacle(reservation);
			daoReservation.update(reservation);
			
		} catch (DALException e) {
			throw new BLLException("Echec updateSpectacle-spectacle:"+reservation, e);
		}
	}
	
	public void removeReservation(int  idReservation) throws BLLException{
		try {
			daoReservation.delete(idReservation);
		} catch (DALException e) {
			throw new BLLException("Echec de la suppression du spectacle - ", e);
		}
		
	}
	
}
