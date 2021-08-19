package com.travelcompany.eshop.mainoperations.DBoperations;

import com.travelcompany.eshop.exceptionhandle.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class CreateTable {

    private static final Logger logger = LoggerFactory.getLogger(CreateTable.class);

    private final Properties sqlCommands = new Properties();

    public void createTable(Connection connection){

    InputStream inputStream = CreateTable.class.getClassLoader().getResourceAsStream("sql.properties");

        try {
            sqlCommands.load(inputStream);
        } catch (IOException e) {
            ExceptionHandler.handleException(e,"unable to find sql.properties");
        }

        Statement statement = null;

        try {
            statement = connection.createStatement();
        } catch (SQLException ex) {
            ExceptionHandler.handleException(ex,"");
        }
        int customerTable = 0;
        try {
            customerTable = statement.executeUpdate(sqlCommands.getProperty("create.customer.table"));
        } catch (SQLException ex) {
            ExceptionHandler.handleException(ex,"There is not such a table");
        }

        logger.info("Created Customer table command was successful with result {}.", customerTable);
        int itineraryTable = 0;
        try {
            itineraryTable = statement.executeUpdate(sqlCommands.getProperty("create.itinerary.table"));
        } catch (SQLException ex) {
            ExceptionHandler.handleException(ex,"There is not such a table");
        }
        logger.info("Created Itinerary table command was successful with result {}.", itineraryTable);
        int ticketsAndPayments = 0;
        try {
            ticketsAndPayments = statement.executeUpdate(sqlCommands.getProperty("create.orderedTicketsAndPayments.table"));
        } catch (SQLException ex) {
            ExceptionHandler.handleException(ex,"There is not such a table");
        }
        logger.info("Created Order Tickets and Payments table command was successful with result {}.", ticketsAndPayments);

//        connection.close();

    }
}

