package fr.eni.spectacle.dal;

import fr.eni.spectacle.bo.Spectacle;

public class DaoFactory {

	// TODO instancier avec le bon JDBC
	public static Dao<Spectacle> getSpectacleDAO() {
		Dao<Spectacle> sDao = null;//new SpectacleDAOJdbcImpl<Spectacle>();
		return sDao;
	}

}
