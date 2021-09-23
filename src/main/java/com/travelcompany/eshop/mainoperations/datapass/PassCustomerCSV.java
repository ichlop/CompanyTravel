package com.travelcompany.eshop.mainoperations.datapass;

import com.travelcompany.eshop.mainoperations.datapass.implementation.DataInsert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class PassCustomerCSV implements DataInsert {

    private static final Logger logger = LoggerFactory.getLogger(PassCustomerCSV.class);

    @Override
    public void insertData(File filename, Connection conn) throws IOException, SQLException {

        DatabaseMetaData dbm = null;

        dbm = conn.getMetaData();
        ResultSet customerTables = dbm.getTables(null, null, "customer", null); //todo: check type to see if exists


        BufferedReader br = null;

        br = new BufferedReader(new FileReader(filename));


        String line = null;


        br.readLine();//skips 1st line

        while (true) {

            if (!((line = br.readLine()) != null)) break;

            String[] value = line.split(",");


            String instertCustomerQuery = "INSERT INTO customer ( id,name,email,addressCity,nationality,category) " +
                    "VALUES ('" + Integer.parseInt(value[0]) + "','" + value[1] + "','" + value[2] + "','" + value[3] + "','" + value[4] + "','" + value[5] + "')";
            PreparedStatement pst = null;

            pst = conn.prepareStatement(instertCustomerQuery);


            pst.executeUpdate();

        }

        br.close();

        logger.info("Customer table created successful");
    }

}
