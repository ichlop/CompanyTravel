package com.travelcompany.eshop.mainoperations.DBoperations;

import com.travelcompany.eshop.exceptionhandle.ExceptionHandler;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class BackupDB {

    public static void doTheBackup(Connection conn) throws SQLException, FileNotFoundException {

        PrintWriter pw = null;

        pw = new PrintWriter("c:\\Users\\chloptsi\\IdeaProjects\\backup\\file1.csv");

        StringBuilder sb = new StringBuilder();

        ResultSet rs = null;

        String query = "select * from Customer";
        PreparedStatement ps = null;

            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();



        while (true) {

                if (!rs.next()) break;

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


            sb.append("\r\n");
        }

        pw.write(sb.toString());
        pw.close();
        System.out.println("finished");
    }


    public static void streamBackup (Map<Object,Long> ldapContent) throws IOException {
        ObjectOutputStream out = null;

            out = new ObjectOutputStream(new FileOutputStream("streamData.csv"));
            out.writeObject(ldapContent);
            out.close();

    }
}
