package Main.dao;

import Main.dao.implementations.DaoRepository;
import Main.model.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class ItineraryRepository implements DaoRepository{

        private static AtomicInteger ID_GENERATOR = new AtomicInteger(1000);

        @Override
        public int addToDb(Connection conn) throws SQLException, ClassNotFoundException {

            Scanner scanner = new Scanner(System.in);
            System.out.println("Departure Airport: ");
            String departureAirport=scanner.nextLine();
            System.out.println("Destination Airport: ");
            String destinationAirport=scanner.nextLine();
            System.out.println("Departure Date: ");
            String departureDate=scanner.nextLine();
            System.out.println("Airlines: ");
            String airlines=scanner.nextLine();

            Class.forName("com.mysql.jc.jdbc.Driver");
            Statement statement = conn.createStatement();

            int id = ID_GENERATOR.getAndIncrement();

            String query = "insert into Customer ( id int primary key auto_increment , departureAirport , destinationAirport , departureDate, airlines) "
                    + " values ( " + id + departureAirport + destinationAirport + departureDate + airlines ;

            statement.executeUpdate(query);

            return id;
        }

        @Override
        public void getfromDb(int id, Connection conn) throws SQLException, ClassNotFoundException {

            String query = "select *  from Itinerary  ";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                System.out.println(rs.getString("departureAirport") + "," + rs.getString("destinationAirport") +
                        "," + rs.getString("departureDate") + "," + rs.getString("airlines"));
            }
        }

        @Override
        public void updateDb(int id, String newEmail, Connection conn) throws SQLException {

            Statement stmt = conn.createStatement();
            Scanner departureDate = new Scanner(System.in);
            String sql = "UPDATE Itinerary SET  = " + departureDate.nextLine() + " WHERE id in (100, 101)";
            stmt.executeUpdate(sql);
            ResultSet rs = stmt.executeQuery(sql);
            rs.close();
        }

        @Override
        public boolean deleteFromDb(int id, Connection conn) throws SQLException {

            String query = "delete from Itinerary where id = ?";
            Statement stmt = conn.createStatement();

            stmt.execute(query);
            conn.close();
            return true;
        }

    @Override
    public List getListfromDb(Connection conn, String query) throws SQLException {
        return null;
    }


}
