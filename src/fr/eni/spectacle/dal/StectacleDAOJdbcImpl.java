package fr.eni.spectacle.dal;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StectacleDAOJdbcImpl implements StectacleDAO {

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


    public void insert(Spectacle a1) throws DALException {


        try {
            String sql = "INSERT INTO ARTICLES (" +
                    "reference," +
                    "marque," +
                    "designation," +
                    "prixUnitaire," +
                    "qteStock," +
                    "type," +
                    "grammage," +
                    "couleur)" +

                    "VALUES (" +
                    "?,"+//"reference,
                    "?,"+//"marque,
                    "?,"+//"designation,
                    "?,"+//"prixUnitaire,\
                    "?,"+//"qteStock,
                    "?,"+//"type,
                    "?,"+//"grammage,
                    "?"+ //"couleur,
                    ")";
            PreparedStatement stmt = this.connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1,a1.getReference());//"reference,
            stmt.setString(2,a1.getMarque());//"marque,
            stmt.setString(3,a1.getDesignation());//"designation,
            stmt.setFloat(4,a1.getPrixUnitaire());//"prixUnitaire,\
            stmt.setInt(5,a1.getQteStock());//"qteStock,
            if ( a1 instanceof Ramette){
                stmt.setString(6,"R");//"type,
                stmt.setInt(7,((Ramette) a1).getGrammage());//"grammage,
                stmt.setNull(8,Types.VARCHAR);//"couleur,

            }
            if ( a1 instanceof Stylo){
                stmt.setString(6,"S");//"type,
                stmt.setNull(7,Types.VARCHAR);//"grammage,
                stmt.setString(8,((Stylo) a1).getCouleur());//"couleur,

            }

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

public Spectacle selectById(Integer idSpectacle) throws DALException {
    try{
        String sql = "SELECT * FROM ARTICLES WHERE idSpectacle = ?";
        PreparedStatement stmt = this.connect.prepareStatement(sql);

        stmt.setInt(1,idSpectacle);//"reference,

        ResultSet res = stmt.executeQuery();

        Spectacle data = null;

         if (res.next()){
             if (res.getString("type") == "S"){
                data = new Stylo(res.getInt("idSpectacle"),res.getString("marque"),res.getString("reference"),res.getString("designation"),res.getFloat("prixUnitaire"),res.getInt("qteStock"),res.getString("couleur"));
            }
            else {
                    data = new Ramette(res.getInt("idSpectacle"),res.getString("marque"),res.getString("reference"),res.getString("designation"),res.getFloat("prixUnitaire"),res.getInt("qteStock"),res.getInt("grammage"));
            }
        //on ferme les connections
        stmt.close();
        //connect.close();
             }

        return data;

        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
    }

    public List<Spectacle> selectAll() throws DALException {
        try{
            String sql = "SELECT * FROM ARTICLES";
            PreparedStatement stmt = this.connect.prepareStatement(sql);


            ResultSet res = stmt.executeQuery();
//on boucle sur les résultats
            List<Spectacle> data = new ArrayList<>();
            while (res.next()){
                //data.add(this.selectById(res.getInt("idSpectacle")));
                if (res.getString("type") == "S"){
                    data.add(new Stylo(res.getInt("idSpectacle"),res.getString("marque"),res.getString("reference"),res.getString("designation"),res.getFloat("prixUnitaire"),res.getInt("qteStock"),res.getString("couleur")));
                }
                else {
                    data.add(new Ramette(res.getInt("idSpectacle"),res.getString("marque"),res.getString("reference"),res.getString("designation"),res.getFloat("prixUnitaire"),res.getInt("qteStock"),res.getInt("grammage")));
                }
            }


            //on ferme les connections
            stmt.close();
            //connect.close();

            return data;

        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }

    }

    public void update(Spectacle a1) throws DALException {
        try {
            String sql = "UPDATE ARTICLES SET " +
                    "reference =?," +
                    "marque =?," +
                    "designation =?," +
                    "prixUnitaire =?," +
                    "qteStock =?," +
                    "type =?," +
                    "grammage =?," +
                    "couleur =? " +
                    "WHERE idSpectacle =?"
                    ;
            PreparedStatement stmt = this.connect.prepareStatement(sql);

            stmt.setString(1,a1.getReference());//"reference,
            stmt.setString(2,a1.getMarque());//"marque,
            stmt.setString(3,a1.getDesignation());//"designation,
            stmt.setFloat(4,a1.getPrixUnitaire());//"prixUnitaire,\
            stmt.setInt(5,a1.getQteStock());//"qteStock,
            if ( a1 instanceof Ramette){
                stmt.setString(6,"R");//"type,
                stmt.setInt(7,((Ramette) a1).getGrammage());//"grammage,
                stmt.setNull(8,Types.VARCHAR);//"couleur,

            }
            if ( a1 instanceof Stylo){
                stmt.setString(6,"S");//"type,
                stmt.setNull(7,Types.VARCHAR);//"grammage,
                stmt.setString(8,((Stylo) a1).getCouleur());//"couleur,

            }
            stmt.setInt(9,a1.getIdSpectacle());//"id,


// on update
            stmt.executeUpdate();

            //on ferme les connections
            stmt.close();
            //connect.close();



        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }



    }

    public void delete(Integer idSpectacle) throws DALException {
        try {
        String sql = "DELETE FROM ARTICLES WHERE idSpectacle = ?";
        PreparedStatement stmt = this.connect.prepareStatement(sql);

        stmt.setInt(1,idSpectacle);//"reference,

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
