package fr.eni.spectacle.dal;


import fr.eni.spectacle.bo.Spectacle;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StectacleDAOJdbcImpl implements Dao{

    private  Connection connect;

    public StectacleDAOJdbcImpl() throws DALException {
        //connections à la base de donnée

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


    public void insert(Object a2) throws DALException {
        Spectacle a1 = (Spectacle) a2;

        try {
            String sql = "INSERT INTO SPECTACLE (" +
                    "titre," +
                    "artiste," +
                    "lieu," +
                    "date," +
                    "places_disponible" +
                    ")" +

                    "VALUES (" +
                    "?,"+//"titre,
                    "?,"+//"artiste,
                    "?,"+//"lieu,
                    "?,"+//"date,\
                    "?"+//"places_disponible,
                    ")";
            PreparedStatement stmt = this.connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1,a1.getTitre());//"titre,
            stmt.setString(2,a1.getArtiste());//"artiste,
            stmt.setString(3,a1.getLieu());//"lieu,
            stmt.setDate(4, a1.getDate());//"date,\
            stmt.setInt(5,a1.getPlacesDisponibles());//"places_disponible,

// on indique le numero d'id auto genéré dans l'objet article
            int nbRows = stmt.executeUpdate();
            if (nbRows == 1){
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()){
                    a1.setIdSpectacle(rs.getInt(1));
                }
                //on ferme les connections
                stmt.close();
                //connect.close();

}

        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
    }

public Spectacle selectById(int id) throws DALException {
    try{
        String sql = "SELECT * FROM SPECTACLE WHERE id = ?";
        PreparedStatement stmt = this.connect.prepareStatement(sql);

        stmt.setInt(1,id);//"reference,

        ResultSet res = stmt.executeQuery();

        Spectacle data = null;

         if (res.next()){
                data = new Spectacle(res.getInt("id"),res.getString("titre"),res.getString("artiste"),res.getString("lieu"),res.getDate("date"),res.getInt("places_disponible"));
        //on ferme les connections
        stmt.close();
        //connect.close();
             }

        return data;

        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
    }

    public List<Spectacle> selectByArtiste(String artiste)throws DALException{
        try{
            String sql = "SELECT * FROM SPECTACLE WHERE artiste LIKE ?";
            PreparedStatement stmt = this.connect.prepareStatement(sql);

            stmt.setString(1,artiste.trim());//"reference,



            ResultSet res = stmt.executeQuery();
//on boucle sur les résultats
            List<Spectacle> data = new ArrayList<>();
            while (res.next()){
                //data.add(this.selectById(res.getInt("id")));
                data.add(new Spectacle(res.getInt("id"),res.getString("titre"),res.getString("artiste"),res.getString("lieu"),res.getDate("date"),res.getInt("places_disponible")));

            }


            //on ferme les connections
            stmt.close();
            //connect.close();

            return data;

        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
    }


    public List<Spectacle> selectAll() throws DALException {
        try{
            String sql = "SELECT * FROM SPECTACLE";
            PreparedStatement stmt = this.connect.prepareStatement(sql);


            ResultSet res = stmt.executeQuery();
//on boucle sur les résultats
            List<Spectacle> data = new ArrayList<>();
            while (res.next()){
                //data.add(this.selectById(res.getInt("id")));
                    data.add(new Spectacle(res.getInt("id"),res.getString("titre"),res.getString("artiste"),res.getString("lieu"),res.getDate("date"),res.getInt("places_disponible")));

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
       Spectacle a1 = (Spectacle) a2;
        try {
            String sql = "UPDATE SPECTACLE  SET " +
                    "titre =?," +
                    "artiste =?," +
                    "lieu =?," +
                    "date =?," +
                    "places_disponible =? " +
                    "WHERE id =?"
                    ;
            PreparedStatement stmt = this.connect.prepareStatement(sql);

            stmt.setString(1,a1.getTitre());//"titre,
            stmt.setString(2,a1.getArtiste());//"artiste,
            stmt.setString(3,a1.getLieu());//"lieu,
            stmt.setDate(4, a1.getDate());//"date,\
            stmt.setInt(5,a1.getPlacesDisponibles());//"places_disponible,
            stmt.setInt(6,a1.getIdSpectacle());//"id,


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
        String sql = "DELETE FROM SPECTACLE WHERE id = ?";
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
