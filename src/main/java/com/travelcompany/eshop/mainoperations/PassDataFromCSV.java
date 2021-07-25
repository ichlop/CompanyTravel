package com.travelcompany.eshop.mainoperations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class PassDataFromCSV {

    private static final Logger logger = LoggerFactory.getLogger(PassDataFromCSV.class);

    public void insertCustomerData(File filename, Connection conn) throws IOException, SQLException {

        DatabaseMetaData dbm = conn.getMetaData();
        ResultSet customerTables = dbm.getTables(null, null, "customer", null);

        BufferedReader br = new BufferedReader(new FileReader(filename));

        String line = null;

        br.readLine();//skips 1st line
        while ((line = br.readLine()) != null) {
            String[] value = line.split(",");


            String instertCustomerQuery = "INSERT INTO customer ( id,name,email,addressCity,nationality,category) " +
                    "VALUES ('" + Integer.parseInt(value[0]) + "','" + value[1] + "','" + value[2] + "','" + value[3] + "','" + value[4] + "','" + value[5] + "')";
            PreparedStatement pst = conn.prepareStatement(instertCustomerQuery);
            pst.executeUpdate();
//                }
        }
        br.close();

        logger.info("Customer table created successful");
    }

    public void insertItineraryData(File filename, Connection conn) throws IOException, SQLException {

        DatabaseMetaData dbm = conn.getMetaData();
        ResultSet itineraryTable = dbm.getTables(null, null, "itinerary", null);

        BufferedReader br = new BufferedReader(new FileReader(filename));

        String line = null;

        br.readLine();//skips 1st line
        while ((line = br.readLine()) != null) {
            String[] value = line.split(",");
            String insertItineraryQuery = "INSERT INTO itinerary ( id,departureAirportId,destinationAirportId,departuredate,airline,price) " +
                    "VALUES ('" + Integer.parseInt(value[0]) + "','" + value[1] + "','" + value[2] + "','" + value[3] + "','" + value[4] + "','" + Integer.parseInt(value[5]) + "')";
            PreparedStatement pst = conn.prepareStatement(insertItineraryQuery);
            pst.executeUpdate();
        }
        br.close();

        logger.info("Itinerary table created successful");
    }

    //
    public void insertTicketData(File filename, Connection conn) throws IOException, SQLException {

        DatabaseMetaData dbm = conn.getMetaData();
        ResultSet ticketsTable = dbm.getTables(null, null, "orderedTicketsAndPayments", null);
//        if (ticketsTable.next()) {
//            logger.info("Tickets table exists");
//        } else {
        BufferedReader br = new BufferedReader(new FileReader(filename));

        String line = null;

        br.readLine();//skips 1st line
        while ((line = br.readLine()) != null) {
            String[] value = line.split(",");
            String insertTicketsQuery = "INSERT INTO orderedTicketsAndPayments ( id,passengerId,itineraryId,paymentMethod,amountPaid) " +
                    "VALUES ('" + Integer.parseInt(value[0]) + "','" + value[1] + "','" + value[2] + "','" + value[3] + "','" + value[4] + "')";
            PreparedStatement pst = conn.prepareStatement(insertTicketsQuery);
            pst.executeUpdate();
        }
        br.close();

        logger.info("Tickets table created successful");
//        }
    }
}
