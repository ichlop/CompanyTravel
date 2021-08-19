package com.travelcompany.eshop.mainoperations.implementations;

import com.travelcompany.eshop.exceptionhandle.ExceptionHandler;
import com.travelcompany.eshop.mainoperations.DataInsert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.sql.*;

public class PassCustomerCSV implements DataInsert {

    private static final Logger logger = LoggerFactory.getLogger(PassCustomerCSV.class);

    @Override
    public void insertData(File filename, Connection conn){

        DatabaseMetaData dbm = null;
        try {
            dbm = conn.getMetaData();
            ResultSet customerTables = dbm.getTables(null, null, "customer", null); //todo: check type to see if exists

        } catch (SQLException ex) {
            ExceptionHandler.handleException(ex,"");
        }

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            ExceptionHandler.handleException(e,"There is not such a file");

        }

        String line = null;

        try {
            br.readLine();//skips 1st line
        } catch (IOException e) {
            ExceptionHandler.handleException(e,"");
        }
        while (true) {
            try {
                if (!((line = br.readLine()) != null)) break;
            } catch (IOException e) {
                ExceptionHandler.handleException(e,"");
            }
            String[] value = line.split(",");


            String instertCustomerQuery = "INSERT INTO customer ( id,name,email,addressCity,nationality,category) " +
                    "VALUES ('" + Integer.parseInt(value[0]) + "','" + value[1] + "','" + value[2] + "','" + value[3] + "','" + value[4] + "','" + value[5] + "')";
            PreparedStatement pst = null;
            try {
                pst = conn.prepareStatement(instertCustomerQuery);
            } catch (SQLException ex) {
                ExceptionHandler.handleException(ex,"");
            }
            try {
                pst.executeUpdate();
            } catch (SQLException ex) {
                ExceptionHandler.handleException(ex,"");
            }
//                }
        }
        try {
            br.close();
        } catch (IOException e) {
            ExceptionHandler.handleException(e,"");
        }
        logger.info("Customer table created successful");
    }

}
