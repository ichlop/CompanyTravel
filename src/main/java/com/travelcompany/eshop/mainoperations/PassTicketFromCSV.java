package com.travelcompany.eshop.mainoperations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class PassTicketFromCSV {

    private static final Logger logger = LoggerFactory.getLogger(PassCustomerFromCSV.class);

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
//
    }
}
