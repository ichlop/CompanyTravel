package Main.mainoperations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static final Logger logger = LoggerFactory.getLogger(DBConnector.class);

    public Connection setUpConnectionWithDB() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelcompany", "root", "root");
        logger.info("The connection was successful");

        return connection;
    }
}