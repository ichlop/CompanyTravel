package com.travelcompany.eshop.dao.implementations;

import com.travelcompany.eshop.dao.DaoRepository;
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
    public int addToDb(Connection conn) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Departure Airport Id: ");
        String departureAirportId = scanner.nextLine();
        System.out.println("Destination Airport Id: ");
        String destinationAirportId = scanner.nextLine();
        System.out.println("Departure Date: ");
        String departureDate = scanner.nextLine();
        System.out.println("Airlines: ");
        String airline = scanner.nextLine();
        System.out.println("Price: ");
        String price = scanner.nextLine();


        Statement statement = null;

        statement = conn.createStatement();


        UUID id = UUID.randomUUID();

        String query = "insert into itinerary (  departureAirportId , destinationAirportId , departureDate, airline, price) "
                + " values ( " + departureAirportId + "','" + destinationAirportId + "','" + departureDate + "','" + airline + "','" + price;


        statement.executeUpdate(query);

        logger.info("Successfully added to DB");

        return id.compareTo(id);
    }

    @Override
    public void getFromDb(int id, Connection conn) throws SQLException {

        String query = "select *  from itinerary  where id =" + id;
        Statement statement = null;

        statement = conn.createStatement();

        ResultSet rs = null;

        rs = statement.executeQuery(query);


        while (true) {

            if (!rs.next()) break;

            System.out.println(rs.getString("departureAirportId") + "," + rs.getString("destinationAirportId") +
                    "," + rs.getString("departureDate") + "," + rs.getString("airline") + "," + rs.getInt("price"));

        }
        conn.close();

    }

    @Override
    public void updateDb(int id, Connection conn) throws SQLException {

        // Open a connection
        System.out.println("Print new email: ");
        Scanner departureAirportId = new Scanner(System.in);
        String query = "UPDATE itinerary SET departureAirport = '" + departureAirportId + "' WHERE id= " + id;
        PreparedStatement stmt = null;

        stmt = conn.prepareStatement(query);

        stmt.executeUpdate(query);

        logger.info("Successfully updated");

        conn.close();

    }

    @Override
    public boolean deleteFromDb(int id, Connection conn) throws SQLException {

        String query = "delete from itinerary where id = " + id;
        Statement stmt = null;

        stmt = conn.createStatement();
        stmt.execute(query);
        logger.info("Successfully deleted");
        conn.close();

        return true;
    }

    @Override
    public List<Itinerary> getListFromDb(Connection conn, String query) throws SQLException {
        Statement statement = null;

        statement = conn.createStatement();

        ResultSet rs = null;

        rs = statement.executeQuery(query);


        List<Itinerary> itineraries = new ArrayList<>();
        while (true) {

            if (!rs.next()) break;

            //retrieve data from row
            int id = 0;
            String departureAirportId = null;
            String destinationAirportId = null;
            Date departureDate = null;
            String airline = null;
            int price = 0;


            id = rs.getInt("id");
            departureAirportId = rs.getString("departureAirportId");
            destinationAirportId = rs.getString("destinationAirportId");
            departureDate = rs.getDate("departureDate");
            airline = rs.getString("airline");
            price = rs.getInt("price");


            //create itinerary
            Itinerary itinerary = new Itinerary();
            itinerary.setId(id);
            itinerary.setDepartureAirportId(departureAirportId);
            itinerary.setDestinationAirportId(destinationAirportId);
            itinerary.setDepartureDate(departureDate);
            itinerary.setAirline(airline);
            itinerary.setPrice(price);

            //add to list
            itineraries.add(itinerary);
        }

        return itineraries;
    }


}
