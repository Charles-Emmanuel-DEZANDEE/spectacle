package fr.eni.spectacle.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionBD {

	private  Connection connect;
	
	public ConnexionBD() throws DALException {
		try {
            Class.forName(Settings.getProperty("driverDB"));
            //DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());


            String url = Settings.getProperty("urldb");
            this.connect = DriverManager.getConnection(url, Settings.getProperty("userdb"),Settings.getProperty("passworddb"));
        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new DALException(e.getMessage());
        }
	}

}
