package com.travelcompany.eshop.mainoperations.implementations;

import com.travelcompany.eshop.exceptionhandle.ExceptionHandler;
import com.travelcompany.eshop.mainoperations.DataInsert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.sql.*;

public class PassTicketsAndPaymentsCSV implements DataInsert {

    private static final Logger logger = LoggerFactory.getLogger(PassTicketsAndPaymentsCSV.class);

    @Override
    public void insertData(File filename, Connection conn) {

        DatabaseMetaData dbm = null;
        try {
            dbm = conn.getMetaData();
        } catch (SQLException ex) {
            ExceptionHandler.handleException(ex,"");

        }
        try {
            ResultSet ticketsTable = dbm.getTables(null, null, "orderedTicketsAndPayments", null);//todo: check type to see if exists
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
            String insertTicketsQuery = "INSERT INTO orderedTicketsAndPayments ( id,passengerId,itineraryId,paymentMethod,amountPaid) " +
                    "VALUES ('" + Integer.parseInt(value[0]) + "','" + value[1] + "','" + value[2] + "','" + value[3] + "','" + value[4] + "')";
            PreparedStatement pst = null;
            try {
                pst = conn.prepareStatement(insertTicketsQuery);
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
        logger.info("Tickets table created successful");

    }
}