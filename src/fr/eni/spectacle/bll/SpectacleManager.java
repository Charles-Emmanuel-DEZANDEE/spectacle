package fr.eni.spectacle.bll;

import java.util.List;

import fr.eni.spectacle.bo.Reservation;
import fr.eni.spectacle.bo.Spectacle;
import fr.eni.spectacle.dal.DALException;
import fr.eni.spectacle.dal.Dao;
import fr.eni.spectacle.dal.ReservationDAOJdbcImpl;
import fr.eni.spectacle.dal.DAOFactory;
import fr.eni.spectacle.dal.StectacleDAOJdbcImpl;

public class SpectacleManager {

	private static Dao<Spectacle> daoSpectalce;
	private static Dao<Reservation> daoReservation;
	private static SpectacleManager instance;
	
	
	private SpectacleManager() throws DALException, BLLException {
		//Instancier le Data Access Object
		daoSpectalce =DAOFactory.getSpectacleDAO();
		daoReservation= DAOFactory.getReservationDAO();
	}
	
	public static SpectacleManager getInstance() throws BLLException, DALException{
		if ( SpectacleManager.instance == null){
			SpectacleManager.instance = new SpectacleManager();
		}
		return SpectacleManager.instance;
	}
	
	public boolean spectacleDispo( Spectacle spectacle) throws BLLException {
		boolean reponse = true;

		if (this.getSpectacleById(spectacle.getIdSpectacle()).getPlacesDisponibles() <= 0) {
			reponse = false;
		}
		return reponse;

	}

	public Spectacle getSpectacleById(int idSpectacle) throws BLLException{
		Spectacle spectacle=null;
		try {
			spectacle = daoSpectalce.selectById(idSpectacle);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur r�cup�ration du spectacle par Id", e);
		}
		
		return spectacle;
	}
	
	public List<Spectacle> getSpectacleByArtiste(String artiste) throws BLLException{
		List<Spectacle> spectacle=null;
		try { 
			spectacle = ((StectacleDAOJdbcImpl)daoSpectalce).selectByArtiste(artiste);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur r�cup�ration spectacles par artiste", e);
		}
		
		return spectacle;
	}
	
	public List<Spectacle> getSpectacles() throws BLLException{
		List<Spectacle> spectacle=null;
		try {
			spectacle = daoSpectalce.selectAll();
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur r�cup�ration spectacles", e);
		}
		
		return spectacle;
	}
	
	public void addSpectacle(Spectacle newSpectacle) throws BLLException {
		if(newSpectacle.getIdSpectacle()!= 0){
			throw new BLLException("Article deja existant.");
		}
		try {
			validerSpectacle(newSpectacle);
			daoSpectalce.insert(newSpectacle);
		} catch (DALException e) {
			throw new BLLException("Echec addSpectacle", e);
		}
	}
	
	public void updateArticle(Spectacle spectacle) throws BLLException{
		try {
			validerSpectacle(spectacle);
			daoSpectalce.update(spectacle);
			
		} catch (DALException e) {
			throw new BLLException("Echec updateSpectacle-spectacle:"+spectacle, e);
		}
	}
	
	public void removeSpectacle(int  idSpectacle) throws BLLException{
		try {
			// supprimer d'abord ses reservations
			((ReservationDAOJdbcImpl)daoReservation).deleteByIdClient(idSpectacle);
			((StectacleDAOJdbcImpl) daoSpectalce).delete(idSpectacle);
		} catch (DALException e) {
			throw new BLLException("Echec de la suppression du spectacle - ", e);
		}
		
	}
	
	
	public void validerSpectacle(Spectacle spectacle) throws BLLException
	{
		boolean valide = true;
		StringBuffer sb = new StringBuffer();
		
		if(spectacle==null){
			throw new BLLException("Spectacle null");
		}
		//Les attributs du spectacle sont obligatoires
		if(spectacle.getArtiste()==null || spectacle.getArtiste().trim().length()==0){
			sb.append("L'article est obligatoire.\n");
			valide = false;
		}
		if(spectacle.getLieu()==null || spectacle.getLieu().trim().length()==0){
			sb.append("Le lieu  est obligatoire.\n");
			valide = false;
		}
		if(spectacle.getPlacesDisponibles()==0){
			sb.append("Les places du spectacle est obligatoire.\n");
			valide = false;
		}
		if(spectacle.getTitre()==null || spectacle.getTitre().trim().length()==0){
			sb.append("Le titre  est obligatoire.\n");
			valide = false;
		}
		if(spectacle.getDate()==null){
			sb.append("Le titre  est obligatoire.\n");
			valide = false;
		}
		
		if(!valide){
			throw new BLLException(sb.toString());
		}

	}
}