package fr.eni.spectacle.dal;


import fr.eni.spectacle.bll.BLLException;
import fr.eni.spectacle.bo.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAOJdbcImpl implements Dao{

    private Connection connect;

    public ClientDAOJdbcImpl() throws DALException, BLLException {
        //connections à la base de donnée

        this.connect = ConnectionSingleton.getConnection().getConnect();

    }


    public void insert(Object a2) throws DALException {
        Client a1 = (Client) a2;

        try {
            String sql = "INSERT INTO CLIENT (" +
                    "nom," +//nom
                    "prenom," + //prenom
                    "email," + //email VARCHAR
                    "adresse," +//adresse TEXT
                    "code_postal," + //code_postal CHAR(5)
                    "ville" +  //ville VARCHAR(200)
                    ")" +

                    "VALUES (" +
                    "?,"+//"nom,
                    "?,"+//"prenom,
                    "?,"+//"email,
                    "?,"+//"adresse,\
                    "?,"+//"code_postal,\
                    "?"+ //"ville,
                    ")";
           //id INT
            PreparedStatement stmt = this.connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1,a1.getNom());//"nom,
            stmt.setString(2,a1.getPrenom());//"prenom,
            stmt.setString(3,a1.getEmail());//"email,
            stmt.setString(4, a1.getAdresse());//"adresse,\
            stmt.setString(5,a1.getCodePostal());//"code_postal,
            stmt.setString(6,a1.getVille());//"ville,

// on indique le numero d'id auto genéré dans l'objet article
            int nbRows = stmt.executeUpdate();
            if (nbRows == 1){
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()){
                    a1.setIdClient(rs.getInt(1));
                }
                //on ferme les connections
                stmt.close();
                //connect.close();

}

        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
    }

public Client selectById(int id) throws DALException {
    try{
        String sql = "SELECT * FROM CLIENT WHERE id = ?";
        PreparedStatement stmt = this.connect.prepareStatement(sql);

        stmt.setInt(1,id);//"reference,

        ResultSet res = stmt.executeQuery();

        Client data = null;

         if (res.next()){
                data = new Client(res.getInt("id"),res.getString("nom"),res.getString("prenom"),res.getString("email"),res.getString("adresse"),res.getString("code_postal"),res.getString("ville"));
        //on ferme les connections
        stmt.close();
        //connect.close();
             }

        return data;

        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
    }

    public List<Client> selectByNomClient(String nom)throws DALException{
        try{
            String sql = "SELECT * FROM CLIENT WHERE nom LIKE ?";
            PreparedStatement stmt = this.connect.prepareStatement(sql);

            stmt.setString(1,nom.trim());//"reference,



            ResultSet res = stmt.executeQuery();
//on boucle sur les résultats
            List<Client> data = new ArrayList<>();
            while (res.next()){
                //data.add(this.selectById(res.getInt("id")));
                data.add(new Client(res.getInt("id"),res.getString("nom"),res.getString("prenom"),res.getString("email"),res.getString("adresse"),res.getString("code_postal"),res.getString("ville")));

            }


            //on ferme les connections
            stmt.close();
            //connect.close();

            return data;

        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
    }


    public List<Client> selectAll() throws DALException {
        try{
            String sql = "SELECT * FROM CLIENT";
            PreparedStatement stmt = this.connect.prepareStatement(sql);


            ResultSet res = stmt.executeQuery();
//on boucle sur les résultats
            List<Client> data = new ArrayList<>();
            while (res.next()){
                //data.add(this.selectById(res.getInt("id")));
                data.add(new Client(res.getInt("id"),res.getString("nom"),res.getString("prenom"),res.getString("email"),res.getString("adresse"),res.getString("code_postal"),res.getString("ville")));

            }


            //on ferme les connections
            stmt.close();
            //connect.close();

            return data;

        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }

    }

    public void update(Object a2) throws DALException {
        Client a1 = (Client) a2;
        try {
            String sql = "UPDATE CLIENT  SET " +
                    "nom =?," +
                    "prenom =?," +
                    "email =?," +
                    "adresse =?," +
                    "code_postal =?," +
                    "ville =? " +
                    "WHERE id =?"
                    ;
            PreparedStatement stmt = this.connect.prepareStatement(sql);

            stmt.setString(1,a1.getNom());//"titre,
            stmt.setString(2,a1.getPrenom());//"artiste,
            stmt.setString(3,a1.getEmail());//"lieu,
            stmt.setString(4, a1.getAdresse());//"date,\
            stmt.setString(5,a1.getCodePostal());//"places_disponible,
            stmt.setString(6,a1.getVille());//"places_disponible,
            stmt.setInt(7,a1.getIdClient());//"id,


// on update
            stmt.executeUpdate();

            //on ferme les connections
            stmt.close();
            //connect.close();



        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }



    }

    public void delete(int id) throws DALException {
        try {
        String sql = "DELETE FROM CLIENT WHERE id = ?";
        PreparedStatement stmt = this.connect.prepareStatement(sql);

        stmt.setInt(1,id);//"reference,

        stmt.executeUpdate();
            //on ferme les connections
            stmt.close();
            //connect.close();


        } catch (SQLException e) {
        throw new DALException(e.getMessage());
    }

}
public void finalize() throws SQLException {
    connect.close();
}
}
