package Main.mainoperations;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

public class PassTheDataFromCSV {

    private static final Logger logger = LoggerFactory.getLogger(PassTheDataFromCSV.class);

    private static AtomicInteger ID_GENERATOR = new AtomicInteger(1000);

    public void insertData(File filename, Connection conn) throws IOException, SQLException {

        BufferedReader br = new BufferedReader(new FileReader(filename));

        String line = null;

        while ((line = br.readLine()) != null) {
            String[] value = line.split(",");
            String query1 = "INSERT INTO Customer ( id,Name,Email,AddressCity,Nationality,Category) VALUES ('" + value[0]+ "','" + value[1]+ "','" + value[2]+ "','" + value[3]+ "','" + value[4]+ "','" + value[5]+ "')";
            PreparedStatement pst = conn.prepareStatement(query1);
            pst.executeUpdate();
        }
        br.close();
    }
}
