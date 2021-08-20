package com.travelcompany.eshop.mainoperations.DBoperations;

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

    public void createTable(Connection connection) throws SQLException, IOException {

        InputStream inputStream = CreateTable.class.getClassLoader().getResourceAsStream("sql.properties");


        sqlCommands.load(inputStream);


        Statement statement = null;


        statement = connection.createStatement();

        int customerTable = 0;

        customerTable = statement.executeUpdate(sqlCommands.getProperty("create.customer.table"));


        logger.info("Created Customer table command was successful with result {}.", customerTable);
        int itineraryTable = 0;

        itineraryTable = statement.executeUpdate(sqlCommands.getProperty("create.itinerary.table"));

        logger.info("Created Itinerary table command was successful with result {}.", itineraryTable);
        int ticketsAndPayments = 0;

        ticketsAndPayments = statement.executeUpdate(sqlCommands.getProperty("create.orderedTicketsAndPayments.table"));

        logger.info("Created Order Tickets and Payments table command was successful with result {}.", ticketsAndPayments);

//        connection.close();

    }
}

