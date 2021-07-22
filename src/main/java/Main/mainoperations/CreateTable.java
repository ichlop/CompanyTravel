package Main.mainoperations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import static java.lang.System.exit;

public class CreateTable {

    private static final Logger logger = LoggerFactory.getLogger(CreateTable.class);

    private final Properties sqlCommands = new Properties();

    public void createTable(Connection connection) throws SQLException, IOException {


//        Statement statement = connection.createStatement();
//        String sqlCustomer = "CREATE TABLE IF NOT EXISTS Customer (" +
//                " id INTEGER not NULL PRIMARY KEY, " +
//                "name VARCHAR(30), " +
//                "email VARCHAR(20), " +
//                "AddressCity VARCHAR(20), " +
//                "Nationality VARCHAR(10), " +
//                "Category VARCHAR(10) ) ";
//
//        String sqlItinerary = "CREATE TABLE IF NOT EXISTS Customer (" +
//                " id INTEGER not NULL PRIMARY KEY, " +
//                "name VARCHAR(30), " +
//                "email VARCHAR(20), " +
//                "AddressCity VARCHAR(20), " +
//                "Nationality VARCHAR(10), " +
//                "Category VARCHAR(10) ) ";
//
//        String sqlOrderPayment = "CREATE TABLE IF NOT EXISTS Customer (" +
//                " id INTEGER not NULL PRIMARY KEY, " +
//                "name VARCHAR(30), " +
//                "email VARCHAR(20), " +
//                "AddressCity VARCHAR(20), " +
//                "Nationality VARCHAR(10), " +
//                "Category VARCHAR(10) ) ";
//
//        statement.executeUpdate(sqlCustomer);
//        statement.executeUpdate(sqlItinerary);
//        statement.executeUpdate(sqlOrderPayment);
//        logger.info("Table successfully created");

    InputStream inputStream = CreateTable.class.getClassLoader().getResourceAsStream("sql.properties");
        if (inputStream == null) {
        logger.error("Sorry, unable to find sql.properties, exiting application.");
        // Abnormal exit
        exit(-1);
        }

    //load a properties file from class path, inside static method
        sqlCommands.load(inputStream);
        Statement statement = connection.createStatement();
        int customerTable = statement.executeUpdate(sqlCommands.getProperty("create.customer.table"));
        logger.info("Created Customer table command was successful with result {}.", customerTable);
        int itineraryTable = statement.executeUpdate(sqlCommands.getProperty("create.itinerary.table"));
        logger.info("Created Customer table command was successful with result {}.", itineraryTable);
        int ticketsAndPayments = statement.executeUpdate(sqlCommands.getProperty("create.orderedTicketsAndPayments.table"));
        logger.info("Created Customer table command was successful with result {}.", ticketsAndPayments);

        connection.close();

    }
}

