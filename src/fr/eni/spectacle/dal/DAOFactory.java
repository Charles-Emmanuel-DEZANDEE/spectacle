package fr.eni.spectacle.dal;

public class DAOFactory {

    public static StectacleDAO getArticleFactory () throws DALException {
        return new StectacleDAOJdbcImpl();
    }
}
