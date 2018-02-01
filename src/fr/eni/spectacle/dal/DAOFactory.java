package fr.eni.spectacle.dal;

import fr.eni.spectacle.bo.Client;
import fr.eni.spectacle.bo.Reservation;
import fr.eni.spectacle.bo.Spectacle;

public class DAOFactory {

	// TODO instancier avec le bon JDBC
	public static Dao<Spectacle> getSpectacleDAO() throws DALException {
		Dao<Spectacle> sDao = new StectacleDAOJdbcImpl();
		return sDao;
	}
	
	public static Dao<Reservation> getReservationDAO() throws DALException {
		// todo mettre le jdbc de reservation
		Dao<Reservation> rDao = new ReservationDAOJdbcImpl();
		return rDao;
	}

	public static Dao<Client> getClientDAO() throws DALException {
		// todo mettre le jdbc de reservation
		Dao<Client> cDao = new ClientDAOJdbcImpl();
		return cDao;
	}

}
