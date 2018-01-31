package fr.eni.spectacle.bll;

import java.util.List;

import fr.eni.spectacle.bo.Spectacle;
import fr.eni.spectacle.dal.DALException;
import fr.eni.spectacle.dal.Dao;
import fr.eni.spectacle.dal.DaoFactory;

public class SpectacleManager {

	private static Dao<Spectacle> daoSpectalce;
	private static SpectacleManager instance;
	
	
	public SpectacleManager() throws DALException {
		//Instancier le Data Access Object
		daoSpectalce =DaoFactory.getSpectacleDAO();
	}
	
	public static SpectacleManager getInstance() throws BLLException, DALException {
		if ( SpectacleManager.instance == null){
			SpectacleManager.instance = new SpectacleManager();
		}
		return SpectacleManager.instance;
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
			// TODO
			//validerSpectacle(newSpectacle);
			daoSpectalce.insert(newSpectacle);
		} catch (DALException e) {
			throw new BLLException("Echec addSpectacle", e);
		}
	}
	
	public void updateArticle(Spectacle spectacle) throws BLLException{
		try {
			// TODO 
			//validerSpectacle(spectacle);
			daoSpectalce.update(spectacle);
			
		} catch (DALException e) {
			throw new BLLException("Echec updateSpectacle-spectacle:"+spectacle, e);
		}
	}
}