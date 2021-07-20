package Main.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectToDB {

    private static final Logger logger = LoggerFactory.getLogger(ConnectToDB.class);

    public Connection setUpConnectionWithDB() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelcompany", "root", "root");
        logger.info("The connection was successful");

        return connection;
    }
}