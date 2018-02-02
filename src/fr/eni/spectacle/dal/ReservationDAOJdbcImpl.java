package fr.eni.spectacle.dal;



import fr.eni.spectacle.bo.Reservation;
import fr.eni.spectacle.bo.Spectacle;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Random;

public class ReservationDAOJdbcImpl implements Dao{

    private  Connection connect;

    public ReservationDAOJdbcImpl() throws DALException {
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

    public int nombrePlaceReserves(Spectacle spec) throws DALException {
        try{
            int idSpect = spec.getIdSpectacle();

            String sql = "SELECT SUM(nombre_places) as total * FROM RESERVATION WHERE spectacle_id = ?";
            PreparedStatement stmt = this.connect.prepareStatement(sql);

            stmt.setInt(1,idSpect);//"reference,

            ResultSet res = stmt.executeQuery();

            int data;

            if (res.next()){
                data = res.getInt("total");

                //on ferme les connections
                stmt.close();
                //connect.close();
            }

            return 0;

        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }

    }


    public void insert(Object r2) throws DALException {
        Reservation r1 = (Reservation) r2;

        try {
            String sql = "INSERT INTO RESERVATION (" +
                    "code_reservation," +                    //code_reservation
                    "spectacle_id," +       // spectacle_id
                    "client_id," +                     //client_id INT
                    "nombre_places," +                    //nombre_places
                    "date_reservation" +         //date_reservation DATETIME


                    ")" +

                    "VALUES (" +
                    "?,"+//"code_reservation,
                    "?,"+//"spectacle_id,
                    "?,"+//"client_id,
                    "?,"+//"nombre_places,\
                    "?"+//"date_reservation,
                    ")";
            PreparedStatement stmt = this.connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //génération du code
            Random rnd = new Random();
            int n = 1000000000 + rnd.nextInt(90000000);
            String fin = String.valueOf(n);
            Date actuelle = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMddss");
            String dat = dateFormat.format(actuelle);
            String temp = String.valueOf(System.nanoTime());
            String code = SHA.sha256(temp);

            r1.setCodeReservation(code);

            //date du jour
            java.sql.Date sqlDate = new java.sql.Date(actuelle.getTime());


            Date now = new Date(6510,11,01);
            stmt.setString(1,code);//"code_reservation,
            stmt.setInt(2,r1.getIdSpectacle());//"spectacle_id,
            stmt.setInt(3,r1.getClientId());//"client_id,
            stmt.setInt(4, r1.getNombrePlaces());//"nombre_places,\
            stmt.setDate(5,sqlDate);//"date_reservation,

// on indique le numero d'id auto genéré dans l'objet article
            int nbRows = stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
    }

public Reservation selectById(int id) throws DALException {
    try{
        String sql = "SELECT * FROM RESERVATION WHERE code_reservation = ?";
        PreparedStatement stmt = this.connect.prepareStatement(sql);

        stmt.setInt(1,id);//"reference,

        ResultSet res = stmt.executeQuery();

        Reservation data = null;

         if (res.next()){
                data = new Reservation(res.getString("code_reservation"),res.getInt("spectacle_id"),res.getInt("client_id"),res.getInt("nombre_places"),res.getDate("date_reservation"));
        //on ferme les connections
        stmt.close();
        //connect.close();
             }

        return data;

        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
    }

    public List<Reservation> selectAll() throws DALException {
        try{
            String sql = "SELECT * FROM RESERVATION";
            PreparedStatement stmt = this.connect.prepareStatement(sql);


            ResultSet res = stmt.executeQuery();
//on boucle sur les résultats
            List<Reservation> data = new ArrayList<>();
            while (res.next()){
                //data.add(this.selectById(res.getInt("id")));
                    data.add(new Reservation(res.getString("code_reservation"),res.getInt("spectacle_id"),res.getInt("client_id"),res.getInt("nombre_places"),res.getDate("date_reservation")));

            }


            //on ferme les connections
            stmt.close();
            //connect.close();

            return data;

        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }

    }

    public void update(Object r2) throws DALException {
        Reservation r1 = (Reservation) r2;
        try {
            String sql = "UPDATE RESERVATION  SET " +
                    "spectacle_id =?," +
                    "client_id =?," +
                    "nombre_places =?," +
                    "date_reservation =? " +
                    "WHERE code_reservation =?"
                    ;
            PreparedStatement stmt = this.connect.prepareStatement(sql);

            stmt.setInt(1,r1.getIdSpectacle());//"spectacle_id,
            stmt.setInt(2,r1.getClientId());//"client_id,
            stmt.setInt(3,r1.getNombrePlaces());//"nombre_places,
            stmt.setDate(4, r1.getDateReservation());//"date_reservation,\
            stmt.setString(5,r1.getCodeReservation());//"code_reservation,


// on update
            stmt.executeUpdate();

            //on ferme les connections
            stmt.close();
            //connect.close();



        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }



    }

    public void delete(String id) throws DALException {
        try {
        String sql = "DELETE FROM RESERVATION WHERE code_reservation = ?";
        PreparedStatement stmt = this.connect.prepareStatement(sql);

        stmt.setString(1,id);//"reference,

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
