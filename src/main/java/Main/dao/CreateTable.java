package Main.dao;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

public class CreateTable {

    private static final Logger logger = LoggerFactory.getLogger(CreateTable.class);
//    private final Properties sqlCommands = new Properties();

//    public void loadSqlCommands() {
//        try (InputStream inputStream = MainTravelCompany.class.getClassLoader()
//                .getResourceAsStream("sql.properties")) {
//            if (inputStream == null) {
//                logger.error("Sorry, unable to find sql.properties, exiting application.");
//                // Abnormal exit
//                exit(-1);
//            }
//
//            //load a properties file from class path, inside static method
//            sqlCommands.load(inputStream);
//        } catch (IOException ex) {
//            logger.error("Sorry, unable to parse sql.properties, exiting application.", ex);
//            // Abnormal exit
//            exit(-1);
//        }
//    }

    public void createTable(Connection connection) throws SQLException, IOException {

        Statement statement = connection.createStatement();
        String sqlCustomer = "CREATE TABLE IF NOT EXISTS Customer (" +
                " id INTEGER not NULL PRIMARY KEY, " +
                "name VARCHAR(30), " +
                "email VARCHAR(20), " +
                "AddressCity VARCHAR(20), " +
                "Nationality VARCHAR(10), " +
                "Category VARCHAR(10) ) ";

        String sqlItinerary = "CREATE TABLE IF NOT EXISTS Customer (" +
                " id INTEGER not NULL PRIMARY KEY, " +
                "name VARCHAR(30), " +
                "email VARCHAR(20), " +
                "AddressCity VARCHAR(20), " +
                "Nationality VARCHAR(10), " +
                "Category VARCHAR(10) ) ";

        String sqlOrderPayment = "CREATE TABLE IF NOT EXISTS Customer (" +
                " id INTEGER not NULL PRIMARY KEY, " +
                "name VARCHAR(30), " +
                "email VARCHAR(20), " +
                "AddressCity VARCHAR(20), " +
                "Nationality VARCHAR(10), " +
                "Category VARCHAR(10) ) ";

        statement.executeUpdate(sqlCustomer);
        statement.executeUpdate(sqlItinerary);
        statement.executeUpdate(sqlOrderPayment);
        logger.info("Table successfully created");
        //        int customerT = statement.executeUpdate(sqlCommands.getProperty("create.customer.table"));
//        logger.info("Created Customer table command was successful with result {}.", customerT);
//        int itineraryT = statement.executeUpdate(sqlCommands.getProperty("create.itinerary.table"));
//        logger.info("Created Customer table command was successful with result {}.", itineraryT);
//        int otapT = statement.executeUpdate(sqlCommands.getProperty("create.orderedTicketsAndPayments.table"));
//        logger.info("Created Customer table command was successful with result {}.", otapT);
    }
}
