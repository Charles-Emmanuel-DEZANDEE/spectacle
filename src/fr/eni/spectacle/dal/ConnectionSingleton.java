package fr.eni.spectacle.dal;

import fr.eni.spectacle.bll.BLLException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSingleton {

    private static ConnectionSingleton instance;
    private Connection connect;

    public Connection getConnect() {
        return connect;
    }

    private ConnectionSingleton() throws DALException {

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

    public static ConnectionSingleton getConnection() throws BLLException, DALException{
        if ( ConnectionSingleton.instance == null){
            ConnectionSingleton.instance = new ConnectionSingleton();
        }
        return ConnectionSingleton.instance;
    }
}
