package com.travelcompany.eshop.dao;

import com.travelcompany.eshop.dao.implementations.DaoRepository;
import com.travelcompany.eshop.exceptionhandle.ExceptionHandler;
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
        public int addToDb(Connection conn){

            Scanner scanner = new Scanner(System.in);
            System.out.println("Departure Airport Id: ");
            String departureAirportId=scanner.nextLine();
            System.out.println("Destination Airport Id: ");
            String destinationAirportId=scanner.nextLine();
            System.out.println("Departure Date: ");
            String departureDate=scanner.nextLine();
            System.out.println("Airlines: ");
            String airline=scanner.nextLine();
            System.out.println("Price: ");
            String price=scanner.nextLine();


            Statement statement = null;
            try {
                statement = conn.createStatement();
            } catch (SQLException ex) {
                ExceptionHandler.handleException(ex,"");

            }

            UUID id = UUID.randomUUID();

            String query = "insert into itinerary ( id int primary key auto_increment , departureAirportId , destinationAirportId , departureDate, airline, price) "
                    + " values ( " + id + departureAirportId + destinationAirportId + departureDate + airline +price ;

            try {
                statement.executeUpdate(query);
            } catch (SQLException ex) {
                ExceptionHandler.handleException(ex,"");
            }
            logger.info("Successfully added to DB");

            return id.compareTo(id);
        }

        @Override
        public void getFromDb(int id, Connection conn){

            String query = "select *  from itinerary  where id =" +id;
            Statement statement = null;
            try {
                statement = conn.createStatement();
            } catch (SQLException ex) {
                ExceptionHandler.handleException(ex,"");
            }
            ResultSet rs = null;
            try {
                rs = statement.executeQuery(query);
            } catch (SQLException ex) {
                ExceptionHandler.handleException(ex,"");
            }

            while (true) {
                try {
                    if (!rs.next()) break;
                } catch (SQLException ex) {
                    ExceptionHandler.handleException(ex,"");

                }
                try {
                    System.out.println(rs.getString("departureAirportId") + "," + rs.getString("destinationAirportId") +
                            "," + rs.getString("departureDate") + "," + rs.getString("airline") + "," + rs.getInt("price"));
                } catch (SQLException ex) {
                    ExceptionHandler.handleException(ex,"");

                }
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                ExceptionHandler.handleException(ex,"");

            }
        }

        @Override
        public void updateDb(int id, Connection conn){

            // Open a connection
            System.out.println("Print new email: ");
            Scanner departureAirportId = new Scanner(System.in);
            String query = "UPDATE itinerary SET departureAirport = '" + departureAirportId + "' WHERE id= " + id;
            PreparedStatement stmt = null;
            try {
                stmt = conn.prepareStatement(query);
            } catch (SQLException ex) {
                ExceptionHandler.handleException(ex,"");

            }
            try {
                stmt.executeUpdate(query);
            } catch (SQLException ex) {
                ExceptionHandler.handleException(ex,"");

            }
            logger.info("Successfully updated");
            try {
                conn.close();
            } catch (SQLException ex) {
                ExceptionHandler.handleException(ex,"");

            }
        }

        @Override
        public boolean deleteFromDb(int id, Connection conn){

            String query = "delete from itinerary where id = " +id;
            Statement stmt = null;
            try {
                stmt = conn.createStatement();
                stmt.execute(query);
                logger.info("Successfully deleted");
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return true;
        }

    @Override
    public List<Itinerary> getListFromDb(Connection conn, String query){
        Statement statement = null;
        try {
            statement = conn.createStatement();
        } catch (SQLException ex) {
            ExceptionHandler.handleException(ex,"");
        }
        ResultSet rs = null;
        try {
            rs = statement.executeQuery(query);
        } catch (SQLException ex) {
            ExceptionHandler.handleException(ex,"");

        }

        List<Itinerary> itineraries = new ArrayList<>();
        while (true) {
            try {
                if (!rs.next()) break;
            } catch (SQLException ex) {
                ExceptionHandler.handleException(ex,"");

            }
            //retrieve data from row
            int id = 0;
            String departureAirportId = null;
            String destinationAirportId = null;
            Date departureDate = null;
            String airline = null;
            int price = 0;

            try {
                id = rs.getInt("id");
                departureAirportId = rs.getString("departureAirportId");
                destinationAirportId = rs.getString("destinationAirportId");
                departureDate = rs.getDate("departureDate");
                airline = rs.getString("airline");
                price = rs.getInt("price");

            } catch (SQLException ex) {
                ExceptionHandler.handleException(ex,"");
            }

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
