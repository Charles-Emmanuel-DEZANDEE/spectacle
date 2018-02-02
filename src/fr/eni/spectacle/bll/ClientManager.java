package fr.eni.spectacle.bll;

import fr.eni.spectacle.bo.Client;
import fr.eni.spectacle.bo.Spectacle;
import fr.eni.spectacle.dal.*;

import java.util.List;

public class ClientManager {

	private static Dao<Client> daoClient;
	private static ClientManager instance;


	private ClientManager() throws DALException, BLLException {
		//Instancier le Data Access Object
		daoClient =DAOFactory.getClientDAO();
	}
	
	public static ClientManager getInstance() throws BLLException, DALException{
		if ( ClientManager.instance == null){
			ClientManager.instance = new ClientManager();
		}
		return ClientManager.instance;
	}
	

	public Client getClientById(int idClient) throws BLLException{
		Client spectacle=null;
		try {
			spectacle = daoClient.selectById(idClient);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur r�cup�ration du spectacle par Id", e);
		}
		
		return spectacle;
	}
	
	public List<Client> getClientByArtiste(String nom) throws BLLException{
		List<Client> clients=null;
		try { 
			clients = ((ClientDAOJdbcImpl)daoClient).selectByNomClient(nom);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur r�cup�ration clients par nom", e);
		}
		
		return clients;
	}
	
	public List<Client> getClients() throws BLLException{
		List<Client> spectacle=null;
		try {
			spectacle = daoClient.selectAll();
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur r�cup�ration clients", e);
		}
		
		return spectacle;
	}
	
	public void addClient(Client newClient) throws BLLException {
		if(newClient.getIdClient()!= 0){
			throw new BLLException("Client deja existant.");
		}
		try {
			validerClient(newClient);
			daoClient.insert(newClient);
		} catch (DALException e) {
			throw new BLLException("Echec addClient", e);
		}
	}
	
	public void updateClient(Client spectacle) throws BLLException{
		try {
			validerClient(spectacle);
			daoClient.update(spectacle);
			
		} catch (DALException e) {
			throw new BLLException("Echec updateClient-spectacle:"+spectacle, e);
		}
	}
	
	public void removeClient(int  idClient) throws BLLException{
		try {
			daoClient.delete(idClient);
		} catch (DALException e) {
			throw new BLLException("Echec de la suppression du client - ", e);
		}
		
	}
	
	
	public void validerClient(Client client) throws BLLException
	{
		boolean valide = true;
		StringBuffer sb = new StringBuffer();
		
		if(client==null){
			throw new BLLException("Spectacle null");
		}
		//Les attributs du client sont obligatoires
		if(client.getNom()==null || client.getNom().trim().length()==0){
			sb.append("Le nom est obligatoire.\n");
			valide = false;
		}
		if(client.getPrenom()==null || client.getPrenom().trim().length()==0){
			sb.append("Le prénom  est obligatoire.\n");
			valide = false;
		}
		if(client.getEmail()==null || client.getEmail().trim().length()==0){
			sb.append("L'email' est obligatoire.\n");
			valide = false;
		}

		if(client.getAdresse()==null || client.getAdresse().trim().length()==0){
			sb.append("Le titre  est obligatoire.\n");
			valide = false;
		}
		if(client.getCodePostal()==null || client.getCodePostal().trim().length()==0){
			sb.append("Le code postal  est obligatoire.\n");
			valide = false;
		}
		if(client.getVille()==null || client.getVille().trim().length()==0){
			sb.append("La ville  est obligatoire.\n");
			valide = false;
		}

		if(!valide){
			throw new BLLException(sb.toString());
		}

	}
}