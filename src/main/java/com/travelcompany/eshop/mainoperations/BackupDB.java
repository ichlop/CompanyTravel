package com.travelcompany.eshop.mainoperations;

import com.travelcompany.eshop.exceptionhandle.ExceptionHandler;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class BackupDB {

    public static void doTheBackup(Connection conn){

        PrintWriter pw = null;
        try {
            pw = new PrintWriter("c:\\Users\\chloptsi\\IdeaProjects\\backup\\file1.csv");
        } catch (FileNotFoundException e) {
            ExceptionHandler.handleException(e,"");
        }
        StringBuilder sb = new StringBuilder();

        ResultSet rs = null;

        String query = "select * from Customer";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            ExceptionHandler.handleException(ex,"");
        }


        while (true) {
            try {
                if (!rs.next()) break;
            } catch (SQLException ex) {
                ExceptionHandler.handleException(ex,"");
            }
            try {
                sb.append(rs.getString("id"));
                sb.append(",");
                sb.append(rs.getString("Name"));
                sb.append(",");
                sb.append(rs.getString("Email"));
                sb.append(",");
                sb.append(rs.getString("AddressCity"));
                sb.append(",");
                sb.append(rs.getString("Nationality"));
                sb.append(",");
                sb.append(rs.getString("Category"));

            } catch (SQLException e) {
                ExceptionHandler.handleException(e,"");
            }
            sb.append("\r\n");
        }

        pw.write(sb.toString());
        pw.close();
        System.out.println("finished");
    }


    public static void streamBackup (Map<Object,Long> ldapContent){
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("streamData.csv"));
            out.writeObject(ldapContent);
            out.close();
        } catch (IOException e) {
            ExceptionHandler.handleException(e,"");
        }
    }
}
