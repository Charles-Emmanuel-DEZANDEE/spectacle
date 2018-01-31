package fr.eni.spectacle.dal;

import fr.eni.spectacle.bo.Spectacle;

public class DaoFactory {

	// TODO instancier avec le bon JDBC
	public static Dao<Spectacle> getSpectacleDAO() throws DALException {
		Dao<Spectacle> sDao = new StectacleDAOJdbcImpl();
		return sDao;
	}

}
