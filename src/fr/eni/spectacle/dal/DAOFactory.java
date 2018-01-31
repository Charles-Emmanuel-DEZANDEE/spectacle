package fr.eni.spectacle.dal;

import fr.eni.spectacle.bo.Reservation;
import fr.eni.spectacle.bo.Spectacle;

public class DaoFactory {

	// TODO instancier avec le bon JDBC
	public static Dao<Spectacle> getSpectacleDAO() throws DALException {
		Dao<Spectacle> sDao = new StectacleDAOJdbcImpl();
		return sDao;
	}
	
	public static Dao<Reservation> getReservationDAO() throws DALException {
		// todo mettre le jdbc de reservation
		Dao<Reservation> rDao = new StectacleDAOJdbcImpl();
		return rDao;
	}

}
