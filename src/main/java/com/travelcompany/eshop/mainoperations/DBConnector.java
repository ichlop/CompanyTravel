package com.travelcompany.eshop.mainoperations;

import com.travelcompany.eshop.exceptionhandle.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static final Logger logger = LoggerFactory.getLogger(DBConnector.class);
    private static final String DB_CONNECTION_URL_MEMORY_MODE = "jdbc:mysql://localhost:3306/travelcompany";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    public Connection setUpConnectionWithDB(){

        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            ExceptionHandler.handleException(e,"'Class not found'");
        }
        try {
            connection = DriverManager.getConnection(DB_CONNECTION_URL_MEMORY_MODE, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException ex) {
            ExceptionHandler.handleException(ex,"'There is something wrong with DB Connection'");
        }
        logger.info("The connection was successful");
        System.out.println("True");

        return connection;
    }
}