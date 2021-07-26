package com.travelcompany.eshop.mainoperations;

import com.travelcompany.eshop.exceptionhandle.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.sql.*;

public class PassDataFromCSV {

    private static final Logger logger = LoggerFactory.getLogger(PassDataFromCSV.class);

    public void insertCustomerData(File filename, Connection conn){

        DatabaseMetaData dbm = null;
        try {
            dbm = conn.getMetaData();
            ResultSet customerTables = dbm.getTables(null, null, "customer", null);

        } catch (SQLException ex) {
            ExceptionHandler.handleException(ex,"");
        }

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            ExceptionHandler.handleException(e,"There is not such a file");

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


            String instertCustomerQuery = "INSERT INTO customer ( id,name,email,addressCity,nationality,category) " +
                    "VALUES ('" + Integer.parseInt(value[0]) + "','" + value[1] + "','" + value[2] + "','" + value[3] + "','" + value[4] + "','" + value[5] + "')";
            PreparedStatement pst = null;
            try {
                pst = conn.prepareStatement(instertCustomerQuery);
            } catch (SQLException ex) {
                ExceptionHandler.handleException(ex,"");
            }
            try {
                pst.executeUpdate();
            } catch (SQLException ex) {
                ExceptionHandler.handleException(ex,"");
            }
//                }
        }
        try {
            br.close();
        } catch (IOException e) {
            ExceptionHandler.handleException(e,"");
        }
        logger.info("Customer table created successful");
    }

    public void insertItineraryData(File filename, Connection conn){

        DatabaseMetaData dbm = null;
        try {
            dbm = conn.getMetaData();
        } catch (SQLException ex) {
            ExceptionHandler.handleException(ex,"");
        }
        try {
            ResultSet itineraryTable = dbm.getTables(null, null, "itinerary", null);
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

    //
    public void insertTicketData(File filename, Connection conn){

        DatabaseMetaData dbm = null;
        try {
            dbm = conn.getMetaData();
        } catch (SQLException ex) {
            ExceptionHandler.handleException(ex,"");

        }
        try {
            ResultSet ticketsTable = dbm.getTables(null, null, "orderedTicketsAndPayments", null);
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
//        }
    }
}
