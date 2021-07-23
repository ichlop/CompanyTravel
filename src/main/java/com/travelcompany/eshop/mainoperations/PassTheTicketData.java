package com.travelcompany.eshop.mainoperations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class PassTheTicketData {

    private static final Logger logger = LoggerFactory.getLogger(PassTheDataFromCSV.class);

    public void insertTicketData(File filename, Connection conn) throws IOException, SQLException {

        DatabaseMetaData dbm = conn.getMetaData();
        ResultSet table3 = dbm.getTables(null, null, "orderedTicketAndPayments", null);
        if (table3.next()) {
            logger.info("Table exists");
        } else {
            BufferedReader br = new BufferedReader(new FileReader(filename));

            String line = null;

            br.readLine();//skips 1st line
            while ((line = br.readLine()) != null) {
                String[] value = line.split(",");
                String query1 = "INSERT INTO orderedTicketsAndPayments ( id,passengerId,itineraryId,paymentMethod,amountPaid) " +
                        "VALUES ('" + Integer.parseInt(value[0]) + "','" + value[1] + "','" + value[2] + "','" + value[3] + "','" + value[4] + "')";
                PreparedStatement pst = conn.prepareStatement(query1);
                pst.executeUpdate();
            }
            br.close();

            logger.info("Table created successful");
        }
//
    }
}
