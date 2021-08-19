package com.travelcompany.eshop.mainoperations.implementations;

import com.travelcompany.eshop.exceptionhandle.ExceptionHandler;
import com.travelcompany.eshop.mainoperations.DataInsert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.sql.*;

public class PassItineraryCsv implements DataInsert {

    private static final Logger logger = LoggerFactory.getLogger(PassItineraryCsv.class);

    @Override
    public void insertData(File filename, Connection conn) {
        DatabaseMetaData dbm = null;
        try {
            dbm = conn.getMetaData();
        } catch (SQLException ex) {
            ExceptionHandler.handleException(ex,"");
        }
        try {
            ResultSet itineraryTable = dbm.getTables(null, null, "itinerary", null);//todo: check type to see if exists
        } catch (SQLException ex) {
            ExceptionHandler.handleException(ex,"");
        }

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            ExceptionHandler.handleException(e,"");

        }

        String line = null;

        try {
            br.readLine();//skips 1st line
        } catch (IOException e) {
            ExceptionHandler.handleException(e,"");
        }
        while (true) {
            try {
                if (!((line = br.readLine()) != null)) break;
            } catch (IOException e) {
                ExceptionHandler.handleException(e,"");
            }
            String[] value = line.split(",");
            String insertItineraryQuery = "INSERT INTO itinerary ( id,departureAirportId,destinationAirportId,departuredate,airline,price) " +
                    "VALUES ('" + Integer.parseInt(value[0]) + "','" + value[1] + "','" + value[2] + "','" + value[3] + "','" + value[4] + "','" + Integer.parseInt(value[5]) + "')";
            PreparedStatement pst = null;
            try {
                pst = conn.prepareStatement(insertItineraryQuery);
            } catch (SQLException ex) {
                ExceptionHandler.handleException(ex,"");
            }
            try {
                pst.executeUpdate();
            } catch (SQLException ex) {
                ExceptionHandler.handleException(ex,"");
            }
        }
        try {
            br.close();
        } catch (IOException e) {
            ExceptionHandler.handleException(e,"");

        }

        logger.info("Itinerary table created successful");
    }
}
