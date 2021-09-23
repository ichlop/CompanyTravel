package com.travelcompany.eshop.mainoperations.datapass;

import com.travelcompany.eshop.mainoperations.datapass.implementation.DataInsert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class PassItineraryCsv implements DataInsert {

    private static final Logger logger = LoggerFactory.getLogger(PassItineraryCsv.class);

    @Override
    public void insertData(File filename, Connection conn) throws IOException, SQLException {
        DatabaseMetaData dbm = null;

        dbm = conn.getMetaData();

        ResultSet itineraryTable = dbm.getTables(null, null, "itinerary", null);//todo: check type to see if exists


        BufferedReader br = null;

        br = new BufferedReader(new FileReader(filename));


        String line = null;


        br.readLine();//skips 1st line

        while (true) {

            if (!((line = br.readLine()) != null)) break;

            String[] value = line.split(",");
            String insertItineraryQuery = "INSERT INTO itinerary ( id,departureAirportId,destinationAirportId,departuredate,airline,price) " +
                    "VALUES ('" + Integer.parseInt(value[0]) + "','" + value[1] + "','" + value[2] + "','" + value[3] + "','" + value[4] + "','" + Integer.parseInt(value[5]) + "')";
            PreparedStatement pst = null;

            pst = conn.prepareStatement(insertItineraryQuery);

            pst.executeUpdate();

        }

        br.close();

        logger.info("Itinerary table created successful");
    }
}
