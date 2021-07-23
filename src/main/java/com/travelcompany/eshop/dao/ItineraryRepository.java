package com.travelcompany.eshop.dao;

import com.travelcompany.eshop.dao.implementations.DaoRepository;
import com.travelcompany.eshop.model.Itinerary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ItineraryRepository implements DaoRepository<Itinerary> {

    private static final Logger logger = LoggerFactory.getLogger(CustomerRepository.class);

        @Override
        public int addToDb(Connection conn) throws SQLException{

            Scanner scanner = new Scanner(System.in);
            System.out.println("Departure Airport: ");
            String departureAirport=scanner.nextLine();
            System.out.println("Destination Airport: ");
            String destinationAirport=scanner.nextLine();
            System.out.println("Departure Date: ");
            String departureDate=scanner.nextLine();
            System.out.println("Airlines: ");
            String airlines=scanner.nextLine();
            System.out.println("Price: ");
            String price=scanner.nextLine();


            Statement statement = conn.createStatement();

            UUID id = UUID.randomUUID();

            String query = "insert into itinerary ( id int primary key auto_increment , departureAirport , destinationAirport , departureDate, airlines, price) "
                    + " values ( " + id + departureAirport + destinationAirport + departureDate + airlines +price ;

            statement.executeUpdate(query);
            logger.info("Successfully added to DB");

            return id.compareTo(id);
        }

        @Override
        public void getFromDb(int id, Connection conn) throws SQLException {

            String query = "select *  from Itinerary  where id =" +id;
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                System.out.println(rs.getString("departureAirport") + "," + rs.getString("destinationAirport") +
                        "," + rs.getString("departureDate") + "," + rs.getString("airlines") + "," + rs.getInt("price"));
            }
            conn.close();
        }

        @Override
        public void updateDb(int id, Connection conn) throws SQLException {

            // Open a connection
            System.out.println("Print new email: ");
            Scanner departureAirport = new Scanner(System.in);
            String query = "UPDATE Itinerary SET departureAirport = '" + departureAirport + "' WHERE id= " + id;
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.executeUpdate(query);
            logger.info("Successfully updated");
            conn.close();
        }

        @Override
        public boolean deleteFromDb(int id, Connection conn) throws SQLException {

            String query = "delete from Itinerary where id = " +id;
            Statement stmt = conn.createStatement();
            stmt.execute(query);
            logger.info("Successfully deleted");
            conn.close();
            return true;
        }

    @Override
    public List<Itinerary> getListFromDb(Connection conn, String query) throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(query);

        List<Itinerary> itineraries = new ArrayList<>();
        while (rs.next()) {
            //retrieve data from row
            int id = rs.getInt("id");
            String departureAirport = rs.getString("departureAirport");
            String destinationAirport = rs.getString("destinationAirport");
            Date departureDate = rs.getDate("departureDate");
            String airline = rs.getString("airline");
            int price = rs.getInt("price");

            //create itinerary
            Itinerary itinerary = new Itinerary();
            itinerary.setId(id);
            itinerary.setDepartureAirportId(departureAirport);
            itinerary.setDestinationAirportId(destinationAirport);
            itinerary.setDepartureDate(departureDate);
            itinerary.setAirlines(airline);
            itinerary.setPrice(price);

            //add to list
            itineraries.add(itinerary);
        }

        return itineraries;
    }


}
