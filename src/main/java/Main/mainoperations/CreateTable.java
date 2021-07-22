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

    InputStream inputStream = CreateTable.class.getClassLoader().getResourceAsStream("sql.properties");
        if (inputStream == null) {
        logger.error("Sorry, unable to find sql.properties, exiting application.");
        exit(-1);
        }

        sqlCommands.load(inputStream);
        Statement statement = connection.createStatement();
        int customerTable = statement.executeUpdate(sqlCommands.getProperty("create.customer.table"));
        logger.info("Created Customer table command was successful with result {}.", customerTable);
        int itineraryTable = statement.executeUpdate(sqlCommands.getProperty("create.itinerary.table"));
        logger.info("Created Itinerary table command was successful with result {}.", itineraryTable);
        int ticketsAndPayments = statement.executeUpdate(sqlCommands.getProperty("create.orderedTicketsAndPayments.table"));
        logger.info("Created Order Tickets and Payments table command was successful with result {}.", ticketsAndPayments);

//        connection.close();

    }
}

