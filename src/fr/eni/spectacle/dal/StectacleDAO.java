package fr.eni.spectacle.dal;

import fr.eni.spectacle.bo.Spectacle;

import java.util.List;

public interface StectacleDAO {
    public void insert(Spectacle a1) throws DALException;
    public Spectacle selectById(Integer idSpectacle) throws DALException;
    public List<Spectacle> selectAll() throws DALException;
    public void update(Spectacle a1) throws DALException;
    public void delete(Integer idSpectacle) throws DALException;

}
