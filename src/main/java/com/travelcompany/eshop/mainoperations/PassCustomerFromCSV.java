package com.travelcompany.eshop.mainoperations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class PassCustomerFromCSV {

    private static final Logger logger = LoggerFactory.getLogger(PassCustomerFromCSV.class);

    public void insertCustomerData(File filename, Connection conn) throws IOException, SQLException {

        DatabaseMetaData dbm = conn.getMetaData();
        ResultSet customerTables = dbm.getTables(null, null, "customer", null);
//        if (customerTables.next()) {
//            logger.info("Customer table exists");
//        } else {
            BufferedReader br = new BufferedReader(new FileReader(filename));

            String line = null;

            br.readLine();//skips 1st line
            while ((line = br.readLine()) != null) {
                String[] value = line.split(",");
//                if (!(Integer.parseInt(value[0]) instanceof Customer)) {
//                    System.out.println("Customer already exists");
//                } else {

                    String instertCustomerQuery = "INSERT INTO customer ( id,name,email,addressCity,nationality,category) " +
                            "VALUES ('" + Integer.parseInt(value[0]) + "','" + value[1] + "','" + value[2] + "','" + value[3] + "','" + value[4] + "','" + value[5] + "')";
                    PreparedStatement pst = conn.prepareStatement(instertCustomerQuery);
                    pst.executeUpdate();
//                }
            }
            br.close();

            logger.info("Customer table created successful");
//        }
//
    }
}
