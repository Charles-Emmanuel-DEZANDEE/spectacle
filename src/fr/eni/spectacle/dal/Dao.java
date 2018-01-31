package fr.eni.spectacle.dal;

import java.util.List;

import fr.eni.spectacle.bo.Spectacle;


public interface Dao<T> {

	public T selectById(T data)throws DALException;
	public List<T> selectAll()throws DALException;
	public void update(T data)throws DALException;
	public void insert(T data)throws DALException;
	public void delete(T data)throws DALException;
	public List<Spectacle> selectByArtiste(T data)throws DALException ;
}
